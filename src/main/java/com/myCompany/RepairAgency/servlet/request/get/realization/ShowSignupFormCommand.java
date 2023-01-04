package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShowSignupFormCommand implements IActionCommand, IHasRoleRequirement {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        Path page = PathFactory.getPath("path.page.forward.signup");
        request.setAttribute("errorEmptyPassword",
                request.getSession().getAttribute("errorEmptyPassword"));
        request.setAttribute("errorLoginPassMessage",
                request.getSession().getAttribute("errorLoginPassMessage"));
        request.setAttribute("errorEmptyLogin",
                request.getSession().getAttribute("errorEmptyLogin"));
        request.setAttribute("errorEmptyPasswordRepeat",
                request.getSession().getAttribute("errorEmptyPasswordRepeat"));

        if ( request.getSession().getAttribute("userRole").equals(Constants.ROLE.Admin)) {
            ArrayList<Constants.ROLE> roles = new ArrayList<>(List.of(Constants.ROLE.values()));
            roles.remove(0);
            request.setAttribute("roles", roles);
        }

        request.getSession().removeAttribute("errorEmptyPassword");
        request.getSession().removeAttribute("errorLoginPassMessage");
        request.getSession().removeAttribute("errorEmptyLogin");
        request.getSession().removeAttribute("errorEmptyPasswordRepeat");
        request.setAttribute("title", "title.signup");
        return page;
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Guest,
                Constants.ROLE.Admin).collect(Collectors.toList());
    }
}