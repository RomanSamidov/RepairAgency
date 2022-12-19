package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.servlet.ConfigurationManager;
import com.myCompany.RepairAgency.servlet.request.ActionCommand;
import jakarta.servlet.http.HttpServletRequest;

public class ShowAboutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.about");
        request.setAttribute("title", "About");
        return page;
    }
}