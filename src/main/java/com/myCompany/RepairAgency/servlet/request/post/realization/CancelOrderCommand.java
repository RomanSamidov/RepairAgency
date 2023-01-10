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


public class CancelOrderCommand implements IActionCommand, IHasRoleRequirement {
    private static final Logger logger = LogManager.getLogger(CancelOrderCommand.class);

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {

        RepairOrder order = ModelManager.ins.getRepairOrderRepository()
                .getById(Long.parseLong(request.getParameter("goalIdOrder")));

        User user = ModelManager.ins.getUserRepository().getById(order.getUser_id());

        if (order.getStatus_id() > Constants.ORDER_STATUS.PENDING_PAYMENT.ordinal() &&
            order.getStatus_id() != Constants.ORDER_STATUS.CANCELED.ordinal() &&
            order.getStatus_id() != Constants.ORDER_STATUS.COMPLETED.ordinal()){

            ModelManager.ins.getRepairOrderRepository().cancelOrderWithMoneyReturn(order.getId());

            ifNeedSendEmail(user, order.getPrice());
        } else {
            order.setStatus_id(Constants.ORDER_STATUS.CANCELED.ordinal());
            ModelManager.ins.getRepairOrderRepository().update(order);
            ifNeedSendEmail(user, 0);
        }

        Path path = PathFactory.getPath("path.page.redirect.order");
        path.addParameter("id", request.getParameter("goalIdOrder"));
        logger.debug("Order "+order.getId()+" was canceled.");
        return path;
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Client, Constants.ROLE.Manager, Constants.ROLE.Admin, Constants.ROLE.Craftsman).collect(Collectors.toList());
    }

    private void ifNeedSendEmail(User user, int increment){
        if(user.isAllow_letters()){
            Constants.LOCALE locale = Constants.LOCALE.values()[user.getLocale_id()];
            if(increment == 0) {
                EmailSender.send(user.getEmail(),
                        user.getLogin() + "  " + locale.getString("text.your_order_was_cancelled"),
                        locale.getString("text.your_order_was_cancelled_and_return") + increment);
            } else {
                EmailSender.send(user.getEmail(),
                        user.getLogin() + "  " + locale.getString("text.your_order_was_cancelled"),
                        locale.getString("text.your_order_was_cancelled"));
            }
            logger.debug("Send email about order cancellation ");
        }
    }

}