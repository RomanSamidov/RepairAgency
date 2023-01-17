package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
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

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ClientPayForOrderCommand implements IActionCommand, IHasRoleRequirement {
    private static final Logger logger = LogManager.getLogger(ClientPayForOrderCommand.class);

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {

        User user = ModelManager.getInstance().getUserRepository().getById((Long)
                request.getSession().getAttribute("userId"));
        if(user.getId() == (long)request.getSession().getAttribute("userId") &&
                ModelManager.getInstance().getOrderUserService().payOrder(
                Long.parseLong(request.getParameter("goalIdOrder")))) {
            logger.debug("Successfully paid");
            ifNeedSendEmail(user, Long.parseLong(request.getParameter("goalIdOrder")));
        } else {
            request.getSession().setAttribute("error","message.not_enough_money");
        }


        Path path = PathFactory.getPath("path.page.redirect.order");
        path.addParameter("id", request.getParameter("goalIdOrder"));

        return path;
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Client).collect(Collectors.toList());
    }

    private void ifNeedSendEmail(User user, long orderId){
        if(user.isAllow_letters()){
            Constants.LOCALE locale = Constants.LOCALE.values()[user.getLocale_id()];
            EmailSender.send(user.getEmail(),
                    user.getLogin() + "  " + locale.getString("text.successful_pay_order"),
                    locale.getString("text.successful_pay_order_with_id") + orderId);
            logger.debug("Send email about successful paid");
        }
    }
}