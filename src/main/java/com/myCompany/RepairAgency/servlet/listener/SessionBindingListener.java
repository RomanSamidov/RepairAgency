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
        logger.debug(" valueBound start");
        logger.debug(" " + event.toString());
        logger.debug(" " + event.getSession().getId());
        logger.debug(" " + event.getSession().toString());
        logger.debug(" " + event.getSource());
        logger.debug(" valueBound end");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        logger.debug(" valueUnbound start");
        logger.debug(" " + event.toString());
        logger.debug(" " + event.getSession().getId());
        logger.debug(" " + event.getSession().toString());
        logger.debug(" " + event.getSource());
        logger.debug(" valueUnbound end");
    }
}
