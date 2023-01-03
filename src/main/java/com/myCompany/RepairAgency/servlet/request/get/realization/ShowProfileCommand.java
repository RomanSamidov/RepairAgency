package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShowProfileCommand implements IActionCommand, IHasRoleRequirement {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        Path page = PathFactory.getPath("path.page.forward.cabinet");
        request.setAttribute("title", "title.cabinet");
        return page;
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Client,
                Constants.ROLE.Admin,
                Constants.ROLE.Manager,
                Constants.ROLE.Craftsman).collect(Collectors.toList());
    }
}
