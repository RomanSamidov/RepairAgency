package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
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

import static org.junit.jupiter.api.Assertions.assertEquals;


class ShowChangePasswordCommandTest {
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
        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedStatic<AttributeFSTRService> ignored3 = Mockito.mockStatic(AttributeFSTRService.class);
             MockedStatic<ViewValidationService> ignored4 = Mockito.mockStatic(ViewValidationService.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.forward.common.change_password"))).thenReturn(mockPath);

            assertEquals(mockPath, new ShowChangePasswordCommand().execute(request, response));
            assertEquals("title.change_password", request.getAttribute("title"));
            ignored2.verify(() -> PathFactory.getPath("path.page.forward.common.change_password"), Mockito.times(1));
        }
    }
}