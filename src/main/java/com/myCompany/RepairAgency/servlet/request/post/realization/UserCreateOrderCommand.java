package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.service.OrderUserService;
import com.myCompany.RepairAgency.servlet.service.ParameterValidationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserCreateOrderCommand implements IActionCommand, IHasRoleRequirement {

    private static final Logger logger = LogManager.getLogger(UserCreateOrderCommand.class);

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {

        if (!(boolean) request.getSession().getAttribute("isUserConfirmed")) {
            request.getSession().setAttribute("errorOrderTextMessage", "text.you_not_confirmed_your_email");
            return PathFactory.getPath("path.page.redirect.orders");
        }

        long userId = (long) request.getSession().getAttribute("userId");

        String text = request.getParameter("orderText");
        if (!ParameterValidationService.validateOrderText(request, text)) {
            return PathFactory.getPath("path.page.redirect.orders");
        }

        if (OrderUserService.createOrder(userId, text)) {
            request.getSession().setAttribute("errorOrderTextMessage", "text.order_added");
            logger.debug("Order created");
            return PathFactory.getPath("path.page.redirect.orders");
        }

        request.getSession().setAttribute("errorOrderTextMessage", "text.user_not_client");
        logger.debug("Order not created");
        return PathFactory.getPath("path.page.redirect.orders");
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Client).collect(Collectors.toList());
    }


}
