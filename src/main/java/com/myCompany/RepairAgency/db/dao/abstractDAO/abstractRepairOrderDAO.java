package com.myCompany.RepairAgency.db.dao.abstractDAO;

import com.myCompany.RepairAgency.db.Fields;
import com.myCompany.RepairAgency.db.entity.RepairOrder;

import java.util.ArrayList;

public abstract class abstractRepairOrderDAO extends abstractDAO<RepairOrder> {

    public abstract ArrayList<RepairOrder> getAllWhereStatusIs(Fields.ORDER_STATUS status);

    public abstract ArrayList<RepairOrder> getAllWhereCraftsmanIdIs(int id);
}
