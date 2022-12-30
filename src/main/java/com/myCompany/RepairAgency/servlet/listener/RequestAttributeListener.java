package com.myCompany.RepairAgency.servlet.listener;

import jakarta.servlet.ServletRequestAttributeEvent;
import jakarta.servlet.ServletRequestAttributeListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@WebListener
public class RequestAttributeListener implements ServletRequestAttributeListener {

    private static final Logger logger = LogManager.getLogger(RequestAttributeListener.class);

    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        logger.debug("[RequestAttributeListener] attributeAdded start");
        logger.debug("[RequestAttributeListener] " + srae.getName());
        logger.debug("[RequestAttributeListener] " + srae.getValue());
        logger.debug("[RequestAttributeListener] attributeAdded end");
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        logger.debug("[RequestAttributeListener] attributeRemoved start");
        logger.debug("[RequestAttributeListener] " + srae.getName());
        logger.debug("[RequestAttributeListener] " + srae.getValue());
        logger.debug("[RequestAttributeListener] attributeRemoved end");
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        logger.debug("[RequestAttributeListener] attributeReplaced start");
        logger.debug("[RequestAttributeListener] " + srae.getName());
        logger.debug("[RequestAttributeListener] " + srae.getValue());
        logger.debug("[RequestAttributeListener] attributeReplaced end");
    }
}
