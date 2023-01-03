package com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.entity;

import com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.primitive.iRepositoryForChangeAble;
import com.myCompany.RepairAgency.model.entity.RepairOrder;

import java.util.ArrayList;

public interface iRepairOrderRepository extends iRepositoryForChangeAble<RepairOrder> {
    ArrayList<RepairOrder> getByStatus(long[] statusIds, SORT_TYPE sort, long skip, long quantity);

    long countByStatus(long[] statusIds);

    ArrayList<RepairOrder> getByCraftNNStatus(long[] craftIds, long[] statusIds, SORT_TYPE sort, long skip, long quantity);

    long countByCraftNNStatus(long[] craftIds, long[] statusIds);

    ArrayList<RepairOrder> getByCraftStatus(long[] craftIds, long[] statusIds, SORT_TYPE sort, long skip, long quantity);

    long countByCraftStatus(long[] craftIds, long[] statusIds);

    ArrayList<RepairOrder> getByUserStatus(long userId, long[] statusIds, SORT_TYPE sort, long skip, long quantity);

    long countByUserStatus(long userId, long[] statusIds);

    ArrayList<RepairOrder> getByCraftUserStatus(long[] craftIds, long userId, long[] statusIds, SORT_TYPE sort, long skip, long quantity);

    long countByCraftUserStatus(long[] craftIds, long userId, long[] statusIds);


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

        private final String toString;
        private final long ordinal;

        SORT_TYPE() {
            this.toString = this.name();
            this.ordinal = ordinal();
        }

        public long getOrdinal() {
            return ordinal;
        }

        public String getToString() {
            return toString;
        }
    }

}
