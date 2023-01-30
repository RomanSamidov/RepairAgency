package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.service.ParameterValidationService;
import com.myCompany.RepairAgency.servlet.service.SendEmailService;
import com.myCompany.RepairAgency.servlet.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChangeEmailCommand implements IActionCommand, IHasRoleRequirement {
    private static final Logger logger = LogManager.getLogger(ChangeEmailCommand.class);

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {

        String email = request.getParameter("email");
        if (!ParameterValidationService.validateEmail(request, email)) {
            return PathFactory.getPath("path.page.redirect.cabinet");
        }

        User user = UserService.get((Long) request.getSession().getAttribute("userId"));

        if (email.equals(user.getEmail())) {
            return PathFactory.getPath("path.page.redirect.cabinet");
        }

        UserService.changeEmail(user, email);
        request.getSession().removeAttribute("waitedCode");
        request.getSession().setAttribute("userEmail", user.getEmail());
        request.getSession().setAttribute("isUserConfirmed", user.isConfirmed());
        SendEmailService.forChangeEmail(user);
        logger.debug("User email was changed ");
        return PathFactory.getPath("path.page.redirect.cabinet");
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Client,
                Constants.ROLE.Admin,
                Constants.ROLE.Manager,
                Constants.ROLE.Craftsman).collect(Collectors.toList());
    }


}