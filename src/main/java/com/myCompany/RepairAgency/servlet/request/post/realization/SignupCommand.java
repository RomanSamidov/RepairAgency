package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.ConfigurationManager;
import com.myCompany.RepairAgency.servlet.MessageManager;
import com.myCompany.RepairAgency.servlet.request.ActionCommand;
import com.myCompany.RepairAgency.servlet.util.Encrypt;
import jakarta.servlet.http.HttpServletRequest;

public class SignupCommand implements ActionCommand {
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
                if (ModelManager.ins.getUser(login) != null) {
                    request.setAttribute("errorLoginPassMessage",
                            MessageManager.getProperty("message.loginexist"));
                    page = ConfigurationManager.getProperty("path.page.signup");
                    request.setAttribute("title", "Signup");
                    return page;
                }
                String userPassword = Encrypt.encrypt(password);
                User user = new User.UserBuilder().setLogin(login)
                        .setPassword(userPassword)
                        .setRole_id(Constants.ROLE.Client.ordinal())
                        .build();
                ModelManager.ins.insertUser(user);
                request.setAttribute("user", login);
                request.getSession().setAttribute("userRole", Constants.ROLE.Client);
                page = ConfigurationManager.getProperty("path.page.cabinet");
                request.setAttribute("title", "cabinet");
                return page;
            } catch (Exception e) {
                System.out.println(e.toString());
                System.out.println("sql errror SignupCommand");
            }
        }

        page = ConfigurationManager.getProperty("path.page.signup");
        request.setAttribute("title", "Login");
        return page;
    }
}