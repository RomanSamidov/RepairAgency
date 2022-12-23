package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShowProfileCommandI implements IActionCommand, IHasRoleRequirement {
    @Override
    public Path execute(HttpServletRequest request) {
        /* в случае ошибки или прямого обращения к контроллеру
         * переадресация на страницу ввода логина */
        Path page = PathFactory.getPath("path.page.forward.cabinet");
//        String page = PathFactory.getProperty("path.page.error");
        request.setAttribute("title", "title.cabinet");
        return page;
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Client,
                Constants.ROLE.Admin,
                Constants.ROLE.Manager,
                Constants.ROLE.Craftsman).collect(Collectors.toList());
    }
}
