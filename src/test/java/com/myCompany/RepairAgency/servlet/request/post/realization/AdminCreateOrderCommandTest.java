package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.db.abstractDB.exception.MyDBException;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.service.OrderUserService;
import com.myCompany.RepairAgency.servlet.util.ParameterValidation;
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

class AdminCreateOrderCommandTest {
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

        try (MockedStatic<ParameterValidation> ignored1 = Mockito.mockStatic(ParameterValidation.class);
             MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.orders"))).thenReturn(mockPath);
            Mockito.when(ParameterValidation.forAdminCreateOrder(request)).thenReturn(false);

            assertEquals(mockPath, new AdminCreateOrderCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.orders"), Mockito.times(1));

        }
    }

    @Test
    void execute2() {
        request.setParameter("clientId", "1");
        request.setParameter("orderText", "Test text текст ї");

        try (MockedStatic<ParameterValidation> ignored1 = Mockito.mockStatic(ParameterValidation.class);
             MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<OrderUserService> ignored4 = Mockito.mockStatic(OrderUserService.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.orders"))).thenReturn(mockPath);
            Mockito.when(OrderUserService.createOrder(1L, "Test text текст ї")).thenReturn(true);
            Mockito.when(ParameterValidation.forAdminCreateOrder(request)).thenReturn(true);

            assertEquals(mockPath, new AdminCreateOrderCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.orders"), Mockito.times(1));
            assertEquals("text.order_added", session.getAttribute("errorOrderTextMessage"));

        }
    }

    @Test
    void execute3() {
        request.setParameter("clientId", "1");
        request.setParameter("orderText", "Test text текст ї");

        try (MockedStatic<ParameterValidation> ignored1 = Mockito.mockStatic(ParameterValidation.class);
             MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<OrderUserService> ignored4 = Mockito.mockStatic(OrderUserService.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.orders"))).thenReturn(mockPath);
            Mockito.when(OrderUserService.createOrder(1L, "Test text текст ї")).thenReturn(false);
            Mockito.when(ParameterValidation.forAdminCreateOrder(request)).thenReturn(true);

            assertEquals(mockPath, new AdminCreateOrderCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.orders"), Mockito.times(1));
            assertEquals("text.user_is_not_a_client", session.getAttribute("errorOrderTextMessage"));

        }
    }

    @Test
    void execute4() {
        request.setParameter("clientId", "1");
        request.setParameter("orderText", "Test text текст ї");

        try (MockedStatic<ParameterValidation> ignored1 = Mockito.mockStatic(ParameterValidation.class);
             MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<OrderUserService> ignored4 = Mockito.mockStatic(OrderUserService.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.orders"))).thenReturn(mockPath);
            Mockito.when(OrderUserService.createOrder(1L, "Test text текст ї")).thenThrow(new MyDBException());
            Mockito.when(ParameterValidation.forAdminCreateOrder(request)).thenReturn(true);

            assertEquals(mockPath, new AdminCreateOrderCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.orders"), Mockito.times(1));
            assertEquals("text.there_are_no_user", session.getAttribute("errorOrderClientIdMessage"));

        }
    }


    @Test
    void allowedUserRoles() {
        assertEquals(Stream.of(Constants.ROLE.Admin).collect(Collectors.toList()),
                new AdminCreateOrderCommand().allowedUserRoles());
    }


}