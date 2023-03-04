package com.myCompany.RepairAgency.model.db.abstractDB.repository.service;

import com.myCompany.RepairAgency.model.db.abstractDB.exception.MyDBException;
import com.myCompany.RepairAgency.model.entity.RepairOrder;
import com.myCompany.RepairAgency.model.entity.User;

public interface iOrderUserService {
    void cancelOrderWithMoneyReturn(RepairOrder order, User user) throws MyDBException;

    boolean payOrder(RepairOrder order, User user) throws MyDBException;
}
