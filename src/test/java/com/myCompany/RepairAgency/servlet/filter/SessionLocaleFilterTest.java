package com.myCompany.RepairAgency.servlet.filter;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.mock.web.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class SessionLocaleFilterTest {
    @Mock
    SessionLocaleFilter filterUnderTest;
    @Mock
    MockFilterChain mockChain;
    @Mock
    MockHttpServletRequest request;
    @Mock
    MockHttpServletResponse response;
    @Mock
    MockHttpSession session;
    @Mock
    MockFilterConfig filterConfig;

    @BeforeEach
    public void initMocks() {
        filterUnderTest = new SessionLocaleFilter();
        mockChain = new MockFilterChain();
        request = new MockHttpServletRequest();
        session = new MockHttpSession();
        response = new MockHttpServletResponse();
        filterConfig = new MockFilterConfig();
        request.setSession(session);
    }

    @Test
    void doFilter1() throws ServletException, IOException {

        try (MockedStatic<UserService> ignored1 = Mockito.mockStatic(UserService.class);
             MockedStatic<Constants.LOCALE> ignored2 = Mockito.mockStatic(Constants.LOCALE.class)) {

            session.setAttribute("userId", 0L);
            request.setParameter("language", "en_US");
            User user = Mockito.mock(new User() {
                private int locale_id;

                @Override
                public User setLocale_id(int id) {
                    this.locale_id = id;
                    return this;
                }

                @Override
                public int getLocale_id() {
                    return locale_id;
                }

            }.getClass());

            Mockito.doCallRealMethod().when(user).setLocale_id(Mockito.anyInt());
            when(UserService.get(Mockito.anyLong())).thenReturn(user);
            Mockito.doCallRealMethod().when(user).getLocale_id();

            Constants.LOCALE locale1 = Mockito.mock(Constants.LOCALE.class);
            Mockito.when(locale1.ordinal()).thenReturn(100);

            Mockito.when(Constants.LOCALE.valueOf(Mockito.anyString()))
                    .thenReturn(locale1);

            filterUnderTest.init(filterConfig);
            filterUnderTest.doFilter(request, response, mockChain);
            filterUnderTest.destroy();

            assertEquals(100L, user.getLocale_id());
            assertEquals("en_US", session.getAttribute("language"));

            Cookie languageCookie = new Cookie("language", "en_US");
            languageCookie.setMaxAge(Integer.MAX_VALUE);
            assertArrayEquals(new Cookie[]{languageCookie},
                    response.getCookies());

            List<String> inSession = StreamSupport.stream(
                            Spliterators.spliteratorUnknownSize(session.getAttributeNames().asIterator(), Spliterator.ORDERED), false)
                    .toList();
            List<String> goal = new ArrayList<>();
            goal.add("userId");
            goal.add("language");

            assertTrue(goal.containsAll(inSession));
            assertTrue(inSession.containsAll(goal));
        }

    }

    @Test
    void doFilter2() throws ServletException, IOException {
        filterUnderTest.doFilter(request, response, mockChain);

        assertEquals("en_US", session.getAttribute("language"));

        List<String> inSession = StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(session.getAttributeNames().asIterator(), Spliterator.ORDERED), false)
                .toList();
        List<String> goal = new ArrayList<>();
        goal.add("language");

        assertTrue(goal.containsAll(inSession));
        assertTrue(inSession.containsAll(goal));
    }
}