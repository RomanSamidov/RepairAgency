package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.entity.DTO.UserDTOFactory;
import com.myCompany.RepairAgency.model.entity.RepairOrder;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.service.InitValuesFromRequestService;
import com.myCompany.RepairAgency.servlet.service.RepairOrderService;
import com.myCompany.RepairAgency.servlet.service.UserService;
import com.myCompany.RepairAgency.servlet.service.ViewValidationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShowOrderPageCommand implements IActionCommand, IHasRoleRequirement {

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("title", "title.order");

        long goalId = InitValuesFromRequestService.initGoalId(request);
        if (goalId <= 0) {
            return PathFactory.getPath("path.page.redirect.orders");
        }

        RepairOrder order = RepairOrderService.get(goalId);
        if (order == null) return PathFactory.getPath("path.page.redirect.orders");

        ArrayList<Constants.ORDER_STATUS> orderStatuses = new ArrayList<>(List.of(Constants.ORDER_STATUS.values()));
        orderStatuses.remove(0);
        request.getSession().setAttribute("orderStatuses", orderStatuses);
        request.getSession().setAttribute("craftsmen", UserDTOFactory.getUsers(
                UserService.getByRole(Constants.ROLE.Craftsman.ordinal(), 0L, 50L)));

        return ViewValidationService.validateForOrderPage(request, order);
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Client,
                Constants.ROLE.Admin,
                Constants.ROLE.Manager,
                Constants.ROLE.Craftsman).collect(Collectors.toList());
    }


}