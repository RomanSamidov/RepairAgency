package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.servlet.Path;
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

class ShowUserCommandTest {
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
        try (MockedStatic<ViewValidation> ignored2 = Mockito.mockStatic(ViewValidation.class);
             MockedStatic<AttributeFSTR> ignored3 = Mockito.mockStatic(AttributeFSTR.class)) {

            Mockito.when(ViewValidation.validateForUserPage(request)).thenReturn(mockPath);

            assertEquals(mockPath, new ShowUserCommand().execute(request, response));
            assertEquals("title.user", request.getAttribute("title"));
//            ignored2.verify( () -> PathFactory.getPath("path.page.forward.guest.signup"), Mockito.times(1));
        }
    }

    @Test
    void allowedUserRoles() {
        assertEquals(Stream.of(Constants.ROLE.Manager,
                        Constants.ROLE.Admin).collect(Collectors.toList()),
                new ShowUserCommand().allowedUserRoles());
    }
}