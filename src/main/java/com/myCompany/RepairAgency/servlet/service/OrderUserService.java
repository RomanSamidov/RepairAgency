package com.myCompany.RepairAgency.servlet.service;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.db.abstractDB.exception.MyDBException;
import com.myCompany.RepairAgency.model.entity.RepairOrder;
import com.myCompany.RepairAgency.model.entity.User;

import java.time.LocalDateTime;

public class OrderUserService {


    public static boolean createOrder(long userId, String text) throws MyDBException {
        User user = ModelManager.getInstance().getUserRepository().getById(userId);
        if (user.getRole_id() == Constants.ROLE.Client.ordinal()) {
            RepairOrder order = new RepairOrder.RepairOrderBuilder()
                    .setUser_id(userId)
                    .setCreation_date(LocalDateTime.now())
                    .setText(text).build();

            ModelManager.getInstance().getRepairOrderRepository().insert(order);

            SendEmailService.forCreateOrder(user, order.getId());

            return true;
        }

        return false;
    }

    public static void cancelOrderWithMoneyReturn(long id) throws MyDBException {
        ModelManager.getInstance().getOrderUserService().cancelOrderWithMoneyReturn(id);
    }

    public static boolean payOrder(long id) throws MyDBException {
        return ModelManager.getInstance().getOrderUserService().payOrder(id);
    }
}
