package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.util.ForTables;
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
        int[] a = ForTables.initSkipQuantity( "Orders", request);
        int skip = a[0];
        int quantity = a[1];
        request.setAttribute("orders", ModelManager.ins.getAllRepairOrdersWhereUserIdIs(userId, skip, quantity));
        long numberOfOrders = ModelManager.ins.getCountRepairOrdersWhereUserIdIs(userId);

        ForTables.updatePagesForJSP(quantity, skip, numberOfOrders, "Orders", request);

        return page;
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Client).collect(Collectors.toList());
    }
}