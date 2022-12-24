package com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.entity;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.primitive.iRepositoryForChangeAble;
import com.myCompany.RepairAgency.model.entity.RepairOrder;

import java.util.ArrayList;

public interface iRepairOrderRepository extends iRepositoryForChangeAble<RepairOrder> {
    ArrayList<RepairOrder> getAllWhereStatusIs(Constants.ORDER_STATUS status, int skip, int quantity);

    ArrayList<RepairOrder> getAllWhereCraftsmanIdIs(int id, int skip, int quantity);
    ArrayList<RepairOrder> getAllWhereUserIdIs(long id, int skip, int quantity);
    long getCountWhereUserIdIs(long id);
}
