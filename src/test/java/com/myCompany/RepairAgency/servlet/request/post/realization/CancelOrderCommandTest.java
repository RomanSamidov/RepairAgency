package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iRepairOrderRepository;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iUserRepository;
import com.myCompany.RepairAgency.model.db.abstractDB.service.iOrderUserService;
import com.myCompany.RepairAgency.model.entity.RepairOrder;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.service.SendEmailService;
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

        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.orders"))).thenReturn(mockPath);

            assertEquals(mockPath, new CancelOrderCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.orders"), Mockito.times(1));

        }
    }

    @Test
    void execute2() {
        request.setParameter("goalIdOrder", "0");

        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<ModelManager> ignored3 = Mockito.mockStatic(ModelManager.class);
             MockedStatic<SendEmailService> ignored4 = Mockito.mockStatic(SendEmailService.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.order"))).thenReturn(mockPath);

            iRepairOrderRepository orderRepo = Mockito.mock(iRepairOrderRepository.class);
            iUserRepository userRepo = Mockito.mock(iUserRepository.class);

            ModelManager manager = Mockito.mock(ModelManager.class);
            Mockito.when(ModelManager.getInstance()).thenReturn(manager);
            Mockito.when(manager.getRepairOrderRepository()).thenReturn(orderRepo);
            Mockito.when(manager.getUserRepository()).thenReturn(userRepo);

            RepairOrder order = Mockito.mock(RepairOrder.class);
            Mockito.when(orderRepo.getById(Mockito.anyLong())).thenReturn(order);

            User user = Mockito.mock(User.class);
            Mockito.when(user.getRole_id()).thenReturn(1);
            Mockito.when(userRepo.getById(Mockito.anyLong())).thenReturn(user);

            assertEquals(mockPath, new CancelOrderCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.order"), Mockito.times(1));
            Mockito.verify(mockPath).addParameter("id", "0");

        }
    }

    @Test
    void execute3() {
        request.setParameter("goalIdOrder", "0");

        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<ModelManager> ignored3 = Mockito.mockStatic(ModelManager.class);
             MockedStatic<SendEmailService> ignored4 = Mockito.mockStatic(SendEmailService.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.order"))).thenReturn(mockPath);

            iRepairOrderRepository orderRepo = Mockito.mock(iRepairOrderRepository.class);
            iUserRepository userRepo = Mockito.mock(iUserRepository.class);

            ModelManager manager = Mockito.mock(ModelManager.class);
            Mockito.when(ModelManager.getInstance()).thenReturn(manager);
            Mockito.when(manager.getRepairOrderRepository()).thenReturn(orderRepo);
            Mockito.when(manager.getUserRepository()).thenReturn(userRepo);

            RepairOrder order = Mockito.mock(RepairOrder.class);
            Mockito.when(order.getUser_id()).thenReturn(4L);
            Mockito.when(order.getStatus_id()).thenReturn(5L);
            Mockito.when(orderRepo.getById(Mockito.anyLong())).thenReturn(order);

            User user = Mockito.mock(User.class);
            Mockito.when(user.getRole_id()).thenReturn(4);
            Mockito.when(userRepo.getById(Mockito.anyLong())).thenReturn(user);

            assertEquals(mockPath, new CancelOrderCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.order"), Mockito.times(1));
            Mockito.verify(mockPath).addParameter("id", "0");

        }
    }

    @Test
    void execute4() {
        request.setParameter("goalIdOrder", "0");

        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<ModelManager> ignored3 = Mockito.mockStatic(ModelManager.class);
             MockedStatic<SendEmailService> ignored4 = Mockito.mockStatic(SendEmailService.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.order"))).thenReturn(mockPath);

            iRepairOrderRepository orderRepo = Mockito.mock(iRepairOrderRepository.class);
            iUserRepository userRepo = Mockito.mock(iUserRepository.class);

            ModelManager manager = Mockito.mock(ModelManager.class);
            Mockito.when(ModelManager.getInstance()).thenReturn(manager);
            Mockito.when(manager.getRepairOrderRepository()).thenReturn(orderRepo);
            Mockito.when(manager.getUserRepository()).thenReturn(userRepo);
            Mockito.when(manager.getOrderUserService()).thenReturn(Mockito.mock(iOrderUserService.class));

            RepairOrder order = Mockito.mock(RepairOrder.class);
            Mockito.when(order.getStatus_id()).thenReturn(5L);
            Mockito.when(orderRepo.getById(Mockito.anyLong())).thenReturn(order);

            User user = Mockito.mock(User.class);
            Mockito.when(user.getRole_id()).thenReturn(1);
            Mockito.when(userRepo.getById(Mockito.anyLong())).thenReturn(user);

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