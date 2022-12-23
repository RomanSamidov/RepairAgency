package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import jakarta.servlet.http.HttpServletRequest;

public class ShowPricingCommandI implements IActionCommand {
    @Override
    public Path execute(HttpServletRequest request) {
        Path page = PathFactory.getPath("path.page.forward.pricing");
        request.setAttribute("title", "title.pricing");
        return page;
    }
}