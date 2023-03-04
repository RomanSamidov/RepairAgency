package com.myCompany.RepairAgency.model.db.abstractDB.repository.entity;

import com.myCompany.RepairAgency.model.db.abstractDB.exception.MyDBException;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.primitive.iRepositoryForChangeAble;
import com.myCompany.RepairAgency.model.entity.RepairOrder;

import java.util.ArrayList;

public interface iRepairOrderRepository extends iRepositoryForChangeAble<RepairOrder> {

    /**
     *  Implementation of this method should  return list of {@link com.myCompany.RepairAgency.model.entity.RepairOrder}
     * @param craftIds Array in which specified ids of selected Craftsmen for return list. If array empty will allow all ids.
     * @param userId ID of selected Client for return list. If 0 allow all.
     * @param statusIds Array in which specified ids of selected Order Statuses for return list. If array empty will allow all ids.
     * @param sort Sorting method for list.
     * @param skip How many elements skip in db.
     * @param quantity How many elements include  in list.
     * @return List of Orders that satisfies all params.
     * @throws MyDBException If db return error, return this exception.
     */
    ArrayList<RepairOrder> getByCraftUserStatus(long[] craftIds, long userId,
                                                long[] statusIds, SORT_TYPE sort,
                                                long skip, long quantity) throws MyDBException;

    /**
     * @param craftIds Array in which specified ids of selected Craftsmen for return list. If array empty will allow all ids.
     * @param userId ID of selected Client for return list. If 0 allow all.
     * @param statusIds Array in which specified ids of selected Order Statuses for return list. If array empty will allow all ids.
     * @return Number of elements that will contain list which satisfies all params.
     */
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
