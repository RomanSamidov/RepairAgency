package com.myCompany.RepairAgency.servlet.listener;


import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import static com.myCompany.RepairAgency.servlet.listener.Logger.log;

@WebListener
public class ContextListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    log("[ContextListener] contextInitialized");
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    log("[ContextListener] contextDestroyed");
  }

}
