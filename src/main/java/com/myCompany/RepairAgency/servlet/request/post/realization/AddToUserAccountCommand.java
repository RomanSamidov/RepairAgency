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


public class AddToUserAccountCommand implements IActionCommand, IHasRoleRequirement {
    private static final Logger logger = LogManager.getLogger(AddToUserAccountCommand.class);

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("addToAccount") != null) {
            int increment = Integer.parseInt(request.getParameter("addToAccount"));
            User user = ModelManager.ins.getUserRepository()
                    .getById(Long.parseLong(request.getParameter("goalIdUser")));
            user.setAccount(user.getAccount() + increment);
            ModelManager.ins.getUserRepository().update(user);
            ifNeedSendEmail(user, increment);
            logger.debug("User account was changed ");
        }
        Path path = PathFactory.getPath("path.page.redirect.user");
        path.addParameter("id", request.getParameter("goalIdUser"));
        return path;
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Manager, Constants.ROLE.Admin).collect(Collectors.toList());
    }

    private void ifNeedSendEmail(User user, int increment){
        if(user.isAllow_letters()){
            Constants.LOCALE locale = Constants.LOCALE.values()[user.getLocale_id()];
            EmailSender.send(user.getEmail(),
                    user.getLogin() + "  " + locale.getString("text.your_account_changed"),
                    locale.getString("text.your_account_changed_with")  + increment);
            logger.debug("Send email about account change ");
        }
    }
}