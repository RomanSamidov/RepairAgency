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

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShowUsersCommand implements IActionCommand, IHasRoleRequirement {
    @Override
    public Path execute(HttpServletRequest request) {
        iUserRepository userRepository = ModelManager.ins.getUserRepository();
        Path page = PathFactory.getPath("path.page.forward.users");
        request.setAttribute("title", "title.users");

        int[] a = ForTables.initSkipQuantity("Users", request);
        int skip = a[0];
        int quantity = a[1];
        if (request.getSession().getAttribute("userRole").equals(Constants.ROLE.Admin)) {
            ForTables.updatePagesForJSP(quantity, skip, userRepository.getCount(), "Users", request);
            request.setAttribute("users", UserDTOFactory.getUsers(userRepository.getWithPagination(skip, quantity)));
        } else {
            long roleId = Constants.ROLE.Client.ordinal();
            if (request.getSession().getAttribute("roleUsers") != null) {
                Constants.ROLE role = (Constants.ROLE) request.getSession().getAttribute("roleUsers");
                roleId = role.ordinal();
            }
            ForTables.updatePagesForJSP(quantity, skip, userRepository.countWhereRoleIs(roleId), "Users", request);
            request.setAttribute("users", UserDTOFactory.getUsers(userRepository.getByRole(roleId, skip, quantity)));
        }

        return page;
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Manager, Constants.ROLE.Admin).collect(Collectors.toList());
    }
}