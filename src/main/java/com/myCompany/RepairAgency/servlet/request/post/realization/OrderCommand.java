package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.entity.RepairOrder;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.util.ForChangeEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class OrderCommand implements IActionCommand, IHasRoleRequirement {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        ForChangeEntity.updateGoalId("Order", request);

        RepairOrder order = ModelManager.ins.getRepairOrderRepository().getById((Long) request.getSession().getAttribute("goalIdOrder"));

        String goalOrderCraftsman_id = request.getParameter("goalOrderCraftsman_id");
        if (goalOrderCraftsman_id != null && !goalOrderCraftsman_id.isBlank()) {
            order.setCraftsman_id(Integer.parseInt(goalOrderCraftsman_id));
        }

        String goalOrderFeedback_mark = request.getParameter("goalOrderFeedback_mark");
        if (goalOrderFeedback_mark != null && !goalOrderFeedback_mark.isBlank()) {
            String goalOrderFeedback_text = request.getParameter("goalOrderFeedback_text");
            if (goalOrderFeedback_text != null && !goalOrderFeedback_text.isBlank()) {
                order.setFeedback_text(goalOrderFeedback_text);
                order.setFeedback_date(LocalDateTime.now());
                order.setFeedback_mark(Integer.parseInt(goalOrderFeedback_mark));
            }
        }

        String goalOrderPrice = request.getParameter("goalOrderPrice");
        if (goalOrderPrice != null && !goalOrderPrice.isBlank()) {
            order.setPrice(Integer.parseInt(goalOrderPrice));
        }

        String goalOrderStatus = request.getParameter("goalOrderStatus");
        if (goalOrderStatus != null && !goalOrderStatus.isBlank()) {

            if (order.getStatus_id() > Constants.ORDER_STATUS.PENDING_PAYMENT.ordinal() &&
                order.getStatus_id() != Constants.ORDER_STATUS.CANCELED.ordinal() &&
                order.getStatus_id() != Constants.ORDER_STATUS.COMPLETED.ordinal()){
                order.setFinish_date(LocalDateTime.now());
                User user = ModelManager.ins.getUserRepository().getById(order.getUser_id());
                user.setAccount(user.getAccount() + order.getPrice());
                ModelManager.ins.getUserRepository().update(user);
            }

            order.setStatus_id(Integer.parseInt(goalOrderStatus));
            if(order.getStatus_id() == Constants.ORDER_STATUS.COMPLETED.ordinal()){
                order.setFinish_date(LocalDateTime.now());
            }
        }


        if(request.getSession().getAttribute("userRole") == Constants.ROLE.Client){
            if(request.getParameter("payOrder") != null &&
                    request.getParameter("payOrder").equals("true")){
                User user = ModelManager.ins.getUserRepository().getById((Long) request.getSession().getAttribute("userId"));
                if(user.getAccount() >= order.getPrice()) {
                    order.setStatus_id(Constants.ORDER_STATUS.PAID.ordinal());
                    user.setAccount(user.getAccount() - order.getPrice());
                    ModelManager.ins.getUserRepository().update(user);
                } else {
                    request.getSession().setAttribute("error","message.not_enough_money");
                }
            }
        }

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