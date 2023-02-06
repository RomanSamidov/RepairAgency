package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.service.UserService;
import com.myCompany.RepairAgency.servlet.util.AttributeFSTR;
import com.myCompany.RepairAgency.servlet.util.ViewValidation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShowProfileCommand implements IActionCommand, IHasRoleRequirement {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("title", "title.cabinet");

        User user = UserService.get(
                (Long) request.getSession().getAttribute("userId"));

        if (user.getRole_id() == Constants.ROLE.Client.ordinal()) {
            request.setAttribute("userAccount", user.getAccount());
        }

        request.setAttribute("isUserAllowLetters", user.isAllow_letters());

        AttributeFSTR.forShowProfile(request);

        return ViewValidation.validateForProfilePage(request);
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Client,
                Constants.ROLE.Admin,
                Constants.ROLE.Craftsman,
                Constants.ROLE.Manager).collect(Collectors.toList());
    }
}
