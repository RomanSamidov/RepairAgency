package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iRepairOrderRepository;
import com.myCompany.RepairAgency.model.entity.DTO.RepairOrderDTOFactory;
import com.myCompany.RepairAgency.model.entity.DTO.UserDTOFactory;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.service.RepairOrderService;
import com.myCompany.RepairAgency.servlet.service.UserService;
import com.myCompany.RepairAgency.servlet.util.AttributeFSTR;
import com.myCompany.RepairAgency.servlet.util.ForTables;
import com.myCompany.RepairAgency.servlet.util.InitValuesFromRequest;
import com.myCompany.RepairAgency.servlet.util.ViewValidation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShowOrdersCommand implements IActionCommand, IHasRoleRequirement {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        Constants.ROLE userRole = (Constants.ROLE) request.getSession().getAttribute("userRole");
        setPageTittle(request, userRole);

        AttributeFSTR.forShowOrders(request);

        long[] craftIds = InitValuesFromRequest.initCraftsmenIds(request);
        long[] statusIds = InitValuesFromRequest.initStatusIds(request);
        long userId = InitValuesFromRequest.initUserId(request);
        iRepairOrderRepository.SORT_TYPE sortType = InitValuesFromRequest.initSortType(request);


        long numberOfOrders = RepairOrderService.countByCraftUserStatus(craftIds, userId, statusIds);
        if (numberOfOrders == 0) {
            request.setAttribute("error", "text.there_are_no_orders");
        }

        int[] a = ForTables.initSkipQuantity("Orders", numberOfOrders, request);
        int skip = a[0];
        int quantity = a[1];

        request.setAttribute("orders", RepairOrderDTOFactory.getRepairOrders(
                RepairOrderService.getByCraftUserStatus(craftIds, userId, statusIds, sortType, skip, quantity)));

        ArrayList<Constants.ORDER_STATUS> orderStatuses = new ArrayList<>(List.of(Constants.ORDER_STATUS.values()));
        orderStatuses.remove(0);

        request.setAttribute("orderStatuses", orderStatuses);
        request.setAttribute("reportFormats", Constants.REPORT_FORMAT.values());
        request.setAttribute("sortTypeOrders", sortType);
        request.setAttribute("sortTypesOrders", iRepairOrderRepository.SORT_TYPE.values());
        request.setAttribute("craftsmen", UserDTOFactory.getUsers(
                UserService.getByRole(Constants.ROLE.Craftsman.ordinal(), 0, 50)));
        return ViewValidation.validateForOrdersPage(request);
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Client,
                Constants.ROLE.Admin,
                Constants.ROLE.Craftsman,
                Constants.ROLE.Manager).collect(Collectors.toList());
    }

    private void setPageTittle(HttpServletRequest request, Constants.ROLE userRole) {
        if (userRole.equals(Constants.ROLE.Admin) || userRole.equals(Constants.ROLE.Manager)) {
            request.setAttribute("title", "title.orders");
        } else request.setAttribute("title", "title.my_orders");
    }
}