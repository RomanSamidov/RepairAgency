package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.util.Encrypt;
import com.myCompany.RepairAgency.servlet.util.VerifyRecaptcha;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SignupCommand implements IActionCommand, IHasRoleRequirement {
    private static final Logger logger = LogManager.getLogger(SignupCommand.class);
    @Override
    public Path execute(HttpServletRequest request) {
        Path page;
        String login = request.getParameter(Constants.LOGIN);
        String password = request.getParameter(Constants.PASSWORD);
        String passwordRepeat = request.getParameter(Constants.PASSWORD_REPEAT);
        // get reCAPTCHA request param
        String gRecaptchaResponse = request
                .getParameter("g-recaptcha-response");
        boolean verify;
        try {
            verify = VerifyRecaptcha.verify(gRecaptchaResponse);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//////////////////////
        verify = true;
///////////////////////
        boolean haveError = false;
        if (password == null || password.isEmpty()) {
            request.getSession().setAttribute("errorEmptyPassword","message.empty_password");
            haveError = true;
        }
        if (passwordRepeat == null || passwordRepeat.isEmpty()) {
            request.getSession().setAttribute("errorEmptyPasswordRepeat","message.empty_password_repeat");
            haveError = true;
        }
        if (login == null || login.isEmpty()) {
            request.getSession().setAttribute("errorEmptyLogin","message.empty_login");
            haveError = true;
        }
        if (!verify) {
            request.getSession().setAttribute("errorRecaptchaMessage","message.error_recaptcha");
            haveError = true;
        }

        if(!haveError) {
            try {
                if (ModelManager.ins.getUser(login) != null) {
                    request.getSession().setAttribute("errorLoginPassMessage","message.login_exist");
                    page = PathFactory.getPath("path.page.redirect.signup");
                    return  page;
                }
                String userPassword = Encrypt.encrypt(password);
                User user = new User.UserBuilder().setLogin(login)
                        .setPassword(userPassword)
                        .setRole_id(Constants.ROLE.Client.ordinal())
                        .build();
                ModelManager.ins.insertUser(user);
                user = ModelManager.ins.getUser(login);
                LoginCommand.initializeUserSessionAttributes(request, user);
                page = PathFactory.getPath("path.page.redirect.cabinet");
                return page;
            } catch (Exception e) {
                logger.error("[SignupCommand] sql error  " + e);
            }
        }

        page = PathFactory.getPath("path.page.redirect.signup");
        return page;
    }


    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Guest).collect(Collectors.toList());
    }
}