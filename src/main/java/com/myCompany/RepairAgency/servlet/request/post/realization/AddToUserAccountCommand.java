package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.service.ParameterValidationService;
import com.myCompany.RepairAgency.servlet.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class AddToUserAccountCommand implements IActionCommand, IHasRoleRequirement {
    private static final Logger logger = LogManager.getLogger(AddToUserAccountCommand.class);

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        String goalIdUser = request.getParameter("goalIdUser");
        if (!ParameterValidationService.validateGoalId(goalIdUser)) {
            return PathFactory.getPath("path.page.redirect.users");
        }

        String addToAccount = request.getParameter("addToAccount");
        if (ParameterValidationService.validateInt(addToAccount)) {
            int increment = Integer.parseInt(addToAccount);
            long userId = Long.parseLong(goalIdUser);

            if (UserService.addToUserAccount(userId, increment)) {
                logger.debug("User account was changed ");
            } else {
                logger.debug("User account was not changed ");
                return PathFactory.getPath("path.page.redirect.users");
            }

        }

        Path path = PathFactory.getPath("path.page.redirect.user");
        path.addParameter("id", goalIdUser);
        return path;
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Manager, Constants.ROLE.Admin).collect(Collectors.toList());
    }


}