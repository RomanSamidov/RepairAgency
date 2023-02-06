package com.myCompany.RepairAgency.servlet.util;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;


class AttributeFSTRTest {
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
        AttributeFSTR.forShowChangePassword(request);
    }

    @Test
    void forShowCreateUserForm() {
        AttributeFSTR.forShowCreateUserForm(request);

    }

    @Test
    void forShowLoginForm() {
        AttributeFSTR.forShowLoginForm(request);
    }

    @Test
    void forShowOrders() {
        AttributeFSTR.forShowOrders(request);
    }

    @Test
    void forShowProfile() {
        AttributeFSTR.forShowProfile(request);
    }

    @Test
    void forShowSignupForm() {
        AttributeFSTR.forShowSignupForm(request);
    }
}