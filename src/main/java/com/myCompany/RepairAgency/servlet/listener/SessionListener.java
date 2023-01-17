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
        logger.debug(" Session created start");
        logger.debug(" " + se.toString());
        logger.debug(" " + se.getSession().getId());
        logger.debug(" " + se.getSession().toString());
        logger.debug(" " + se.getSource().toString());
        logger.debug(" Session created end");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        logger.debug(" Session destroyed start");
        logger.debug(" " + se.toString());
        logger.debug(" " + se.getSession().getId());
        logger.debug(" " + se.getSession().toString());
        logger.debug(" " + se.getSource().toString());
        logger.debug(" Session destroyed end");
    }
}
