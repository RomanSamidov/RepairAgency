package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.service.UserService;
import com.myCompany.RepairAgency.servlet.util.AttributeFSTR;
import com.myCompany.RepairAgency.servlet.util.ViewValidation;
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

class ShowProfileCommandTest {

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
    void execute() {

        session.setAttribute("userId", 0L);
        try (MockedStatic<UserService> ignored2 = Mockito.mockStatic(UserService.class);
             MockedStatic<AttributeFSTR> ignored3 = Mockito.mockStatic(AttributeFSTR.class);
             MockedStatic<ViewValidation> ignored4 = Mockito.mockStatic(ViewValidation.class)) {


            Mockito.when(ViewValidation.validateForProfilePage(request)).thenReturn(mockPath);


            User user = Mockito.mock(User.class);
            Mockito.when(UserService.get(Mockito.anyLong())).thenReturn(user);

            Mockito.when(user.getRole_id()).thenReturn(Constants.ROLE.Client.ordinal());
            Mockito.when(user.getAccount()).thenReturn(111);
            Mockito.when(user.isAllow_letters()).thenReturn(true);


            assertEquals(mockPath, new ShowProfileCommand().execute(request, response));
            assertEquals("title.cabinet", request.getAttribute("title"));
            assertEquals(111, request.getAttribute("userAccount"));
            assertEquals(true, request.getAttribute("isUserAllowLetters"));
        }
    }


    @Test
    void allowedUserRoles() {
        assertEquals(Stream.of(Constants.ROLE.Client,
                        Constants.ROLE.Admin,
                        Constants.ROLE.Craftsman,
                        Constants.ROLE.Manager).collect(Collectors.toList()),
                new ShowProfileCommand().allowedUserRoles());
    }
}