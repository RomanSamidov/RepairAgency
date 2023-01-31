package com.myCompany.RepairAgency.servlet.service;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.db.abstractDB.exception.MyDBException;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.util.VerifyRecaptcha;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParameterValidationServiceTest {
    @Mock
    Path mockPath;
    @Mock
    MockHttpServletRequest request;
    @Mock
    MockHttpSession session;
    @Mock
    MockHttpServletResponse response;


    @BeforeEach
    public void initMocks() {
        response = new MockHttpServletResponse();
        mockPath = Mockito.mock(Path.class);
        request = new MockHttpServletRequest();
        session = new MockHttpSession();
        request.setSession(session);
    }

    @Test
    void forAdminCreateOrder1() {
        request.setParameter("clientId", "1");
        request.setParameter("orderText", "recap");

        try (MockedStatic<VerifyRecaptcha> ignored1 = Mockito.mockStatic(VerifyRecaptcha.class)) {
            Mockito.when(VerifyRecaptcha.verify(Mockito.anyString())).thenReturn(true);
            assertTrue(ParameterValidationService.forAdminCreateOrder(request));
        }
    }

    @Test
    void forAdminCreateOrder2() {

        try (MockedStatic<VerifyRecaptcha> ignored1 = Mockito.mockStatic(VerifyRecaptcha.class)) {
            Mockito.when(VerifyRecaptcha.verify(Mockito.anyString())).thenReturn(true);
            assertFalse(ParameterValidationService.forAdminCreateOrder(request));
        }
    }

    @Test
    void forChangePassword1() {
        request.setParameter(Constants.EMAIL, "test@email.co");
        request.setParameter("g-recaptcha-response", "recap");

        try (MockedStatic<VerifyRecaptcha> ignored1 = Mockito.mockStatic(VerifyRecaptcha.class)) {
            Mockito.when(VerifyRecaptcha.verify(Mockito.anyString())).thenReturn(true);

            User user = Mockito.mock(User.class);
            Mockito.when(user.getEmail()).thenReturn("test2@mail.co");
            assertFalse(ParameterValidationService.forChangePassword(request, user));
        }
    }

    @Test
    void forChangePassword2() {
        request.setParameter(Constants.EMAIL, "test@email.co");
        request.setParameter("g-recaptcha-response", "recap");

        try (MockedStatic<VerifyRecaptcha> ignored1 = Mockito.mockStatic(VerifyRecaptcha.class)) {
            Mockito.when(VerifyRecaptcha.verify(Mockito.anyString())).thenReturn(true);

            assertFalse(ParameterValidationService.forChangePassword(request, null));
        }
    }

    @Test
    void validateOrderText() {
        String s = Stream.generate(() -> "s").limit(300).collect(Collectors.joining());
        assertFalse(ParameterValidationService.validateOrderText(request, s));
    }

    @Test
    void validateEmail1() {
        String s = Stream.generate(() -> "s").limit(30).collect(Collectors.joining());
        assertFalse(ParameterValidationService.validateEmail(request, s));
    }

    @Test
    void validateEmail2() {
        assertFalse(ParameterValidationService.validateEmail(request, null));
    }

    @Test
    void validateEmail3() {
        String s = Stream.generate(() -> "s").limit(29).collect(Collectors.joining());
        assertFalse(ParameterValidationService.validateEmail(request, s));
    }

    @Test
    void validateLogin1() {
        String s = Stream.generate(() -> "s").limit(30).collect(Collectors.joining());
        assertFalse(ParameterValidationService.validateLogin(request, s));
    }

    @Test
    void validateLogin2() {
        assertFalse(ParameterValidationService.validateLogin(request, null));
    }

    @Test
    void validateLogin3() {
        assertTrue(ParameterValidationService.validateLogin(request, "null"));
    }

    @Test
    void validateRecaptcha() {
        try (MockedStatic<VerifyRecaptcha> ignored1 = Mockito.mockStatic(VerifyRecaptcha.class)) {
            Mockito.when(VerifyRecaptcha.verify(Mockito.anyString())).thenReturn(false);

            assertFalse(ParameterValidationService.validateRecaptcha(request, "ads"));
        }
    }

    @Test
    void validatePassword1() {
        String s = Stream.generate(() -> "s").limit(63).collect(Collectors.joining());
        assertFalse(ParameterValidationService.validatePassword(request, s));
    }

    @Test
    void validatePassword2() {
        assertFalse(ParameterValidationService.validatePassword(request, null));
    }

    @Test
    void validatePasswordAndRepeat1() {
        String s = Stream.generate(() -> "s").limit(60).collect(Collectors.joining());
        assertTrue(ParameterValidationService.validatePasswordAndRepeat(request, s, s));
    }

    @Test
    void validatePasswordAndRepeat2() {
        String s = Stream.generate(() -> "s").limit(60).collect(Collectors.joining());
        assertFalse(ParameterValidationService.validatePasswordAndRepeat(request, s, "s"));
    }

    @Test
    void validatePasswordAndRepeat3() {
        assertFalse(ParameterValidationService.validatePasswordAndRepeat(request, null, "s"));
    }

    @Test
    void validatePasswordAndRepeat4() {
        assertFalse(ParameterValidationService.validatePasswordAndRepeat(request, "null", null));
    }

    @Test
    void validateRoleId1() {
        assertTrue(ParameterValidationService.validateRoleId(request, "1"));
    }

    @Test
    void validateRoleId2() {
        assertFalse(ParameterValidationService.validateRoleId(request, "10"));
    }

    @Test
    void validateOrderStatusId1() {
        assertTrue(ParameterValidationService.validateOrderStatusId(request, "1"));
    }

    @Test
    void validateOrderStatusId2() {
        assertFalse(ParameterValidationService.validateOrderStatusId(request, "0"));
    }

    @Test
    void validateGoalId() {
    }

    @Test
    void validateInt1() {
        assertTrue(ParameterValidationService.validateInt("0"));
    }

    @Test
    void validateInt2() {
        assertFalse(ParameterValidationService.validateInt("ab"));
    }

    @Test
    void validateCraftsmanId1() {
        try (MockedStatic<UserService> ignored1 = Mockito.mockStatic(UserService.class)) {

            User user = Mockito.mock(User.class);
            Mockito.when(user.getRole_id()).thenReturn(3);

            Mockito.when(UserService.get(Mockito.anyLong())).thenReturn(user);

            assertTrue(ParameterValidationService.validateCraftsmanId("1"));
        }
    }

    @Test
    void validateCraftsmanId2() {
        try (MockedStatic<UserService> ignored1 = Mockito.mockStatic(UserService.class)) {

            Mockito.when(UserService.get(Mockito.anyLong())).thenThrow(new MyDBException());

            assertFalse(ParameterValidationService.validateCraftsmanId("1"));
        }
    }

    @Test
    void validateCraftsmanId3() {
        assertFalse(ParameterValidationService.validateCraftsmanId("0"));
    }

    @Test
    void validateFeedbackText1() {
        String s = Stream.generate(() -> "s").limit(255).collect(Collectors.joining());
        assertFalse(ParameterValidationService.validateFeedbackText(request, s));
    }

    @Test
    void validateFeedbackText2() {
        assertFalse(ParameterValidationService.validateFeedbackText(request, null));
    }

    @Test
    void validateFeedbackText3() {
        assertTrue(ParameterValidationService.validateFeedbackText(request, "null"));
    }
}