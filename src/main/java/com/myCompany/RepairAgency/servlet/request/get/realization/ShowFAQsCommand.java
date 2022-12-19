package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.servlet.ConfigurationManager;
import com.myCompany.RepairAgency.servlet.request.ActionCommand;
import jakarta.servlet.http.HttpServletRequest;

public class ShowFAQsCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.faqs");
        request.setAttribute("title", "FAQs");
        return page;
    }
}