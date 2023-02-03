package com.myCompany.RepairAgency.model.db.abstractDB.repository.entity;

import com.myCompany.RepairAgency.model.db.abstractDB.exception.MyDBException;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.primitive.iRepositoryForChangeAble;
import com.myCompany.RepairAgency.model.entity.RepairOrder;

import java.util.ArrayList;

public interface iRepairOrderRepository extends iRepositoryForChangeAble<RepairOrder> {
    ArrayList<RepairOrder> getByCraftUserStatus(long[] craftIds, long userId,
                                                long[] statusIds, SORT_TYPE sort,
                                                long skip, long quantity) throws MyDBException;

    long countByCraftUserStatus(long[] craftIds, long userId, long[] statusIds) throws MyDBException;


    enum SORT_TYPE {
        ORDER_BY_ID_ASC,
        ORDER_BY_ID_DESC,
        ORDER_BY_CRAFTSMAN_ID_ASC,
        ORDER_BY_CRAFTSMAN_ID_DESC,
        ORDER_BY_CREATION_DATE_ASC,
        ORDER_BY_CREATION_DATE_DESC,
        ORDER_BY_STATUS_ASC,
        ORDER_BY_STATUS_DESC,
        ORDER_BY_PRICE_ASC,
        ORDER_BY_PRICE_DESC;

        private final String toString = name();
        private final long ordinal = ordinal();

        public long getOrdinal() {
            return ordinal;
        }

        public String getToString() {
            return toString;
        }
    }

}
