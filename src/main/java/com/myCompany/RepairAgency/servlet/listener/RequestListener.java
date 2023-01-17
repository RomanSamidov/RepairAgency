package com.myCompany.RepairAgency.servlet.listener;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@WebListener
public class RequestListener implements ServletRequestListener {

    private static final Logger logger = LogManager.getLogger(RequestListener.class);

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        logger.debug(" requestDestroyed");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        logger.debug(" requestInitialized");
    }
}
