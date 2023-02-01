package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.entity.RepairOrder;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.service.*;
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

        String goalIdOrder = request.getParameter("goalIdOrder");
        if (!ParameterValidationService.validateGoalId(goalIdOrder))
            return PathFactory.getPath("path.page.redirect.orders");


        RepairOrder order = RepairOrderService.get(Long.parseLong(goalIdOrder));

        User user = UserService.get(order.getUser_id());

        if (!((user.getRole_id() == Constants.ROLE.Client.ordinal() && user.getId() == order.getUser_id()) ||
                (user.getRole_id() == Constants.ROLE.Craftsman.ordinal() && user.getId() == order.getCraftsman_id()) ||
                user.getRole_id() == Constants.ROLE.Manager.ordinal() ||
                user.getRole_id() == Constants.ROLE.Admin.ordinal())) {
            Path path = PathFactory.getPath("path.page.redirect.order");
            path.addParameter("id", request.getParameter("goalIdOrder"));
            return path;
        }

        OrderUserService.cancelOrder(order.getId());

        Path path = PathFactory.getPath("path.page.redirect.order");
        path.addParameter("id", request.getParameter("goalIdOrder"));
        logger.debug("Order " + order.getId() + " was canceled.");
        return path;
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Client,
                Constants.ROLE.Manager,
                Constants.ROLE.Admin,
                Constants.ROLE.Craftsman).collect(Collectors.toList());
    }


}