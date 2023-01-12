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
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConfirmEmailCommand implements IActionCommand, IHasRoleRequirement {

    private static final Logger logger = LogManager.getLogger(ConfirmEmailCommand.class);

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("confirmationCode");
        User user = ModelManager.getInstance().getUserRepository().getById((Long) request.getSession().getAttribute("userId"));

        if(request.getSession().getAttribute("waitedCode") == null || Boolean.parseBoolean(request.getParameter("sendCodeAgain"))) {
            int leftLimit = 97; // letter 'a'
            int rightLimit = 122; // letter 'z'
            int targetStringLength = 10;
            Random random = new Random();

            String generatedString = random.ints(leftLimit, rightLimit + 1)
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            request.getSession().setAttribute("waitedCode", generatedString);

            Constants.LOCALE locale = Constants.LOCALE.values()[user.getLocale_id()];
            EmailSender.send(user.getEmail(),
                    user.getLogin() + "  " + locale.getString("text.confirm_your_email")
                    ,locale.getString("text.confirm_your_email_enter_code") + generatedString );
            return PathFactory.getPath("path.page.redirect.cabinet");
        }

        code = code.trim();
        if(code.equals(request.getSession().getAttribute("waitedCode"))){
            user.setConfirmed(true);
            ModelManager.getInstance().getUserRepository().update(user);
            request.getSession().setAttribute("isUserConfirmed", user.isConfirmed());
            request.getSession().removeAttribute("waitedCode");
            ifNeedSendEmail(user);
            request.getSession().removeAttribute("waitedCode");
        } else {
            request.getSession().setAttribute("confirmationCodeError", "text.wrong_code");
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

    private void ifNeedSendEmail(User user){
        if(user.isAllow_letters()){
            Constants.LOCALE locale = Constants.LOCALE.values()[user.getLocale_id()];
            EmailSender.send(user.getEmail(),
                    user.getLogin() + "  " + locale.getString("text.your_email_confirmed"),
                    locale.getString("text.your_email_confirmed"));
            logger.debug("Send email about email confirming");
        }
    }
}