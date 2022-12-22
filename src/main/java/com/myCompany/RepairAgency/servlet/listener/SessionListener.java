package com.myCompany.RepairAgency.servlet.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@WebListener
public class SessionListener implements HttpSessionListener {

    private static final Logger logger = LogManager.getLogger(SessionListener.class);
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        logger.debug("[SessionListener] Session created start");
        logger.debug("[SessionListener] " + se.toString());
        logger.debug("[SessionListener] " + se.getSession().getId());
        logger.debug("[SessionListener] " + se.getSession().toString());
        logger.debug("[SessionListener] " + se.getSource().toString());
        logger.debug("[SessionListener] Session created end");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        logger.debug("[SessionListener] Session destroyed start");
        logger.debug("[SessionListener] " + se.toString());
        logger.debug("[SessionListener] " + se.getSession().getId());
        logger.debug("[SessionListener] " + se.getSession().toString());
        logger.debug("[SessionListener] " + se.getSource().toString());
        logger.debug("[SessionListener] Session destroyed end");
    }
}
