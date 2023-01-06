package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.entity.iUserRepository;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.util.Encrypt;
import com.myCompany.RepairAgency.servlet.util.VerifyRecaptcha;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SignupCommand implements IActionCommand, IHasRoleRequirement {
    private static final Logger logger = LogManager.getLogger(SignupCommand.class);

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        Path page;
        String login = request.getParameter(Constants.LOGIN);
        String password = request.getParameter(Constants.PASSWORD);
        String passwordRepeat = request.getParameter(Constants.PASSWORD_REPEAT);
        String email = request.getParameter(Constants.EMAIL);
        int roleId = 0;
        if(request.getSession().getAttribute("userRole").equals(Constants.ROLE.Admin)) {
            roleId = Integer.parseInt(request.getParameter("role"));
        }
        // get reCAPTCHA request param
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        boolean haveError = false;

        boolean verify;
        verify = VerifyRecaptcha.verify(gRecaptchaResponse);
        if (!verify) {
            request.getSession().setAttribute("errorRecaptchaMessage", "message.error_recaptcha");
            haveError = true;
        }

        if (email == null || email.isEmpty()) {
            request.getSession().setAttribute("errorEmptyEmail", "message.empty_emailsome_line");
            haveError = true;
        }
        if (password == null || password.isEmpty()) {
            request.getSession().setAttribute("errorEmptyPassword", "message.empty_password");
            haveError = true;
        }
        if (passwordRepeat == null || passwordRepeat.isEmpty()) {
            request.getSession().setAttribute("errorEmptyPasswordRepeat", "message.empty_repeat_password");
            haveError = true;
        }
        if (login == null || login.isEmpty()) {
            request.getSession().setAttribute("errorEmptyLogin", "message.empty_login");
            haveError = true;
        }


        if (!haveError) {
            try {
                iUserRepository userRepository = ModelManager.ins.getUserRepository();
                if (userRepository.getByLogin(login) != null) {
                    request.getSession().setAttribute("errorLoginPassMessage", "message.login_exist");
                    page = PathFactory.getPath("path.page.redirect.signup");
                    return page;
                }
                String userPassword = Encrypt.encrypt(password);
                User user = new User.UserBuilder().setLogin(login)
                        .setPassword(userPassword)
                        .setEmail(email)
                        .setAllow_letters(true)
                        .setConfirmed(false)
                        .setRole_id(Constants.ROLE.Client.ordinal())
                        .build();

                if(request.getSession().getAttribute("userRole").equals(Constants.ROLE.Admin)){
                    if (roleId != 0) user.setRole_id(roleId);
                }
                userRepository.insert(user);
                if(request.getSession().getAttribute("userRole").equals(Constants.ROLE.Admin)) {
                    page = PathFactory.getPath("path.page.redirect.signup");
                    return page;
                }
                user = userRepository.getByLogin(login);
                LoginCommand.initializeUserSessionAttributes(request, user);
                page = PathFactory.getPath("path.page.redirect.cabinet");
                return page;
            } catch (Exception e) {
                logger.error("[SignupCommand] sql error  " + e);
                Arrays.stream(e.getStackTrace()).iterator().forEachRemaining(x -> logger.error("[SignupCommand] sql error  " + x));
            }
        }

        page = PathFactory.getPath("path.page.redirect.signup");
        return page;
    }


    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Guest, Constants.ROLE.Admin).collect(Collectors.toList());
    }
}