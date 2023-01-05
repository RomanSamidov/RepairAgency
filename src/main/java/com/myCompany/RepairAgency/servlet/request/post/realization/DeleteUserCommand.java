package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeleteUserCommand implements IActionCommand, IHasRoleRequirement {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {

        long userId = Long.parseLong(request.getParameter("goalIdUser"));

        ModelManager.ins.getUserRepository().delete(
                ModelManager.ins.getUserRepository().getById(userId)
        );

        return PathFactory.getPath("path.page.redirect.users");
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Admin).collect(Collectors.toList());
    }
}