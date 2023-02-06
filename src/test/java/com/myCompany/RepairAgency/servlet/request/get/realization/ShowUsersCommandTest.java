package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.entity.DTO.UserDTO;
import com.myCompany.RepairAgency.model.entity.DTO.UserDTOFactory;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.service.UserService;
import com.myCompany.RepairAgency.servlet.util.AttributeFSTR;
import com.myCompany.RepairAgency.servlet.util.ForTables;
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

class ShowUsersCommandTest {
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

        try (MockedStatic<ViewValidation> ignored1 = Mockito.mockStatic(ViewValidation.class);
             MockedStatic<UserService> ignored2 = Mockito.mockStatic(UserService.class);
             MockedStatic<AttributeFSTR> ignored3 = Mockito.mockStatic(AttributeFSTR.class);
             MockedStatic<UserDTOFactory> ignored4 = Mockito.mockStatic(UserDTOFactory.class);
             MockedStatic<ForTables> ignored5 = Mockito.mockStatic(ForTables.class);
             MockedStatic<InitValuesFromRequest> ignored6 = Mockito.mockStatic(InitValuesFromRequest.class)) {


            Mockito.when(ViewValidation.validateForUsersPage(request)).thenReturn(mockPath);
            Mockito.when(InitValuesFromRequest.initRoleId(request)).thenReturn(0L);


            Mockito.when(ForTables.initSkipQuantity(Mockito.eq("Users"), Mockito.anyLong(), Mockito.eq(request))).thenReturn(new int[]{0, 5});

            ArrayList<UserDTO> usersDTO = new ArrayList<>();
            usersDTO.add(Mockito.mock(UserDTO.class));

            ArrayList<User> users = new ArrayList<>();
            users.add(Mockito.mock(User.class));
            Mockito.when(UserDTOFactory.getUsers(users)).thenReturn(usersDTO);

            Mockito.when(UserService.getByRole(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyLong())).thenReturn(users);
            Mockito.when(UserService.countWhereRoleIs(Mockito.anyLong())).thenReturn(0L);


            assertEquals(mockPath, new ShowUsersCommand().execute(request, response));
            assertEquals("title.users", request.getAttribute("title"));
            assertEquals(usersDTO, request.getAttribute("users"));
            assertEquals("text.there_are_no_users", request.getAttribute("error"));

            ArrayList<Constants.ROLE> rolesUsers = new ArrayList<>(List.of(Constants.ROLE.values()));
            rolesUsers.remove(0);
            assertEquals(rolesUsers, request.getAttribute("rolesUsers"));
        }
    }

    @Test
    void execute2() {
        session.setAttribute("userRole", Constants.ROLE.Manager);

        try (MockedStatic<ViewValidation> ignored1 = Mockito.mockStatic(ViewValidation.class);
             MockedStatic<UserService> ignored2 = Mockito.mockStatic(UserService.class);
             MockedStatic<AttributeFSTR> ignored3 = Mockito.mockStatic(AttributeFSTR.class);
             MockedStatic<UserDTOFactory> ignored4 = Mockito.mockStatic(UserDTOFactory.class);
             MockedStatic<ForTables> ignored5 = Mockito.mockStatic(ForTables.class);
             MockedStatic<InitValuesFromRequest> ignored6 = Mockito.mockStatic(InitValuesFromRequest.class)) {


            Mockito.when(ViewValidation.validateForUsersPage(request)).thenReturn(mockPath);
            Mockito.when(InitValuesFromRequest.initRoleId(request)).thenReturn(0L);


            Mockito.when(ForTables.initSkipQuantity(Mockito.eq("Users"), Mockito.anyLong(), Mockito.eq(request))).thenReturn(new int[]{0, 5});

            ArrayList<UserDTO> usersDTO = new ArrayList<>();
            usersDTO.add(Mockito.mock(UserDTO.class));

            ArrayList<User> users = new ArrayList<>();
            users.add(Mockito.mock(User.class));
            Mockito.when(UserDTOFactory.getUsers(users)).thenReturn(usersDTO);

            Mockito.when(UserService.getByRole(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyLong())).thenReturn(users);
            Mockito.when(UserService.countWhereRoleIs(Mockito.anyLong())).thenReturn(0L);


            assertEquals(mockPath, new ShowUsersCommand().execute(request, response));
            assertEquals("title.users", request.getAttribute("title"));
            assertEquals(usersDTO, request.getAttribute("users"));
            assertEquals("text.there_are_no_users", request.getAttribute("error"));

            ArrayList<Constants.ROLE> rolesUsers = new ArrayList<>(List.of(Constants.ROLE.values()));
            rolesUsers.remove(0);
            rolesUsers.remove(0);
            rolesUsers.remove(0);
            assertEquals(rolesUsers, request.getAttribute("rolesUsers"));
        }
    }

    @Test
    void allowedUserRoles() {
        assertEquals(Stream.of(
                        Constants.ROLE.Manager,
                        Constants.ROLE.Admin).collect(Collectors.toList()),
                new ShowUsersCommand().allowedUserRoles());
    }
}