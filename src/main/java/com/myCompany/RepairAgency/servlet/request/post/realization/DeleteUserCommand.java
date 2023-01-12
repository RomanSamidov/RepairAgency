package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.util.EmailSender;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeleteUserCommand implements IActionCommand, IHasRoleRequirement {
    private static final Logger logger = LogManager.getLogger(DeleteUserCommand.class);
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {

        long userId = Long.parseLong(request.getParameter("goalIdUser"));
        User user = ModelManager.getInstance().getUserRepository().getById(userId);
        ModelManager.getInstance().getUserRepository().delete(user);
        ifNeedSendEmail(user);
        logger.debug("User profile deleted");
        return PathFactory.getPath("path.page.redirect.users");
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Admin).collect(Collectors.toList());
    }

    private void ifNeedSendEmail(User user){
        if(user.isAllow_letters()){
            Constants.LOCALE locale = Constants.LOCALE.values()[user.getLocale_id()];
            EmailSender.send(user.getEmail(),
                    user.getLogin() + "  " + locale.getString("text.your_profile_deleted"),
                    locale.getString("text.your_profile_deleted"));
            logger.debug("Send email profile deletion");
        }
    }

}