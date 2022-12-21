package com.myCompany.RepairAgency.servlet.listener;

import jakarta.servlet.ServletRequestAttributeEvent;
import jakarta.servlet.ServletRequestAttributeListener;
import jakarta.servlet.annotation.WebListener;

import static com.myCompany.RepairAgency.servlet.listener.Logger.log;

@WebListener
public class RequestAttributeListener implements ServletRequestAttributeListener {
    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        log("[RequestAttributeListener] attributeAdded start");
        log("[RequestAttributeListener] " + srae.getName());
        log("[RequestAttributeListener] " + srae.getValue());
        log("[RequestAttributeListener] attributeAdded end");
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        log("[RequestAttributeListener] attributeRemoved start");
        log("[RequestAttributeListener] " + srae.getName());
        log("[RequestAttributeListener] " + srae.getValue());
        log("[RequestAttributeListener] attributeRemoved end");
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        log("[RequestAttributeListener] attributeReplaced start");
        log("[RequestAttributeListener] " + srae.getName());
        log("[RequestAttributeListener] " + srae.getValue());
        log("[RequestAttributeListener] attributeReplaced end");
    }
}
