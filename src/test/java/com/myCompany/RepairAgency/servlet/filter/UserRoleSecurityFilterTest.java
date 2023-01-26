package com.myCompany.RepairAgency.servlet.filter;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.request.get.GetCommandFactory;
import com.myCompany.RepairAgency.servlet.request.post.PostCommandFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.mock.web.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserRoleSecurityFilterTest {

    @Mock
    UserRoleSecurityFilter filterUnderTest;
    @Mock
    MockFilterChain mockChain;
    @Mock
    MockHttpServletRequest request;
    @Mock
    MockHttpServletResponse response;
    @Mock
    MockHttpSession session;
    @Mock
    PostCommandFactory postCommandFactory;
    @Mock
    GetCommandFactory getCommandFactory;
    @Mock
    MockFilterConfig filterConfig;
    @Mock
    Path mockPath;

    @BeforeEach
    public void initMocks() {
        filterUnderTest = new UserRoleSecurityFilter();
        mockChain = Mockito.mock(MockFilterChain.class);
        request = new MockHttpServletRequest();
        session = new MockHttpSession();
        response = new MockHttpServletResponse();
        filterConfig = new MockFilterConfig();
        postCommandFactory = Mockito.mock(PostCommandFactory.class);
        getCommandFactory = Mockito.mock(GetCommandFactory.class);
        mockPath = Mockito.mock(Path.class);
        request.setSession(session);
    }


    @Test
    void doFilterWrongURI() throws ServletException, IOException {
        request.setRequestURI("wrong");

        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class)) {

            Mockito.when(PathFactory.getPath("path.page.redirect.home")).thenReturn(mockPath);

            filterUnderTest.init(filterConfig);
            filterUnderTest.doFilter(request, response, mockChain);
            filterUnderTest.destroy();

            assertEquals(mockPath.toString(), response.getRedirectedUrl());

        }
    }


    @Test
    void doFilter1() throws ServletException, IOException {
        session.setAttribute("userRole", null);
        request.setRequestURI("/RepairAgency/controller/test");
        request.setMethod("POST");

        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<PostCommandFactory> ignored3 = Mockito.mockStatic(PostCommandFactory.class)) {


            Mockito.when(PathFactory.getPath(Mockito.anyString())).thenReturn(mockPath);

            TestCommand command = Mockito.mock(TestCommand.class);
            Mockito.when(command.allowedUserRoles()).thenCallRealMethod();
            Mockito.when(command.execute(Mockito.any(), Mockito.any())).thenCallRealMethod();

            Mockito.when(PostCommandFactory.getInstance()).thenReturn(postCommandFactory);
            Mockito.when(postCommandFactory.defineCommand(Mockito.any())).thenReturn(command);

            filterUnderTest.init(filterConfig);
            filterUnderTest.doFilter(request, response, mockChain);
            filterUnderTest.destroy();
            Mockito.verify(mockChain, Mockito.times(1)).doFilter(Mockito.any(), Mockito.any());
            assertNull(response.getRedirectedUrl());

            assertEquals(Constants.ROLE.Guest, session.getAttribute("userRole"));
            assertEquals(mockPath.toString(), session.getAttribute("_menu_url"));


        }
    }

    @Test
    void doFilter2() throws ServletException, IOException {
        session.setAttribute("userRole", null);
        request.setRequestURI("/RepairAgency/controller/test");
        request.setMethod("GET");

        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<GetCommandFactory> ignored3 = Mockito.mockStatic(GetCommandFactory.class)) {


            Mockito.when(PathFactory.getPath(Mockito.anyString())).thenReturn(mockPath);

            TestCommand command = Mockito.mock(TestCommand.class);
            Mockito.when(command.allowedUserRoles()).thenCallRealMethod();
            Mockito.when(command.execute(Mockito.any(), Mockito.any())).thenCallRealMethod();

            Mockito.when(GetCommandFactory.getInstance()).thenReturn(getCommandFactory);
            Mockito.when(getCommandFactory.defineCommand(Mockito.any())).thenReturn(command);

            filterUnderTest.init(filterConfig);
            filterUnderTest.doFilter(request, response, mockChain);
            filterUnderTest.destroy();
            Mockito.verify(mockChain, Mockito.times(1)).doFilter(Mockito.any(), Mockito.any());
            assertNull(response.getRedirectedUrl());


        }
    }

    @Test
    void doFilter3() throws ServletException, IOException {
        session.setAttribute("userRole", null);
        request.setRequestURI("/RepairAgency/controller/test");
        request.setMethod("GET");

        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<GetCommandFactory> ignored3 = Mockito.mockStatic(GetCommandFactory.class)) {


            Mockito.when(PathFactory.getPath(Mockito.anyString())).thenReturn(mockPath);

            TestCommand2 command = Mockito.mock(TestCommand2.class);
            Mockito.when(command.allowedUserRoles()).thenCallRealMethod();

            Mockito.when(GetCommandFactory.getInstance()).thenReturn(getCommandFactory);
            Mockito.when(getCommandFactory.defineCommand(Mockito.any())).thenReturn(command);

            filterUnderTest.init(filterConfig);
            filterUnderTest.doFilter(request, response, mockChain);
            filterUnderTest.destroy();

            Mockito.verify(mockChain, Mockito.times(0)).doFilter(Mockito.any(), Mockito.any());
            assertEquals(mockPath.toString(), response.getRedirectedUrl());

        }
    }

    private class TestCommand implements IActionCommand, IHasRoleRequirement {
        @Override
        public Path execute(HttpServletRequest request, HttpServletResponse response) {
            return mockPath;
        }

        @Override
        public List<Constants.ROLE> allowedUserRoles() {
            return Stream.of(
                    Constants.ROLE.Admin,
                    Constants.ROLE.Client,
                    Constants.ROLE.Craftsman,
                    Constants.ROLE.Guest,
                    Constants.ROLE.Manager
            ).collect(Collectors.toList());
        }
    }

    private class TestCommand2 implements IActionCommand, IHasRoleRequirement {
        @Override
        public Path execute(HttpServletRequest request, HttpServletResponse response) {
            return mockPath;
        }

        @Override
        public List<Constants.ROLE> allowedUserRoles() {
            return new ArrayList<>();
        }
    }


}