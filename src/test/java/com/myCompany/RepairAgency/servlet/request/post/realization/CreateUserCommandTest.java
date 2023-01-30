package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.service.ParameterValidationService;
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

class CreateUserCommandTest {
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
        request.setParameter("newIsUserAllowLetters", "false");

        request.setParameter(Constants.EMAIL, "testEmail");
        request.setParameter(Constants.PASSWORD, "pass");
        request.setParameter(Constants.PASSWORD_REPEAT, "pass");
        request.setParameter(Constants.LOGIN, "login");
        request.setParameter("g-recaptcha-response", "test");
        request.setParameter("role", "3");

        try (MockedStatic<ParameterValidationService> ignored1 = Mockito.mockStatic(ParameterValidationService.class);
             MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<UserService> ignored3 = Mockito.mockStatic(UserService.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.create_user"))).thenReturn(mockPath);


            Mockito.when(ParameterValidationService.validateEmail(request, "testEmail")).thenReturn(true);
            Mockito.when(ParameterValidationService.validatePasswordAndRepeat(request, "pass", "pass")).thenReturn(true);
            Mockito.when(ParameterValidationService.validateLogin(request, "login")).thenReturn(true);
            Mockito.when(ParameterValidationService.validateRecaptcha(request, "test")).thenReturn(true);
            Mockito.when(ParameterValidationService.validateRoleId(request, "3")).thenReturn(true);


            Mockito.when(UserService.checkUserExistence("login")).thenReturn(false);

            assertEquals(mockPath, new CreateUserCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.create_user"), Mockito.times(1));

        }
    }

    @Test
    void execute2() {
        session.setAttribute("userId", 1L);
        request.setParameter("newIsUserAllowLetters", "false");

        request.setParameter(Constants.EMAIL, "testEmail");
        request.setParameter(Constants.PASSWORD, "pass");
        request.setParameter(Constants.PASSWORD_REPEAT, "pass");
        request.setParameter(Constants.LOGIN, "login");
        request.setParameter("g-recaptcha-response", "test");
        request.setParameter("role", "3");

        try (MockedStatic<ParameterValidationService> ignored1 = Mockito.mockStatic(ParameterValidationService.class);
             MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<UserService> ignored3 = Mockito.mockStatic(UserService.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.create_user"))).thenReturn(mockPath);


            Mockito.when(ParameterValidationService.validateEmail(request, "testEmail")).thenReturn(true);
            Mockito.when(ParameterValidationService.validatePasswordAndRepeat(request, "pass", "pass")).thenReturn(true);
            Mockito.when(ParameterValidationService.validateLogin(request, "login")).thenReturn(true);
            Mockito.when(ParameterValidationService.validateRecaptcha(request, "test")).thenReturn(true);
            Mockito.when(ParameterValidationService.validateRoleId(request, "3")).thenReturn(true);


            Mockito.when(UserService.checkUserExistence("login")).thenReturn(true);

            assertEquals(mockPath, new CreateUserCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.create_user"), Mockito.times(1));

        }
    }


    @Test
    void execute3() {
        session.setAttribute("userId", 1L);
        request.setParameter("newIsUserAllowLetters", "false");

        request.setParameter(Constants.EMAIL, "testEmail");
        request.setParameter(Constants.PASSWORD, "pass");
        request.setParameter(Constants.PASSWORD_REPEAT, "pass");
        request.setParameter(Constants.LOGIN, "login");
        request.setParameter("g-recaptcha-response", "test");
        request.setParameter("role", "3");

        try (MockedStatic<ParameterValidationService> ignored1 = Mockito.mockStatic(ParameterValidationService.class);
             MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<UserService> ignored3 = Mockito.mockStatic(UserService.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.create_user"))).thenReturn(mockPath);


            Mockito.when(ParameterValidationService.validateEmail(request, "testEmail")).thenReturn(false);
            Mockito.when(ParameterValidationService.validatePasswordAndRepeat(request, "pass", "pass")).thenReturn(false);
            Mockito.when(ParameterValidationService.validateLogin(request, "login")).thenReturn(false);
            Mockito.when(ParameterValidationService.validateRecaptcha(request, "test")).thenReturn(false);
            Mockito.when(ParameterValidationService.validateRoleId(request, "3")).thenReturn(false);


            assertEquals(mockPath, new CreateUserCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.create_user"), Mockito.times(1));

        }
    }

    @Test
    void allowedUserRoles() {
        assertEquals(Stream.of(Constants.ROLE.Admin).collect(Collectors.toList()),
                new CreateUserCommand().allowedUserRoles());
    }

}