package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.entity.DTO.RepairOrderDTOFactory;
import com.myCompany.RepairAgency.model.entity.DTO.UserDTOFactory;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.util.ForTables;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Arrays;
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

        request.setAttribute("title", "title.my_orders");
        long[] craftIds = null , statusIds = Arrays.stream(Constants.ORDER_STATUS.values()).mapToLong(Constants.ORDER_STATUS::getOrdinal).toArray();

        if( request.getSession().getAttribute("statusOrders") != null) {
            long[] tempStatusIds = ((long[])request.getSession().getAttribute("statusOrders"));
            if(tempStatusIds[0] != 0) statusIds = tempStatusIds;
        }

        if(request.getSession().getAttribute("userRole") == Constants.ROLE.Client ) {
            if(request.getSession().getAttribute("craftsmanIdOrders") != null) {
                long[] tempCraftIds =  ((long[])request.getSession().getAttribute("craftsmanIdOrders"));
                if(tempCraftIds[0] != 0) craftIds = tempCraftIds;
            }
            long userId = (long) request.getSession().getAttribute("userId");
            if(craftIds != null){
                numberOfOrders = ModelManager.ins.getCountByCraftUserStatus(craftIds, userId, statusIds);
                request.setAttribute("orders", RepairOrderDTOFactory.getRepairOrders(ModelManager.ins.getByCraftUserStatus(craftIds, userId, statusIds,skip, quantity)));
            } else {
                numberOfOrders = ModelManager.ins.getCountByUserStatus(userId, statusIds);
                request.setAttribute("orders", RepairOrderDTOFactory.getRepairOrders(ModelManager.ins.getByUserStatus(userId, statusIds,skip, quantity)));
            }
        } else if(request.getSession().getAttribute("userRole") == Constants.ROLE.Craftsman ) {
            craftIds = new long[]{(long) request.getSession().getAttribute("userId")};
            numberOfOrders = ModelManager.ins.getCountByCraftStatus(craftIds, statusIds);
            request.setAttribute("orders", RepairOrderDTOFactory.getRepairOrders(ModelManager.ins.getByCraftStatus(craftIds, statusIds,skip, quantity)));
        } else {
            if(request.getSession().getAttribute("craftsmanIdOrders") != null) {
                long[] tempCraftIds =  ((long[])request.getSession().getAttribute("craftsmanIdOrders"));
                if(tempCraftIds[0] != 0) craftIds = tempCraftIds;
            }
            request.setAttribute("title", "title.orders");
            if(craftIds != null){
                numberOfOrders = ModelManager.ins.getCountByCraftStatus(craftIds, statusIds);
                request.setAttribute("orders", RepairOrderDTOFactory.getRepairOrders(ModelManager.ins.getByCraftStatus(craftIds, statusIds,skip, quantity)));
            } else {
                numberOfOrders = ModelManager.ins.getCountByStatus(statusIds);
                request.setAttribute("orders", RepairOrderDTOFactory.getRepairOrders(ModelManager.ins.getByStatus(statusIds,skip, quantity)));
            }
        }



        ForTables.updatePagesForJSP(quantity, skip, numberOfOrders, "Orders", request);
        ArrayList<Constants.ORDER_STATUS> orderStatuses = new ArrayList<>(List.of(Constants.ORDER_STATUS.values()));
        orderStatuses.remove(0);
        request.getSession().setAttribute("orderStatuses", orderStatuses);
        request.getSession().setAttribute("craftsmen", UserDTOFactory.getUsers(ModelManager.ins.getAllUsersByRole(Constants.ROLE.Craftsman.ordinal(), 0,50)));

        return page;
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Client, Constants.ROLE.Admin, Constants.ROLE.Craftsman, Constants.ROLE.Manager).collect(Collectors.toList());
    }
}