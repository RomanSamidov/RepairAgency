package com.myCompany.RepairAgency.servlet.listener;

import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.annotation.WebListener;

import static com.myCompany.RepairAgency.servlet.listener.Logger.log;

@WebListener
public class ContextAttributeListener implements ServletContextAttributeListener {
    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        log("[ContextAttributeListener] attributeAdded start");
        log("[ContextAttributeListener] " + event.getName());
        log("[ContextAttributeListener] " + event.getValue());
        log("[ContextAttributeListener] attributeAdded end");
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        log("[ContextAttributeListener] attributeRemoved start");
        log("[ContextAttributeListener] " + event.getName());
        log("[ContextAttributeListener] " + event.getValue());
        log("[ContextAttributeListener] attributeRemoved end");
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        log("[ContextAttributeListener] attributeReplaced start");
        log("[ContextAttributeListener] " + event.getName());
        log("[ContextAttributeListener] " + event.getValue());
        log("[ContextAttributeListener] attributeReplaced end");
    }
}
