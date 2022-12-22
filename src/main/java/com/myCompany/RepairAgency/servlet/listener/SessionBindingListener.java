package com.myCompany.RepairAgency.servlet.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@WebListener
public class SessionBindingListener implements HttpSessionBindingListener {

    private static final Logger logger = LogManager.getLogger(SessionBindingListener.class);

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        logger.debug("[SessionBindingListener] valueBound start");
        logger.debug("[SessionBindingListener] " + event.toString());
        logger.debug("[SessionBindingListener] " + event.getSession().getId());
        logger.debug("[SessionBindingListener] " + event.getSession().toString());
        logger.debug("[SessionBindingListener] " + event.getSource());
        logger.debug("[SessionBindingListener] valueBound end");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        logger.debug("[SessionBindingListener] valueUnbound start");
        logger.debug("[SessionBindingListener] " + event.toString());
        logger.debug("[SessionBindingListener] " + event.getSession().getId());
        logger.debug("[SessionBindingListener] " + event.getSession().toString());
        logger.debug("[SessionBindingListener] " + event.getSource());
        logger.debug("[SessionBindingListener] valueUnbound end");
    }
}
