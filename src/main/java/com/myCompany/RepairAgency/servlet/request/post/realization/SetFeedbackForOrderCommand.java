package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.entity.RepairOrder;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.service.RepairOrderService;
import com.myCompany.RepairAgency.servlet.util.ParameterValidation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class SetFeedbackForOrderCommand implements IActionCommand, IHasRoleRequirement {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        String goalOrderFeedback_mark = request.getParameter("goalOrderFeedback_mark");
        String goalOrderFeedback_text = request.getParameter("goalOrderFeedback_text");
        String goalIdOrder = request.getParameter("goalIdOrder");

        if (!ParameterValidation.validateGoalId(goalIdOrder))
            return PathFactory.getPath("path.page.redirect.orders");


        if (ParameterValidation.validateInt(goalOrderFeedback_mark) &&
                ParameterValidation.validateFeedbackText(request, goalOrderFeedback_text)) {

            RepairOrder order = RepairOrderService.get(Long.parseLong(goalIdOrder));
            if (order == null) return PathFactory.getPath("path.page.redirect.orders");

            if (request.getSession().getAttribute("userId").equals(order.getUser_id()) ||
                    request.getSession().getAttribute("userRole").equals(Constants.ROLE.Admin)) {
                order.setFeedback_text(goalOrderFeedback_text);
                order.setFeedback_date(LocalDateTime.now());
                order.setFeedback_mark(Integer.parseInt(goalOrderFeedback_mark));
                RepairOrderService.update(order);
            }
        }

        Path path = PathFactory.getPath("path.page.redirect.order");
        path.addParameter("id", request.getParameter("goalIdOrder"));
        return path;
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Client,
                Constants.ROLE.Admin).collect(Collectors.toList());
    }
}