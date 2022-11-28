package com.myCompany.RepairAgency.db.dao.PSQLDAO;

import com.myCompany.RepairAgency.db.Fields;
import com.myCompany.RepairAgency.db.dao.abstractDAO.abstractRoleDAO;
import com.myCompany.RepairAgency.db.entity.Role;

import java.sql.*;
import java.util.ArrayList;

public class PSQLRoleDAO extends abstractRoleDAO {

    @Override
    public void insert(Role obj) {
        throw new RuntimeException();
    }

    @Override
    public Role getById(int id) {
        ResultSet rs;
        try {
            Connection con=PSQLConnectionPool.getConnection();
            PreparedStatement pstmt=con.prepareStatement(PSQLQuery.RolesQuery.SELECT_BY_ID);
            pstmt.setInt(1, id);
            rs=pstmt.executeQuery();
            rs.next();
            PSQLConnectionPool.releaseConnection(con);
            return new Role.RoleBuilder().setId(rs.getInt(Fields.ID)).setName(rs.getString(Fields.NAME)).build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Role obj) {
        throw new RuntimeException();
    }

    @Override
    public void delete(Role obj) {
        throw new RuntimeException();
    }

    @Override
    public ArrayList<Role> getAll() {
        ResultSet rs;
        ArrayList<Role> list=new ArrayList<>();
        try {
            Connection con=PSQLConnectionPool.getConnection();
            Statement stmt=con.createStatement();
            rs=stmt.executeQuery(PSQLQuery.RolesQuery.SELECT_ALL);
            while (rs.next()) {
                list.add(new Role.RoleBuilder().setId(rs.getInt(Fields.ID)).setName(rs.getString(Fields.NAME)).build());
            }
            PSQLConnectionPool.releaseConnection(con);
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
