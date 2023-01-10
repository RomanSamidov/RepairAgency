package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.entity.DTO.RepairOrderDTO;
import com.myCompany.RepairAgency.model.entity.DTO.RepairOrderDTOFactory;
import com.myCompany.RepairAgency.model.entity.DTO.UserDTOFactory;
import com.myCompany.RepairAgency.model.entity.RepairOrder;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShowOrderPageCommand implements IActionCommand, IHasRoleRequirement {

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        Path page = PathFactory.getPath("path.page.forward.order");
        request.setAttribute("title", "title.order");

        long goalId = initGoalId(request);
        if(goalId == 0)  {
            page = PathFactory.getPath("path.page.redirect.orders");
            return page;
        }

        RepairOrder order = ModelManager.ins.getRepairOrderRepository().getById(goalId);
        if(order == null) return PathFactory.getPath("path.page.redirect.orders");

        if (!checkAccessibility(request, RepairOrderDTOFactory.getRepairOrder(order))) {
            request.setAttribute("error", "message.not_allowed");
        } else request.setAttribute("goalOrder", RepairOrderDTOFactory.getRepairOrder(order));

        ArrayList<Constants.ORDER_STATUS> orderStatuses = new ArrayList<>(List.of(Constants.ORDER_STATUS.values()));
        orderStatuses.remove(0);
        request.getSession().setAttribute("orderStatuses", orderStatuses);
        request.getSession().setAttribute("craftsmen", UserDTOFactory.getUsers(
                ModelManager.ins.getUserRepository()
                        .getByRole(Constants.ROLE.Craftsman.ordinal(), 0, 50)));

        return page;
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Client,
                Constants.ROLE.Admin,
                Constants.ROLE.Manager,
                Constants.ROLE.Craftsman).collect(Collectors.toList());
    }

    private long initGoalId(HttpServletRequest request){
        if(request.getParameter("id") != null) {
            return Long.parseLong(request.getParameter("id"));
        }
        return 0;
    }

    private boolean checkAccessibility(HttpServletRequest request, RepairOrderDTO order){
        if ((request.getSession().getAttribute("userRole")).equals(Constants.ROLE.Client)) {
            return (long) request.getSession().getAttribute("userId") == order.getUser_id();
        } else if (request.getSession().getAttribute("userRole").equals(Constants.ROLE.Craftsman)) {
            return (long) request.getSession().getAttribute("userId") == order.getCraftsman_id();
        }
        return true;
    }
}