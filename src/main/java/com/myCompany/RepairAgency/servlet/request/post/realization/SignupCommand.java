package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.ActionCommand;
import com.myCompany.RepairAgency.servlet.util.Encrypt;
import jakarta.servlet.http.HttpServletRequest;

public class SignupCommand implements ActionCommand {
    @Override
    public Path execute(HttpServletRequest request) {
        Path page;
// извлечение из запроса логина и пароля
        String login = request.getParameter(Constants.LOGIN);
        String password = request.getParameter(Constants.PASSWORD);
        String passwordRepeat = request.getParameter(Constants.PASSWORD_REPEAT);
// проверка логина и пароля
        boolean haveError = false;
        if (password == null || password.isEmpty()) {
            request.getSession().setAttribute("errorEmptyPassword","message.emptypassword");
            haveError = true;
        }
        if (passwordRepeat == null || passwordRepeat.isEmpty()) {
            request.getSession().setAttribute("errorEmptyPasswordRepeat","message.emptypasswordrepeat");
            haveError = true;
        }
        if (login == null || login.isEmpty()) {
            request.getSession().setAttribute("errorEmptyLogin","message.emptylogin");
            haveError = true;
        }

        if(!haveError) {
            try {
                if (ModelManager.ins.getUser(login) != null) {
                    request.getSession().setAttribute("errorLoginPassMessage","message.loginexist");
                    page = PathFactory.getPath("path.page.redirect.signup");
                    return  page;
                }
                String userPassword = Encrypt.encrypt(password);
                User user = new User.UserBuilder().setLogin(login)
                        .setPassword(userPassword)
                        .setRole_id(Constants.ROLE.Client.ordinal())
                        .build();
                ModelManager.ins.insertUser(user);
                request.getSession().setAttribute("user", login);
                request.getSession().setAttribute("userRole", Constants.ROLE.Client);
                page = PathFactory.getPath("path.page.redirect.cabinet");
                return page;
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("sql errror SignupCommand");
            }
        }

        page = PathFactory.getPath("path.page.redirect.signup");
        return page;
    }
}