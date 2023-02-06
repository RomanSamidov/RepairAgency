package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.service.UserService;
import com.myCompany.RepairAgency.servlet.util.Encrypt;
import com.myCompany.RepairAgency.servlet.util.ParameterValidation;
import com.myCompany.RepairAgency.servlet.util.SendEmail;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ChangePasswordCommand implements IActionCommand {

    private static final Logger logger = LogManager.getLogger(ChangePasswordCommand.class);

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("confirmationCodePassword");
        String password = request.getParameter(Constants.PASSWORD);

        String login = request.getParameter(Constants.LOGIN);
        String email = request.getParameter(Constants.EMAIL);
        request.getSession().setAttribute("login", login);
        request.getSession().setAttribute("email", email);

        if (!ParameterValidation.validateLogin(request, login)) {
            request.getSession().removeAttribute("waitedCodePassword");
            return PathFactory.getPath("path.page.redirect.change_password");
        }

        User user = UserService.get(login);
        if (!ParameterValidation.forChangePassword(request, user)) {
            request.getSession().removeAttribute("waitedCodePassword");
            return PathFactory.getPath("path.page.redirect.change_password");
        }
        if (request.getSession().getAttribute("waitedCodePassword") == null ||
                Boolean.parseBoolean(request.getParameter("sendCodeAgain"))) {
            String generatedString = Encrypt.generateCode();

            request.getSession().setAttribute("waitedCodePassword", generatedString);

            SendEmail.forChangePasswordCode(user, generatedString);

            return PathFactory.getPath("path.page.redirect.change_password");
        }


        String passwordRepeat = request.getParameter(Constants.PASSWORD_REPEAT);
        if (ParameterValidation.validatePasswordAndRepeat(request, password, passwordRepeat)) {
            code = code.trim();
            if (code.equals(request.getSession().getAttribute("waitedCodePassword"))) {

                UserService.changePassword(user, password);

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


}