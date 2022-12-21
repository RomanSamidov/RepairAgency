package com.myCompany.RepairAgency.servlet.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import static com.myCompany.RepairAgency.servlet.listener.Logger.log;

@WebListener
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log("[SessionListener] Session created start");
        log("[SessionListener] " + se.toString());
        log("[SessionListener] " + se.getSession().getId());
        log("[SessionListener] " + se.getSession().toString());
        log("[SessionListener] " + se.getSource().toString());
        log("[SessionListener] Session created end");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log("[SessionListener] Session destroyed start");
        log("[SessionListener] " + se.toString());
        log("[SessionListener] " + se.getSession().getId());
        log("[SessionListener] " + se.getSession().toString());
        log("[SessionListener] " + se.getSource().toString());
        log("[SessionListener] Session destroyed end");
    }
}
