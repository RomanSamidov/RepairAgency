package com.myCompany.RepairAgency.model.db.abstractDB.service;

import com.myCompany.RepairAgency.model.db.abstractDB.exception.MyDBException;

public interface iOrderUserService {
    void cancelOrderWithMoneyReturn(long orderId) throws MyDBException;

    boolean payOrder(long orderId) throws MyDBException;
}
