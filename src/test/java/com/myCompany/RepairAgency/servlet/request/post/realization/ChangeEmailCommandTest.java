package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iUserRepository;
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

class ChangeEmailCommandTest {
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
        request.setParameter("email", "0");

        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<ModelManager> ignored3 = Mockito.mockStatic(ModelManager.class);
             MockedStatic<SendEmailService> ignored4 = Mockito.mockStatic(SendEmailService.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.redirect.cabinet"))).thenReturn(mockPath);

            iUserRepository userRepo = Mockito.mock(iUserRepository.class);

            ModelManager manager = Mockito.mock(ModelManager.class);
            Mockito.when(ModelManager.getInstance()).thenReturn(manager);
            Mockito.when(manager.getUserRepository()).thenReturn(userRepo);

            User user = Mockito.mock(User.class);
            Mockito.when(userRepo.getById(Mockito.anyLong())).thenReturn(user);

            assertEquals(mockPath, new ChangeEmailCommand().execute(request, response));
            ignored2.verify(() -> PathFactory.getPath("path.page.redirect.cabinet"), Mockito.times(1));

        }
    }


    @Test
    void allowedUserRoles() {
        assertEquals(Stream.of(Constants.ROLE.Client,
                        Constants.ROLE.Admin,
                        Constants.ROLE.Manager,
                        Constants.ROLE.Craftsman).collect(Collectors.toList()),
                new ChangeEmailCommand().allowedUserRoles());
    }
}