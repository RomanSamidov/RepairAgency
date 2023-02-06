package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.util.ForTables;
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

class OrdersCommandTest {
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
        request.setParameter("statusOrders", "1", "2");
        request.setParameter("craftsmanIdOrders", "6", "7");
        request.setParameter("sortTypeOrders", "ORDER_BY_ID_DESC");
        request.setParameter("createReport", "true");

        try (MockedStatic<ParameterValidation> ignored1 = Mockito.mockStatic(ParameterValidation.class);
             MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<ForTables> ignored3 = Mockito.mockStatic(ForTables.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.orders"))).thenReturn(mockPath);
            Mockito.when(ParameterValidation.forAdminCreateOrder(request)).thenReturn(false);


            assertEquals(mockPath, new OrdersCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.orders"), Mockito.times(1));

        }
    }


    @Test
    void execute2() {
        request.setParameter("statusOrders", "sc");
        request.setParameter("craftsmanIdOrders", "s");
        request.setParameter("sortTypeOrders", "ORDER_BY_ID_DESC");
        request.setParameter("createReport", "true");

        try (MockedStatic<ParameterValidation> ignored1 = Mockito.mockStatic(ParameterValidation.class);
             MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<ForTables> ignored3 = Mockito.mockStatic(ForTables.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.orders"))).thenReturn(mockPath);
            Mockito.when(ParameterValidation.forAdminCreateOrder(request)).thenReturn(false);


            assertEquals(mockPath, new OrdersCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.orders"), Mockito.times(1));

        }
    }

    @Test
    void allowedUserRoles() {
        assertEquals(Stream.of(Constants.ROLE.Client,
                        Constants.ROLE.Admin,
                        Constants.ROLE.Craftsman,
                        Constants.ROLE.Manager).collect(Collectors.toList()),
                new OrdersCommand().allowedUserRoles());
    }
}