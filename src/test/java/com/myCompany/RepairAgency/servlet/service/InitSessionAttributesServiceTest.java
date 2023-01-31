package com.myCompany.RepairAgency.servlet.service;

import com.myCompany.RepairAgency.model.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;

class InitSessionAttributesServiceTest {
    @Mock
    MockHttpServletRequest request;
    @Mock
    MockHttpSession session;


    @BeforeEach
    public void initMocks() {
        request = new MockHttpServletRequest();
        session = new MockHttpSession();
        request.setSession(session);
    }

    @Test
    void initUserSessionAttributes() {

        try (MockedStatic<ViewValidationService> ignored1 = Mockito.mockStatic(ViewValidationService.class)) {

            User user = Mockito.mock(User.class);
            Mockito.when(user.getLocale_id()).thenReturn(1);
            Mockito.when(user.getLogin()).thenReturn("1");
            Mockito.when(user.getId()).thenReturn(0L);
            Mockito.when(user.getEmail()).thenReturn("0L");
            Mockito.when(user.isConfirmed()).thenReturn(true);
            Mockito.when(user.getRole_id()).thenReturn(1);

            InitSessionAttributesService.initUserSessionAttributes(request, user);
        }
    }
}