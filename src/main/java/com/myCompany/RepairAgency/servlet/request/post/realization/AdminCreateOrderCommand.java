package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.entity.RepairOrder;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.util.EmailSender;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdminCreateOrderCommand implements IActionCommand, IHasRoleRequirement {

    private static final Logger logger = LogManager.getLogger(AdminCreateOrderCommand.class);

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        Path page;

        if(!(boolean)request.getSession().getAttribute("isUserConfirmed")) {
            request.getSession().setAttribute("errorOrderTextMessage", "text.you_not_confirmed_your_email");
            return PathFactory.getPath("path.page.redirect.orders");
        }

        long userId = Long.parseLong(request.getParameter("clientId"));
        String text = request.getParameter("orderText");
        if (text == null || text.isBlank()) {
            request.getSession().setAttribute("errorOrderTextMessage", "text.order_empty");
            page = PathFactory.getPath("path.page.redirect.orders");
            return page;
        }

        try {
            User user = ModelManager.getInstance().getUserRepository().getById(userId);
            if (user.getRole_id() == Constants.ROLE.Client.ordinal()) {
                RepairOrder order = new RepairOrder.RepairOrderBuilder()
                        .setUser_id(userId)
                        .setCreation_date(LocalDateTime.now())
                        .setText(text).build();

                ModelManager.getInstance().getRepairOrderRepository().insert(order);


                ifNeedSendEmail(order, user);
                request.getSession().setAttribute("errorOrderTextMessage", "text.order_added");
                logger.debug("Order created");
            }else {
                request.getSession().setAttribute("errorOrderTextMessage", "text.user_is_not_a_client");
            }
        } catch (Exception e) {
            request.getSession().setAttribute("errorOrderClientIdMessage", "text.there_are_no_user");

            logger.error(e);
        }

        page = PathFactory.getPath("path.page.redirect.orders");
        return page;
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Admin).collect(Collectors.toList());
    }

    private void ifNeedSendEmail(RepairOrder order, User user){
        if(user.isAllow_letters()){
            Constants.LOCALE locale = Constants.LOCALE.values()[user.getLocale_id()];
            EmailSender.send(user.getEmail(),
                    user.getLogin() + "  " + locale.getString("text.your_order_created"),
                    locale.getString("text.your_created_with_id") + order.getId());
            logger.debug("Send email about order creation");
        }
    }

}
