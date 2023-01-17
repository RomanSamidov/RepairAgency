package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iUserRepository;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.util.EmailSender;
import com.myCompany.RepairAgency.servlet.util.Encrypt;
import com.myCompany.RepairAgency.servlet.util.VerifyRecaptcha;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreateUserCommand implements IActionCommand, IHasRoleRequirement {
    private static final Logger logger = LogManager.getLogger(CreateUserCommand.class);

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        Path page;
        String login = request.getParameter(Constants.LOGIN);
        String password = request.getParameter(Constants.PASSWORD);
        String email = request.getParameter(Constants.EMAIL);
        int roleId = initRoleId(request);
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");

        boolean haveError = isInputHasErrors(request);

        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
        if (!verify) {
            request.getSession().setAttribute("errorRecaptchaMessage", "message.error_recaptcha");
            haveError = true;
        }

        if (!haveError) {
            try {
                iUserRepository userRepository = ModelManager.getInstance().getUserRepository();
                    if (userRepository.getByLogin(login) != null) {
                        request.getSession().setAttribute("errorLoginPassMessage", "message.login_exist");
                        page = PathFactory.getPath("path.page.redirect.create_user");
                        return page;
                    }
                String userPassword = Encrypt.encrypt(password);
                User user = new User.UserBuilder().setLogin(login)
                        .setPassword(userPassword)
                        .setEmail(email)
                        .setAllow_letters(true)
                        .setConfirmed(false)
                        .setRole_id(roleId)
                        .build();

                userRepository.insert(user);
                ifNeedSendEmail(user);

                page = PathFactory.getPath("path.page.redirect.create_user");
                return page;

            } catch (Exception e) {
                logger.error("[SignupCommand] sql error  " + e);
            }
        }

        page = PathFactory.getPath("path.page.redirect.create_user");
        return page;
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Admin).collect(Collectors.toList());
    }
    private int initRoleId(HttpServletRequest request) {
            return Integer.parseInt(request.getParameter("role"));
    }

    private boolean isInputHasErrors(HttpServletRequest request){
        String login = request.getParameter(Constants.LOGIN);
        String password = request.getParameter(Constants.PASSWORD);
        String passwordRepeat = request.getParameter(Constants.PASSWORD_REPEAT);
        String email = request.getParameter(Constants.EMAIL);
        boolean haveError = false;
        if (email == null || email.isEmpty()) {
            request.getSession().setAttribute("errorEmptyEmail", "message.empty_email");
            haveError = true;
        }
        if (password == null || password.isEmpty()) {
            request.getSession().setAttribute("errorEmptyPassword", "message.empty_password");
            haveError = true;
        }
        if (passwordRepeat == null || passwordRepeat.isEmpty()) {
            request.getSession().setAttribute("errorEmptyPasswordRepeat",
                    "message.empty_repeat_password");
            haveError = true;
        }
        if (login == null || login.isEmpty()) {
            request.getSession().setAttribute("errorEmptyLogin", "message.empty_login");
            haveError = true;
        }
        if(!Objects.equals(passwordRepeat, password)){
            request.getSession().setAttribute("errorPasswordsNotEqual", "message.not_equal_passwords");
            haveError = true;
        }

        return haveError;
    }


    private void ifNeedSendEmail(User user){
        if(user.isAllow_letters()){
            Constants.LOCALE locale = Constants.LOCALE.values()[user.getLocale_id()];
            EmailSender.send(user.getEmail(),
                    user.getLogin() + "  " + locale.getString("text.you_signup"),
                    locale.getString("text.you_have_signup"));
            logger.debug("Send email about successful registration");
        }
    }
}