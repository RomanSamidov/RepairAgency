package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.entity.DTO.UserDTOFactory;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShowUserCommand implements IActionCommand, IHasRoleRequirement {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        Path page = PathFactory.getPath("path.page.forward.user");
        request.setAttribute("title", "title.user");

        long userId = initUserId(request);

        User user =  ModelManager.ins.getUserRepository().getById(userId);
        if(user == null) return PathFactory.getPath("path.page.redirect.users");

        request.setAttribute("goalUser", UserDTOFactory.getUser(user));
        return page;
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Manager, Constants.ROLE.Admin).collect(Collectors.toList());
    }

    private long initUserId(HttpServletRequest request) {
        Constants.ROLE userRole = (Constants.ROLE) request.getSession().getAttribute("userRole");
        long userId = 0;
        if(!(userRole.equals(Constants.ROLE.Admin) || userRole.equals(Constants.ROLE.Manager))) {
            userId = (long) request.getSession().getAttribute("userId");
        } else {
            if(request.getParameter("id") != null) {
                userId = Long.parseLong(request.getParameter("id"));
            }
        }

        if (userId <= 0) {
            userId = (long) request.getSession().getAttribute("userId");
        }

        return userId;
    }

}