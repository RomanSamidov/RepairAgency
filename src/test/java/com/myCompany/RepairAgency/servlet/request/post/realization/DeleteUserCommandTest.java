package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.db.abstractDB.exception.MyDBException;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.service.ParameterValidationService;
import com.myCompany.RepairAgency.servlet.service.SendEmailService;
import com.myCompany.RepairAgency.servlet.service.UserService;
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

class DeleteUserCommandTest {
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
        request.setParameter("goalIdUser", "0");

        try (MockedStatic<ParameterValidationService> ignored1 = Mockito.mockStatic(ParameterValidationService.class);
             MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<UserService> ignored3 = Mockito.mockStatic(UserService.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.users"))).thenReturn(mockPath);
            Mockito.when(ParameterValidationService.forAdminCreateOrder(request)).thenReturn(false);

            User user = Mockito.mock(User.class);
            Mockito.when(UserService.get(Mockito.anyLong())).thenThrow(new MyDBException());

            assertEquals(mockPath, new DeleteUserCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.users"), Mockito.times(1));

        }
    }

    @Test
    void execute2() {
        session.setAttribute("userId", 1L);
        request.setParameter("goalIdUser", "0");

        try (MockedStatic<ParameterValidationService> ignored1 = Mockito.mockStatic(ParameterValidationService.class);
             MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<UserService> ignored3 = Mockito.mockStatic(UserService.class);
             MockedStatic<SendEmailService> ignored4 = Mockito.mockStatic(SendEmailService.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.users"))).thenReturn(mockPath);
            Mockito.when(ParameterValidationService.forAdminCreateOrder(request)).thenReturn(false);

            User user = Mockito.mock(User.class);
            Mockito.when(UserService.get(Mockito.anyLong())).thenReturn(user);

            assertEquals(mockPath, new DeleteUserCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.users"), Mockito.times(1));

        }
    }

    @Test
    void allowedUserRoles() {
        assertEquals(Stream.of(Constants.ROLE.Admin
                ).collect(Collectors.toList()),
                new DeleteUserCommand().allowedUserRoles());
    }
}