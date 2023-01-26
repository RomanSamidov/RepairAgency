package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.db.abstractDB.exception.MyDBException;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.service.OrderUserService;
import com.myCompany.RepairAgency.servlet.service.SendEmailService;
import com.myCompany.RepairAgency.servlet.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ClientPayForOrderCommand implements IActionCommand, IHasRoleRequirement {
    private static final Logger logger = LogManager.getLogger(ClientPayForOrderCommand.class);

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        User user = UserService.get((Long) session.getAttribute("userId"));
        try {
            if (user.getId() == (long) session.getAttribute("userId") &&
                    OrderUserService.payOrder(
                            Long.parseLong(request.getParameter("goalIdOrder")))) {
                logger.debug("Successfully paid");
                SendEmailService.forPayForOrder(user, Long.parseLong(request.getParameter("goalIdOrder")));
            } else {
                session.setAttribute("error", "message.not_enough_money");
            }
        } catch (NumberFormatException | MyDBException e) {
            return PathFactory.getPath("path.page.redirect.orders");
        }

        Path path = PathFactory.getPath("path.page.redirect.order");
        path.addParameter("id", request.getParameter("goalIdOrder"));
        return path;
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Client).collect(Collectors.toList());
    }


}