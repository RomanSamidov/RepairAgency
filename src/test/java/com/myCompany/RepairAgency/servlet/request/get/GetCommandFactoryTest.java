package com.myCompany.RepairAgency.servlet.request.get;

import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.abstractCommandFactory;
import com.myCompany.RepairAgency.servlet.request.get.realization.EmptyCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetCommandFactoryTest {
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

        request.setRequestURI("/controller/teSt");

        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedConstruction<EmptyCommand> ignored3 = Mockito.mockConstructionWithAnswer(EmptyCommand.class,
                     invocation -> Mockito.mock(EmptyCommand.class));
             MockedStatic<GetCommandEnum> ignored4 = Mockito.mockStatic(GetCommandEnum.class)) {

            IActionCommand command = Mockito.mock(IActionCommand.class);
            GetCommandEnum commandEnum = Mockito.mock(GetCommandEnum.class);
            Mockito.when(GetCommandEnum.valueOf("TEST")).thenReturn(commandEnum);
            Mockito.when(commandEnum.getCurrentCommand()).thenReturn(command);

            abstractCommandFactory commandFactory = GetCommandFactory.getInstance();

            assertEquals(command, commandFactory.defineCommand(request));
        }
    }

    @Test
    void defineCommand2() {

        request.setRequestURI("/controller/");

        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedConstruction<EmptyCommand> ignored3 = Mockito.mockConstruction(EmptyCommand.class);
             MockedStatic<GetCommandEnum> ignored4 = Mockito.mockStatic(GetCommandEnum.class)) {

            IActionCommand command = Mockito.mock(IActionCommand.class);
            GetCommandEnum commandEnum = Mockito.mock(GetCommandEnum.class);
            Mockito.when(GetCommandEnum.valueOf(Mockito.anyString())).thenReturn(commandEnum);
            Mockito.when(commandEnum.getCurrentCommand()).thenReturn(command);

            abstractCommandFactory commandFactory = GetCommandFactory.getInstance();
            assertEquals(EmptyCommand.class, commandFactory.defineCommand(request).getClass());
        }
    }

    @Test
    void defineCommand3() {

        request.setRequestURI("/controller/teSt");
        IActionCommand emptyCommand = Mockito.mock(IActionCommand.class);

        try (MockedStatic<PathFactory> ignored2 = Mockito.mockStatic(PathFactory.class);
             MockedConstruction<EmptyCommand> ignored3 = Mockito.mockConstruction(EmptyCommand.class);
             MockedStatic<GetCommandEnum> ignored4 = Mockito.mockStatic(GetCommandEnum.class)) {

            IActionCommand command = Mockito.mock(IActionCommand.class);
            GetCommandEnum commandEnum = Mockito.mock(GetCommandEnum.class);
            Mockito.when(GetCommandEnum.valueOf(Mockito.anyString())).thenThrow(new IllegalArgumentException());
            Mockito.when(commandEnum.getCurrentCommand()).thenReturn(command);

            abstractCommandFactory commandFactory = GetCommandFactory.getInstance();

            assertEquals(EmptyCommand.class, commandFactory.defineCommand(request).getClass());
            assertEquals("TEST" + " message.wrongCommand", request.getAttribute("wrongCommand"));
        }
    }
}