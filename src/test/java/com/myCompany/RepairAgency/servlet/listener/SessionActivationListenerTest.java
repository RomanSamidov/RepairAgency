package com.myCompany.RepairAgency.servlet.listener;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class SessionActivationListenerTest {
    @Test
    void execute() {
        HttpSessionEvent event = Mockito.mock(HttpSessionEvent.class);
        Mockito.when(event.getSession()).thenReturn(Mockito.mock(HttpSession.class));

        SessionActivationListener listener = new SessionActivationListener();

        listener.sessionWillPassivate(event);
        listener.sessionDidActivate(event);
    }
}