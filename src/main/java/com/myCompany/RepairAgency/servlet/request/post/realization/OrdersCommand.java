package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iRepairOrderRepository;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.util.ForTables;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class OrdersCommand implements IActionCommand, IHasRoleRequirement {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        ForTables.updateSkipQuantity("Orders", request);

        prepareAttributes(request);

        return PathFactory.getPath("path.page.redirect.orders");
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Client, Constants.ROLE.Admin, Constants.ROLE.Craftsman, Constants.ROLE.Manager).collect(Collectors.toList());

    }

    private void prepareAttributes(HttpServletRequest request) {
        if (request.getParameter("statusOrders") != null) {
            long[] statusId = Arrays.stream(request.getParameterValues("statusOrders")).mapToLong(Long::parseLong).toArray();
            request.getSession().setAttribute("statusOrders", statusId);
        }
        if (request.getParameter("craftsmanIdOrders") != null) {
            long[] craftsmanId = Arrays.stream(request.getParameterValues("craftsmanIdOrders")).mapToLong(Long::parseLong).toArray();
            request.getSession().setAttribute("craftsmanIdOrders", craftsmanId);
        }
        if (request.getParameter("sortTypeOrders") != null) {
            iRepairOrderRepository.SORT_TYPE sortType = iRepairOrderRepository.SORT_TYPE.valueOf(request.getParameter("sortTypeOrders"));
            request.getSession().setAttribute("sortTypeOrders", sortType);
        }
        if (request.getParameter("createReport") != null) {
            boolean createReport = Boolean.parseBoolean(request.getParameter("createReport"));
            if (createReport)request.getSession().setAttribute("createReport", true);
        }

        if (request.getParameter("reportFormat") != null) {
            Constants.REPORT_FORMAT format = Constants.REPORT_FORMAT.valueOf(request.getParameter("reportFormat"));
            request.getSession().setAttribute("reportFormat", format);
        }
    }
}