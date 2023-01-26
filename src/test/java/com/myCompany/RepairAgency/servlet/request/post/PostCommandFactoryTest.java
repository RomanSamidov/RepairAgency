package com.myCompany.RepairAgency.servlet.request.post;

import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.abstractCommandFactory;
import com.myCompany.RepairAgency.servlet.request.post.realization.EmptyCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PostCommandFactoryTest {
    @Mock
    MockHttpServletRequest request;
    @Mock
    MockHttpSession session;


    @BeforeEach
    public void initMocks() {
        request = new MockHttpServletRequest();
        session = new MockHttpSession();
        request.setSession(session);
    }

    @Test
    void defineCommand1() {

        request.setParameter("command", "tEst");

        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedConstruction<EmptyCommand> ignored3 = Mockito.mockConstructionWithAnswer(EmptyCommand.class,
                     invocation -> Mockito.mock(EmptyCommand.class));
             MockedStatic<PostCommandEnum> ignored4 = Mockito.mockStatic(PostCommandEnum.class)) {

            IActionCommand command = Mockito.mock(IActionCommand.class);
            PostCommandEnum commandEnum = Mockito.mock(PostCommandEnum.class);
            Mockito.when(PostCommandEnum.valueOf("TEST")).thenReturn(commandEnum);
            Mockito.when(commandEnum.getCurrentCommand()).thenReturn(command);

            abstractCommandFactory commandFactory = PostCommandFactory.getInstance();

            assertEquals(command, commandFactory.defineCommand(request));
        }
    }

    @Test
    void defineCommand2() {

        request.setParameter("command", (String) null);

        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedConstruction<EmptyCommand> ignored3 = Mockito.mockConstruction(EmptyCommand.class);
             MockedStatic<PostCommandEnum> ignored4 = Mockito.mockStatic(PostCommandEnum.class)) {

            IActionCommand command = Mockito.mock(IActionCommand.class);
            PostCommandEnum commandEnum = Mockito.mock(PostCommandEnum.class);
            Mockito.when(PostCommandEnum.valueOf(Mockito.anyString())).thenReturn(commandEnum);
            Mockito.when(commandEnum.getCurrentCommand()).thenReturn(command);

            abstractCommandFactory commandFactory = PostCommandFactory.getInstance();
            assertEquals(EmptyCommand.class, commandFactory.defineCommand(request).getClass());
        }
    }

    @Test
    void defineCommand3() {

        request.setParameter("command", "tEst");


        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedConstruction<EmptyCommand> ignored3 = Mockito.mockConstruction(EmptyCommand.class);
             MockedStatic<PostCommandEnum> ignored4 = Mockito.mockStatic(PostCommandEnum.class)) {

            IActionCommand command = Mockito.mock(IActionCommand.class);
            PostCommandEnum commandEnum = Mockito.mock(PostCommandEnum.class);
            Mockito.when(PostCommandEnum.valueOf(Mockito.anyString())).thenThrow(new IllegalArgumentException());
            Mockito.when(commandEnum.getCurrentCommand()).thenReturn(command);

            abstractCommandFactory commandFactory = PostCommandFactory.getInstance();

            assertEquals(EmptyCommand.class, commandFactory.defineCommand(request).getClass());
            assertEquals("TEST" + " message.wrongCommand", request.getAttribute("wrongCommand"));
        }
    }
}