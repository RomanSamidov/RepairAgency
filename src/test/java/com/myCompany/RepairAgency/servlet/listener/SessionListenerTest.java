package com.myCompany.RepairAgency.servlet.listener;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class SessionListenerTest {
    @Test
    void execute() {
        HttpSessionEvent event = Mockito.mock(HttpSessionEvent.class);
        Mockito.when(event.getSession()).thenReturn(Mockito.mock(HttpSession.class));
        Mockito.when(event.getSource()).thenReturn(Mockito.mock(Object.class));

        SessionListener listener = new SessionListener();

        listener.sessionCreated(event);
        listener.sessionDestroyed(event);
    }
}