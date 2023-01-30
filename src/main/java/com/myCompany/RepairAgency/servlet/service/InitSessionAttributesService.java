package com.myCompany.RepairAgency.servlet.service;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class InitSessionAttributesService {

    public static void initUserSessionAttributes(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute("language", Constants.LOCALE.values()[user.getLocale_id()].toString());
        session.setAttribute("userLogin", user.getLogin());
        session.setAttribute("userId", user.getId());
        session.setAttribute("userEmail", user.getEmail());
        session.setAttribute("isUserConfirmed", user.isConfirmed());

        Constants.ROLE userRole = Constants.ROLE.values()[user.getRole_id()];
        session.setAttribute("userRole", userRole);

        ViewValidationService.setMenu(request);
    }

}
