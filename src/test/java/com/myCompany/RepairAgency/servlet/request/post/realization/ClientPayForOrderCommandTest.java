package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.db.abstractDB.exception.MyDBException;
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

class ClientPayForOrderCommandTest {
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
        request.setParameter("goalIdOrder", "1");

        try (MockedStatic<ParameterValidation> ignored1 = Mockito.mockStatic(ParameterValidation.class);
             MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<UserService> ignored3 = Mockito.mockStatic(UserService.class);
             MockedStatic<RepairOrderService> ignored4 = Mockito.mockStatic(RepairOrderService.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.orders"))).thenReturn(mockPath);
            Mockito.when(ParameterValidation.validateGoalId("1")).thenReturn(false);

            User user = Mockito.mock(User.class);
            Mockito.when(UserService.get(Mockito.anyLong())).thenReturn(user);

            assertEquals(mockPath, new ClientPayForOrderCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.orders"), Mockito.times(1));

        }
    }

    @Test
    void execute2() {
        session.setAttribute("userId", 1L);
        request.setParameter("goalIdOrder", "1");

        try (MockedStatic<ParameterValidation> ignored1 = Mockito.mockStatic(ParameterValidation.class);
             MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<UserService> ignored3 = Mockito.mockStatic(UserService.class);
             MockedStatic<RepairOrderService> ignored4 = Mockito.mockStatic(RepairOrderService.class);
             MockedStatic<OrderUserService> ignored5 = Mockito.mockStatic(OrderUserService.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.order"))).thenReturn(mockPath);
            Mockito.when(ParameterValidation.validateGoalId("1")).thenReturn(true);

            User user = Mockito.mock(User.class);
            Mockito.when(user.getId()).thenReturn(1L);
            Mockito.when(UserService.get(Mockito.anyLong())).thenReturn(user);

            RepairOrder repairOrder = Mockito.mock(RepairOrder.class);
            Mockito.when(repairOrder.getUser_id()).thenReturn(1L);
            Mockito.when(RepairOrderService.get(Mockito.anyLong())).thenReturn(repairOrder);

            Mockito.when(OrderUserService.payOrder(Mockito.anyLong())).thenReturn(false);

            assertEquals(mockPath, new ClientPayForOrderCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.order"), Mockito.times(1));

        }
    }

    @Test
    void execute3() {
        session.setAttribute("userId", 1L);
        request.setParameter("goalIdOrder", "1");

        try (MockedStatic<ParameterValidation> ignored1 = Mockito.mockStatic(ParameterValidation.class);
             MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<UserService> ignored3 = Mockito.mockStatic(UserService.class);
             MockedStatic<RepairOrderService> ignored4 = Mockito.mockStatic(RepairOrderService.class);
             MockedStatic<OrderUserService> ignored5 = Mockito.mockStatic(OrderUserService.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.orders"))).thenReturn(mockPath);
            Mockito.when(ParameterValidation.validateGoalId("1")).thenReturn(true);

            User user = Mockito.mock(User.class);
            Mockito.when(user.getId()).thenReturn(1L);
            Mockito.when(UserService.get(Mockito.anyLong())).thenReturn(user);

            RepairOrder repairOrder = Mockito.mock(RepairOrder.class);
            Mockito.when(repairOrder.getUser_id()).thenReturn(1L);
            Mockito.when(RepairOrderService.get(Mockito.anyLong())).thenReturn(repairOrder);

            Mockito.when(OrderUserService.payOrder(Mockito.anyLong())).thenThrow(new MyDBException());

            assertEquals(mockPath, new ClientPayForOrderCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.orders"), Mockito.times(1));

        }
    }

    @Test
    void execute4() {
        session.setAttribute("userId", 1L);
        request.setParameter("goalIdOrder", "1");

        try (MockedStatic<ParameterValidation> ignored1 = Mockito.mockStatic(ParameterValidation.class);
             MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<UserService> ignored3 = Mockito.mockStatic(UserService.class);
             MockedStatic<RepairOrderService> ignored4 = Mockito.mockStatic(RepairOrderService.class);
             MockedStatic<OrderUserService> ignored5 = Mockito.mockStatic(OrderUserService.class);
             MockedStatic<SendEmail> ignored6 = Mockito.mockStatic(SendEmail.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.order"))).thenReturn(mockPath);
            Mockito.when(ParameterValidation.validateGoalId("1")).thenReturn(true);

            User user = Mockito.mock(User.class);
            Mockito.when(user.getId()).thenReturn(1L);
            Mockito.when(UserService.get(Mockito.anyLong())).thenReturn(user);

            RepairOrder repairOrder = Mockito.mock(RepairOrder.class);
            Mockito.when(repairOrder.getUser_id()).thenReturn(1L);
            Mockito.when(RepairOrderService.get(Mockito.anyLong())).thenReturn(repairOrder);

            Mockito.when(OrderUserService.payOrder(Mockito.anyLong())).thenReturn(true);

            assertEquals(mockPath, new ClientPayForOrderCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.order"), Mockito.times(1));

        }
    }

    @Test
    void allowedUserRoles() {
        assertEquals(Stream.of(Constants.ROLE.Client).collect(Collectors.toList()),
                new ClientPayForOrderCommand().allowedUserRoles());
    }
}