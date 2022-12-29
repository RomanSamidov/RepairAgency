package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.entity.DTO.UserDTOFactory;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShowUserCommand implements IActionCommand, IHasRoleRequirement {
    @Override
    public Path execute(HttpServletRequest request) {
        Path page = PathFactory.getPath("path.page.forward.user");
        request.setAttribute("title", "title.user");
//        long userId = ForChangeEntity.initGoalId("User", request);
        long userId = Long.parseLong(request.getParameter("id"));
        if(userId == 0) {userId = (long) request.getSession().getAttribute("userId");}
        request.setAttribute("goalUser", UserDTOFactory.getUser(ModelManager.ins.getUser(userId)));
        return page;
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Manager, Constants.ROLE.Admin).collect(Collectors.toList());
    }
}