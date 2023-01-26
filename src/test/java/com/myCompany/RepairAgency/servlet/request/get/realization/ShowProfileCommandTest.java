package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iUserRepository;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.service.AttributeFSTRService;
import com.myCompany.RepairAgency.servlet.service.ViewValidationService;
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
        try (MockedStatic<ModelManager> ignored2 = Mockito.mockStatic(ModelManager.class);
             MockedStatic<AttributeFSTRService> ignored3 = Mockito.mockStatic(AttributeFSTRService.class);
             MockedStatic<ViewValidationService> ignored4 = Mockito.mockStatic(ViewValidationService.class)) {

            ModelManager manager = Mockito.mock(ModelManager.class);
            Mockito.when(ModelManager.getInstance()).thenReturn(manager);

            Mockito.when(ViewValidationService.validateForProfilePage(request)).thenReturn(mockPath);

            iUserRepository userRepo = Mockito.mock(iUserRepository.class);
            User user = Mockito.mock(User.class);
            Mockito.when(userRepo.getById(Mockito.anyLong())).thenReturn(user);
            Mockito.when(manager.getUserRepository()).thenReturn(userRepo);

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