package com.myCompany.RepairAgency.model.db.PostgeSQL.factory;

import com.myCompany.RepairAgency.model.Fields;
import com.myCompany.RepairAgency.model.entity.OrderStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderStatusFactory extends abstractEntityFactory<OrderStatus> {

    public final static OrderStatusFactory ins = new OrderStatusFactory();
    @Override
    protected OrderStatus readEntity(ResultSet rs) throws SQLException {
        return new OrderStatus.OrderStatusBuilder()
                .setId(rs.getInt(Fields.ID))
                .setName(rs.getString(Fields.NAME))
                .build();
    }
}
