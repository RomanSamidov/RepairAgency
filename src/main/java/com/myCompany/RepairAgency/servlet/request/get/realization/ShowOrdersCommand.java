package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.entity.iRepairOrderRepository;
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
        int[] a = ForTables.initSkipQuantity("Orders", request);
        int skip = a[0];
        int quantity = a[1];
        long numberOfOrders;
        iRepairOrderRepository orderRepository = ModelManager.ins.getRepairOrderRepository();

        request.setAttribute("title", "title.my_orders");
        long[] craftIds = null, statusIds = Arrays.stream(Constants.ORDER_STATUS.values()).mapToLong(Constants.ORDER_STATUS::getOrdinal).toArray();

        if (request.getSession().getAttribute("statusOrders") != null) {
            long[] tempStatusIds = ((long[]) request.getSession().getAttribute("statusOrders"));
            if (tempStatusIds[0] != 0) statusIds = tempStatusIds;
        }

        iRepairOrderRepository.SORT_TYPE sortType = iRepairOrderRepository.SORT_TYPE.ORDER_BY_ID_ASC;
        if (request.getSession().getAttribute("sortTypeOrders") != null) {
            sortType = ((iRepairOrderRepository.SORT_TYPE) request.getSession().getAttribute("sortTypeOrders"));
        }


        if (request.getSession().getAttribute("userRole") == Constants.ROLE.Client) {
            if (request.getSession().getAttribute("craftsmanIdOrders") != null) {
                long[] tempCraftIds = ((long[]) request.getSession().getAttribute("craftsmanIdOrders"));
                if (tempCraftIds[0] != 0) craftIds = tempCraftIds;
            }
            long userId = (long) request.getSession().getAttribute("userId");
            if (craftIds != null) {
                numberOfOrders = orderRepository.countByCraftUserStatus(craftIds, userId, statusIds);
                request.setAttribute("orders", RepairOrderDTOFactory.getRepairOrders(orderRepository.getByCraftUserStatus(craftIds, userId, statusIds, sortType, skip, quantity)));
            } else {
                numberOfOrders = orderRepository.countByUserStatus(userId, statusIds);
                request.setAttribute("orders", RepairOrderDTOFactory.getRepairOrders(orderRepository.getByUserStatus(userId, statusIds, sortType, skip, quantity)));
            }
        } else if (request.getSession().getAttribute("userRole") == Constants.ROLE.Craftsman) {
            craftIds = new long[]{(long) request.getSession().getAttribute("userId")};
            numberOfOrders = orderRepository.countByCraftNNStatus(craftIds, statusIds);
            System.out.println(Arrays.toString(craftIds));
            request.setAttribute("orders", RepairOrderDTOFactory.getRepairOrders(orderRepository.getByCraftNNStatus(craftIds, statusIds, sortType, skip, quantity)));
        } else {
            if (request.getSession().getAttribute("craftsmanIdOrders") != null) {
                long[] tempCraftIds = ((long[]) request.getSession().getAttribute("craftsmanIdOrders"));
                if (tempCraftIds[0] != 0) craftIds = tempCraftIds;
            }
            request.setAttribute("title", "title.orders");
            if (craftIds != null) {
                numberOfOrders = orderRepository.countByCraftStatus(craftIds, statusIds);
                request.setAttribute("orders", RepairOrderDTOFactory.getRepairOrders(orderRepository.getByCraftStatus(craftIds, statusIds, sortType, skip, quantity)));
            } else {
                numberOfOrders = orderRepository.countByStatus(statusIds);
                request.setAttribute("orders", RepairOrderDTOFactory.getRepairOrders(orderRepository.getByStatus(statusIds, sortType, skip, quantity)));
            }
        }


        ForTables.updatePagesForJSP(quantity, skip, numberOfOrders, "Orders", request);
        ArrayList<Constants.ORDER_STATUS> orderStatuses = new ArrayList<>(List.of(Constants.ORDER_STATUS.values()));
        orderStatuses.remove(0);
        request.getSession().setAttribute("orderStatuses", orderStatuses);
        request.getSession().setAttribute("sortTypeOrders", sortType);
        request.getSession().setAttribute("sortTypesOrders", iRepairOrderRepository.SORT_TYPE.values());
        request.getSession().setAttribute("craftsmen", UserDTOFactory.getUsers(ModelManager.ins.getUserRepository().getByRole(Constants.ROLE.Craftsman.ordinal(), 0, 50)));

        return page;
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Client, Constants.ROLE.Admin, Constants.ROLE.Craftsman, Constants.ROLE.Manager).collect(Collectors.toList());
    }
}