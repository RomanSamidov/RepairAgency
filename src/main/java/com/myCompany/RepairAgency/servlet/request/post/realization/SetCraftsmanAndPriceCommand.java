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


public class SetCraftsmanAndPriceCommand implements IActionCommand, IHasRoleRequirement {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        ForChangeEntity.updateGoalId("Order", request);

        String goalOrderCraftsman_id = request.getParameter("goalOrderCraftsman_id");
        String goalOrderPrice = request.getParameter("goalOrderPrice");
        if (goalOrderCraftsman_id != null && !goalOrderCraftsman_id.isBlank() &&
                goalOrderPrice != null && !goalOrderPrice.isBlank()) {
            RepairOrder order = ModelManager.ins.getRepairOrderRepository().getById((Long) request.getSession().getAttribute("goalIdOrder"));
            order.setCraftsman_id(Integer.parseInt(goalOrderCraftsman_id));
            order.setPrice(Integer.parseInt(goalOrderPrice));
            order.setStatus_id(Constants.ORDER_STATUS.PENDING_PAYMENT.ordinal());
            ModelManager.ins.getRepairOrderRepository().update(order);
            User user = ModelManager.ins.getUserRepository().getById(order.getUser_id());
            EmailSender.send(user.getEmail(),
                    "Your order updated.",
                    "Your order now has a price and appointed craftsman. Please pay your order");
        }
        Path path = PathFactory.getPath("path.page.redirect.order");
        path.addParameter("id", request.getParameter("goalIdOrder"));

        return path;
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Client, Constants.ROLE.Manager, Constants.ROLE.Admin, Constants.ROLE.Craftsman).collect(Collectors.toList());
    }
}