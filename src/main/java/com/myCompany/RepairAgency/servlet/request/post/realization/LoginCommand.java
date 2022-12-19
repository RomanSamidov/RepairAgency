package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.ConfigurationManager;
import com.myCompany.RepairAgency.servlet.util.Encrypt;
import com.myCompany.RepairAgency.servlet.MessageManager;
import com.myCompany.RepairAgency.servlet.request.ActionCommand;
import jakarta.servlet.http.HttpServletRequest;

public class LoginCommand implements ActionCommand {


    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
// извлечение из запроса логина и пароля
        String login = request.getParameter(Constants.LOGIN);
        String password = request.getParameter(Constants.PASSWORD);
// проверка логина и пароля
        boolean haveError = false;
        if (password == null || password.isEmpty()) {
            request.setAttribute("errorEmptyPassword",
                    MessageManager.getProperty("message.emptypassword"));
            haveError = true;
        }
        if (login == null || login.isEmpty()) {
            request.setAttribute("errorEmptyLogin",
                    MessageManager.getProperty("message.emptylogin"));
            haveError = true;
        }

        if(!haveError) {
            try {
                User user = ModelManager.ins.getUser(login);
                String userPassword = user.getPassword();
                if (userPassword.equals(Encrypt.encrypt(password))) {
                    request.setAttribute("user", login);
                    request.getSession().setAttribute("userRole", Constants.ROLE.values()[user.getRole_id()]);
                    page = ConfigurationManager.getProperty("path.page.cabinet");
                    request.setAttribute("title", "Cabinet");
                    return page;
                } else {
                    request.setAttribute("errorLoginPassMessage",
                            MessageManager.getProperty("message.loginerror"));
                    page = ConfigurationManager.getProperty("path.page.login");
                    request.setAttribute("title", "Login");
                }
            } catch (Exception e) {
                System.out.println(e.toString());
                System.out.println("sql errror LoginCommand");
            }
        }

        page = ConfigurationManager.getProperty("path.page.login");
        request.setAttribute("title", "Login");
        return page;
    }



}