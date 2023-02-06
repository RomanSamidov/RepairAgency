package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.entity.DTO.UserDTO;
import com.myCompany.RepairAgency.model.entity.DTO.UserDTOFactory;
import com.myCompany.RepairAgency.model.entity.RepairOrder;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.service.RepairOrderService;
import com.myCompany.RepairAgency.servlet.service.UserService;
import com.myCompany.RepairAgency.servlet.util.InitValuesFromRequest;
import com.myCompany.RepairAgency.servlet.util.ViewValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShowOrderPageCommandTest {
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
             MockedStatic<InitValuesFromRequest> ignored3 = Mockito.mockStatic(InitValuesFromRequest.class)) {

            Mockito.when(InitValuesFromRequest.initGoalId(request)).thenReturn(0L);
            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.orders"))).thenReturn(mockPath);

            assertEquals(mockPath, new ShowOrderPageCommand().execute(request, response));
            assertEquals("title.order", request.getAttribute("title"));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.orders"), Mockito.times(1));
        }
    }

    @Test
    void execute2() {
        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<RepairOrderService> ignored1 = Mockito.mockStatic(RepairOrderService.class);
             MockedStatic<InitValuesFromRequest> ignored3 = Mockito.mockStatic(InitValuesFromRequest.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.orders"))).thenReturn(mockPath);
            request.setParameter("id", "1");
            Mockito.when(InitValuesFromRequest.initGoalId(request)).thenReturn(1L);


            Mockito.when(RepairOrderService.get(Mockito.anyLong())).thenReturn(null);


            assertEquals(mockPath, new ShowOrderPageCommand().execute(request, response));
            assertEquals("title.order", request.getAttribute("title"));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.orders"), Mockito.times(1));
        }
    }

    @Test
    void execute3() {
        try (MockedStatic<RepairOrderService> ignored0 = Mockito.mockStatic(RepairOrderService.class);
             MockedStatic<UserService> ignored1 = Mockito.mockStatic(UserService.class);
             MockedStatic<UserDTOFactory> ignored2 = Mockito.mockStatic(UserDTOFactory.class);
             MockedStatic<ViewValidation> ignored3 = Mockito.mockStatic(ViewValidation.class);
             MockedStatic<InitValuesFromRequest> ignored4 = Mockito.mockStatic(InitValuesFromRequest.class)) {

//            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.orders"))).thenReturn(mockPath);
            request.setParameter("id", "1");
            Mockito.when(InitValuesFromRequest.initGoalId(request)).thenReturn(1L);

            RepairOrder order = Mockito.mock(RepairOrder.class);
            Mockito.when(RepairOrderService.get(Mockito.anyLong())).thenReturn(order);


            ArrayList<User> users = new ArrayList<>();
            users.add(Mockito.mock(User.class));
            Mockito.when(UserService.getByRole(Mockito.eq((long) (Constants.ROLE.Craftsman.ordinal())),
                    Mockito.anyLong(), Mockito.anyLong())).thenReturn(users);


            Mockito.when(ViewValidation.validateForOrderPage(request, order)).thenReturn(mockPath);

            ArrayList<UserDTO> usersDTO = new ArrayList<>();
            usersDTO.add(Mockito.mock(UserDTO.class));
            Mockito.when(UserDTOFactory.getUsers(Mockito.eq(users))).thenReturn(usersDTO);

            assertEquals(mockPath, new ShowOrderPageCommand().execute(request, response));
            assertEquals("title.order", request.getAttribute("title"));
//            ignored2.verify( () -> PathFactory.getPath("path.page.redirect.orders"), Mockito.times(1));

            ArrayList<Constants.ORDER_STATUS> orderStatuses = new ArrayList<>(List.of(Constants.ORDER_STATUS.values()));
            orderStatuses.remove(0);
            assertEquals(orderStatuses, session.getAttribute("orderStatuses"));

            assertEquals(usersDTO, session.getAttribute("craftsmen"));
        }
    }


    @Test
    void allowedUserRoles() {
        assertEquals(Stream.of(Constants.ROLE.Client,
                        Constants.ROLE.Admin,
                        Constants.ROLE.Manager,
                        Constants.ROLE.Craftsman).collect(Collectors.toList()),
                new ShowOrderPageCommand().allowedUserRoles());
    }
}