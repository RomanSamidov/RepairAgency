package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iRepairOrderRepository;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iUserRepository;
import com.myCompany.RepairAgency.model.entity.DTO.UserDTO;
import com.myCompany.RepairAgency.model.entity.DTO.UserDTOFactory;
import com.myCompany.RepairAgency.model.entity.RepairOrder;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.service.ViewValidationService;
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
        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.orders"))).thenReturn(mockPath);

            assertEquals(mockPath, new ShowOrderPageCommand().execute(request, response));
            assertEquals("title.order", request.getAttribute("title"));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.orders"), Mockito.times(1));
        }
    }

    @Test
    void execute2() {
        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<ModelManager> ignored1 = Mockito.mockStatic(ModelManager.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.orders"))).thenReturn(mockPath);
            request.setParameter("id", "1");

            iRepairOrderRepository orderRepo = Mockito.mock(iRepairOrderRepository.class);

            ModelManager manager = Mockito.mock(ModelManager.class);
            Mockito.when(manager.getRepairOrderRepository()).thenReturn(orderRepo);
            Mockito.when(ModelManager.getInstance()).thenReturn(manager);

//            RepairOrder order = Mockito.mock(RepairOrder.class);
            Mockito.when(orderRepo.getById(Mockito.anyLong())).thenReturn(null);


            assertEquals(mockPath, new ShowOrderPageCommand().execute(request, response));
            assertEquals("title.order", request.getAttribute("title"));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.orders"), Mockito.times(1));
        }
    }

    @Test
    void execute3() {
        try (MockedStatic<ModelManager> ignored1 = Mockito.mockStatic(ModelManager.class);
             MockedStatic<UserDTOFactory> ignored2 = Mockito.mockStatic(UserDTOFactory.class);
             MockedStatic<ViewValidationService> ignored3 = Mockito.mockStatic(ViewValidationService.class)) {

//            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.orders"))).thenReturn(mockPath);
            request.setParameter("id", "1");

            iRepairOrderRepository orderRepo = Mockito.mock(iRepairOrderRepository.class);

            ModelManager manager = Mockito.mock(ModelManager.class);
            Mockito.when(manager.getRepairOrderRepository()).thenReturn(orderRepo);
            Mockito.when(ModelManager.getInstance()).thenReturn(manager);

            RepairOrder order = Mockito.mock(RepairOrder.class);
            Mockito.when(orderRepo.getById(Mockito.anyLong())).thenReturn(order);

            iUserRepository userRepo = Mockito.mock(iUserRepository.class);
            ArrayList<User> users = new ArrayList<>();
            users.add(Mockito.mock(User.class));
            Mockito.when(userRepo.getByRole(Mockito.eq((long) (Constants.ROLE.Craftsman.ordinal())),
                    Mockito.anyInt(), Mockito.anyInt())).thenReturn(users);
            Mockito.when(manager.getUserRepository()).thenReturn(userRepo);


            Mockito.when(ViewValidationService.validateForOrderPage(request, order)).thenReturn(mockPath);

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