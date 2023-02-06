package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.entity.RepairOrder;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.service.OrderUserService;
import com.myCompany.RepairAgency.servlet.service.RepairOrderService;
import com.myCompany.RepairAgency.servlet.service.UserService;
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

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CancelOrderCommandTest {
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

        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<ParameterValidation> ignored1 = Mockito.mockStatic(ParameterValidation.class)) {

            Mockito.when(ParameterValidation.validateGoalId(Mockito.anyString())).thenReturn(false);
            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.orders"))).thenReturn(mockPath);

            assertEquals(mockPath, new CancelOrderCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.orders"), Mockito.times(1));

        }
    }

    @Test
    void execute2() {
        request.setParameter("goalIdOrder", "0");

        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<ParameterValidation> ignored1 = Mockito.mockStatic(ParameterValidation.class);
             MockedStatic<UserService> ignored3 = Mockito.mockStatic(UserService.class);
             MockedStatic<OrderUserService> ignored5 = Mockito.mockStatic(OrderUserService.class);
             MockedStatic<RepairOrderService> ignored6 = Mockito.mockStatic(RepairOrderService.class);
             MockedStatic<SendEmail> ignored4 = Mockito.mockStatic(SendEmail.class)) {

            Mockito.when(ParameterValidation.validateGoalId(Mockito.anyString())).thenReturn(true);
            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.order"))).thenReturn(mockPath);


            RepairOrder order = Mockito.mock(RepairOrder.class);
            Mockito.when(RepairOrderService.get(Mockito.anyLong())).thenReturn(order);

            User user = Mockito.mock(User.class);
            Mockito.when(user.getRole_id()).thenReturn(1);
            Mockito.when(UserService.get(Mockito.anyLong())).thenReturn(user);

            assertEquals(mockPath, new CancelOrderCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.order"), Mockito.times(1));
            Mockito.verify(mockPath).addParameter("id", "0");

        }
    }

    @Test
    void execute3() {
        request.setParameter("goalIdOrder", "0");

        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<ParameterValidation> ignored1 = Mockito.mockStatic(ParameterValidation.class);
             MockedStatic<UserService> ignored3 = Mockito.mockStatic(UserService.class);
             MockedStatic<OrderUserService> ignored5 = Mockito.mockStatic(OrderUserService.class);
             MockedStatic<RepairOrderService> ignored6 = Mockito.mockStatic(RepairOrderService.class);
             MockedStatic<SendEmail> ignored4 = Mockito.mockStatic(SendEmail.class)) {

            Mockito.when(ParameterValidation.validateGoalId(Mockito.anyString())).thenReturn(true);
            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.order"))).thenReturn(mockPath);


            RepairOrder order = Mockito.mock(RepairOrder.class);
            Mockito.when(order.getUser_id()).thenReturn(4L);
            Mockito.when(order.getStatus_id()).thenReturn(5L);
            Mockito.when(RepairOrderService.get(Mockito.anyLong())).thenReturn(order);

            User user = Mockito.mock(User.class);
            Mockito.when(user.getRole_id()).thenReturn(4);
            Mockito.when(UserService.get(Mockito.anyLong())).thenReturn(user);

            assertEquals(mockPath, new CancelOrderCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.order"), Mockito.times(1));
            Mockito.verify(mockPath).addParameter("id", "0");

        }
    }

    @Test
    void execute4() {
        request.setParameter("goalIdOrder", "0");

        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<ParameterValidation> ignored1 = Mockito.mockStatic(ParameterValidation.class);
             MockedStatic<UserService> ignored3 = Mockito.mockStatic(UserService.class);
             MockedStatic<OrderUserService> ignored5 = Mockito.mockStatic(OrderUserService.class);
             MockedStatic<RepairOrderService> ignored6 = Mockito.mockStatic(RepairOrderService.class);
             MockedStatic<SendEmail> ignored4 = Mockito.mockStatic(SendEmail.class)) {

            Mockito.when(ParameterValidation.validateGoalId(Mockito.anyString())).thenReturn(true);
            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.order"))).thenReturn(mockPath);


            RepairOrder order = Mockito.mock(RepairOrder.class);
            Mockito.when(order.getStatus_id()).thenReturn(5L);
            Mockito.when(RepairOrderService.get(Mockito.anyLong())).thenReturn(order);

            User user = Mockito.mock(User.class);
            Mockito.when(user.getRole_id()).thenReturn(1);
            Mockito.when(UserService.get(Mockito.anyLong())).thenReturn(user);

            assertEquals(mockPath, new CancelOrderCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.order"), Mockito.times(1));
            Mockito.verify(mockPath).addParameter("id", "0");

        }
    }


    @Test
    void allowedUserRoles() {
        assertEquals(Stream.of(Constants.ROLE.Client,
                        Constants.ROLE.Manager,
                        Constants.ROLE.Admin,
                        Constants.ROLE.Craftsman).collect(Collectors.toList()),
                new CancelOrderCommand().allowedUserRoles());
    }
}