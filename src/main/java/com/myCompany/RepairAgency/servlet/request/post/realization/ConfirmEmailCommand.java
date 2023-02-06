package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.service.UserService;
import com.myCompany.RepairAgency.servlet.util.Encrypt;
import com.myCompany.RepairAgency.servlet.util.SendEmail;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConfirmEmailCommand implements IActionCommand, IHasRoleRequirement {

    private static final Logger logger = LogManager.getLogger(ConfirmEmailCommand.class);

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("confirmationCode");
        HttpSession session = request.getSession();
        User user = UserService.get((Long) request.getSession().getAttribute("userId"));

        if (session.getAttribute("waitedCode") == null ||
                Boolean.parseBoolean(request.getParameter("sendCodeAgain"))) {
            String generatedString = Encrypt.generateCode();
            session.setAttribute("waitedCode", generatedString);

            SendEmail.forConfirmEmailCode(user, generatedString);

            return PathFactory.getPath("path.page.redirect.cabinet");
        }

        code = code.trim();
        if (code.equals(session.getAttribute("waitedCode"))) {
            user.setConfirmed(true);
            UserService.update(user);
            session.setAttribute("isUserConfirmed", user.isConfirmed());
            session.removeAttribute("waitedCode");
            SendEmail.forConfirmEmail(user);
        } else {
            session.setAttribute("confirmationCodeError", "text.wrong_code");
        }

        return PathFactory.getPath("path.page.redirect.cabinet");
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Client,
                Constants.ROLE.Admin,
                Constants.ROLE.Manager,
                Constants.ROLE.Craftsman).collect(Collectors.toList());
    }


}