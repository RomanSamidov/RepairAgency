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

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ManagerPayForOrderCommand implements IActionCommand, IHasRoleRequirement {
    private static final Logger logger = LogManager.getLogger(ManagerPayForOrderCommand.class);

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        if(request.getSession().getAttribute("userRole") == Constants.ROLE.Manager ||
                request.getSession().getAttribute("userRole") == Constants.ROLE.Admin ){
            RepairOrder order = ModelManager.getInstance().getRepairOrderRepository().getById(
                    Long.parseLong(request.getParameter("goalIdOrder")));
            order.setStatus_id(Constants.ORDER_STATUS.PAID.ordinal());
            ModelManager.getInstance().getRepairOrderRepository().update(order);
            User user = ModelManager.getInstance().getUserRepository().getById(order.getUser_id());
            ifNeedSendEmail(user, Long.parseLong(request.getParameter("goalIdOrder")));
        }

        Path path = PathFactory.getPath("path.page.redirect.order");
        path.addParameter("id", request.getParameter("goalIdOrder"));

        return path;
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Manager,
                Constants.ROLE.Admin).collect(Collectors.toList());
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