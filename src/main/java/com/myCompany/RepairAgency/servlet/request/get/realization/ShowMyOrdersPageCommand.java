package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShowMyOrdersPageCommand implements IActionCommand, IHasRoleRequirement {
    @Override
    public Path execute(HttpServletRequest request) {
        Path page = PathFactory.getPath("path.page.forward.my_orders");
        request.setAttribute("title", "title.my_orders");

        long userId = (long) request.getSession().getAttribute("userId");
        Integer skip = (Integer) request.getSession().getAttribute("skipOrders");
        Integer quantity = (Integer) request.getSession().getAttribute("quantityOrders");
        if (quantity == null || quantity == 0) quantity = 5;
        if (skip == null) skip = 0;
        request.setAttribute("orders", ModelManager.ins.getAllRepairOrdersWhereUserIdIs(userId, skip, quantity));
        return page;
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Client).collect(Collectors.toList());
    }
}