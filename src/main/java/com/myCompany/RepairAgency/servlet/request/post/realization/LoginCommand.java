package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.service.UserService;
import com.myCompany.RepairAgency.servlet.util.ParameterValidation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LoginCommand implements IActionCommand, IHasRoleRequirement {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter(Constants.LOGIN);
        String password = request.getParameter(Constants.PASSWORD);
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");

        if (ParameterValidation.validatePassword(request, password) &
                ParameterValidation.validateLogin(request, login) &
                ParameterValidation.validateRecaptcha(request, gRecaptchaResponse)) {
            if (UserService.loginUser(login, password, request)) {
                return PathFactory.getPath("path.page.redirect.cabinet");
            }
        }

        return PathFactory.getPath("path.page.redirect.login");
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Guest).collect(Collectors.toList());
    }

}