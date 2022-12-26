package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.entity.DTO.UserDTOFactory;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.util.ForTables;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShowUsersCommand implements IActionCommand, IHasRoleRequirement {
    @Override
    public Path execute(HttpServletRequest request) {
        Path page = PathFactory.getPath("path.page.forward.users");
        request.setAttribute("title", "title.users");
        long roleId = Constants.ROLE.Client.ordinal();
        int[] a = ForTables.initSkipQuantity( "Users", request);
        int skip = a[0];
        int quantity = a[1];
        ForTables.updatePagesForJSP(quantity, skip, ModelManager.ins.getCountUsersWhereRoleIs(roleId), "Users", request);
        request.setAttribute("users", UserDTOFactory.getUsers(ModelManager.ins.getAllUsersByRole(roleId, skip, quantity)));
        return page;
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Manager, Constants.ROLE.Admin).collect(Collectors.toList());
    }
}