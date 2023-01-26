package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShowFAQsCommandTest {
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
        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class)) {

            Mockito.when(PathFactory.getPath(Mockito.eq("path.page.forward.common.faqs"))).thenReturn(mockPath);

            assertEquals(mockPath, new ShowFAQsCommand().execute(request, response));
            assertEquals("title.faqs", request.getAttribute("title"));
            ignored2.verify(() -> PathFactory.getPath("path.page.forward.common.faqs"), Mockito.times(1));
        }
    }
}