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
        logger.debug(" attributeAdded start");
        logger.debug(" " + event.getName());
        logger.debug(" " + event.getValue());
        logger.debug(" attributeAdded end");
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        logger.debug(" attributeRemoved start");
        logger.debug(" " + event.getName());
        logger.debug(" " + event.getValue());
        logger.debug(" attributeRemoved end");
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        logger.debug(" attributeReplaced start");
        logger.debug(" " + event.getName());
        logger.debug(" " + event.getValue());
        logger.debug(" attributeReplaced end");
    }
}
