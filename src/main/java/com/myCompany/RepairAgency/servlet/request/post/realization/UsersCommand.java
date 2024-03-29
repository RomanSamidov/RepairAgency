package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.util.ForTables;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class UsersCommand implements IActionCommand, IHasRoleRequirement {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        ForTables.updateSkipQuantity("Users", request);
        String roleUsers = request.getParameter("roleUsers");
        if (roleUsers != null) {
            updateRoleUsers(request, roleUsers);
        }
        return PathFactory.getPath("path.page.redirect.users");
    }

    private void updateRoleUsers(HttpServletRequest request, String roleUsers) {
        if (roleUsers.equals("0")) {
            request.getSession().removeAttribute("roleUsers");
        } else {
            Constants.ROLE role;
            try {
                role = Constants.ROLE.valueOf(roleUsers);
            } catch (IllegalArgumentException e) {
                role = Constants.ROLE.Client;
            }
            if (request.getSession().getAttribute("userRole").equals(Constants.ROLE.Manager) &&
                    (role != Constants.ROLE.Client && role != Constants.ROLE.Craftsman))
                role = Constants.ROLE.Client;

            request.getSession().setAttribute("roleUsers", role);
        }
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Manager, Constants.ROLE.Admin).collect(Collectors.toList());
    }
}