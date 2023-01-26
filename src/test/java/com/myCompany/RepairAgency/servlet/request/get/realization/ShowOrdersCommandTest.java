package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iRepairOrderRepository;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iUserRepository;
import com.myCompany.RepairAgency.model.entity.DTO.RepairOrderDTO;
import com.myCompany.RepairAgency.model.entity.DTO.RepairOrderDTOFactory;
import com.myCompany.RepairAgency.model.entity.DTO.UserDTO;
import com.myCompany.RepairAgency.model.entity.DTO.UserDTOFactory;
import com.myCompany.RepairAgency.model.entity.RepairOrder;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.service.AttributeFSTRService;
import com.myCompany.RepairAgency.servlet.service.InitValuesFromRequestService;
import com.myCompany.RepairAgency.servlet.service.ViewValidationService;
import com.myCompany.RepairAgency.servlet.util.ForTables;
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

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ShowOrdersCommandTest {
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
        session.setAttribute("userRole", Constants.ROLE.Admin);

        try (MockedStatic<ModelManager> ignored1 = Mockito.mockStatic(ModelManager.class); MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<AttributeFSTRService> ignored3 = Mockito.mockStatic(AttributeFSTRService.class);
             MockedStatic<ViewValidationService> ignored4 = Mockito.mockStatic(ViewValidationService.class);
             MockedStatic<InitValuesFromRequestService> ignored5 = Mockito.mockStatic(InitValuesFromRequestService.class);
             MockedStatic<ForTables> ignored6 = Mockito.mockStatic(ForTables.class);
             MockedStatic<RepairOrderDTOFactory> ignored7 = Mockito.mockStatic(RepairOrderDTOFactory.class);
             MockedStatic<UserDTOFactory> ignored8 = Mockito.mockStatic(UserDTOFactory.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.forward.common.change_password"))).thenReturn(mockPath);
            Mockito.when(ViewValidationService.validateForOrdersPage(request)).thenReturn(mockPath);

            Mockito.when(InitValuesFromRequestService.initCraftsmenIds(request, Constants.ROLE.Admin)).thenReturn(new long[]{1, 2});
            Mockito.when(InitValuesFromRequestService.initStatusIds(request)).thenReturn(new long[]{1, 2, 3, 4});
            Mockito.when(InitValuesFromRequestService.initUserId(request, Constants.ROLE.Admin)).thenReturn(0L);
            Mockito.when(InitValuesFromRequestService.initSortType(request)).thenReturn(iRepairOrderRepository.SORT_TYPE.ORDER_BY_ID_ASC);


            ArrayList<RepairOrder> orders = new ArrayList<>();
            orders.add(Mockito.mock(RepairOrder.class));
            iRepairOrderRepository orderRepo = Mockito.mock(iRepairOrderRepository.class);
            Mockito.when(orderRepo.getByCraftUserStatus(
                    Mockito.any(), Mockito.anyLong(), Mockito.any(), Mockito.any(), Mockito.anyLong(), Mockito.anyLong()
            )).thenReturn(orders);

            ModelManager manager = Mockito.mock(ModelManager.class);
            Mockito.when(manager.getRepairOrderRepository()).thenReturn(orderRepo);

            Mockito.when(ModelManager.getInstance()).thenReturn(manager);

            Mockito.when(orderRepo.countByCraftUserStatus(Mockito.any(), Mockito.anyLong(), Mockito.any())).thenReturn(100L);

            Mockito.when(ForTables.initSkipQuantity(Mockito.eq("Orders"), Mockito.anyLong(), Mockito.eq(request))).thenReturn(new int[]{0, 5});

            ArrayList<RepairOrderDTO> ordersDTO = new ArrayList<>();
            ordersDTO.add(Mockito.mock(RepairOrderDTO.class));
            Mockito.when(RepairOrderDTOFactory.getRepairOrders(orders)).thenReturn(ordersDTO);

            iUserRepository userRepo = Mockito.mock(iUserRepository.class);
            ArrayList<User> users = new ArrayList<>();
            users.add(Mockito.mock(User.class));
            Mockito.when(userRepo.getByRole(Mockito.eq((long) (Constants.ROLE.Craftsman.ordinal())),
                    Mockito.anyInt(), Mockito.anyInt())).thenReturn(users);
            Mockito.when(manager.getUserRepository()).thenReturn(userRepo);

            ArrayList<UserDTO> usersDTO = new ArrayList<>();
            usersDTO.add(Mockito.mock(UserDTO.class));
            Mockito.when(UserDTOFactory.getUsers(users)).thenReturn(usersDTO);

            ArrayList<Constants.ORDER_STATUS> orderStatuses = new ArrayList<>(List.of(Constants.ORDER_STATUS.values()));
            orderStatuses.remove(0);

            assertEquals(mockPath, new ShowOrdersCommand().execute(request, response));
            assertEquals("title.orders", request.getAttribute("title"));
            assertEquals(ordersDTO, request.getAttribute("orders"));
            assertEquals(orderStatuses, request.getAttribute("orderStatuses"));
            assertArrayEquals(Constants.REPORT_FORMAT.values(), (Object[]) request.getAttribute("reportFormats"));
            assertEquals(iRepairOrderRepository.SORT_TYPE.ORDER_BY_ID_ASC, request.getAttribute("sortTypeOrders"));
            assertEquals(usersDTO, request.getAttribute("craftsmen"));


//            ignored2.verify( () -> PathFactory.getPath("path.page.forward.common.change_password"), Mockito.times(1));
        }
    }

    @Test
    void execute2() {
        session.setAttribute("userRole", Constants.ROLE.Client);

        try (MockedStatic<ModelManager> ignored1 = Mockito.mockStatic(ModelManager.class); MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<AttributeFSTRService> ignored3 = Mockito.mockStatic(AttributeFSTRService.class);
             MockedStatic<ViewValidationService> ignored4 = Mockito.mockStatic(ViewValidationService.class);
             MockedStatic<InitValuesFromRequestService> ignored5 = Mockito.mockStatic(InitValuesFromRequestService.class);
             MockedStatic<ForTables> ignored6 = Mockito.mockStatic(ForTables.class);
             MockedStatic<RepairOrderDTOFactory> ignored7 = Mockito.mockStatic(RepairOrderDTOFactory.class);
             MockedStatic<UserDTOFactory> ignored8 = Mockito.mockStatic(UserDTOFactory.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.forward.common.change_password"))).thenReturn(mockPath);
            Mockito.when(ViewValidationService.validateForOrdersPage(request)).thenReturn(mockPath);

            Mockito.when(InitValuesFromRequestService.initCraftsmenIds(request, Constants.ROLE.Admin)).thenReturn(new long[]{1, 2});
            Mockito.when(InitValuesFromRequestService.initStatusIds(request)).thenReturn(new long[]{1, 2, 3, 4});
            Mockito.when(InitValuesFromRequestService.initUserId(request, Constants.ROLE.Admin)).thenReturn(0L);
            Mockito.when(InitValuesFromRequestService.initSortType(request)).thenReturn(iRepairOrderRepository.SORT_TYPE.ORDER_BY_ID_ASC);


            ArrayList<RepairOrder> orders = new ArrayList<>();
            orders.add(Mockito.mock(RepairOrder.class));
            iRepairOrderRepository orderRepo = Mockito.mock(iRepairOrderRepository.class);
            Mockito.when(orderRepo.getByCraftUserStatus(
                    Mockito.any(), Mockito.anyLong(), Mockito.any(), Mockito.any(), Mockito.anyLong(), Mockito.anyLong()
            )).thenReturn(orders);

            ModelManager manager = Mockito.mock(ModelManager.class);
            Mockito.when(manager.getRepairOrderRepository()).thenReturn(orderRepo);

            Mockito.when(ModelManager.getInstance()).thenReturn(manager);

            Mockito.when(orderRepo.countByCraftUserStatus(Mockito.any(), Mockito.anyLong(), Mockito.any())).thenReturn(0L);

            Mockito.when(ForTables.initSkipQuantity(Mockito.eq("Orders"), Mockito.anyLong(), Mockito.eq(request))).thenReturn(new int[]{0, 5});

            ArrayList<RepairOrderDTO> ordersDTO = new ArrayList<>();
            ordersDTO.add(Mockito.mock(RepairOrderDTO.class));
            Mockito.when(RepairOrderDTOFactory.getRepairOrders(orders)).thenReturn(ordersDTO);

            iUserRepository userRepo = Mockito.mock(iUserRepository.class);
            ArrayList<User> users = new ArrayList<>();
            users.add(Mockito.mock(User.class));
            Mockito.when(userRepo.getByRole(Mockito.eq((long) (Constants.ROLE.Craftsman.ordinal())),
                    Mockito.anyInt(), Mockito.anyInt())).thenReturn(users);
            Mockito.when(manager.getUserRepository()).thenReturn(userRepo);

            ArrayList<UserDTO> usersDTO = new ArrayList<>();
            usersDTO.add(Mockito.mock(UserDTO.class));
            Mockito.when(UserDTOFactory.getUsers(users)).thenReturn(usersDTO);

            ArrayList<Constants.ORDER_STATUS> orderStatuses = new ArrayList<>(List.of(Constants.ORDER_STATUS.values()));
            orderStatuses.remove(0);

            assertEquals(mockPath, new ShowOrdersCommand().execute(request, response));
            assertEquals("title.my_orders", request.getAttribute("title"));
            assertEquals("text.there_are_no_orders", request.getAttribute("error"));
            assertEquals(ordersDTO, request.getAttribute("orders"));
            assertEquals(orderStatuses, request.getAttribute("orderStatuses"));
            assertArrayEquals(Constants.REPORT_FORMAT.values(), (Object[]) request.getAttribute("reportFormats"));
            assertEquals(iRepairOrderRepository.SORT_TYPE.ORDER_BY_ID_ASC, request.getAttribute("sortTypeOrders"));
            assertEquals(usersDTO, request.getAttribute("craftsmen"));
//            ignored2.verify( () -> PathFactory.getPath("path.page.forward.common.change_password"), Mockito.times(1));
        }
    }


    @Test
    void allowedUserRoles() {
        assertEquals(Stream.of(Constants.ROLE.Client,
                        Constants.ROLE.Admin,
                        Constants.ROLE.Craftsman,
                        Constants.ROLE.Manager).collect(Collectors.toList()),
                new ShowOrdersCommand().allowedUserRoles());
    }
}