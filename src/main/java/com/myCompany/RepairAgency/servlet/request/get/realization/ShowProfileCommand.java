package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShowProfileCommand implements IActionCommand, IHasRoleRequirement {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("title", "title.cabinet");
        User user = ModelManager.getInstance().getUserRepository().getById(
                (Long)request.getSession().getAttribute("userId"));
        if (user.getRole_id() == Constants.ROLE.Client.ordinal()) {
            request.setAttribute("userAccount", user.getAccount());
        }
            request.setAttribute("isUserAllowLetters",user.isAllow_letters());
        request.setAttribute("confirmationCodeError"
                , request.getSession().getAttribute("confirmationCodeError"));
        request.getSession().removeAttribute("confirmationCodeError");

        HttpSession session = request.getSession();
        Constants.ROLE userRole = (Constants.ROLE) session.getAttribute("userRole");
        Path page = switch (userRole) {
            case Guest -> null;
            case Admin -> PathFactory.getPath("path.page.forward.admin.cabinet");
            case Manager -> PathFactory.getPath("path.page.forward.manager.cabinet");
            case Craftsman -> PathFactory.getPath("path.page.forward.craftsman.cabinet");
            case Client -> PathFactory.getPath("path.page.forward.client.cabinet");
        };

        if((boolean)session.getAttribute("isUserConfirmed")){
            request.setAttribute("_email_confirmed_url", PathFactory.getPath("path.page.cabinet.part.email_confirmed").toString());
        } else if(session.getAttribute("waitedCode") != null){
            request.setAttribute("_email_confirmed_url", PathFactory.getPath("path.page.cabinet.part.email_not_confirmed_with_code").toString());
        } else {
            request.setAttribute("_email_confirmed_url", PathFactory.getPath("path.page.cabinet.part.email_not_confirmed_without_code").toString());
        }


        return page;
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Client,
                Constants.ROLE.Admin,
                Constants.ROLE.Manager,
                Constants.ROLE.Craftsman).collect(Collectors.toList());
    }
}
