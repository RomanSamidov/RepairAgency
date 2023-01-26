package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.service.ParameterValidationService;
import com.myCompany.RepairAgency.servlet.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChangeProfileSettingsCommand implements IActionCommand, IHasRoleRequirement {

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        User user = UserService.get((Long) request.getSession().getAttribute("userId"));

        String allowLetters = request.getParameter("newIsUserAllowLetters");

        if (!ParameterValidationService.validateBoolean(allowLetters))
            return PathFactory.getPath("path.page.redirect.cabinet");

        boolean isUserAllowLetters = Boolean.parseBoolean(allowLetters);
        user.setAllow_letters(isUserAllowLetters);
        UserService.update(user);
        request.getSession().setAttribute("isUserAllowLetters", user.isAllow_letters());

        return PathFactory.getPath("path.page.redirect.cabinet");
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Client,
                Constants.ROLE.Admin,
                Constants.ROLE.Manager,
                Constants.ROLE.Craftsman).collect(Collectors.toList());
    }

}