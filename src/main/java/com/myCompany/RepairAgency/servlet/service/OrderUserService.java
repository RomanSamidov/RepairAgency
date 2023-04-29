package com.myCompany.RepairAgency.servlet.service;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.db.abstractDB.exception.MyDBException;
import com.myCompany.RepairAgency.model.entity.RepairOrder;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.util.SendEmail;

import java.time.LocalDateTime;

public class OrderUserService {


    public static boolean createOrder(long userId, String text) throws MyDBException {
        User user = UserService.get(userId);
        if (user.getRole_id() == Constants.ROLE.Client.ordinal()) {
            RepairOrder order = new RepairOrder.RepairOrderBuilder()
                    .setUser_id(userId)
                    .setCreation_date(LocalDateTime.now())
                    .setText(text).build();

            ModelManager.getInstance().getRepairOrderRepository().insert(order);

            SendEmail.forCreateOrder(user, order.getId());

            return true;
        }

        return false;
    }

    public static void cancelOrder(long orderId) throws MyDBException {
        RepairOrder order = RepairOrderService.get(orderId);
        User user = UserService.get(order.getUser_id());

        order.setFinish_date(LocalDateTime.now());

        if (order.getStatus_id() > Constants.ORDER_STATUS.PENDING_PAYMENT.ordinal() &&
                order.getStatus_id() != Constants.ORDER_STATUS.CANCELED.ordinal() &&
                order.getStatus_id() != Constants.ORDER_STATUS.COMPLETED.ordinal()) {
            order.setStatus_id(Constants.ORDER_STATUS.CANCELED.ordinal());
            cancelOrderWithMoneyReturn(order, user);
        } else {
            order.setStatus_id(Constants.ORDER_STATUS.CANCELED.ordinal());
            cancelOrderWithoutMoneyReturn(order, user);
        }



    }

    private static void cancelOrderWithoutMoneyReturn(RepairOrder order, User user) throws MyDBException {
        RepairOrderService.update(order);
        SendEmail.forCancelOrder(user);
    }

    private static void cancelOrderWithMoneyReturn(RepairOrder order, User user) throws MyDBException {
        ModelManager.getInstance().getOrderUserService().cancelOrderWithMoneyReturn(order, user);
        SendEmail.forCancelOrder(user, order.getPrice());
    }

    public static boolean payOrder(long orderId) throws MyDBException {

        RepairOrder order = RepairOrderService.get(orderId);

        User user = UserService.get(order.getUser_id());

        if (user.getAccount() < order.getPrice()) {
            return false;
        }

        order.setStatus_id(Constants.ORDER_STATUS.PAID.ordinal());

        ModelManager manager = ModelManager.getInstance();
        return manager.getOrderUserService().payOrder(order, user);
    }
}
