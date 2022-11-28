package com.myCompany.RepairAgency.db.dao.PSQLDAO;

import com.myCompany.RepairAgency.db.Fields;
import com.myCompany.RepairAgency.db.dao.abstractDAO.abstractOrderStatusDAO;
import com.myCompany.RepairAgency.db.entity.OrderStatus;

import java.sql.*;
import java.util.ArrayList;

public class PSQLOrderStatusDAO extends abstractOrderStatusDAO {

    @Override
    public void insert(OrderStatus status) {
        throw new RuntimeException();
    }

    @Override
    public OrderStatus getById(int id) {
        ResultSet rs;
        try {
            Connection con=PSQLConnectionPool.getConnection();
            PreparedStatement pstmt=con.prepareStatement(PSQLQuery.Orders_statusesQuery.SELECT_BY_ID);
            pstmt.setInt(1, id);
            rs=pstmt.executeQuery();
            rs.next();
            PSQLConnectionPool.releaseConnection(con);
            return new OrderStatus.OrderStatusBuilder().setId(rs.getInt(Fields.ID))
                    .setName(rs.getString(Fields.NAME)).build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(OrderStatus status) {
        throw new RuntimeException();
    }

    @Override
    public void delete(OrderStatus status) {
        throw new RuntimeException();
    }

    @Override
    public ArrayList<OrderStatus> getAll() {
        ResultSet rs;
        ArrayList<OrderStatus> list=new ArrayList<>();
        try {
            Connection con=PSQLConnectionPool.getConnection();
            Statement stmt=con.createStatement();
            rs=stmt.executeQuery(PSQLQuery.Orders_statusesQuery.SELECT_ALL);
            while (rs.next()) {
                list.add(new OrderStatus.OrderStatusBuilder().setId(rs.getInt(Fields.ID))
                        .setName(rs.getString(Fields.NAME)).build());
            }
            PSQLConnectionPool.releaseConnection(con);
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
