package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.entity.iUserRepository;
import com.myCompany.RepairAgency.model.entity.DTO.UserDTOFactory;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
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
        iUserRepository userRepository = ModelManager.getInstance().getUserRepository();
        Path page = PathFactory.getPath("path.page.forward.users");
        request.setAttribute("title", "title.users");

        long roleId = initRoleId(request);
        setListOfRolesUsers(request);

        long numberOfUsers = userRepository.countWhereRoleIs(roleId);
        int[] a = ForTables.initSkipQuantity("Users", numberOfUsers, request);
        int skip = a[0];
        int quantity = a[1];
        request.setAttribute("users", UserDTOFactory.getUsers(userRepository.getByRole(roleId, skip, quantity)));

        return page;
    }

    private long initRoleId(HttpServletRequest request) {
        if (request.getSession().getAttribute("roleUsers") != null) {
            Constants.ROLE role = (Constants.ROLE) request.getSession().getAttribute("roleUsers");
            return role.ordinal();
        } else {
            if (request.getSession().getAttribute("userRole").equals(Constants.ROLE.Admin)) {
                return 0;
            } else {
                request.getSession().setAttribute("roleUsers", Constants.ROLE.Client);
                return Constants.ROLE.Client.ordinal();
            }
        }
    }

    private void setListOfRolesUsers(HttpServletRequest request) {
        ArrayList<Constants.ROLE> rolesUsers = new ArrayList<>(List.of(Constants.ROLE.values()));
        rolesUsers.remove(0);
        if(!request.getSession().getAttribute("userRole").equals(Constants.ROLE.Admin)){
            rolesUsers.remove(0);
            rolesUsers.remove(0);
        }
        request.setAttribute("rolesUsers", rolesUsers);
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Manager, Constants.ROLE.Admin).collect(Collectors.toList());
    }
}