package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.entity.RepairOrder;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.service.ParameterValidationService;
import com.myCompany.RepairAgency.servlet.service.RepairOrderService;
import com.myCompany.RepairAgency.servlet.service.SendEmailService;
import com.myCompany.RepairAgency.servlet.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class SetOrderStatusCommand implements IActionCommand, IHasRoleRequirement {
    private static final Logger logger = LogManager.getLogger(SetOrderStatusCommand.class);

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        String goalOrderStatus = request.getParameter("goalOrderStatus");
        String goalIdOrder = request.getParameter("goalIdOrder");

        if (!ParameterValidationService.validateGoalId(goalIdOrder))
            return PathFactory.getPath("path.page.redirect.orders");

        if (ParameterValidationService.validateOrderStatusId(request, goalOrderStatus)) {
            RepairOrder order = RepairOrderService.get(Long.parseLong(goalIdOrder));
            if (order == null) return PathFactory.getPath("path.page.redirect.orders");

            if ((request.getSession().getAttribute("userRole").equals(Constants.ROLE.Craftsman) &&
                    request.getSession().getAttribute("userId").equals(order.getCraftsman_id())) ||
                    request.getSession().getAttribute("userRole").equals(Constants.ROLE.Admin)) {
                order.setStatus_id(Integer.parseInt(goalOrderStatus));
                if (order.getStatus_id() == Constants.ORDER_STATUS.COMPLETED.ordinal()) {
                    order.setFinish_date(LocalDateTime.now());
                }
                RepairOrderService.update(order);

                User user = UserService.get(order.getUser_id());
                SendEmailService.forSetOrderStatus(user, order.getId());
            }
        }

        Path path = PathFactory.getPath("path.page.redirect.order");
        path.addParameter("id", request.getParameter("goalIdOrder"));
        return path;
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Craftsman, Constants.ROLE.Admin).collect(Collectors.toList());
    }


}