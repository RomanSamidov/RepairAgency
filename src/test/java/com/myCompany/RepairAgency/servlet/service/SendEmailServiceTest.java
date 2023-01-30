package com.myCompany.RepairAgency.servlet.service;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.util.EmailSender;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;


class SendEmailServiceTest {



    @Test
    void forDownload() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpSession session = new MockHttpSession();
        request.setSession(session);
        session.setAttribute("userId", 0L);

        try (MockedStatic<UserService> ignored2 = Mockito.mockStatic(UserService.class);
             MockedStatic<EmailSender> ignored3 = Mockito.mockStatic(EmailSender.class);
             MockedStatic<Constants.LOCALE> ignored4 = Mockito.mockStatic(Constants.LOCALE.class)) {

            Mockito.when(Constants.LOCALE.values()).thenReturn(new Constants.LOCALE[]{Mockito.mock(Constants.LOCALE.class)});

            User user = Mockito.mock(User.class);
            Mockito.when(UserService.get(Mockito.anyLong())).thenReturn(user);
            Mockito.when(user.isAllow_letters()).thenReturn(true);
            Mockito.when(user.getLocale_id()).thenReturn(0);

            SendEmailService.forDownload(request, "boba");
        }

    }

    @Test
    void forAddToUserAccount() {
        try (MockedStatic<EmailSender> ignored3 = Mockito.mockStatic(EmailSender.class);
             MockedStatic<Constants.LOCALE> ignored4 = Mockito.mockStatic(Constants.LOCALE.class)) {

            Mockito.when(Constants.LOCALE.values()).thenReturn(new Constants.LOCALE[]{Mockito.mock(Constants.LOCALE.class)});
            User user = Mockito.mock(User.class);
            Mockito.when(user.isAllow_letters()).thenReturn(true);
            Mockito.when(user.getLocale_id()).thenReturn(0);

            SendEmailService.forAddToUserAccount(user, 10);
        }
    }

    @Test
    void forCreateOrder() {
        try (MockedStatic<EmailSender> ignored3 = Mockito.mockStatic(EmailSender.class);
             MockedStatic<Constants.LOCALE> ignored4 = Mockito.mockStatic(Constants.LOCALE.class)) {

            Mockito.when(Constants.LOCALE.values()).thenReturn(new Constants.LOCALE[]{Mockito.mock(Constants.LOCALE.class)});
            User user = Mockito.mock(User.class);
            Mockito.when(user.isAllow_letters()).thenReturn(true);
            Mockito.when(user.getLocale_id()).thenReturn(0);

            SendEmailService.forCreateOrder(user, 10);
        }
    }

    @Test
    void forCancelOrder() {
        try (MockedStatic<EmailSender> ignored3 = Mockito.mockStatic(EmailSender.class);
             MockedStatic<Constants.LOCALE> ignored4 = Mockito.mockStatic(Constants.LOCALE.class)) {

            Mockito.when(Constants.LOCALE.values()).thenReturn(new Constants.LOCALE[]{Mockito.mock(Constants.LOCALE.class)});
            User user = Mockito.mock(User.class);
            Mockito.when(user.isAllow_letters()).thenReturn(true);
            Mockito.when(user.getLocale_id()).thenReturn(0);

            SendEmailService.forCancelOrder(user, 10);
        }
    }

    @Test
    void testForCancelOrder() {
        try (MockedStatic<EmailSender> ignored3 = Mockito.mockStatic(EmailSender.class);
             MockedStatic<Constants.LOCALE> ignored4 = Mockito.mockStatic(Constants.LOCALE.class)) {

            Mockito.when(Constants.LOCALE.values()).thenReturn(new Constants.LOCALE[]{Mockito.mock(Constants.LOCALE.class)});
            User user = Mockito.mock(User.class);
            Mockito.when(user.isAllow_letters()).thenReturn(true);
            Mockito.when(user.getLocale_id()).thenReturn(0);

            SendEmailService.forCancelOrder(user);
        }
    }

    @Test
    void forChangeEmail() {
        try (MockedStatic<EmailSender> ignored3 = Mockito.mockStatic(EmailSender.class);
             MockedStatic<Constants.LOCALE> ignored4 = Mockito.mockStatic(Constants.LOCALE.class)) {

            Mockito.when(Constants.LOCALE.values()).thenReturn(new Constants.LOCALE[]{Mockito.mock(Constants.LOCALE.class)});
            User user = Mockito.mock(User.class);
            Mockito.when(user.isAllow_letters()).thenReturn(true);
            Mockito.when(user.getLocale_id()).thenReturn(0);

            SendEmailService.forChangeEmail(user);
        }
    }

    @Test
    void forConfirmEmail() {
        try (MockedStatic<EmailSender> ignored3 = Mockito.mockStatic(EmailSender.class);
             MockedStatic<Constants.LOCALE> ignored4 = Mockito.mockStatic(Constants.LOCALE.class)) {

            Mockito.when(Constants.LOCALE.values()).thenReturn(new Constants.LOCALE[]{Mockito.mock(Constants.LOCALE.class)});
            User user = Mockito.mock(User.class);
            Mockito.when(user.isAllow_letters()).thenReturn(true);
            Mockito.when(user.getLocale_id()).thenReturn(0);

            SendEmailService.forConfirmEmail(user);
        }
    }

    @Test
    void forDeleteOrder() {
        try (MockedStatic<EmailSender> ignored3 = Mockito.mockStatic(EmailSender.class);
             MockedStatic<Constants.LOCALE> ignored4 = Mockito.mockStatic(Constants.LOCALE.class)) {

            Mockito.when(Constants.LOCALE.values()).thenReturn(new Constants.LOCALE[]{Mockito.mock(Constants.LOCALE.class)});
            User user = Mockito.mock(User.class);
            Mockito.when(user.isAllow_letters()).thenReturn(true);
            Mockito.when(user.getLocale_id()).thenReturn(0);

            SendEmailService.forDeleteOrder(user, 10);
        }
    }

    @Test
    void forDeleteUser() {
        try (MockedStatic<EmailSender> ignored3 = Mockito.mockStatic(EmailSender.class);
             MockedStatic<Constants.LOCALE> ignored4 = Mockito.mockStatic(Constants.LOCALE.class)) {

            Mockito.when(Constants.LOCALE.values()).thenReturn(new Constants.LOCALE[]{Mockito.mock(Constants.LOCALE.class)});
            User user = Mockito.mock(User.class);
            Mockito.when(user.isAllow_letters()).thenReturn(true);
            Mockito.when(user.getLocale_id()).thenReturn(0);

            SendEmailService.forDeleteUser(user);
        }
    }

    @Test
    void forChangePassword() {
        try (MockedStatic<EmailSender> ignored3 = Mockito.mockStatic(EmailSender.class);
             MockedStatic<Constants.LOCALE> ignored4 = Mockito.mockStatic(Constants.LOCALE.class)) {

            Mockito.when(Constants.LOCALE.values()).thenReturn(new Constants.LOCALE[]{Mockito.mock(Constants.LOCALE.class)});
            User user = Mockito.mock(User.class);
            Mockito.when(user.isAllow_letters()).thenReturn(true);
            Mockito.when(user.getLocale_id()).thenReturn(0);

            SendEmailService.forChangePassword(user);
        }
    }

    @Test
    void forChangePasswordCode() {
        try (MockedStatic<EmailSender> ignored3 = Mockito.mockStatic(EmailSender.class);
             MockedStatic<Constants.LOCALE> ignored4 = Mockito.mockStatic(Constants.LOCALE.class)) {

            Mockito.when(Constants.LOCALE.values()).thenReturn(new Constants.LOCALE[]{Mockito.mock(Constants.LOCALE.class)});
            User user = Mockito.mock(User.class);
            Mockito.when(user.isAllow_letters()).thenReturn(true);
            Mockito.when(user.getLocale_id()).thenReturn(0);

            SendEmailService.forChangePasswordCode(user, "code");
        }
    }

    @Test
    void forConfirmEmailCode() {
        try (MockedStatic<EmailSender> ignored3 = Mockito.mockStatic(EmailSender.class);
             MockedStatic<Constants.LOCALE> ignored4 = Mockito.mockStatic(Constants.LOCALE.class)) {

            Mockito.when(Constants.LOCALE.values()).thenReturn(new Constants.LOCALE[]{Mockito.mock(Constants.LOCALE.class)});
            User user = Mockito.mock(User.class);
            Mockito.when(user.isAllow_letters()).thenReturn(true);
            Mockito.when(user.getLocale_id()).thenReturn(0);

            SendEmailService.forConfirmEmailCode(user," 10");
        }
    }

    @Test
    void forSignup() {
        try (MockedStatic<EmailSender> ignored3 = Mockito.mockStatic(EmailSender.class);
             MockedStatic<Constants.LOCALE> ignored4 = Mockito.mockStatic(Constants.LOCALE.class)) {

            Mockito.when(Constants.LOCALE.values()).thenReturn(new Constants.LOCALE[]{Mockito.mock(Constants.LOCALE.class)});
            User user = Mockito.mock(User.class);
            Mockito.when(user.isAllow_letters()).thenReturn(true);
            Mockito.when(user.getLocale_id()).thenReturn(0);

            SendEmailService.forSignup(user);
        }
    }

    @Test
    void forLogin() {
        try (MockedStatic<EmailSender> ignored3 = Mockito.mockStatic(EmailSender.class);
             MockedStatic<Constants.LOCALE> ignored4 = Mockito.mockStatic(Constants.LOCALE.class)) {

            Mockito.when(Constants.LOCALE.values()).thenReturn(new Constants.LOCALE[]{Mockito.mock(Constants.LOCALE.class)});
            User user = Mockito.mock(User.class);
            Mockito.when(user.isAllow_letters()).thenReturn(true);
            Mockito.when(user.getLocale_id()).thenReturn(0);

            SendEmailService.forLogin(user);
        }
    }

    @Test
    void forPayForOrder() {
        try (MockedStatic<EmailSender> ignored3 = Mockito.mockStatic(EmailSender.class);
             MockedStatic<Constants.LOCALE> ignored4 = Mockito.mockStatic(Constants.LOCALE.class)) {

            Mockito.when(Constants.LOCALE.values()).thenReturn(new Constants.LOCALE[]{Mockito.mock(Constants.LOCALE.class)});
            User user = Mockito.mock(User.class);
            Mockito.when(user.isAllow_letters()).thenReturn(true);
            Mockito.when(user.getLocale_id()).thenReturn(0);

            SendEmailService.forPayForOrder(user, 10);
        }
    }

    @Test
    void forSetCraftPrice() {
        try (MockedStatic<EmailSender> ignored3 = Mockito.mockStatic(EmailSender.class);
             MockedStatic<Constants.LOCALE> ignored4 = Mockito.mockStatic(Constants.LOCALE.class)) {

            Mockito.when(Constants.LOCALE.values()).thenReturn(new Constants.LOCALE[]{Mockito.mock(Constants.LOCALE.class)});
            User user = Mockito.mock(User.class);
            Mockito.when(user.isAllow_letters()).thenReturn(true);
            Mockito.when(user.getLocale_id()).thenReturn(0);

            SendEmailService.forSetCraftPrice(user, 10);
        }
    }

    @Test
    void forSetOrderStatus() {
        try (MockedStatic<EmailSender> ignored3 = Mockito.mockStatic(EmailSender.class);
             MockedStatic<Constants.LOCALE> ignored4 = Mockito.mockStatic(Constants.LOCALE.class)) {

            Mockito.when(Constants.LOCALE.values()).thenReturn(new Constants.LOCALE[]{Mockito.mock(Constants.LOCALE.class)});
            User user = Mockito.mock(User.class);
            Mockito.when(user.isAllow_letters()).thenReturn(true);
            Mockito.when(user.getLocale_id()).thenReturn(0);

            SendEmailService.forSetOrderStatus(user, 10);
        }
    }
}