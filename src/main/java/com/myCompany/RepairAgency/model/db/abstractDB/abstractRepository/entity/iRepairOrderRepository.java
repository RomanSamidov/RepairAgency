package com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.entity;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.primitive.iRepositoryForChangeAble;
import com.myCompany.RepairAgency.model.entity.RepairOrder;

import java.util.ArrayList;

public interface iRepairOrderRepository extends iRepositoryForChangeAble<RepairOrder> {
    ArrayList<RepairOrder> getAllWhereUserIdIs(long id, int skip, int quantity);
    long getCountWhereUserIdIs(long id);
    ArrayList<RepairOrder> getAllWhereCraftsmanIdIs(long id, int skip, int quantity);
    long getCountWhereCraftsmanIdIs(long id);

    ArrayList<RepairOrder> getByCraftUserStatus(long[] craftIds, long userId, long[] statusIds, int skip, int quantity);
    long getCountByCraftUserStatus(long[] craftIds, long userId, long[] statusIds);
    ArrayList<RepairOrder> getByCraftStatus(long[] craftIds, long[] statusIds, int skip, int quantity);
    long getCountByCraftStatus(long[] craftIds, long[] statusIds);
    ArrayList<RepairOrder> getByUserStatus(long userId, long[] statusIds, int skip, int quantity);
    long getCountByUserStatus(long userId, long[] statusIds);
    ArrayList<RepairOrder> getByStatus(long[] statusIds, int skip, int quantity);
    long getCountByStatus(long[] statusIds);


}
