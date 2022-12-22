package com.myCompany.RepairAgency.servlet.listener;


import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@WebListener
public class ContextAttributeListener implements ServletContextAttributeListener {
    private static final Logger logger = LogManager.getLogger(ContextAttributeListener.class);

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        logger.debug("[ContextAttributeListener] attributeAdded start");
        logger.debug("[ContextAttributeListener] " + event.getName());
        logger.debug("[ContextAttributeListener] " + event.getValue());
        logger.debug("[ContextAttributeListener] attributeAdded end");
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        logger.debug("[ContextAttributeListener] attributeRemoved start");
        logger.debug("[ContextAttributeListener] " + event.getName());
        logger.debug("[ContextAttributeListener] " + event.getValue());
        logger.debug("[ContextAttributeListener] attributeRemoved end");
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        logger.debug("[ContextAttributeListener] attributeReplaced start");
        logger.debug("[ContextAttributeListener] " + event.getName());
        logger.debug("[ContextAttributeListener] " + event.getValue());
        logger.debug("[ContextAttributeListener] attributeReplaced end");
    }
}
