package com.myCompany.RepairAgency.servlet.listener;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionBindingEvent;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class SessionBindingListenerTest {
    @Test
    void execute() {
        HttpSessionBindingEvent event = Mockito.mock(HttpSessionBindingEvent.class);
        Mockito.when(event.getSession()).thenReturn(Mockito.mock(HttpSession.class));

        SessionBindingListener listener = new SessionBindingListener();

        listener.valueBound(event);
        listener.valueUnbound(event);
    }
}