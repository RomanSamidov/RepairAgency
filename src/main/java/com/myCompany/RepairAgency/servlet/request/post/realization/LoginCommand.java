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

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LoginCommand implements IActionCommand, IHasRoleRequirement {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);

    public static void initializeUserSessionAttributes(HttpServletRequest request, User user) {
        request.getSession().setAttribute("userLogin", user.getLogin());
        request.getSession().setAttribute("userId", user.getId());
        request.getSession().setAttribute("userRole", Constants.ROLE.values()[user.getRole_id()]);
        if (user.getRole_id() == Constants.ROLE.Client.ordinal()) {
            request.getSession().setAttribute("userAccount", user.getAccount());
        }
    }

    @Override
    public Path execute(HttpServletRequest request) {
        Path page;
        String login = request.getParameter(Constants.LOGIN);
        String password = request.getParameter(Constants.PASSWORD);

        // get reCAPTCHA request param
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        boolean verify;
        boolean haveError = false;
        verify = VerifyRecaptcha.verify(gRecaptchaResponse);
        if (!verify) {
            request.getSession().setAttribute("errorRecaptchaMessage", "message.error_recaptcha");
            haveError = true;
        }


        if (password == null || password.isEmpty()) {
            request.getSession().setAttribute("errorEmptyPassword", "message.empty_password");
            haveError = true;
        }
        if (login == null || login.isEmpty()) {
            request.getSession().setAttribute("errorEmptyLogin", "message.empty_login");
            haveError = true;
        }


        if (!haveError) {
            try {
                User user = ModelManager.ins.getUserRepository().getByLogin(login);
                String userPassword = user.getPassword();
                if (userPassword.equals(Encrypt.encrypt(password))) {
                    initializeUserSessionAttributes(request, user);
                    page = PathFactory.getPath("path.page.redirect.cabinet");
                    return page;
                } else {
                    request.getSession().setAttribute("errorLoginPassMessage", "message.login_error");
                }
            } catch (Exception e) {
                request.getSession().setAttribute("errorLoginPassMessage", "message.login_not_exist_error");
                logger.error("[LoginCommand] sql error  " + e);
            }
        }


        page = PathFactory.getPath("path.page.redirect.login");
        return page;
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Guest).collect(Collectors.toList());
    }

}