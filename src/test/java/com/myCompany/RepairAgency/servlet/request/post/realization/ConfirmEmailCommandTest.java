package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.service.UserService;
import com.myCompany.RepairAgency.servlet.util.Encrypt;
import com.myCompany.RepairAgency.servlet.util.SendEmail;
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

class ConfirmEmailCommandTest {
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
        request.setParameter("confirmationCode", "testCode");

        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<Encrypt> ignored4 = Mockito.mockStatic(Encrypt.class);
             MockedStatic<UserService> ignored5 = Mockito.mockStatic(UserService.class);
             MockedStatic<SendEmail> ignored6 = Mockito.mockStatic(SendEmail.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.cabinet"))).thenReturn(mockPath);

            Mockito.when(Encrypt.generateCode()).thenReturn("testCode");

            User user = Mockito.mock(User.class);
            Mockito.when(UserService.get(Mockito.anyLong())).thenReturn(user);

            assertEquals(mockPath, new ConfirmEmailCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.cabinet"), Mockito.times(1));

        }
    }

    @Test
    void execute2() {
        session.setAttribute("userId", 1L);
        session.setAttribute("waitedCode", "testCode");
        request.setParameter("confirmationCode", "  testqCode  ");

        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<Encrypt> ignored4 = Mockito.mockStatic(Encrypt.class);
             MockedStatic<UserService> ignored5 = Mockito.mockStatic(UserService.class);
             MockedStatic<SendEmail> ignored6 = Mockito.mockStatic(SendEmail.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.cabinet"))).thenReturn(mockPath);

            Mockito.when(Encrypt.generateCode()).thenReturn("testCode");

            User user = Mockito.mock(User.class);
            Mockito.when(UserService.get(Mockito.anyLong())).thenReturn(user);

            assertEquals(mockPath, new ConfirmEmailCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.cabinet"), Mockito.times(1));

        }
    }

    @Test
    void execute3() {
        session.setAttribute("userId", 1L);
        session.setAttribute("waitedCode", "testCode");
        request.setParameter("confirmationCode", "  testCode  ");

        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<Encrypt> ignored4 = Mockito.mockStatic(Encrypt.class);
             MockedStatic<UserService> ignored5 = Mockito.mockStatic(UserService.class);
             MockedStatic<SendEmail> ignored6 = Mockito.mockStatic(SendEmail.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.cabinet"))).thenReturn(mockPath);

            Mockito.when(Encrypt.generateCode()).thenReturn("testCode");

            User user = Mockito.mock(User.class);
            Mockito.when(UserService.get(Mockito.anyLong())).thenReturn(user);

            assertEquals(mockPath, new ConfirmEmailCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.cabinet"), Mockito.times(1));

        }
    }

    @Test
    void allowedUserRoles() {
        assertEquals(Stream.of(Constants.ROLE.Client,
                        Constants.ROLE.Admin,
                        Constants.ROLE.Manager,
                        Constants.ROLE.Craftsman).collect(Collectors.toList()),
                new ConfirmEmailCommand().allowedUserRoles());
    }
}