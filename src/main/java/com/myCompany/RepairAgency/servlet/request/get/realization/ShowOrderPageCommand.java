package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
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
        Constants.ROLE userRole = (Constants.ROLE) request.getSession().getAttribute("userRole");
        Path page = switch (userRole) {
            case Guest -> null;
            case Admin -> PathFactory.getPath("path.page.forward.admin.order");
            case Manager -> PathFactory.getPath("path.page.forward.manager.order");
            case Craftsman -> PathFactory.getPath("path.page.forward.craftsman.order");
            case Client -> PathFactory.getPath("path.page.forward.client.order");
        };
        request.setAttribute("title", "title.order");

        long goalId = initGoalId(request);
        if(goalId == 0)  {
            page = PathFactory.getPath("path.page.redirect.orders");
            return page;
        }

        RepairOrder order = ModelManager.getInstance().getRepairOrderRepository().getById(goalId);
        if(order == null) return PathFactory.getPath("path.page.redirect.orders");

        if (!checkAccessibility(request, order)) {
            request.setAttribute("error", "message.not_allowed");
            request.setAttribute("_show_order_url", PathFactory.getPath("path.page.forward.common.empty").toString());
        } else {
            request.setAttribute("goalOrder", RepairOrderDTOFactory.getRepairOrder(order));
            request.setAttribute("_show_order_url", PathFactory.getPath("path.page.order.part.show_order").toString());
        }

        if((userRole == Constants.ROLE.Craftsman || userRole == Constants.ROLE.Admin)){
            if(order.getStatus_id()==Constants.ORDER_STATUS.PAID.ordinal()) {
                request.setAttribute("_craft_order_url", PathFactory.getPath("path.page.order.part.craftsman.take_order").toString());
            } else if(order.getStatus_id()==Constants.ORDER_STATUS.IN_PROGRESS.ordinal()) {
                request.setAttribute("_craft_order_url", PathFactory.getPath("path.page.order.part.craftsman.complete_order").toString());
            }else {
                request.setAttribute("_craft_order_url", PathFactory.getPath("path.page.forward.common.empty").toString());
            }
        }else {
            request.setAttribute("_craft_order_url", PathFactory.getPath("path.page.forward.common.empty").toString());
        }

        if((userRole == Constants.ROLE.Client || userRole == Constants.ROLE.Admin)){
            if(order.getStatus_id()==Constants.ORDER_STATUS.PENDING_PAYMENT.ordinal() && userRole != Constants.ROLE.Admin) {
                request.setAttribute("_client_order_url", PathFactory.getPath("path.page.order.part.client.pay_order").toString());
            } else if(order.getStatus_id()==Constants.ORDER_STATUS.COMPLETED.ordinal()) {
                request.setAttribute("_client_order_url", PathFactory.getPath("path.page.order.part.client.set_feedback").toString());
            }else {
                request.setAttribute("_client_order_url", PathFactory.getPath("path.page.forward.common.empty").toString());
            }
        }else {
            request.setAttribute("_client_order_url", PathFactory.getPath("path.page.forward.common.empty").toString());
        }

        if((userRole == Constants.ROLE.Manager || userRole == Constants.ROLE.Admin)){
            if(order.getStatus_id()==Constants.ORDER_STATUS.PENDING_PAYMENT.ordinal()) {
                request.setAttribute("_manager_order_url", PathFactory.getPath("path.page.order.part.manager.pay_order").toString());
            } else if(order.getStatus_id()==Constants.ORDER_STATUS.CREATED.ordinal()) {
                request.setAttribute("_manager_order_url", PathFactory.getPath("path.page.order.part.manager.set_craftsman_and_price").toString());
            }else {
                request.setAttribute("_manager_order_url", PathFactory.getPath("path.page.forward.common.empty").toString());
            }
        }else {
            request.setAttribute("_manager_order_url", PathFactory.getPath("path.page.forward.common.empty").toString());
        }

        if(order.getStatus_id()!=Constants.ORDER_STATUS.COMPLETED.ordinal() && order.getStatus_id()!=Constants.ORDER_STATUS.CANCELED.ordinal() && checkAccessibility(request, order)) {
            request.setAttribute("_cancel_order_url", PathFactory.getPath("path.page.order.part.cancel_order").toString());
        } else {
            request.setAttribute("_cancel_order_url", PathFactory.getPath("path.page.forward.common.empty").toString());
        }


        ArrayList<Constants.ORDER_STATUS> orderStatuses = new ArrayList<>(List.of(Constants.ORDER_STATUS.values()));
        orderStatuses.remove(0);
        request.getSession().setAttribute("orderStatuses", orderStatuses);
        request.getSession().setAttribute("craftsmen", UserDTOFactory.getUsers(
                ModelManager.getInstance().getUserRepository()
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

    private boolean checkAccessibility(HttpServletRequest request, RepairOrder order){
        if ((request.getSession().getAttribute("userRole")).equals(Constants.ROLE.Client)) {
            return (long) request.getSession().getAttribute("userId") == order.getUser_id();
        } else if (request.getSession().getAttribute("userRole").equals(Constants.ROLE.Craftsman)) {
            return (long) request.getSession().getAttribute("userId") == order.getCraftsman_id();
        }
        return true;
    }
}