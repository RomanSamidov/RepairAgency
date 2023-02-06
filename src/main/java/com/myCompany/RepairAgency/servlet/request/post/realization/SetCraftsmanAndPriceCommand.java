package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.entity.RepairOrder;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.service.RepairOrderService;
import com.myCompany.RepairAgency.servlet.service.UserService;
import com.myCompany.RepairAgency.servlet.util.ParameterValidation;
import com.myCompany.RepairAgency.servlet.util.SendEmail;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class SetCraftsmanAndPriceCommand implements IActionCommand, IHasRoleRequirement {
    private static final Logger logger = LogManager.getLogger(SetCraftsmanAndPriceCommand.class);

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        String goalOrderCraftsman_id = request.getParameter("goalOrderCraftsman_id");
        String goalOrderPrice = request.getParameter("goalOrderPrice");
        String goalIdOrder = request.getParameter("goalIdOrder");

        if (ParameterValidation.validateCraftsmanId(goalOrderCraftsman_id) &&
                ParameterValidation.validateInt(goalOrderPrice) &&
                ParameterValidation.validateGoalId(goalIdOrder)) {

            RepairOrder order = RepairOrderService.get(Long.parseLong(goalIdOrder));
            if (order == null) return PathFactory.getPath("path.page.redirect.orders");

            order.setCraftsman_id(Integer.parseInt(goalOrderCraftsman_id));
            order.setPrice(Integer.parseInt(goalOrderPrice));
            order.setStatus_id(Constants.ORDER_STATUS.PENDING_PAYMENT.ordinal());
            RepairOrderService.update(order);

            User user = UserService.get(order.getUser_id());
            SendEmail.forSetCraftPrice(user, order.getId());
            logger.debug("Order successfully paid");
        }

        Path path = PathFactory.getPath("path.page.redirect.order");
        path.addParameter("id", request.getParameter("goalIdOrder"));
        return path;
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(
                Constants.ROLE.Manager,
                Constants.ROLE.Admin).collect(Collectors.toList());
    }


}