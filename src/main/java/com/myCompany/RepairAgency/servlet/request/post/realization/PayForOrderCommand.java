package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.entity.RepairOrder;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.util.EmailSender;
import com.myCompany.RepairAgency.servlet.util.ForChangeEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class PayForOrderCommand implements IActionCommand, IHasRoleRequirement {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        if(request.getSession().getAttribute("userRole") == Constants.ROLE.Client){
            if(request.getParameter("payOrder") != null &&
                    request.getParameter("payOrder").equals("true")){
                User user = ModelManager.ins.getUserRepository().getById((Long) request.getSession().getAttribute("userId"));
                RepairOrder order = ModelManager.ins.getRepairOrderRepository().getById((Long) request.getSession().getAttribute("goalIdOrder"));
                if(user.getAccount() >= order.getPrice()) {
                    order.setStatus_id(Constants.ORDER_STATUS.PAID.ordinal());
                    user.setAccount(user.getAccount() - order.getPrice());
                    ModelManager.ins.getUserRepository().update(user);
                    ModelManager.ins.getRepairOrderRepository().update(order);

                    EmailSender.send((String)request.getSession().getAttribute("userEmail"),
                            "You paid for order",
                            "You successfully paid for order " + order.getId());
                } else {
                    request.getSession().setAttribute("error","message.not_enough_money");
                }
            }
        }


        Path path = PathFactory.getPath("path.page.redirect.order");
        path.addParameter("id", request.getParameter("goalIdOrder"));

        return path;
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Client, Constants.ROLE.Manager, Constants.ROLE.Admin, Constants.ROLE.Craftsman).collect(Collectors.toList());
    }
}