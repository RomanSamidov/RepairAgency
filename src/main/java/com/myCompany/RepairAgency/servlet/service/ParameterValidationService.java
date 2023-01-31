package com.myCompany.RepairAgency.servlet.service;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.db.abstractDB.exception.MyDBException;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.util.VerifyRecaptcha;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.regex.Pattern;

public class ParameterValidationService {

    public static boolean forAdminCreateOrder(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String clientId = request.getParameter("clientId");
        String text = request.getParameter("orderText");
        boolean somethingNULL = false;

        if (clientId == null || clientId.isBlank()) {
            session.setAttribute("errorOrderClientIdMessage", "text.client_id_empty");
            somethingNULL = true;
        }
        if (!validateOrderText(request, text)) {
            somethingNULL = true;
        }
        if (!validateGoalId(clientId)) {
            somethingNULL = true;
        }

        return !somethingNULL;
    }

    public static boolean forChangePassword(HttpServletRequest request, User user) {
        String email = request.getParameter(Constants.EMAIL);
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");

        boolean verify = validateRecaptcha(request, gRecaptchaResponse);

        if (validateEmail(request, email)) verify = true;

        if (user == null) {
            request.getSession().setAttribute("confirmationCodeError", "message.login_not_exist_error");
            verify = false;
        } else if (!user.getEmail().equals(email)) {
            request.getSession().setAttribute("confirmationCodeError", "message.wrong_email");
            verify = false;
        }
        return verify;
    }

    public static boolean validateOrderText(HttpServletRequest request, String text) {
        if (text == null || text.isBlank()) {
            request.getSession().setAttribute("errorOrderTextMessage", "text.order_empty");
            return false;
        }
        if (text.length() >= 255) {
            request.getSession().setAttribute("errorOrderTextMessage", "message.order_to_long");
            return false;
        }
        return true;
    }

    public static boolean validateEmail(HttpServletRequest request, String email) {
        if (email == null || email.isEmpty()) {
            request.getSession().setAttribute("errorEmptyEmail", "message.empty_email");
            return false;
        }

        if (email.length() >= 30) {
            request.getSession().setAttribute("errorEmptyEmail", "message.email_to_long");
            return false;
        }

        String regexPattern = "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$";
        if (!Pattern.compile(regexPattern).matcher(email).matches()) {
            request.getSession().setAttribute("errorEmptyEmail", "message.wrong_email_regex");
            return false;
        }
        return true;
    }


    public static boolean validateLogin(HttpServletRequest request, String login) {
        if (login == null || login.isEmpty()) {
            request.getSession().setAttribute("errorEmptyLogin", "message.empty_login");
            return false;
        }

        if (login.length() >= 30) {
            request.getSession().setAttribute("errorEmptyLogin", "message.login_to_long");
            return false;
        }

        return true;
    }


    public static boolean validateRecaptcha(HttpServletRequest request, String gRecaptchaResponse) {
        if (!VerifyRecaptcha.verify(gRecaptchaResponse)) {
            request.getSession().setAttribute("errorRecaptchaMessage", "message.error_recaptcha");
            return false;
        }
        return true;
    }

    public static boolean validatePassword(HttpServletRequest request, String password) {
        if (password == null || password.isEmpty()) {
            request.getSession().setAttribute("errorEmptyPassword", "message.empty_password");
            return false;
        }

        if (password.length() >= 63) {
            request.getSession().setAttribute("errorEmptyPassword", "message.password_to_long");
            return false;
        }

//        String regexPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])$";
//        if( !Pattern.compile(regexPattern).matcher(password).matches()) {
//            request.getSession().setAttribute("errorEmptyPassword", "message.wrong_password_regex");
//            return false;
//        }
        return true;
    }

    public static boolean validatePasswordAndRepeat(HttpServletRequest request, String password, String passwordRepeat) {
        if (!validatePassword(request, password)) return false;

        if (passwordRepeat == null || passwordRepeat.isEmpty()) {
            request.getSession().setAttribute("errorEmptyPasswordRepeat", "message.empty_repeat_password");
            return false;
        }

        if (!password.equals(passwordRepeat)) {
            request.getSession().setAttribute("errorPasswordsNotEqual", "message.not_equal_passwords");
            return false;
        }

        return true;
    }


    public static boolean validateRoleId(HttpServletRequest request, String roleId) {
        if (roleId.equals("0") || roleId.equals("1") || roleId.equals("2") || roleId.equals("3") || roleId.equals("4")) {
            return true;
        }
        request.getSession().setAttribute("errorRoleId", "message.wrong_role_id");
        return false;
    }

    public static boolean validateOrderStatusId(HttpServletRequest request, String roleId) {
        if (roleId.equals("1") || roleId.equals("2") || roleId.equals("3") || roleId.equals("4") || roleId.equals("5") || roleId.equals("6")) {
            return true;
        }
        request.getSession().setAttribute("errorOrderStatusId", "message.wrong_status_id");
        return false;
    }

    public static boolean validateGoalId(String goalId) {
        try {
            long id = Long.parseLong(goalId);
            if (id <= 0) return false;
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean validateInt(String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean validateCraftsmanId(String goalOrderCraftsmanId) {
        try {
            long id = Long.parseLong(goalOrderCraftsmanId);
            if (id <= 0) return false;
            if (UserService.get(id).getRole_id() != Constants.ROLE.Craftsman.ordinal()) return false;
        } catch (NumberFormatException | MyDBException | NullPointerException e) {
            return false;
        }
        return true;
    }

    public static boolean validateFeedbackText(HttpServletRequest request, String text) {
        if (text == null || text.isBlank()) {
            request.getSession().setAttribute("errorFeedbackTextMessage", "text.feedback_text_empty");
            return false;
        }
        if (text.length() >= 255) {
            request.getSession().setAttribute("errorFeedbackTextMessage", "message.feedback_to_long");
            return false;
        }
        return true;
    }
}
