package com.myCompany.RepairAgency.servlet.listener;

import jakarta.servlet.ServletRequestAttributeEvent;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


class RequestAttributeListenerTest {
    @Test
    void execute() {
        ServletRequestAttributeEvent event = Mockito.mock(ServletRequestAttributeEvent.class);

        RequestAttributeListener listener = new RequestAttributeListener();

        listener.attributeAdded(event);
        listener.attributeRemoved(event);
        listener.attributeReplaced(event);
    }
}