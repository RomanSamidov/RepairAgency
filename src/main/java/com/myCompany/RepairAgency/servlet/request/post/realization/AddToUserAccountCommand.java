package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.util.EmailSender;
import com.myCompany.RepairAgency.servlet.util.ForChangeEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class AddToUserAccountCommand implements IActionCommand, IHasRoleRequirement {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        ForChangeEntity.updateGoalId("User", request);
        if (request.getParameter("addToAccount") != null) {
            int increment = Integer.parseInt(request.getParameter("addToAccount"));
            User user = ModelManager.ins.getUserRepository().getById(ForChangeEntity.initGoalId("User", request));
            user.setAccount(user.getAccount() + increment);
            ModelManager.ins.getUserRepository().update(user);
            EmailSender.send(user.getEmail(),
                    "Your account was updated.",
                    "To your account was added " + increment);
        }
        Path path = PathFactory.getPath("path.page.redirect.user");
        path.addParameter("id", request.getParameter("goalIdUser"));
        return path;
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Manager, Constants.ROLE.Admin).collect(Collectors.toList());
    }
}