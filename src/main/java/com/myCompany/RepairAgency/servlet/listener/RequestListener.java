package com.myCompany.RepairAgency.servlet.listener;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;

import static com.myCompany.RepairAgency.servlet.listener.Logger.log;

@WebListener
public class RequestListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        log("[RequestListener] requestDestroyed");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        log("[RequestListener] requestInitialized");
    }
}
