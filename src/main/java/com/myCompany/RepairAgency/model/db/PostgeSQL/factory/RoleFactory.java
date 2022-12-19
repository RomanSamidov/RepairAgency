package com.myCompany.RepairAgency.model.db.PostgeSQL.factory;

import com.myCompany.RepairAgency.model.Fields;
import com.myCompany.RepairAgency.model.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleFactory extends abstractEntityFactory<Role> {

    public final static RoleFactory ins = new RoleFactory();
    @Override
    protected Role readEntity(ResultSet rs) throws SQLException {
        return new Role.RoleBuilder()
                .setId(rs.getInt(Fields.ID))
                .setName(rs.getString(Fields.NAME))
                .build();
    }
}
