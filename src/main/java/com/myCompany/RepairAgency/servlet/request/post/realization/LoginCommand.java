package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LoginCommand implements IActionCommand, IHasRoleRequirement {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);

    public static void initializeUserSessionAttributes(HttpServletRequest request, User user) {
        request.getSession().setAttribute("language", Constants.LOCALE.values()[user.getLocale_id()].toString());
        request.getSession().setAttribute("userLogin", user.getLogin());
        request.getSession().setAttribute("userId", user.getId());
        request.getSession().setAttribute("userEmail", user.getEmail());
        request.getSession().setAttribute("isUserConfirmed", user.isConfirmed());
        request.getSession().setAttribute("userRole", Constants.ROLE.values()[user.getRole_id()]);
    }

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        Path page;
        String login = request.getParameter(Constants.LOGIN);
        String password = request.getParameter(Constants.PASSWORD);
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
                User user = ModelManager.getInstance().getUserRepository().getByLogin(login);
                String userPassword = user.getPassword();
                if (userPassword.equals(Encrypt.encrypt(password))) {
                    initializeUserSessionAttributes(request, user);
                    page = PathFactory.getPath("path.page.redirect.cabinet");
                    ifNeedSendEmail(user);
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

    private void ifNeedSendEmail(User user){
        if(user.isAllow_letters()){
            Constants.LOCALE locale = Constants.LOCALE.values()[user.getLocale_id()];
            EmailSender.send(user.getEmail(),
                    user.getLogin() + "  " + locale.getString("text.successful_login_to_your_profile"),
                    locale.getString("text.someone_login_to_your_profile"));
            logger.debug("Successful login");
        }
    }

}