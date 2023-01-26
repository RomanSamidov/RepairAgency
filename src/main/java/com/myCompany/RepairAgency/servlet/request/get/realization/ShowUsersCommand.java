package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.entity.DTO.UserDTOFactory;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.service.InitValuesFromRequestService;
import com.myCompany.RepairAgency.servlet.service.UserService;
import com.myCompany.RepairAgency.servlet.service.ViewValidationService;
import com.myCompany.RepairAgency.servlet.util.ForTables;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShowUsersCommand implements IActionCommand, IHasRoleRequirement {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("title", "title.users");

        long roleId = InitValuesFromRequestService.initRoleId(request);
        setListOfRolesUsers(request);

        long numberOfUsers = UserService.countWhereRoleIs(roleId);
        int[] a = ForTables.initSkipQuantity("Users", numberOfUsers, request);
        int skip = a[0];
        int quantity = a[1];
        request.setAttribute("users", UserDTOFactory.getUsers(UserService.getByRole(roleId, skip, quantity)));
        if (numberOfUsers == 0) {
            request.setAttribute("error", "text.there_are_no_users");
        }
        return ViewValidationService.validateForUsersPage(request);
    }


    private void setListOfRolesUsers(HttpServletRequest request) {
        ArrayList<Constants.ROLE> rolesUsers = new ArrayList<>(List.of(Constants.ROLE.values()));
        rolesUsers.remove(0);
        if (!request.getSession().getAttribute("userRole").equals(Constants.ROLE.Admin)) {
            rolesUsers.remove(0);
            rolesUsers.remove(0);
        }
        request.setAttribute("rolesUsers", rolesUsers);
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Manager,
                Constants.ROLE.Admin).collect(Collectors.toList());
    }
}