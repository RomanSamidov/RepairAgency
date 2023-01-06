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
import com.myCompany.RepairAgency.servlet.util.ForChangeEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class CancelOrderCommand implements IActionCommand, IHasRoleRequirement {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        ForChangeEntity.updateGoalId("Order", request);

        RepairOrder order = ModelManager.ins.getRepairOrderRepository().getById((Long) request.getSession().getAttribute("goalIdOrder"));
        if (order.getStatus_id() > Constants.ORDER_STATUS.PENDING_PAYMENT.ordinal() &&
            order.getStatus_id() != Constants.ORDER_STATUS.CANCELED.ordinal() &&
            order.getStatus_id() != Constants.ORDER_STATUS.COMPLETED.ordinal()){
            order.setFinish_date(LocalDateTime.now());
            User user = ModelManager.ins.getUserRepository().getById(order.getUser_id());
            user.setAccount(user.getAccount() + order.getPrice());
            ModelManager.ins.getUserRepository().update(user);
            EmailSender.send(user.getEmail(),
                    "Your order was canceled.",
                    "Your order " + order.getId() + " was canceled.");
        }
        order.setStatus_id(Constants.ORDER_STATUS.CANCELED.ordinal());
        ModelManager.ins.getRepairOrderRepository().update(order);

        Path path = PathFactory.getPath("path.page.redirect.order");
        path.addParameter("id", request.getParameter("goalIdOrder"));

        return path;
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Client, Constants.ROLE.Manager, Constants.ROLE.Admin, Constants.ROLE.Craftsman).collect(Collectors.toList());
    }
}