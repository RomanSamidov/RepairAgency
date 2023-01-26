package com.myCompany.RepairAgency.servlet.listener;

import jakarta.servlet.ServletContextAttributeEvent;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ContextAttributeListenerTest {
    @Test
    void execute() {
        ServletContextAttributeEvent event = Mockito.mock(ServletContextAttributeEvent.class);

        ContextAttributeListener listener = new ContextAttributeListener();

        listener.attributeAdded(event);
        listener.attributeRemoved(event);
        listener.attributeReplaced(event);
    }

}