package com.myCompany.RepairAgency.servlet.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionActivationListener;
import jakarta.servlet.http.HttpSessionEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@WebListener
public class SessionActivationListener implements HttpSessionActivationListener {

    private static final Logger logger = LogManager.getLogger(SessionActivationListener.class);

    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {
        logger.debug("[SessionActivationListener] sessionWillPassivate start");
        logger.debug("[SessionActivationListener] " + se.toString());
        logger.debug("[SessionActivationListener] " + se.getSession().getId());
        logger.debug("[SessionActivationListener] " + se.getSession().toString());
        logger.debug("[SessionActivationListener] " + se.getSource());
        logger.debug("[SessionActivationListener] sessionWillPassivate end");
    }

    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
        logger.debug("[SessionActivationListener] sessionDidActivate start");
        logger.debug("[SessionActivationListener] " + se.toString());
        logger.debug("[SessionActivationListener] " + se.getSession().getId());
        logger.debug("[SessionActivationListener] " + se.getSession().toString());
        logger.debug("[SessionActivationListener] " + se.getSource());
        logger.debug("[SessionActivationListener] sessionDidActivate end");
    }
}
