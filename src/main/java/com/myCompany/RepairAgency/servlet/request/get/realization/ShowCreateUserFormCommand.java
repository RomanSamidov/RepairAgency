package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.util.AttributeFSTR;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShowCreateUserFormCommand implements IActionCommand, IHasRoleRequirement {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("title", "title.create_user");

        AttributeFSTR.forShowCreateUserForm(request);

        ArrayList<Constants.ROLE> roles = new ArrayList<>(List.of(Constants.ROLE.values()));
        roles.remove(0);
        request.setAttribute("roles", roles);

        return PathFactory.getPath("path.page.forward.admin.create_user");
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(
                Constants.ROLE.Admin).collect(Collectors.toList());
    }
}