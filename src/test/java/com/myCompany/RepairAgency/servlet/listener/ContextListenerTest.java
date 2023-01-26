package com.myCompany.RepairAgency.servlet.listener;

import jakarta.servlet.ServletContextEvent;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ContextListenerTest {
    @Test
    void execute() {
        ServletContextEvent event = Mockito.mock(ServletContextEvent.class);

        ContextListener listener = new ContextListener();

        listener.contextInitialized(event);
        listener.contextDestroyed(event);
    }
}