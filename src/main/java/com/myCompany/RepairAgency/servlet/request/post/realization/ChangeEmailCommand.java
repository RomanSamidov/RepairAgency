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

public class ChangeEmailCommand implements IActionCommand, IHasRoleRequirement {
    private static final Logger logger = LogManager.getLogger(ChangeEmailCommand.class);

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        User user = ModelManager.getInstance().getUserRepository().getById((Long) request.getSession().getAttribute("userId"));
        user.setConfirmed(false);
        String email = request.getParameter("email");
        user.setEmail(email);
        ModelManager.getInstance().getUserRepository().update(user);
        request.getSession().setAttribute("userEmail", user.getEmail());
        request.getSession().setAttribute("isUserConfirmed", user.isConfirmed());
        ifNeedSendEmail(user);
        logger.debug("User email was changed ");
        return PathFactory.getPath("path.page.redirect.cabinet");
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Client,
                Constants.ROLE.Admin,
                Constants.ROLE.Manager,
                Constants.ROLE.Craftsman).collect(Collectors.toList());
    }

    private void ifNeedSendEmail(User user){
        if(user.isAllow_letters()){
            Constants.LOCALE locale = Constants.LOCALE.values()[user.getLocale_id()];
            EmailSender.send(user.getEmail(),
                    user.getLogin() + "  " + locale.getString("text.your_email_changed"),
                    locale.getString("text.your_email_changed"));
            logger.debug("Send email about email change");
        }
    }

}