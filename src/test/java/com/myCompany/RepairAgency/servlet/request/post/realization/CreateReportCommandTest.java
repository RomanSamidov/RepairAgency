package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iRepairOrderRepository;
import com.myCompany.RepairAgency.model.entity.DTO.RepairOrderDTO;
import com.myCompany.RepairAgency.model.entity.DTO.RepairOrderDTOFactory;
import com.myCompany.RepairAgency.model.entity.RepairOrder;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.service.InitValuesFromRequestService;
import com.myCompany.RepairAgency.servlet.service.ParameterValidationService;
import com.myCompany.RepairAgency.servlet.service.RepairOrderService;
import com.myCompany.RepairAgency.servlet.util.report.ReportManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateReportCommandTest {
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
    void execute1() throws IOException {
        session.setAttribute("userId", 1L);
        session.setAttribute("language", "ua_UK");
        request.setParameter("newIsUserAllowLetters", "false");
        request.setParameter("reportFormat", "XLS");

        try (MockedStatic<ParameterValidationService> ignored1 = Mockito.mockStatic(ParameterValidationService.class);
             MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<RepairOrderService> ignored3 = Mockito.mockStatic(RepairOrderService.class);
             MockedStatic<InitValuesFromRequestService> ignored4 = Mockito.mockStatic(InitValuesFromRequestService.class);
             MockedStatic<RepairOrderDTOFactory> ignored5 = Mockito.mockStatic(RepairOrderDTOFactory.class);
             MockedStatic<ReportManager> ignored6 = Mockito.mockStatic(ReportManager.class)) {

            Mockito.when(InitValuesFromRequestService.initCraftsmenIds(request)).thenReturn(new long[]{0L});
            Mockito.when(InitValuesFromRequestService.initStatusIds(request)).thenReturn(new long[]{0L});
            Mockito.when(InitValuesFromRequestService.initUserId(request)).thenReturn(0L);
            Mockito.when(InitValuesFromRequestService.initSortType(request)).thenReturn(iRepairOrderRepository.SORT_TYPE.ORDER_BY_ID_ASC);


            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.download"))).thenReturn(mockPath);
            Mockito.when(ParameterValidationService.forAdminCreateOrder(request)).thenReturn(false);

            Mockito.when(RepairOrderService.countByCraftUserStatus(Mockito.any(), Mockito.anyLong(), Mockito.any())).thenReturn(1L);


            ArrayList<RepairOrder> orders = new ArrayList<>();
            orders.add(Mockito.mock(RepairOrder.class));
            Mockito.when(RepairOrderService.getByCraftUserStatus(
                    Mockito.any(), Mockito.anyLong(), Mockito.any(), Mockito.any(), Mockito.anyLong(), Mockito.anyLong()
            )).thenReturn(orders);

            ArrayList<RepairOrderDTO> ordersDTO = new ArrayList<>();
            ordersDTO.add(Mockito.mock(RepairOrderDTO.class));
            Mockito.when(RepairOrderDTOFactory.getRepairOrders(orders)).thenReturn(ordersDTO);

            Mockito.when(ReportManager.getReportWriter(Mockito.any(), Mockito.any(), Mockito.anyLong(), Mockito.any())).thenReturn("dads");


//            User user = Mockito.mock(User.class);
//            Mockito.when(UserService.get(Mockito.anyLong())).thenReturn(user);

            assertEquals(mockPath, new CreateReportCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.download"), Mockito.times(1));

        }
    }

    @Test
    void execute2() throws IOException {
        session.setAttribute("userId", 1L);
        session.setAttribute("language", "ua_UK");
        request.setParameter("newIsUserAllowLetters", "false");
        request.setParameter("reportFormat", "XLSsss");

        try (MockedStatic<ParameterValidationService> ignored1 = Mockito.mockStatic(ParameterValidationService.class);
             MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<RepairOrderService> ignored3 = Mockito.mockStatic(RepairOrderService.class);
             MockedStatic<InitValuesFromRequestService> ignored4 = Mockito.mockStatic(InitValuesFromRequestService.class);
             MockedStatic<RepairOrderDTOFactory> ignored5 = Mockito.mockStatic(RepairOrderDTOFactory.class);
             MockedStatic<ReportManager> ignored6 = Mockito.mockStatic(ReportManager.class)) {

            Mockito.when(InitValuesFromRequestService.initCraftsmenIds(request)).thenReturn(new long[]{0L});
            Mockito.when(InitValuesFromRequestService.initStatusIds(request)).thenReturn(new long[]{0L});
            Mockito.when(InitValuesFromRequestService.initUserId(request)).thenReturn(0L);
            Mockito.when(InitValuesFromRequestService.initSortType(request)).thenReturn(iRepairOrderRepository.SORT_TYPE.ORDER_BY_ID_ASC);


            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.forward.error"))).thenReturn(mockPath);
            Mockito.when(ParameterValidationService.forAdminCreateOrder(request)).thenReturn(false);

            Mockito.when(RepairOrderService.countByCraftUserStatus(Mockito.any(), Mockito.anyLong(), Mockito.any())).thenReturn(1L);


            ArrayList<RepairOrder> orders = new ArrayList<>();
            orders.add(Mockito.mock(RepairOrder.class));
            Mockito.when(RepairOrderService.getByCraftUserStatus(
                    Mockito.any(), Mockito.anyLong(), Mockito.any(), Mockito.any(), Mockito.anyLong(), Mockito.anyLong()
            )).thenReturn(orders);

            ArrayList<RepairOrderDTO> ordersDTO = new ArrayList<>();
            ordersDTO.add(Mockito.mock(RepairOrderDTO.class));
            Mockito.when(RepairOrderDTOFactory.getRepairOrders(orders)).thenReturn(ordersDTO);

            Mockito.when(ReportManager.getReportWriter(Mockito.any(), Mockito.any(), Mockito.anyLong(), Mockito.any())).thenThrow(new IOException());


//            User user = Mockito.mock(User.class);
//            Mockito.when(UserService.get(Mockito.anyLong())).thenReturn(user);

            assertEquals(mockPath, new CreateReportCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.forward.error"), Mockito.times(1));

        }
    }

    @Test
    void allowedUserRoles() {
        assertEquals(Stream.of(Constants.ROLE.Client,
                        Constants.ROLE.Admin,
                        Constants.ROLE.Craftsman,
                        Constants.ROLE.Manager).collect(Collectors.toList()),
                new CreateReportCommand().allowedUserRoles());
    }
}