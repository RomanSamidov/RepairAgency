package com.myCompany.RepairAgency.db.dao.PSQLDAO;

import com.myCompany.RepairAgency.db.Fields;
import com.myCompany.RepairAgency.db.dao.abstractDAO.abstractRepairOrderDAO;
import com.myCompany.RepairAgency.db.entity.RepairOrder;

import java.sql.*;
import java.util.ArrayList;

public class PSQLRepairOrderDAO extends abstractRepairOrderDAO {
    @Override
    public void insert(RepairOrder order) {
        try {
            Connection con=PSQLConnectionPool.getConnection();
            PreparedStatement pstmt=con.prepareStatement(PSQLQuery.RepairOrdersQuery.INSERT);
            pstmt.setInt(1, order.getUser_id());
            pstmt.setInt(2, order.getCraftsman_id());
            pstmt.setString(3, order.getText());
            pstmt.setInt(4, order.getPrice());
            pstmt.setInt(5, order.getStatus_id());
            pstmt.setString(6, order.getFeedback_text());
            pstmt.setInt(7, order.getFeedback_mark());
            pstmt.executeUpdate();
            PSQLConnectionPool.releaseConnection(con);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public RepairOrder getById(int id) {
        ResultSet rs;
        try {
            Connection con=PSQLConnectionPool.getConnection();
            PreparedStatement pstmt=con.prepareStatement(PSQLQuery.RepairOrdersQuery.SELECT_BY_ID);
            pstmt.setInt(1, id);
            rs=pstmt.executeQuery();
            rs.next();
            PSQLConnectionPool.releaseConnection(con);
            return new RepairOrder.RepairOrderBuilder().setId(rs.getInt(Fields.ID)).setUser_id(rs.getInt(Fields.USER_ID))
                    .setCraftsman_id(rs.getInt(Fields.CRAFTSMAN_ID)).setText(rs.getString(Fields.TEXT)).
                    setPrice(rs.getInt(Fields.PRICE)).setFeedback_text(rs.getString(Fields.FEEDBACK_TEXT))
                    .setFeedback_mark(rs.getInt(Fields.FEEDBACK_MARK)).build();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(RepairOrder order) {
        try {
            Connection con=PSQLConnectionPool.getConnection();
            PreparedStatement pstmt=con.prepareStatement(PSQLQuery.RepairOrdersQuery.UPDATE);
            pstmt.setInt(1, order.getUser_id());
            pstmt.setInt(2, order.getCraftsman_id());
            pstmt.setString(3, order.getText());
            pstmt.setInt(4, order.getPrice());
            pstmt.setInt(5, order.getStatus_id());
            pstmt.setString(6, order.getFeedback_text());
            pstmt.setInt(7, order.getFeedback_mark());
            pstmt.setInt(8, order.getId());
            pstmt.executeUpdate();
            PSQLConnectionPool.releaseConnection(con);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(RepairOrder order) {
        try {
            Connection con=PSQLConnectionPool.getConnection();
            PreparedStatement pstmt=con.prepareStatement(PSQLQuery.RepairOrdersQuery.DELETE);
            pstmt.setInt(1, order.getId());
            pstmt.executeUpdate();
            PSQLConnectionPool.releaseConnection(con);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<RepairOrder> getAll() {
        ResultSet rs;
        ArrayList<RepairOrder> list=new ArrayList<>();
        try {
            Connection con=PSQLConnectionPool.getConnection();
            Statement stmt=con.createStatement();
            rs=stmt.executeQuery(PSQLQuery.RepairOrdersQuery.SELECT_ALL);
            while (rs.next()) {
                list.add(new RepairOrder.RepairOrderBuilder().setId(rs.getInt(Fields.ID)).setUser_id(rs.getInt(Fields.USER_ID))
                        .setCraftsman_id(rs.getInt(Fields.CRAFTSMAN_ID)).setText(rs.getString(Fields.TEXT)).
                        setPrice(rs.getInt(Fields.PRICE)).setFeedback_text(rs.getString(Fields.FEEDBACK_TEXT)).setFeedback_mark(rs.getInt(Fields.FEEDBACK_MARK)).build());
            }
            PSQLConnectionPool.releaseConnection(con);
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<RepairOrder> getAllWhereStatusIs(Fields.ORDER_STATUS status) {
        ResultSet rs;
        ArrayList<RepairOrder> list=new ArrayList<>();
        try {
            Connection con=PSQLConnectionPool.getConnection();
            PreparedStatement pstmt=con.prepareStatement(PSQLQuery.RepairOrdersQuery.SELECT_BY_ID);
            pstmt.setInt(1, status.ordinal());
            rs=pstmt.executeQuery();
            PSQLConnectionPool.releaseConnection(con);
            while (rs.next()) {
                list.add(new RepairOrder.RepairOrderBuilder().setId(rs.getInt(Fields.ID)).setUser_id(rs.getInt(Fields.USER_ID))
                        .setCraftsman_id(rs.getInt(Fields.CRAFTSMAN_ID)).setText(rs.getString(Fields.TEXT)).
                        setPrice(rs.getInt(Fields.PRICE)).setFeedback_text(rs.getString(Fields.FEEDBACK_TEXT))
                        .setFeedback_mark(rs.getInt(Fields.FEEDBACK_MARK)).build());
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<RepairOrder> getAllWhereCraftsmanIdIs(int id) {
        ResultSet rs;
        ArrayList<RepairOrder> list=new ArrayList<>();
        try {
            Connection con=PSQLConnectionPool.getConnection();
            PreparedStatement pstmt=con.prepareStatement(PSQLQuery.RepairOrdersQuery.SELECT_BY_CRAFTSMAN_ID);
            pstmt.setInt(1, id);
            rs=pstmt.executeQuery();
            PSQLConnectionPool.releaseConnection(con);
            while (rs.next()) {
                list.add(new RepairOrder.RepairOrderBuilder().setId(rs.getInt(Fields.ID)).setUser_id(rs.getInt(Fields.USER_ID))
                        .setCraftsman_id(rs.getInt(Fields.CRAFTSMAN_ID)).setText(rs.getString(Fields.TEXT)).
                        setPrice(rs.getInt(Fields.PRICE)).setFeedback_text(rs.getString(Fields.FEEDBACK_TEXT))
                        .setFeedback_mark(rs.getInt(Fields.FEEDBACK_MARK)).build());
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
