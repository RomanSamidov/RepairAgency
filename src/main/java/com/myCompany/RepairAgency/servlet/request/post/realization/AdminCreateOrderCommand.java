package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.db.abstractDB.exception.MyDBException;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.service.OrderUserService;
import com.myCompany.RepairAgency.servlet.service.ParameterValidationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdminCreateOrderCommand implements IActionCommand, IHasRoleRequirement {

    private static final Logger logger = LogManager.getLogger(AdminCreateOrderCommand.class);

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        if (!ParameterValidationService.forAdminCreateOrder(request))
            return PathFactory.getPath("path.page.redirect.orders");

        long userId = Long.parseLong(request.getParameter("clientId"));
        String text = request.getParameter("orderText");

        try {
            if (OrderUserService.createOrder(userId, text)) {
                session.setAttribute("errorOrderTextMessage", "text.order_added");
                logger.debug("Order created");
            } else {
                session.setAttribute("errorOrderTextMessage", "text.user_is_not_a_client");
            }
        } catch (MyDBException e) {
            session.setAttribute("errorOrderClientIdMessage", "text.there_are_no_user");
            logger.error("Catch MyDBException");
        }

        return PathFactory.getPath("path.page.redirect.orders");
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Admin).collect(Collectors.toList());
    }


}
