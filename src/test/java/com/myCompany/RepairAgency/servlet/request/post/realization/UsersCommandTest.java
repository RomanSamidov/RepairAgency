package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.util.ForTables;
import com.myCompany.RepairAgency.servlet.util.ParameterValidation;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

class UsersCommandTest {
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
    void execute1() {
        session.setAttribute("userId", 1L);
        request.setParameter("roleUsers", "0");

        try (MockedStatic<ParameterValidation> ignored1 = Mockito.mockStatic(ParameterValidation.class);
             MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<ForTables> ignored3 = Mockito.mockStatic(ForTables.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.users"))).thenReturn(mockPath);
            Mockito.when(ParameterValidation.validateRoleId(request, "0")).thenReturn(false);

            assertEquals(mockPath, new UsersCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.users"), Mockito.times(1));

        }
    }

    @Test
    void execute2() {
        session.setAttribute("userId", 1L);
        request.setParameter("roleUsers", "0");

        try (MockedStatic<ParameterValidation> ignored1 = Mockito.mockStatic(ParameterValidation.class);
             MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<ForTables> ignored3 = Mockito.mockStatic(ForTables.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.users"))).thenReturn(mockPath);
            Mockito.when(ParameterValidation.validateRoleId(request, "0")).thenReturn(true);

            assertEquals(mockPath, new UsersCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.users"), Mockito.times(1));

        }
    }


    @Test
    void execute3() {
        session.setAttribute("userId", 1L);
        session.setAttribute("userRole", Constants.ROLE.Manager);
        request.setParameter("roleUsers", "Manager");

        try (MockedStatic<ParameterValidation> ignored1 = Mockito.mockStatic(ParameterValidation.class);
             MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<ForTables> ignored3 = Mockito.mockStatic(ForTables.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.users"))).thenReturn(mockPath);

            assertEquals(mockPath, new UsersCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.users"), Mockito.times(1));

        }
    }


    @Test
    void execute4() {
        session.setAttribute("userId", 1L);
        session.setAttribute("userRole", Constants.ROLE.Manager);
        request.setParameter("roleUsers", "ew");

        try (MockedStatic<ParameterValidation> ignored1 = Mockito.mockStatic(ParameterValidation.class);
             MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<ForTables> ignored3 = Mockito.mockStatic(ForTables.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.users"))).thenReturn(mockPath);

            assertEquals(mockPath, new UsersCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.users"), Mockito.times(1));

        }
    }

    @Test
    void allowedUserRoles() {
        assertEquals(Stream.of(Constants.ROLE.Manager,
                        Constants.ROLE.Admin).collect(Collectors.toList()),
                new UsersCommand().allowedUserRoles());
    }
}