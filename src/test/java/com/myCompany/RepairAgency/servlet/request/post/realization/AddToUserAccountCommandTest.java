package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.service.UserService;
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

class AddToUserAccountCommandTest {
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
        request.setParameter("goalIdUser", "0");
        request.setParameter("addToAccount", "100");

        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<UserService> ignored4 = Mockito.mockStatic(UserService.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.user"))).thenReturn(mockPath);
            Mockito.when(UserService.addToUserAccount(0L, 100)).thenReturn(true);

            assertEquals(mockPath, new AddToUserAccountCommand().execute(request, response));
            ignored4.verify(() -> UserService.addToUserAccount(0L, 100));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.user"), Mockito.times(1));
            Mockito.verify(mockPath).addParameter("id", "0");
        }
    }

    @Test
    void execute2() {
        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.users"))).thenReturn(mockPath);

            assertEquals(mockPath, new AddToUserAccountCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.users"), Mockito.times(1));
        }
    }

    @Test
    void execute3() {
        request.setParameter("goalIdUser", "0");

        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.user"))).thenReturn(mockPath);

            assertEquals(mockPath, new AddToUserAccountCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.user"), Mockito.times(1));
        }
    }

    @Test
    void execute4() {
        request.setParameter("goalIdUser", "0");
        request.setParameter("addToAccount", "100");

        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<UserService> ignored4 = Mockito.mockStatic(UserService.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.users"))).thenReturn(mockPath);
            Mockito.when(UserService.addToUserAccount(0L, 100)).thenReturn(false);

            assertEquals(mockPath, new AddToUserAccountCommand().execute(request, response));
            ignored4.verify(() -> UserService.addToUserAccount(0L, 100));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.users"), Mockito.times(1));
        }
    }


    @Test
    void allowedUserRoles() {
        assertEquals(Stream.of(Constants.ROLE.Manager, Constants.ROLE.Admin).collect(Collectors.toList()),
                new AddToUserAccountCommand().allowedUserRoles());
    }
}