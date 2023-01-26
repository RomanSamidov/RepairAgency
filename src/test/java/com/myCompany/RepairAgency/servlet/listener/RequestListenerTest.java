package com.myCompany.RepairAgency.servlet.listener;

import jakarta.servlet.ServletRequestEvent;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


class RequestListenerTest {
    @Test
    void execute() {
        ServletRequestEvent event = Mockito.mock(ServletRequestEvent.class);

        RequestListener listener = new RequestListener();

        listener.requestDestroyed(event);
        listener.requestInitialized(event);
    }
}