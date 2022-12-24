package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.entity.RepairOrder;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreateOrderCommand implements IActionCommand, IHasRoleRequirement {

    private static final Logger logger = LogManager.getLogger(CreateOrderCommand.class);
    @Override
    public Path execute(HttpServletRequest request) {
        Path page;

        long userId = Long.parseLong(request.getParameter("userId"));
        String text = request.getParameter("orderText");
        if (text == null || text.isBlank()) {
            request.getSession().setAttribute("errorOrderTextMessage", "text.order_empty");
            page = PathFactory.getPath("path.page.redirect.order");
            return page;
        }
        try {
        RepairOrder order = new RepairOrder.RepairOrderBuilder()
               .setUser_id(userId)
               .setText(text).build();

            ModelManager.ins.insertRepairOrder(order);
            request.getSession().setAttribute("errorOrderTextMessage", "text.order_added");
        } catch (Exception e) {
            logger.error("[CreateOrderCommand] "+ e);
        }

        page = PathFactory.getPath("path.page.redirect.order");
        return page;
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Client).collect(Collectors.toList());
    }
}
