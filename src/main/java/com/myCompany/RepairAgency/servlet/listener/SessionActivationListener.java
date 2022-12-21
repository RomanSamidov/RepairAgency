package com.myCompany.RepairAgency.servlet.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionActivationListener;
import jakarta.servlet.http.HttpSessionEvent;

import static com.myCompany.RepairAgency.servlet.listener.Logger.log;

@WebListener
public class SessionActivationListener implements HttpSessionActivationListener {

    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {
        log("[SessionActivationListener] sessionWillPassivate start");
        log("[SessionActivationListener] " + se.toString());
        log("[SessionActivationListener] " + se.getSession().getId());
        log("[SessionActivationListener] " + se.getSession().toString());
        log("[SessionActivationListener] " + se.getSource());
        log("[SessionActivationListener] sessionWillPassivate end");
    }

    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
        log("[SessionActivationListener] sessionDidActivate start");
        log("[SessionActivationListener] " + se.toString());
        log("[SessionActivationListener] " + se.getSession().getId());
        log("[SessionActivationListener] " + se.getSession().toString());
        log("[SessionActivationListener] " + se.getSource());
        log("[SessionActivationListener] sessionDidActivate end");
    }
}
