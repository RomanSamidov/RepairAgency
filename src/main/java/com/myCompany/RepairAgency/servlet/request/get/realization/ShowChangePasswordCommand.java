package com.myCompany.RepairAgency.servlet.request.get.realization;


import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.service.AttributeFSTRService;
import com.myCompany.RepairAgency.servlet.service.ViewValidationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ShowChangePasswordCommand implements IActionCommand {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        Path page = PathFactory.getPath("path.page.forward.common.change_password");
        request.setAttribute("title", "title.change_password");

        AttributeFSTRService.forShowChangePassword(request);

        ViewValidationService.validateForChangePassword(request);

        return page;
    }

}
