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
        logger.debug(" sessionWillPassivate start");
        logger.debug(" " + se.toString());
        logger.debug(" " + se.getSession().getId());
        logger.debug(" " + se.getSession().toString());
        logger.debug(" " + se.getSource());
        logger.debug(" sessionWillPassivate end");
    }

    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
        logger.debug(" sessionDidActivate start");
        logger.debug(" " + se.toString());
        logger.debug(" " + se.getSession().getId());
        logger.debug(" " + se.getSession().toString());
        logger.debug(" " + se.getSource());
        logger.debug(" sessionDidActivate end");
    }
}
