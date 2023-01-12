package com.myCompany.RepairAgency.servlet.filter;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.entity.iUserRepository;
import com.myCompany.RepairAgency.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.mock.web.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class SessionLocaleFilterTest {
    SessionLocaleFilter filterUnderTest;
    MockFilterChain mockChain;
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    MockHttpSession session;

    @Test
    void doFilter() throws ServletException, IOException {

        try (MockedStatic<ModelManager> ignored1 = Mockito.mockStatic(ModelManager.class);
             MockedStatic<Constants.LOCALE> ignored2 = Mockito.mockStatic(Constants.LOCALE.class)){

            filterUnderTest = new SessionLocaleFilter();
            mockChain = new MockFilterChain();
            request = new MockHttpServletRequest();
            session = new MockHttpSession();
            response = new MockHttpServletResponse();
            MockFilterConfig filterConfig = new MockFilterConfig();

            session.setAttribute("userId",0L);
            request.setSession(session);
            request.setParameter("language", "en_US");
            User user = Mockito.mock(new User() {
                private int locale_id;
             @Override
                public User setLocale_id(int id){
                 this.locale_id=id;
                 return this;
             }
             @Override
             public int getLocale_id() {
                 return locale_id;
             }

            }.getClass());
            iUserRepository userRepo = Mockito.mock(new iUserRepository() {
                @Override
                public User getByLogin(String login) {
                    return null;
                }

                @Override
                public ArrayList<User> getByRole(long roleId, int skip, int quantity) {
                    return null;
                }

                @Override
                public long countWhereRoleIs(long id) {
                    return 0;
                }

                @Override
                public void update(User obj) {

                }

                @Override
                public void delete(User obj) {

                }

                @Override
                public void insert(User obj) {

                }

                @Override
                public User getById(long id) {
                    return user;
                }
            }.getClass());

            Mockito.doCallRealMethod().when(user).setLocale_id(Mockito.anyInt());
            when(userRepo.getById(Mockito.anyLong())).thenReturn(user);
            Mockito.doCallRealMethod().when(user).getLocale_id();

            ModelManager manager = Mockito.mock(ModelManager.class);
            Mockito.when(manager.getUserRepository()).thenReturn(userRepo);

            Constants.LOCALE locale1 = Mockito.mock(Constants.LOCALE.class);
            Mockito.when(locale1.ordinal()).thenReturn(100);

            Mockito.when(Constants.LOCALE.valueOf(Mockito.anyString()))
                    .thenReturn(locale1);

            Mockito.when(ModelManager.getInstance()).thenReturn(manager);

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



            filterUnderTest = new SessionLocaleFilter();
            mockChain = new MockFilterChain();
            request = new MockHttpServletRequest();
            session = new MockHttpSession();
            response = new MockHttpServletResponse();

            request.setSession(session);

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