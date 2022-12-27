package com.myCompany.RepairAgency.model.db.PostgeSQL.factory;

import com.myCompany.RepairAgency.model.Fields;
import com.myCompany.RepairAgency.model.entity.RepairOrder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class RepairOrderFactory extends  abstractEntityFactory<RepairOrder> {
    public final static RepairOrderFactory ins = new RepairOrderFactory();
    @Override
    protected RepairOrder readEntity(ResultSet rs) throws SQLException {
        return new RepairOrder.RepairOrderBuilder()
                .setId(rs.getInt(Fields.ID))
                .setUser_id(rs.getInt(Fields.USER_ID))
                .setCraftsman_id(rs.getInt(Fields.CRAFTSMAN_ID))
                .setCreation_date(toLocalDateTime(rs.getTimestamp(Fields.CREATION_DATE)))
                .setText(rs.getString(Fields.TEXT))
                .setPrice(rs.getInt(Fields.PRICE))
                .setFinish_date(toLocalDateTime(rs.getTimestamp(Fields.FINISH_DATE)))
                .setStatus_id(rs.getInt(Fields.STATUS_ID))
                .setFeedback_date(toLocalDateTime(rs.getTimestamp(Fields.FEEDBACK_DATE)))
                .setFeedback_text(rs.getString(Fields.FEEDBACK_TEXT))
                .setFeedback_mark(rs.getInt(Fields.FEEDBACK_MARK))
                .build();
    }

    private LocalDateTime toLocalDateTime(Timestamp ts){
        if(ts == null) return null;
        return ts.toLocalDateTime();
    }
}
