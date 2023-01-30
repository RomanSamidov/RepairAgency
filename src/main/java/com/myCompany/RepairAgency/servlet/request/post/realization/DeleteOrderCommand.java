package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.db.abstractDB.exception.MyDBException;
import com.myCompany.RepairAgency.model.entity.RepairOrder;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.service.RepairOrderService;
import com.myCompany.RepairAgency.servlet.service.SendEmailService;
import com.myCompany.RepairAgency.servlet.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeleteOrderCommand implements IActionCommand, IHasRoleRequirement {
    private static final Logger logger = LogManager.getLogger(DeleteOrderCommand.class);

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        long orderId;
        RepairOrder order;
        try {
            orderId = Long.parseLong(request.getParameter("goalIdOrder"));
            order = RepairOrderService.get(orderId);
        } catch (NumberFormatException | MyDBException e) {
            return PathFactory.getPath("path.page.redirect.orders");
        }

        if (request.getSession().getAttribute("userRole").equals(Constants.ROLE.Client)) {
            if (order.getUser_id() == (long) request.getSession().getAttribute("userId")) {
                RepairOrderService.delete(orderId);
            } else {
                Path path = PathFactory.getPath("path.page.redirect.order");
                path.addParameter("id", String.valueOf(orderId));
                return path;
            }
        } else RepairOrderService.delete(orderId);

        User user = UserService.get(order.getUser_id());
        SendEmailService.forDeleteOrder(user, order.getId());
        return PathFactory.getPath("path.page.redirect.orders");
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Client, Constants.ROLE.Admin).collect(Collectors.toList());
    }


}