package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import jakarta.servlet.http.HttpServletRequest;

public class EmptyCommand implements IActionCommand {
    @Override
    public Path execute(HttpServletRequest request) {
        return PathFactory.getPath("path.page.redirect.login");
    }
}