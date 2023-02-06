package com.myCompany.RepairAgency.servlet.util;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.entity.DTO.RepairOrderDTOFactory;
import com.myCompany.RepairAgency.model.entity.DTO.UserDTOFactory;
import com.myCompany.RepairAgency.model.entity.RepairOrder;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;


class ViewValidationTest {
    @Mock
    Path mockPath;
    @Mock
    MockHttpServletRequest request;
    @Mock
    MockHttpSession session;


    @BeforeEach
    public void initMocks() {
        mockPath = Mockito.mock(Path.class);
        request = new MockHttpServletRequest();
        session = new MockHttpSession();
        request.setSession(session);
    }

    @Test
    void setMenuClient() {
        session.setAttribute("userRole", Constants.ROLE.Client);
        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class)) {
            Mockito.when(PathFactory.getPath(Mockito.anyString())).thenReturn(mockPath);
            ViewValidation.setMenu(request);
        }

    }

    @Test
    void setMenuGuest() {
        session.setAttribute("userRole", Constants.ROLE.Guest);
        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class)) {
            Mockito.when(PathFactory.getPath(Mockito.anyString())).thenReturn(mockPath);
            ViewValidation.setMenu(request);
        }
    }

    @Test
    void setMenuCraftsman() {
        session.setAttribute("userRole", Constants.ROLE.Craftsman);
        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class)) {
            Mockito.when(PathFactory.getPath(Mockito.anyString())).thenReturn(mockPath);
            ViewValidation.setMenu(request);
        }
    }

    @Test
    void setMenuManager() {
        session.setAttribute("userRole", Constants.ROLE.Manager);
        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class)) {
            Mockito.when(PathFactory.getPath(Mockito.anyString())).thenReturn(mockPath);
            ViewValidation.setMenu(request);
        }
    }

    @Test
    void setMenuAdmin() {
        session.setAttribute("userRole", Constants.ROLE.Admin);
        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class)) {
            Mockito.when(PathFactory.getPath(Mockito.anyString())).thenReturn(mockPath);
            ViewValidation.setMenu(request);
        }
    }

    @Test
    void validateForChangePassword1() {
        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class)) {
            Mockito.when(PathFactory.getPath(Mockito.anyString())).thenReturn(mockPath);
            ViewValidation.validateForChangePassword(request);
        }
    }

    @Test
    void validateForChangePassword2() {
        session.setAttribute("waitedCodePassword", "any");
        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class)) {
            Mockito.when(PathFactory.getPath(Mockito.anyString())).thenReturn(mockPath);
            ViewValidation.validateForChangePassword(request);
        }
    }

    @Test
    void validateForOrderPageAdmin() {
        session.setAttribute("userRole", Constants.ROLE.Admin);

        try (MockedStatic<PathFactory> ignored1 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<RepairOrderDTOFactory> ignored2 = Mockito.mockStatic(RepairOrderDTOFactory.class)) {
            Mockito.when(PathFactory.getPath(Mockito.anyString())).thenReturn(mockPath);

            RepairOrder order = Mockito.mock(RepairOrder.class);
            Mockito.when(order.getStatus_id()).thenReturn(3L);

            ViewValidation.validateForOrderPage(request, order);
        }
    }

    @Test
    void validateForOrderPageGuest() {
        session.setAttribute("userRole", Constants.ROLE.Guest);

        try (MockedStatic<PathFactory> ignored1 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<RepairOrderDTOFactory> ignored2 = Mockito.mockStatic(RepairOrderDTOFactory.class)) {
            Mockito.when(PathFactory.getPath(Mockito.anyString())).thenReturn(mockPath);

            RepairOrder order = Mockito.mock(RepairOrder.class);
            Mockito.when(order.getStatus_id()).thenReturn(3L);

            ViewValidation.validateForOrderPage(request, order);
        }
    }

    @Test
    void validateForOrderPageCraftsman1() {
        session.setAttribute("userRole", Constants.ROLE.Craftsman);
        session.setAttribute("userId", 0L);

        try (MockedStatic<PathFactory> ignored1 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<RepairOrderDTOFactory> ignored2 = Mockito.mockStatic(RepairOrderDTOFactory.class)) {
            Mockito.when(PathFactory.getPath(Mockito.anyString())).thenReturn(mockPath);

            RepairOrder order = Mockito.mock(RepairOrder.class);
            Mockito.when(order.getStatus_id()).thenReturn(5L);
            Mockito.when(order.getCraftsman_id()).thenReturn(0L);

            ViewValidation.validateForOrderPage(request, order);
        }
    }


    @Test
    void validateForOrderPageCraftsman2() {
        session.setAttribute("userRole", Constants.ROLE.Craftsman);
        session.setAttribute("userId", 0L);

        try (MockedStatic<PathFactory> ignored1 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<RepairOrderDTOFactory> ignored2 = Mockito.mockStatic(RepairOrderDTOFactory.class)) {
            Mockito.when(PathFactory.getPath(Mockito.anyString())).thenReturn(mockPath);

            RepairOrder order = Mockito.mock(RepairOrder.class);
            Mockito.when(order.getStatus_id()).thenReturn(5L);
            Mockito.when(order.getCraftsman_id()).thenReturn(2L);

            ViewValidation.validateForOrderPage(request, order);
        }
    }


    @Test
    void validateForOrderPageManager1() {
        session.setAttribute("userRole", Constants.ROLE.Manager);

        try (MockedStatic<PathFactory> ignored1 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<RepairOrderDTOFactory> ignored2 = Mockito.mockStatic(RepairOrderDTOFactory.class)) {
            Mockito.when(PathFactory.getPath(Mockito.anyString())).thenReturn(mockPath);

            RepairOrder order = Mockito.mock(RepairOrder.class);
            Mockito.when(order.getStatus_id()).thenReturn(2L);

            ViewValidation.validateForOrderPage(request, order);
        }
    }


    @Test
    void validateForOrderPageManager2() {
        session.setAttribute("userRole", Constants.ROLE.Manager);

        try (MockedStatic<PathFactory> ignored1 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<RepairOrderDTOFactory> ignored2 = Mockito.mockStatic(RepairOrderDTOFactory.class)) {
            Mockito.when(PathFactory.getPath(Mockito.anyString())).thenReturn(mockPath);

            RepairOrder order = Mockito.mock(RepairOrder.class);
            Mockito.when(order.getStatus_id()).thenReturn(1L);

            ViewValidation.validateForOrderPage(request, order);
        }
    }


    @Test
    void validateForOrderPageClient1() {
        session.setAttribute("userRole", Constants.ROLE.Client);
        session.setAttribute("userId", 0L);

        try (MockedStatic<PathFactory> ignored1 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<RepairOrderDTOFactory> ignored2 = Mockito.mockStatic(RepairOrderDTOFactory.class)) {
            Mockito.when(PathFactory.getPath(Mockito.anyString())).thenReturn(mockPath);

            RepairOrder order = Mockito.mock(RepairOrder.class);
            Mockito.when(order.getStatus_id()).thenReturn(2L);
            Mockito.when(order.getUser_id()).thenReturn(0L);

            ViewValidation.validateForOrderPage(request, order);
        }
    }


    @Test
    void validateForOrderPageClient2() {
        session.setAttribute("userRole", Constants.ROLE.Client);
        session.setAttribute("userId", 0L);

        try (MockedStatic<PathFactory> ignored1 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<RepairOrderDTOFactory> ignored2 = Mockito.mockStatic(RepairOrderDTOFactory.class)) {
            Mockito.when(PathFactory.getPath(Mockito.anyString())).thenReturn(mockPath);

            RepairOrder order = Mockito.mock(RepairOrder.class);
            Mockito.when(order.getStatus_id()).thenReturn(6L);
            Mockito.when(order.getUser_id()).thenReturn(0L);

            ViewValidation.validateForOrderPage(request, order);
        }
    }


    @Test
    void validateForOrderPageClient3() {
        session.setAttribute("userRole", Constants.ROLE.Client);
        session.setAttribute("userId", 0L);

        try (MockedStatic<PathFactory> ignored1 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<RepairOrderDTOFactory> ignored2 = Mockito.mockStatic(RepairOrderDTOFactory.class)) {
            Mockito.when(PathFactory.getPath(Mockito.anyString())).thenReturn(mockPath);

            RepairOrder order = Mockito.mock(RepairOrder.class);
            Mockito.when(order.getStatus_id()).thenReturn(6L);
            Mockito.when(order.getUser_id()).thenReturn(1L);

            ViewValidation.validateForOrderPage(request, order);
        }
    }

    @Test
    void validateForOrdersPageGuest() {
        session.setAttribute("userRole", Constants.ROLE.Guest);
        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class)) {
            Mockito.when(PathFactory.getPath(Mockito.anyString())).thenReturn(mockPath);
            ViewValidation.validateForOrdersPage(request);
        }
    }

    @Test
    void validateForOrdersPageAdmin() {
        session.setAttribute("userRole", Constants.ROLE.Admin);
        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class)) {
            Mockito.when(PathFactory.getPath(Mockito.anyString())).thenReturn(mockPath);
            ViewValidation.validateForOrdersPage(request);
        }
    }

    @Test
    void validateForOrdersPageManager() {
        session.setAttribute("userRole", Constants.ROLE.Manager);
        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class)) {
            Mockito.when(PathFactory.getPath(Mockito.anyString())).thenReturn(mockPath);
            ViewValidation.validateForOrdersPage(request);
        }
    }

    @Test
    void validateForOrdersPageCraftsman() {
        session.setAttribute("userRole", Constants.ROLE.Craftsman);
        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class)) {
            Mockito.when(PathFactory.getPath(Mockito.anyString())).thenReturn(mockPath);
            ViewValidation.validateForOrdersPage(request);
        }
    }

    @Test
    void validateForOrdersPageClient() {
        session.setAttribute("userRole", Constants.ROLE.Client);
        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class)) {
            Mockito.when(PathFactory.getPath(Mockito.anyString())).thenReturn(mockPath);
            ViewValidation.validateForOrdersPage(request);
        }
    }

    @Test
    void validateForProfilePageGuest() {
        session.setAttribute("userRole", Constants.ROLE.Guest);
        session.setAttribute("isUserConfirmed", false);
        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class)) {
            Mockito.when(PathFactory.getPath(Mockito.anyString())).thenReturn(mockPath);
            ViewValidation.validateForProfilePage(request);
        }
    }

    @Test
    void validateForProfilePageAdmin() {
        session.setAttribute("userRole", Constants.ROLE.Admin);
        session.setAttribute("isUserConfirmed", true);
        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class)) {
            Mockito.when(PathFactory.getPath(Mockito.anyString())).thenReturn(mockPath);
            ViewValidation.validateForProfilePage(request);
        }
    }

    @Test
    void validateForProfilePageManager() {
        session.setAttribute("userRole", Constants.ROLE.Manager);
        session.setAttribute("isUserConfirmed", false);
        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class)) {
            Mockito.when(PathFactory.getPath(Mockito.anyString())).thenReturn(mockPath);
            ViewValidation.validateForProfilePage(request);
        }
    }

    @Test
    void validateForProfilePageCraftsman() {
        session.setAttribute("userRole", Constants.ROLE.Craftsman);
        session.setAttribute("isUserConfirmed", false);
        session.setAttribute("waitedCode", "any");
        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class)) {
            Mockito.when(PathFactory.getPath(Mockito.anyString())).thenReturn(mockPath);
            ViewValidation.validateForProfilePage(request);
        }
    }

    @Test
    void validateForProfilePageClient() {
        session.setAttribute("userRole", Constants.ROLE.Client);
        session.setAttribute("isUserConfirmed", false);
        session.setAttribute("waitedCode", "any");
        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class)) {
            Mockito.when(PathFactory.getPath(Mockito.anyString())).thenReturn(mockPath);
            ViewValidation.validateForProfilePage(request);
        }
    }

    @Test
    void validateForUsersPageClient() {
        session.setAttribute("userRole", Constants.ROLE.Client);
        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class)) {
            Mockito.when(PathFactory.getPath(Mockito.anyString())).thenReturn(mockPath);
            ViewValidation.validateForUsersPage(request);
        }
    }

    @Test
    void validateForUsersPageAdmin() {
        session.setAttribute("userRole", Constants.ROLE.Admin);
        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class)) {
            Mockito.when(PathFactory.getPath(Mockito.anyString())).thenReturn(mockPath);
            ViewValidation.validateForUsersPage(request);
        }
    }

    @Test
    void validateForUsersPageManager() {
        session.setAttribute("userRole", Constants.ROLE.Manager);
        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class)) {
            Mockito.when(PathFactory.getPath(Mockito.anyString())).thenReturn(mockPath);
            ViewValidation.validateForUsersPage(request);
        }
    }

    @Test
    void validateForUserPageAdmin1() {
        session.setAttribute("userRole", Constants.ROLE.Admin);
        session.setAttribute("userId", 1L);
        request.setParameter("id", "1");

        try (MockedStatic<PathFactory> ignored1 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<UserService> ignored2 = Mockito.mockStatic(UserService.class)) {
            Mockito.when(PathFactory.getPath(Mockito.anyString())).thenReturn(mockPath);

            Mockito.when(UserService.get(Mockito.anyLong())).thenReturn(null);

            ViewValidation.validateForUserPage(request);
        }
    }

    @Test
    void validateForUserPageAdmin2() {
        session.setAttribute("userRole", Constants.ROLE.Admin);
        session.setAttribute("userId", 1L);
        request.setParameter("id", "1");

        try (MockedStatic<PathFactory> ignored1 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<UserService> ignored2 = Mockito.mockStatic(UserService.class);
             MockedStatic<UserDTOFactory> ignored3 = Mockito.mockStatic(UserDTOFactory.class)) {
            Mockito.when(PathFactory.getPath(Mockito.anyString())).thenReturn(mockPath);

            User user = Mockito.mock(User.class);
            Mockito.when(user.getRole_id()).thenReturn(4);
            Mockito.when(UserService.get(Mockito.anyLong())).thenReturn(user);

            ViewValidation.validateForUserPage(request);
        }
    }

    @Test
    void validateForUserPageManager() {
        session.setAttribute("userRole", Constants.ROLE.Manager);
        session.setAttribute("userId", 1L);
        request.setParameter("id", "1");

        try (MockedStatic<PathFactory> ignored1 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<UserService> ignored2 = Mockito.mockStatic(UserService.class);
             MockedStatic<UserDTOFactory> ignored3 = Mockito.mockStatic(UserDTOFactory.class)) {
            Mockito.when(PathFactory.getPath(Mockito.anyString())).thenReturn(mockPath);

            User user = Mockito.mock(User.class);
            Mockito.when(user.getRole_id()).thenReturn(2);
            Mockito.when(UserService.get(Mockito.anyLong())).thenReturn(user);

            ViewValidation.validateForUserPage(request);
        }
    }

    @Test
    void validateForUserPageClient() {
        session.setAttribute("userRole", Constants.ROLE.Client);
        session.setAttribute("userId", 1L);

        try (MockedStatic<PathFactory> ignored1 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<UserService> ignored2 = Mockito.mockStatic(UserService.class);
             MockedStatic<UserDTOFactory> ignored3 = Mockito.mockStatic(UserDTOFactory.class)) {
            Mockito.when(PathFactory.getPath(Mockito.anyString())).thenReturn(mockPath);

            User user = Mockito.mock(User.class);
            Mockito.when(user.getRole_id()).thenReturn(2);
            Mockito.when(UserService.get(Mockito.anyLong())).thenReturn(user);

            ViewValidation.validateForUserPage(request);
        }
    }
}