package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.entity.RepairOrder;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.util.ForChangeEntity;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class OrderCommand implements IActionCommand, IHasRoleRequirement {
    @Override
    public Path execute(HttpServletRequest request) {
        ForChangeEntity.updateGoalId("Order", request);

        String goalOrderCraftsman_id = request.getParameter("goalOrderCraftsman_id");
        if(goalOrderCraftsman_id != null && !goalOrderCraftsman_id.isBlank()) {
            int newId = Integer.parseInt(goalOrderCraftsman_id);
            RepairOrder order = ModelManager.ins.getRepairOrder((Long) request.getSession().getAttribute("goalIdOrder"));
            ModelManager.ins.updateRepairOrder(order.setCraftsman_id(newId));
        }

        String goalOrderFeedback_text = request.getParameter("goalOrderFeedback_text");
        if(goalOrderFeedback_text != null && !goalOrderFeedback_text.isBlank()) {
            RepairOrder order = ModelManager.ins.getRepairOrder((Long) request.getSession().getAttribute("goalIdOrder"));
            ModelManager.ins.updateRepairOrder(order.setFeedback_text(goalOrderFeedback_text));
        }

        String goalOrderFeedback_mark = request.getParameter("goalOrderFeedback_mark");
        if(goalOrderFeedback_mark != null && !goalOrderFeedback_mark.isBlank()) {
            int newMark = Integer.parseInt(goalOrderFeedback_mark);
            RepairOrder order = ModelManager.ins.getRepairOrder((Long) request.getSession().getAttribute("goalIdOrder"));
            ModelManager.ins.updateRepairOrder(order.setFeedback_mark(newMark));
        }

        String goalOrderPrice = request.getParameter("goalOrderPrice");
        if(goalOrderPrice != null && !goalOrderPrice.isBlank()) {
            int newPrice = Integer.parseInt(goalOrderPrice);
            RepairOrder order = ModelManager.ins.getRepairOrder((Long) request.getSession().getAttribute("goalIdOrder"));
            ModelManager.ins.updateRepairOrder(order.setPrice(newPrice).setStatus_id(Constants.ORDER_STATUS.PENDING_PAYMENT.ordinal()));
        }

        String goalOrderStatus = request.getParameter("goalOrderStatus");
        if(goalOrderStatus != null && !goalOrderStatus.isBlank()) {
            int newStatus = Integer.parseInt(goalOrderStatus);
            RepairOrder order = ModelManager.ins.getRepairOrder((Long) request.getSession().getAttribute("goalIdOrder"));
            ModelManager.ins.updateRepairOrder(order.setStatus_id(newStatus));
        }
        return PathFactory.getPath("path.page.redirect.order");
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Client, Constants.ROLE.Manager, Constants.ROLE.Admin, Constants.ROLE.Craftsman).collect(Collectors.toList());
    }
}