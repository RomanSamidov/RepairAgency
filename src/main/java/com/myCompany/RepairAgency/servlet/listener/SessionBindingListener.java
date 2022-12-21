package com.myCompany.RepairAgency.servlet.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

import static com.myCompany.RepairAgency.servlet.listener.Logger.log;

@WebListener
public class SessionBindingListener implements HttpSessionBindingListener {

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        log("[SessionBindingListener] valueBound start");
        log("[SessionBindingListener] " + event.toString());
        log("[SessionBindingListener] " + event.getSession().getId());
        log("[SessionBindingListener] " + event.getSession().toString());
        log("[SessionBindingListener] " + event.getSource());
        log("[SessionBindingListener] valueBound end");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        log("[SessionBindingListener] valueUnbound start");
        log("[SessionBindingListener] " + event.toString());
        log("[SessionBindingListener] " + event.getSession().getId());
        log("[SessionBindingListener] " + event.getSession().toString());
        log("[SessionBindingListener] " + event.getSource());
        log("[SessionBindingListener] valueUnbound end");
    }
}
