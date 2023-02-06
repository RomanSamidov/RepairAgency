package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.service.UserService;
import com.myCompany.RepairAgency.servlet.util.Encrypt;
import com.myCompany.RepairAgency.servlet.util.ParameterValidation;
import com.myCompany.RepairAgency.servlet.util.SendEmail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChangePasswordCommandTest {
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
        request.setParameter(Constants.LOGIN, "test_login");

        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<ParameterValidation> ignored3 = Mockito.mockStatic(ParameterValidation.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.change_password"))).thenReturn(mockPath);
            Mockito.when(ParameterValidation.validateLogin(Mockito.any(), Mockito.anyString())).thenReturn(false);


            assertEquals(mockPath, new ChangePasswordCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.change_password"), Mockito.times(1));

            ignored3.verify(() -> ParameterValidation.validateLogin(request, "test_login"), Mockito.times(1));

        }
    }


    @Test
    void execute2() {
        request.setParameter(Constants.LOGIN, "test_login");

        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<ParameterValidation> ignored3 = Mockito.mockStatic(ParameterValidation.class);
             MockedStatic<UserService> ignored5 = Mockito.mockStatic(UserService.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.change_password"))).thenReturn(mockPath);
            Mockito.when(ParameterValidation.validateLogin(Mockito.eq(request), Mockito.anyString())).thenReturn(true);

            User user = Mockito.mock(User.class);
            Mockito.when(UserService.get(Mockito.anyString())).thenReturn(user);
            Mockito.when(ParameterValidation.forChangePassword(request, user)).thenReturn(false);

            assertEquals(mockPath, new ChangePasswordCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.change_password"), Mockito.times(1));
            ignored3.verify(() -> ParameterValidation.validateLogin(request, "test_login"), Mockito.times(1));

        }
    }

    @Test
    void execute3() {
        request.setParameter("sendCodeAgain", "dds");
        request.setParameter(Constants.LOGIN, "test_login");

        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<ParameterValidation> ignored3 = Mockito.mockStatic(ParameterValidation.class);
             MockedStatic<Encrypt> ignored4 = Mockito.mockStatic(Encrypt.class);
             MockedStatic<UserService> ignored5 = Mockito.mockStatic(UserService.class);
             MockedStatic<SendEmail> ignored6 = Mockito.mockStatic(SendEmail.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.change_password"))).thenReturn(mockPath);
            Mockito.when(ParameterValidation.validateLogin(Mockito.eq(request), Mockito.anyString())).thenReturn(true);

            Mockito.when(Encrypt.generateCode()).thenReturn("Test");

            User user = Mockito.mock(User.class);
            Mockito.when(UserService.get(Mockito.anyString())).thenReturn(user);
            Mockito.when(ParameterValidation.forChangePassword(request, user)).thenReturn(true);

            assertEquals(mockPath, new ChangePasswordCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.change_password"), Mockito.times(1));
            ignored3.verify(() -> ParameterValidation.validateLogin(request, "test_login"), Mockito.times(1));

        }
    }

    @Test
    void execute4() {
        session.setAttribute("waitedCodePassword", "test");
        request.setParameter(Constants.LOGIN, "test_login");

        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<ParameterValidation> ignored3 = Mockito.mockStatic(ParameterValidation.class);
             MockedStatic<Encrypt> ignored4 = Mockito.mockStatic(Encrypt.class);
             MockedStatic<UserService> ignored5 = Mockito.mockStatic(UserService.class);
             MockedStatic<SendEmail> ignored6 = Mockito.mockStatic(SendEmail.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.change_password"))).thenReturn(mockPath);
            Mockito.when(ParameterValidation.validateLogin(Mockito.eq(request), Mockito.anyString())).thenReturn(true);

            Mockito.when(Encrypt.generateCode()).thenReturn("Test");

            User user = Mockito.mock(User.class);
            Mockito.when(UserService.get(Mockito.anyString())).thenReturn(user);
            Mockito.when(ParameterValidation.forChangePassword(request, user)).thenReturn(true);

            Mockito.when(ParameterValidation.validatePasswordAndRepeat(Mockito.eq(request),
                    Mockito.anyString(),
                    Mockito.anyString())).thenReturn(false);

            assertEquals(mockPath, new ChangePasswordCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.change_password"), Mockito.times(1));
            ignored3.verify(() -> ParameterValidation.validateLogin(request, "test_login"), Mockito.times(1));

        }
    }

    @Test
    void execute5() {
        session.setAttribute("waitedCodePassword", "test");
        request.setParameter("confirmationCodePassword", "   test   ");
        request.setParameter(Constants.LOGIN, "test_login");
        request.setParameter(Constants.PASSWORD, "test_pass");
        request.setParameter(Constants.PASSWORD_REPEAT, "test_pass_rep");

        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<ParameterValidation> ignored3 = Mockito.mockStatic(ParameterValidation.class);
             MockedStatic<Encrypt> ignored4 = Mockito.mockStatic(Encrypt.class);
             MockedStatic<UserService> ignored5 = Mockito.mockStatic(UserService.class);
             MockedStatic<SendEmail> ignored6 = Mockito.mockStatic(SendEmail.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.login"))).thenReturn(mockPath);
            Mockito.when(ParameterValidation.validateLogin(Mockito.eq(request), Mockito.anyString())).thenReturn(true);

            Mockito.when(Encrypt.generateCode()).thenReturn("Test");

            User user = Mockito.mock(User.class);
            Mockito.when(UserService.get(Mockito.anyString())).thenReturn(user);
            Mockito.when(ParameterValidation.forChangePassword(request, user)).thenReturn(true);

            Mockito.when(ParameterValidation.validatePasswordAndRepeat(Mockito.eq(request),
                    Mockito.anyString(),
                    Mockito.anyString())).thenReturn(true);

            assertEquals(mockPath, new ChangePasswordCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.login"), Mockito.times(1));
            ignored3.verify(() -> ParameterValidation.validateLogin(request, "test_login"), Mockito.times(1));

        }
    }

    @Test
    void execute6() {
        session.setAttribute("waitedCodePassword", "no");
        request.setParameter("confirmationCodePassword", "   test   ");
        request.setParameter(Constants.LOGIN, "test_login");
        request.setParameter(Constants.PASSWORD, "test_pass");
        request.setParameter(Constants.PASSWORD_REPEAT, "test_pass_rep");

        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<ParameterValidation> ignored3 = Mockito.mockStatic(ParameterValidation.class);
             MockedStatic<Encrypt> ignored4 = Mockito.mockStatic(Encrypt.class);
             MockedStatic<UserService> ignored5 = Mockito.mockStatic(UserService.class);
             MockedStatic<SendEmail> ignored6 = Mockito.mockStatic(SendEmail.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.change_password"))).thenReturn(mockPath);
            Mockito.when(ParameterValidation.validateLogin(Mockito.eq(request), Mockito.anyString())).thenReturn(true);

            Mockito.when(Encrypt.generateCode()).thenReturn("Test");

            User user = Mockito.mock(User.class);
            Mockito.when(UserService.get(Mockito.anyString())).thenReturn(user);
            Mockito.when(ParameterValidation.forChangePassword(request, user)).thenReturn(true);

            Mockito.when(ParameterValidation.validatePasswordAndRepeat(Mockito.eq(request),
                    Mockito.anyString(),
                    Mockito.anyString())).thenReturn(true);

            assertEquals(mockPath, new ChangePasswordCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.change_password"), Mockito.times(1));
            ignored3.verify(() -> ParameterValidation.validateLogin(request, "test_login"), Mockito.times(1));

        }
    }
}