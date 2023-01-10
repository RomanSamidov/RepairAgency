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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class SetOrderStatusCommand implements IActionCommand, IHasRoleRequirement {
    private static final Logger logger = LogManager.getLogger(SetOrderStatusCommand.class);

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        String goalOrderStatus = request.getParameter("goalOrderStatus");
        if (goalOrderStatus != null && !goalOrderStatus.isBlank()) {
            RepairOrder order = ModelManager.ins.getRepairOrderRepository().getById(
                    Long.parseLong(request.getParameter("goalIdOrder")));
            if(request.getSession().getAttribute("userRole").equals(Constants.ROLE.Craftsman) &&
                    request.getSession().getAttribute("userId").equals(order.getCraftsman_id())) {
                order.setStatus_id(Integer.parseInt(goalOrderStatus));
                if (order.getStatus_id() == Constants.ORDER_STATUS.COMPLETED.ordinal()) {
                    order.setFinish_date(LocalDateTime.now());
                }
                ModelManager.ins.getRepairOrderRepository().update(order);
                User user = ModelManager.ins.getUserRepository().getById(order.getUser_id());
                ifNeedSendEmail(user, order.getId());
            }
        }

        Path path = PathFactory.getPath("path.page.redirect.order");
        path.addParameter("id", request.getParameter("goalIdOrder"));

        return path;
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Admin, Constants.ROLE.Craftsman).collect(Collectors.toList());
    }

    private void ifNeedSendEmail(User user, long orderId){
        if(user.isAllow_letters()){
            Constants.LOCALE locale = Constants.LOCALE.values()[user.getLocale_id()];
            EmailSender.send(user.getEmail(),
                    user.getLogin() + "  " + locale.getString("text.your_order_updated"),
                    locale.getString("text.order_now_has_another_status") + orderId);
            logger.debug("Send email about successful paid");
        }
    }
}