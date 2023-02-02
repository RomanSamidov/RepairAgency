package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.db.abstractDB.exception.MyDBException;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.service.SendEmailService;
import com.myCompany.RepairAgency.servlet.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeleteUserCommand implements IActionCommand, IHasRoleRequirement {
    private static final Logger logger = LogManager.getLogger(DeleteUserCommand.class);

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        User user;
        try {
            long userId = Long.parseLong(request.getParameter("goalIdUser"));
            if(userId == (long)request.getSession().getAttribute("userId")){
                return PathFactory.getPath("path.page.redirect.users");
            }
            user = UserService.get(userId);
            UserService.delete(user);
        } catch (NumberFormatException | MyDBException e) {
            return PathFactory.getPath("path.page.redirect.users");
        }

        SendEmailService.forDeleteUser(user);
        logger.debug("User profile deleted");
        return PathFactory.getPath("path.page.redirect.users");
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Admin).collect(Collectors.toList());
    }


}