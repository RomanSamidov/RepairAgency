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

    public static void cancelOrder(long orderId) throws MyDBException {
        RepairOrder order = RepairOrderService.get(orderId);
        User user = UserService.get(order.getUser_id());
        if (order.getStatus_id() > Constants.ORDER_STATUS.PENDING_PAYMENT.ordinal() &&
                order.getStatus_id() != Constants.ORDER_STATUS.CANCELED.ordinal() &&
                order.getStatus_id() != Constants.ORDER_STATUS.COMPLETED.ordinal()) {

            ModelManager.getInstance().getOrderUserService().cancelOrderWithMoneyReturn(orderId);
            SendEmailService.forCancelOrder(user, order.getPrice());
        } else {
            cancelOrderWithoutMoneyReturn(order, user);
        }
    }

    private static void cancelOrderWithoutMoneyReturn(RepairOrder order, User user) throws MyDBException {
        order.setStatus_id(Constants.ORDER_STATUS.CANCELED.ordinal());
        order.setFinish_date(LocalDateTime.now());
        ModelManager.getInstance().getRepairOrderRepository().update(order);
        SendEmailService.forCancelOrder(user);
    }

    public static boolean payOrder(long id) throws MyDBException {
        return ModelManager.getInstance().getOrderUserService().payOrder(id);
    }
}
