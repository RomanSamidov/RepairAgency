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

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeleteOrderCommand implements IActionCommand, IHasRoleRequirement {
    private static final Logger logger = LogManager.getLogger(DeleteOrderCommand.class);
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
            RepairOrder order = ModelManager.getInstance().getRepairOrderRepository().getById(
                    Long.parseLong(request.getParameter("goalIdOrder")));
            if(request.getSession().getAttribute("userRole").equals(Constants.ROLE.Admin)) {
                ModelManager.getInstance().getRepairOrderRepository().delete(order);
                User user = ModelManager.getInstance().getUserRepository().getById(order.getUser_id());
                ifNeedSendEmail(user, order.getId());
            }


        Path path = PathFactory.getPath("path.page.redirect.order");
        path.addParameter("id", request.getParameter("goalIdOrder"));

        return path;
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Admin, Constants.ROLE.Client).collect(Collectors.toList());
    }

    private void ifNeedSendEmail(User user, long orderId){
        if(user.isAllow_letters()){
            Constants.LOCALE locale = Constants.LOCALE.values()[user.getLocale_id()];
            EmailSender.send(user.getEmail(),
                    user.getLogin() + "  " + locale.getString("text.your_order_deleted"),
                    locale.getString("text.your_order_deleted_id")+ orderId);
            logger.debug("Send email profile deletion");
        }
    }

}