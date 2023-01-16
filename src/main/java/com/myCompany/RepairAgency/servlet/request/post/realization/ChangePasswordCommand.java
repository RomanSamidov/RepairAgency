package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.util.EmailSender;
import com.myCompany.RepairAgency.servlet.util.Encrypt;
import com.myCompany.RepairAgency.servlet.util.VerifyRecaptcha;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.Random;

public class ChangePasswordCommand implements IActionCommand {

    private static final Logger logger = LogManager.getLogger(ChangePasswordCommand.class);

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("confirmationCodePassword");
        String login = request.getParameter(Constants.LOGIN);
        String password = request.getParameter(Constants.PASSWORD);
        String email = request.getParameter(Constants.EMAIL);
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");

        request.getSession().setAttribute("login", login);
        request.getSession().setAttribute("email", email);


        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
        if (!verify) {
            request.getSession().setAttribute("errorRecaptchaMessage", "message.error_recaptcha");

        }

        if (email == null || email.isEmpty()) {
            request.getSession().setAttribute("errorEmptyEmail", "message.empty_email");
            return PathFactory.getPath("path.page.redirect.change_password");
        }
        if (login == null || login.isEmpty()) {
            request.getSession().setAttribute("errorEmptyLogin", "message.empty_login");
            return PathFactory.getPath("path.page.redirect.change_password");
        }

        User user = ModelManager.getInstance().getUserRepository().getByLogin(login);
        if(user == null){
            request.getSession().setAttribute("confirmationCodeError", "message.login_not_exist_error");
            return PathFactory.getPath("path.page.redirect.change_password");
        }
        if(!user.getEmail().equals(email)){
            request.getSession().setAttribute("confirmationCodeError", "message.wrong_email");
            return PathFactory.getPath("path.page.redirect.change_password");
        }

        if(request.getSession().getAttribute("waitedCodePassword") == null ||
                Boolean.parseBoolean(request.getParameter("sendCodeAgain"))) {
            int leftLimit = 97; // letter 'a'
            int rightLimit = 122; // letter 'z'
            int targetStringLength = 10;
            Random random = new Random();

            String generatedString = random.ints(leftLimit, rightLimit + 1)
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            request.getSession().setAttribute("waitedCodePassword", generatedString);
            System.out.println(user.getLocale_id());
            Constants.LOCALE locale = Constants.LOCALE.values()[user.getLocale_id()];
            EmailSender.send(user.getEmail(),
                    user.getLogin() + "  " + locale.getString("text.confirm_your_password_change")
                    ,locale.getString("text.confirm_your_password_change_code") + generatedString );
            return PathFactory.getPath("path.page.redirect.change_password");
        } else {
            if(!user.getEmail().equals(email)){
                request.getSession().setAttribute("confirmationCodeError", "message.wrong_code");
                return PathFactory.getPath("path.page.redirect.change_password");
            }
        }

        boolean haveError = isInputHasErrors(request);
        if(!haveError) {
            code = code.trim();
            if (code.equals(request.getSession().getAttribute("waitedCodePassword"))) {
                user.setPassword(Encrypt.encrypt(password));
                ModelManager.getInstance().getUserRepository().update(user);
                ifNeedSendEmail(user);
                request.getSession().removeAttribute("waitedCodePassword");
                request.getSession().removeAttribute("login");
                request.getSession().removeAttribute("email");
                return PathFactory.getPath("path.page.redirect.login");
            } else {
                request.getSession().setAttribute("confirmationCodeError", "text.wrong_code");
            }
        }
        return PathFactory.getPath("path.page.redirect.change_password");
    }


    private boolean isInputHasErrors(HttpServletRequest request){
        String password = request.getParameter(Constants.PASSWORD);
        String passwordRepeat = request.getParameter(Constants.PASSWORD_REPEAT);
        boolean haveError = false;
        if (password == null || password.isEmpty()) {
            request.getSession().setAttribute("errorEmptyPassword", "message.empty_password");
            haveError = true;
        }
        if (passwordRepeat == null || passwordRepeat.isEmpty()) {
            request.getSession().setAttribute("errorEmptyPasswordRepeat",
                    "message.empty_repeat_password");
            haveError = true;
        }
        if(!Objects.equals(passwordRepeat, password)){
            request.getSession().setAttribute("errorPasswordsNotEqual", "message.not_equal_passwords");
            haveError = true;
        }

        return haveError;
    }

    private void ifNeedSendEmail(User user){
        Constants.LOCALE locale = Constants.LOCALE.values()[user.getLocale_id()];
        EmailSender.send(user.getEmail(),
                user.getLogin() + "  " + locale.getString("text.your_password_changed"),
                locale.getString("text.your_password_changed"));
        logger.debug("Send email about password changing");
    }
}