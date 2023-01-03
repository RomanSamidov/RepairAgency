package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ShowAboutCommand implements IActionCommand {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        Path page = PathFactory.getPath("path.page.forward.about");
        request.setAttribute("title", "title.about");
        return page;
    }
}