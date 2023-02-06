package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.entity.RepairOrder;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
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

class SetOrderStatusCommandTest {
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
        request.setParameter("goalIdOrder", "0");
        request.setParameter("goalOrderStatus", "0");

        try (MockedStatic<ParameterValidation> ignored1 = Mockito.mockStatic(ParameterValidation.class);
             MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<UserService> ignored3 = Mockito.mockStatic(UserService.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.orders"))).thenReturn(mockPath);
            Mockito.when(ParameterValidation.validateGoalId("0")).thenReturn(false);

            assertEquals(mockPath, new SetOrderStatusCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.orders"), Mockito.times(1));

        }
    }


    @Test
    void execute2() {
        request.setParameter("goalIdOrder", "0");
        request.setParameter("goalOrderStatus", "0");

        try (MockedStatic<ParameterValidation> ignored1 = Mockito.mockStatic(ParameterValidation.class);
             MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<UserService> ignored3 = Mockito.mockStatic(UserService.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.order"))).thenReturn(mockPath);
            Mockito.when(ParameterValidation.validateGoalId("0")).thenReturn(true);
            Mockito.when(ParameterValidation.validateOrderStatusId(request, "0")).thenReturn(false);

            assertEquals(mockPath, new SetOrderStatusCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.order"), Mockito.times(1));

        }
    }


    @Test
    void execute3() {
        request.setParameter("goalIdOrder", "0");
        request.setParameter("goalOrderStatus", "0");

        try (MockedStatic<ParameterValidation> ignored1 = Mockito.mockStatic(ParameterValidation.class);
             MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<RepairOrderService> ignored3 = Mockito.mockStatic(RepairOrderService.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.orders"))).thenReturn(mockPath);
            Mockito.when(ParameterValidation.validateGoalId("0")).thenReturn(true);
            Mockito.when(ParameterValidation.validateOrderStatusId(request, "0")).thenReturn(true);

            Mockito.when(RepairOrderService.get(Mockito.anyLong())).thenReturn(null);

            assertEquals(mockPath, new SetOrderStatusCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.orders"), Mockito.times(1));

        }
    }


    @Test
    void execute4() {
        request.setParameter("goalIdOrder", "0");
        session.setAttribute("userRole", Constants.ROLE.Client);
        request.setParameter("goalOrderStatus", "0");

        try (MockedStatic<ParameterValidation> ignored1 = Mockito.mockStatic(ParameterValidation.class);
             MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<RepairOrderService> ignored3 = Mockito.mockStatic(RepairOrderService.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.order"))).thenReturn(mockPath);
            Mockito.when(ParameterValidation.validateGoalId("0")).thenReturn(true);
            Mockito.when(ParameterValidation.validateOrderStatusId(request, "0")).thenReturn(true);

            RepairOrder repairOrder = Mockito.mock(RepairOrder.class);
            Mockito.when(RepairOrderService.get(Mockito.anyLong())).thenReturn(repairOrder);

            assertEquals(mockPath, new SetOrderStatusCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.order"), Mockito.times(1));

        }
    }

    @Test
    void execute5() {
        request.setParameter("goalIdOrder", "0");
        session.setAttribute("userRole", Constants.ROLE.Craftsman);
        session.setAttribute("userId", 0L);
        request.setParameter("goalOrderStatus", "0");

        try (MockedStatic<ParameterValidation> ignored1 = Mockito.mockStatic(ParameterValidation.class);
             MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<RepairOrderService> ignored3 = Mockito.mockStatic(RepairOrderService.class);
             MockedStatic<UserService> ignored4 = Mockito.mockStatic(UserService.class);
             MockedStatic<SendEmail> ignored5 = Mockito.mockStatic(SendEmail.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.order"))).thenReturn(mockPath);
            Mockito.when(ParameterValidation.validateGoalId("0")).thenReturn(true);
            Mockito.when(ParameterValidation.validateOrderStatusId(request, "0")).thenReturn(true);

            RepairOrder repairOrder = Mockito.mock(RepairOrder.class);
            Mockito.when(repairOrder.getCraftsman_id()).thenReturn(0L);
            Mockito.when(repairOrder.getStatus_id()).thenReturn(6L);
            Mockito.when(RepairOrderService.get(Mockito.anyLong())).thenReturn(repairOrder);

            User user = Mockito.mock(User.class);
            Mockito.when(UserService.get(Mockito.anyLong())).thenReturn(user);

            assertEquals(mockPath, new SetOrderStatusCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.order"), Mockito.times(1));

        }
    }

    @Test
    void allowedUserRoles() {
        assertEquals(Stream.of(Constants.ROLE.Craftsman,
                        Constants.ROLE.Admin).collect(Collectors.toList()),
                new SetOrderStatusCommand().allowedUserRoles());
    }
}