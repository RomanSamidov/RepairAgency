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
        logger.debug(" attributeAdded start");
        logger.debug(" " + srae.getName());
        logger.debug(" " + srae.getValue());
        logger.debug(" attributeAdded end");
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        logger.debug(" attributeRemoved start");
        logger.debug(" " + srae.getName());
        logger.debug(" " + srae.getValue());
        logger.debug(" attributeRemoved end");
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        logger.debug(" attributeReplaced start");
        logger.debug(" " + srae.getName());
        logger.debug(" " + srae.getValue());
        logger.debug(" attributeReplaced end");
    }
}
