package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.entity.RepairOrder;
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

public class CreateOrderCommand implements IActionCommand, IHasRoleRequirement {

    private static final Logger logger = LogManager.getLogger(CreateOrderCommand.class);

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        Path page;

        if(!(boolean)request.getSession().getAttribute("isUserConfirmed")) {
            request.getSession().setAttribute("errorOrderTextMessage", "text.you_not_confirmed");
            return PathFactory.getPath("path.page.redirect.orders");
        }

        long userId = (long) request.getSession().getAttribute("userId");
        String text = request.getParameter("orderText");
        if (text == null || text.isBlank()) {
            request.getSession().setAttribute("errorOrderTextMessage", "text.order_empty");
            page = PathFactory.getPath("path.page.redirect.orders");
            return page;
        }
        try {
            RepairOrder order = new RepairOrder.RepairOrderBuilder()
                    .setUser_id(userId)
                    .setCreation_date(LocalDateTime.now())
                    .setText(text).build();

            ModelManager.ins.getRepairOrderRepository().insert(order);
            if((boolean)request.getSession().getAttribute("isUserAllowLetters")) {
                EmailSender.send((String)request.getSession().getAttribute("userEmail"),
                        "Your order created",
                        "Order with id " + order.getId() + " created.");
            }
            request.getSession().setAttribute("errorOrderTextMessage", "text.order_added");
        } catch (Exception e) {
            logger.error("[CreateOrderCommand] " + e);
        }

        page = PathFactory.getPath("path.page.redirect.orders");
        return page;
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Client).collect(Collectors.toList());
    }
}
