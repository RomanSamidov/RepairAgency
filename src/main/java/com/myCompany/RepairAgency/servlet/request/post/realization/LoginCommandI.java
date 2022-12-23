package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.util.Encrypt;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LoginCommandI implements IActionCommand, IHasRoleRequirement {
    private static final Logger logger = LogManager.getLogger(LoginCommandI.class);

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
                    initializeUserSessionAttributes(request, user);
                    page = PathFactory.getPath("path.page.redirect.cabinet");
                    return page;
                } else {
                    request.getSession().setAttribute("errorLoginPassMessage","message.loginerror");
                }
            } catch (Exception e) {
                request.getSession().setAttribute("errorLoginPassMessage","message.loginnotexisterror");
                logger.error("[LoginCommandI] sql error  " + e);
            }
        }


        page = PathFactory.getPath("path.page.redirect.login");
        return page;
    }

    public static void initializeUserSessionAttributes(HttpServletRequest request, User user ) {
        request.getSession().setAttribute("userLogin", user.getLogin());
        request.getSession().setAttribute("userRole", Constants.ROLE.values()[user.getRole_id()]);
        if(user.getRole_id() == Constants.ROLE.Client.ordinal()) {
            request.getSession().setAttribute("userAccount", user.getAccount());
        }
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Guest).collect(Collectors.toList());
    }

}