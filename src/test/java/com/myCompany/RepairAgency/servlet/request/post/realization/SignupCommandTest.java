package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.service.InitSessionAttributesService;
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

class SignupCommandTest {
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
        request.setParameter(Constants.EMAIL, "false");

        try (MockedStatic<ParameterValidationService> ignored1 = Mockito.mockStatic(ParameterValidationService.class);
             MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<UserService> ignored3 = Mockito.mockStatic(UserService.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.signup"))).thenReturn(mockPath);
            Mockito.when(ParameterValidationService.validateEmail(request, "false")).thenReturn(false);

            assertEquals(mockPath, new SignupCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.signup"), Mockito.times(1));

        }
    }

    @Test
    void execute2() {
        request.setParameter(Constants.EMAIL, "false");
        request.setParameter(Constants.PASSWORD, "pass");
        request.setParameter(Constants.PASSWORD_REPEAT, "pass");
        request.setParameter(Constants.LOGIN, "login");
        request.setParameter("g-recaptcha-response", "a");

        try (MockedStatic<ParameterValidationService> ignored1 = Mockito.mockStatic(ParameterValidationService.class);
             MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<UserService> ignored3 = Mockito.mockStatic(UserService.class);
             MockedStatic<InitSessionAttributesService> ignored4 = Mockito.mockStatic(InitSessionAttributesService.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.cabinet"))).thenReturn(mockPath);

            Mockito.when(ParameterValidationService.validateEmail(request, "false")).thenReturn(true);
            Mockito.when(ParameterValidationService.validatePasswordAndRepeat(request, "pass", "pass")).thenReturn(true);
            Mockito.when(ParameterValidationService.validateLogin(request, "login")).thenReturn(true);
            Mockito.when(ParameterValidationService.validateRecaptcha(request, "a")).thenReturn(true);


            Mockito.when(UserService.checkUserExistence(Mockito.anyString())).thenReturn(false);

            assertEquals(mockPath, new SignupCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.cabinet"), Mockito.times(1));

        }
    }

    @Test
    void execute3() {
        request.setParameter(Constants.EMAIL, "false");
        request.setParameter(Constants.PASSWORD, "pass");
        request.setParameter(Constants.PASSWORD_REPEAT, "pass");
        request.setParameter(Constants.LOGIN, "login");
        request.setParameter("g-recaptcha-response", "a");

        try (MockedStatic<ParameterValidationService> ignored1 = Mockito.mockStatic(ParameterValidationService.class);
             MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<UserService> ignored3 = Mockito.mockStatic(UserService.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.signup"))).thenReturn(mockPath);

            Mockito.when(ParameterValidationService.validateEmail(request, "false")).thenReturn(true);
            Mockito.when(ParameterValidationService.validatePasswordAndRepeat(request, "pass", "pass")).thenReturn(true);
            Mockito.when(ParameterValidationService.validateLogin(request, "login")).thenReturn(true);
            Mockito.when(ParameterValidationService.validateRecaptcha(request, "a")).thenReturn(true);


            Mockito.when(UserService.checkUserExistence(Mockito.anyString())).thenReturn(true);

            assertEquals(mockPath, new SignupCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.signup"), Mockito.times(1));

        }
    }

    @Test
    void allowedUserRoles() {
        assertEquals(Stream.of(Constants.ROLE.Guest
                ).collect(Collectors.toList()),
                new SignupCommand().allowedUserRoles());
    }
}