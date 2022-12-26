package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.entity.DTO.RepairOrderDTOFactory;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.util.ForTables;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShowOrdersCommand implements IActionCommand, IHasRoleRequirement {
    @Override
    public Path execute(HttpServletRequest request) {
        Path page = PathFactory.getPath("path.page.forward.orders");
        int[] a = ForTables.initSkipQuantity( "Orders", request);
        int skip = a[0];
        int quantity = a[1];
        long numberOfOrders;

        if(request.getSession().getAttribute("userRole") == Constants.ROLE.Client ) {
            request.setAttribute("title", "title.my_orders");
            long userId = (long) request.getSession().getAttribute("userId");
            numberOfOrders = ModelManager.ins.getCountRepairOrdersWhereUserIdIs(userId);
            request.setAttribute("orders", RepairOrderDTOFactory.getRepairOrders(ModelManager.ins.getAllRepairOrdersWhereUserIdIs(userId, skip, quantity)));
        } else if(request.getSession().getAttribute("userRole") == Constants.ROLE.Craftsman ) {
            request.setAttribute("title", "title.my_orders");
            long userId = (long) request.getSession().getAttribute("userId");
            numberOfOrders = ModelManager.ins.getCountRepairOrdersWhereCraftsmanIdIs(userId);
            request.setAttribute("orders", RepairOrderDTOFactory.getRepairOrders(ModelManager.ins.getAllRepairOrdersWhereCraftsmanIdIs(userId, skip, quantity)));
        } else {
            request.setAttribute("title", "title.orders");
            numberOfOrders = ModelManager.ins.getCountRepairOrders();
            request.setAttribute("orders", RepairOrderDTOFactory.getRepairOrders(ModelManager.ins.getRepairOrdersWithPagination(skip, quantity)));
        }
        ForTables.updatePagesForJSP(quantity, skip, numberOfOrders, "Orders", request);

        return page;
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Client, Constants.ROLE.Admin, Constants.ROLE.Craftsman, Constants.ROLE.Manager).collect(Collectors.toList());
    }
}