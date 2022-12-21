package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.ActionCommand;
import com.myCompany.RepairAgency.servlet.util.Encrypt;
import jakarta.servlet.http.HttpServletRequest;

public class LoginCommand implements ActionCommand {


    @Override
    public Path execute(HttpServletRequest request) {
        Path page;
// извлечение из запроса логина и пароля
        String login = request.getParameter(Constants.LOGIN);
        String password = request.getParameter(Constants.PASSWORD);
// проверка логина и пароля
        boolean haveError = false;
        if (password == null || password.isEmpty()) {
            request.getSession().setAttribute("errorEmptyPassword","message.emptypassword");
            haveError = true;
        }
        if (login == null || login.isEmpty()) {
            request.getSession().setAttribute("errorEmptyLogin","message.emptylogin");
            haveError = true;
        }

        if(!haveError) {
            try {
                User user = ModelManager.ins.getUser(login);
                String userPassword = user.getPassword();
                if (userPassword.equals(Encrypt.encrypt(password))) {
                    request.getSession().setAttribute("user", login);
                    request.getSession().setAttribute("userRole", Constants.ROLE.values()[user.getRole_id()]);
                    page = PathFactory.getPath("path.page.redirect.cabinet");
                    return page;
                } else {
                    request.getSession().setAttribute("errorLoginPassMessage","message.loginerror");
                }
            } catch (Exception e) {
                request.getSession().setAttribute("errorLoginPassMessage","message.loginnotexisterror");
                System.out.println(e);
                System.out.println("sql errror LoginCommand");
            }
        }


        page = PathFactory.getPath("path.page.redirect.login");
        return page;
    }



}