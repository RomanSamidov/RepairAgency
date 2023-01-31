package com.myCompany.RepairAgency.servlet.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;


class AttributeFSTRServiceTest {
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
    void forShowChangePassword() {
        AttributeFSTRService.forShowChangePassword(request);
    }

    @Test
    void forShowCreateUserForm() {
        AttributeFSTRService.forShowCreateUserForm(request);

    }

    @Test
    void forShowLoginForm() {
        AttributeFSTRService.forShowLoginForm(request);
    }

    @Test
    void forShowOrders() {
        AttributeFSTRService.forShowOrders(request);
    }

    @Test
    void forShowProfile() {
        AttributeFSTRService.forShowProfile(request);
    }

    @Test
    void forShowSignupForm() {
        AttributeFSTRService.forShowSignupForm(request);
    }
}