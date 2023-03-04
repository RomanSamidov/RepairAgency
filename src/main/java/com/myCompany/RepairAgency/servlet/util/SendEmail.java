package com.myCompany.RepairAgency.servlet.util;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * class contains code for all scenarios in which need to send email to user.
 */
public class SendEmail {
    private static final Logger logger = LogManager.getLogger(SendEmail.class);


    public static void forDownload(HttpServletRequest request, String filename) {
        User user = UserService.get((Long)
                request.getSession().getAttribute("userId"));
        if (user.isAllow_letters()) {
            Constants.LOCALE locale = Constants.LOCALE.values()[user.getLocale_id()];
            EmailSender.send(user.getEmail(),
                    user.getLogin() + "  " + locale.getString("text.your_report"),
                    locale.getString("text.here_is_your_report"),
                    new File(filename));
            logger.debug("Send email with file " + filename);
        }
    }


    public static void forAddToUserAccount(User user, int increment) {
        if (user.isAllow_letters()) {
            Constants.LOCALE locale = Constants.LOCALE.values()[user.getLocale_id()];
            EmailSender.send(user.getEmail(),
                    user.getLogin() + "  " + locale.getString("text.your_account_changed"),
                    locale.getString("text.your_account_changed_with") + increment);
            logger.debug("Send email about account change ");
        }
    }


    public static void forCreateOrder(User user, long orderId) {
        if (user.isAllow_letters()) {
            Constants.LOCALE locale = Constants.LOCALE.values()[user.getLocale_id()];
            EmailSender.send(user.getEmail(),
                    user.getLogin() + "  " + locale.getString("text.your_order_created"),
                    locale.getString("text.your_created_with_id") + orderId);
            logger.debug("Send email about order creation");
        }
    }

    public static void forCancelOrder(User user, int increment) {
        if (user.isAllow_letters()) {
            Constants.LOCALE locale = Constants.LOCALE.values()[user.getLocale_id()];
            EmailSender.send(user.getEmail(),
                    user.getLogin() + "  " + locale.getString("text.your_order_was_cancelled"),
                    locale.getString("text.your_order_was_cancelled_and_return") + increment);
            logger.debug("Send email about order cancellation ");
        }
    }

    public static void forCancelOrder(User user) {
        if (user.isAllow_letters()) {
            Constants.LOCALE locale = Constants.LOCALE.values()[user.getLocale_id()];
            EmailSender.send(user.getEmail(),
                    user.getLogin() + "  " + locale.getString("text.your_order_was_cancelled"),
                    locale.getString("text.your_order_was_cancelled"));
            logger.debug("Send email about order cancellation ");
        }
    }


    public static void forChangeEmail(User user) {
        if (user.isAllow_letters()) {
            Constants.LOCALE locale = Constants.LOCALE.values()[user.getLocale_id()];
            EmailSender.send(user.getEmail(),
                    user.getLogin() + "  " + locale.getString("text.your_email_changed"),
                    locale.getString("text.your_email_changed"));
            logger.debug("Send email about email change");
        }
    }

    public static void forConfirmEmail(User user) {
        if (user.isAllow_letters()) {
            Constants.LOCALE locale = Constants.LOCALE.values()[user.getLocale_id()];
            EmailSender.send(user.getEmail(),
                    user.getLogin() + "  " + locale.getString("text.your_email_confirmed"),
                    locale.getString("text.your_email_confirmed"));
            logger.debug("Send email about email confirming");
        }
    }

    public static void forDeleteOrder(User user, long orderId) {
        if (user.isAllow_letters()) {
            Constants.LOCALE locale = Constants.LOCALE.values()[user.getLocale_id()];
            EmailSender.send(user.getEmail(),
                    user.getLogin() + "  " + locale.getString("text.your_order_deleted"),
                    locale.getString("text.your_order_deleted_id") + orderId);
            logger.debug("Send email order deletion");
        }
    }

    public static void forDeleteUser(User user) {
        if (user.isAllow_letters()) {
            Constants.LOCALE locale = Constants.LOCALE.values()[user.getLocale_id()];
            EmailSender.send(user.getEmail(),
                    user.getLogin() + "  " + locale.getString("text.your_profile_deleted"),
                    locale.getString("text.your_profile_deleted"));
            logger.debug("Send email profile deletion");
        }
    }


    public static void forChangePassword(User user) {
        Constants.LOCALE locale = Constants.LOCALE.values()[user.getLocale_id()];
        EmailSender.send(user.getEmail(),
                user.getLogin() + "  " + locale.getString("text.your_password_changed"),
                locale.getString("text.your_password_changed"));
        logger.debug("Send email about password changing");
    }


    public static void forChangePasswordCode(User user, String code) {
        Constants.LOCALE locale = Constants.LOCALE.values()[user.getLocale_id()];
        EmailSender.send(user.getEmail(),
                user.getLogin() + "  " + locale.getString("text.confirm_your_password_change")
                , locale.getString("text.confirm_your_password_change_code") + code);
    }

    public static void forConfirmEmailCode(User user, String code) {
        Constants.LOCALE locale = Constants.LOCALE.values()[user.getLocale_id()];
        EmailSender.send(user.getEmail(),
                user.getLogin() + "  " + locale.getString("text.confirm_your_email")
                , locale.getString("text.confirm_your_email_enter_code") + code);
    }


    public static void forSignup(User user) {
        if (user.isAllow_letters()) {
            Constants.LOCALE locale = Constants.LOCALE.values()[user.getLocale_id()];
            EmailSender.send(user.getEmail(),
                    user.getLogin() + "  " + locale.getString("text.you_signup"),
                    locale.getString("text.you_have_signup"));
            logger.debug("Send email about successful registration");
        }
    }


    public static void forLogin(User user) {
        if (user.isAllow_letters()) {
            Constants.LOCALE locale = Constants.LOCALE.values()[user.getLocale_id()];
            EmailSender.send(user.getEmail(),
                    user.getLogin() + "  " + locale.getString("text.successful_login_to_your_profile"),
                    locale.getString("text.someone_login_to_your_profile"));
            logger.debug("Successful login");
        }
    }

    public static void forPayForOrder(User user, long orderId) {
        if (user.isAllow_letters()) {
            Constants.LOCALE locale = Constants.LOCALE.values()[user.getLocale_id()];
            EmailSender.send(user.getEmail(),
                    user.getLogin() + "  " + locale.getString("text.successful_pay_order"),
                    locale.getString("text.successful_pay_order_with_id") + orderId);
            logger.debug("Send email about successful paid");
        }
    }


    public static void forSetCraftPrice(User user, long orderId) {
        if (user.isAllow_letters()) {
            Constants.LOCALE locale = Constants.LOCALE.values()[user.getLocale_id()];
            EmailSender.send(user.getEmail(),
                    user.getLogin() + "  " + locale.getString("text.your_order_updated"),
                    locale.getString("text.order_now_has_price") + orderId);
            logger.debug("Send email about successful paid");
        }
    }

    public static void forSetOrderStatus(User user, long orderId) {
        if (user.isAllow_letters()) {
            Constants.LOCALE locale = Constants.LOCALE.values()[user.getLocale_id()];
            EmailSender.send(user.getEmail(),
                    user.getLogin() + "  " + locale.getString("text.your_order_updated"),
                    locale.getString("text.order_now_has_another_status") + orderId);
            logger.debug("Send email about successful paid");
        }
    }
}
