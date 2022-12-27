package com.myCompany.RepairAgency.model.db.PostgeSQL.factory;

import com.myCompany.RepairAgency.model.Fields;
import com.myCompany.RepairAgency.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserFactory extends abstractEntityFactory<User>{

    public final static UserFactory ins = new UserFactory();

    @Override
    protected User readEntity(ResultSet rs) throws SQLException{
        return new User.UserBuilder()
                .setId(rs.getInt(Fields.ID))
                .setLogin(rs.getString(Fields.LOGIN))
                .setPassword(rs.getString(Fields.PASSWORD))
                .setEmail(rs.getString(Fields.EMAIL))
                .setRole_id(rs.getInt(Fields.ROLE_ID))
                .setAccount(rs.getInt(Fields.ACCOUNT))
                .build();
    }

}
