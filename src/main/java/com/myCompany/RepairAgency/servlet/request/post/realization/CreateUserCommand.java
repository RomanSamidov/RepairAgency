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

public class CreateUserCommand implements IActionCommand, IHasRoleRequirement {
    private static final Logger logger = LogManager.getLogger(CreateUserCommand.class);

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter(Constants.EMAIL);
        String password = request.getParameter(Constants.PASSWORD);
        String passwordRepeat = request.getParameter(Constants.PASSWORD_REPEAT);
        String login = request.getParameter(Constants.LOGIN);
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        String roleId = request.getParameter("role");

        if (ParameterValidation.validateEmail(request, email) &
                ParameterValidation.validatePasswordAndRepeat(request, password, passwordRepeat) &
                ParameterValidation.validateLogin(request, login) &
                ParameterValidation.validateRecaptcha(request, gRecaptchaResponse) &
                ParameterValidation.validateRoleId(request, roleId)) {

            if (UserService.checkUserExistence(login)) {
                request.getSession().setAttribute("errorLoginPassMessage", "message.login_exist");
                return PathFactory.getPath("path.page.redirect.create_user");
            }

            UserService.registerNewUser(login, password, email, Integer.parseInt(roleId));
            request.getSession().setAttribute("userCreated", "message.user_created");
        }

        return PathFactory.getPath("path.page.redirect.create_user");
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Admin).collect(Collectors.toList());
    }


}