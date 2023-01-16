package com.myCompany.RepairAgency.model.db.abstractDB.service;

public interface iOrderUserService {
    void cancelOrderWithMoneyReturn(long orderId);
    boolean payOrder(long orderId);
}
