package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.service.OrderUserService;
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

class UserCreateOrderCommandTest {
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
        session.setAttribute("isUserConfirmed", false);

        try (MockedStatic<ParameterValidationService> ignored1 = Mockito.mockStatic(ParameterValidationService.class);
             MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<UserService> ignored3 = Mockito.mockStatic(UserService.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.orders"))).thenReturn(mockPath);

            assertEquals(mockPath, new UserCreateOrderCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.orders"), Mockito.times(1));

        }
    }

    @Test
    void execute2() {
        session.setAttribute("userId", 0L);
        session.setAttribute("isUserConfirmed", true);

        request.setParameter("orderText", "false");


        try (MockedStatic<ParameterValidationService> ignored1 = Mockito.mockStatic(ParameterValidationService.class);
             MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<UserService> ignored3 = Mockito.mockStatic(UserService.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.orders"))).thenReturn(mockPath);
            Mockito.when(ParameterValidationService.validateOrderText(request, "false")).thenReturn(false);

            assertEquals(mockPath, new UserCreateOrderCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.orders"), Mockito.times(1));

        }
    }

    @Test
    void execute3() {
        session.setAttribute("userId", 0L);
        session.setAttribute("isUserConfirmed", true);

        request.setParameter("orderText", "false");


        try (MockedStatic<ParameterValidationService> ignored1 = Mockito.mockStatic(ParameterValidationService.class);
             MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<OrderUserService> ignored3 = Mockito.mockStatic(OrderUserService.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.orders"))).thenReturn(mockPath);
            Mockito.when(ParameterValidationService.validateOrderText(request, "false")).thenReturn(true);

            Mockito.when(OrderUserService.createOrder(0L, "false")).thenReturn(false);

            assertEquals(mockPath, new UserCreateOrderCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.orders"), Mockito.times(1));

        }
    }

    @Test
    void execute4() {
        session.setAttribute("userId", 0L);
        session.setAttribute("isUserConfirmed", true);

        request.setParameter("orderText", "false");


        try (MockedStatic<ParameterValidationService> ignored1 = Mockito.mockStatic(ParameterValidationService.class);
             MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<OrderUserService> ignored3 = Mockito.mockStatic(OrderUserService.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.orders"))).thenReturn(mockPath);
            Mockito.when(ParameterValidationService.validateOrderText(request, "false")).thenReturn(true);

            Mockito.when(OrderUserService.createOrder(0L, "false")).thenReturn(true);

            assertEquals(mockPath, new UserCreateOrderCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.orders"), Mockito.times(1));

        }
    }

    @Test
    void allowedUserRoles() {
        assertEquals(Stream.of(Constants.ROLE.Client
                ).collect(Collectors.toList()),
                new UserCreateOrderCommand().allowedUserRoles());
    }
}