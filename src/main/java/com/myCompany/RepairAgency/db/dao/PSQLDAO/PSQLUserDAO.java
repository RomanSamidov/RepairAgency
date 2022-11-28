package com.myCompany.RepairAgency.db.dao.PSQLDAO;

import com.myCompany.RepairAgency.db.Fields;
import com.myCompany.RepairAgency.db.dao.abstractDAO.abstractUserDAO;
import com.myCompany.RepairAgency.db.entity.User;

import java.sql.*;
import java.util.ArrayList;

public class PSQLUserDAO extends abstractUserDAO {

    @Override
    public void insert(User user) {
        try {
            Connection con=PSQLConnectionPool.getConnection();
            PreparedStatement pstmt=con.prepareStatement(PSQLQuery.UsersQuery.INSERT);
            pstmt.setString(1, user.getLogin());
            pstmt.setString(2, user.getPassword());
            pstmt.setInt(3, user.getRole_id());
            pstmt.setInt(4, user.getAccount());
            pstmt.executeUpdate();
            PSQLConnectionPool.releaseConnection(con);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getById(int id) {
        ResultSet rs;
        try {
            Connection con=PSQLConnectionPool.getConnection();
            PreparedStatement pstmt=con.prepareStatement(PSQLQuery.UsersQuery.SELECT_BY_ID);
            pstmt.setInt(1, id);
            rs=pstmt.executeQuery();
            rs.next();
            PSQLConnectionPool.releaseConnection(con);
            return new User.UserBuilder().setId(rs.getInt(Fields.ID)).setLogin(rs.getString(Fields.LOGIN))
                    .setPassword(rs.getString(Fields.PASSWORD)).setRole_id(rs.getInt(Fields.ROLE_ID)).
                    setAccount(rs.getInt(Fields.ACCOUNT)).build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {
        try {
            Connection con=PSQLConnectionPool.getConnection();
            PreparedStatement pstmt=con.prepareStatement(PSQLQuery.UsersQuery.UPDATE);
            pstmt.setString(1, user.getLogin());
            pstmt.setString(2, user.getPassword());
            pstmt.setInt(3, user.getRole_id());
            pstmt.setInt(4, user.getAccount());
            pstmt.setInt(5, user.getId());
            pstmt.executeUpdate();
            PSQLConnectionPool.releaseConnection(con);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(User user) {
        try {
            Connection con=PSQLConnectionPool.getConnection();
            PreparedStatement pstmt=con.prepareStatement(PSQLQuery.UsersQuery.DELETE);
            pstmt.setInt(1, user.getId());
            pstmt.executeUpdate();
            PSQLConnectionPool.releaseConnection(con);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<User> getAll() {
        ResultSet rs;
        ArrayList<User> list=new ArrayList<>();
        try {
            Connection con=PSQLConnectionPool.getConnection();
            Statement stmt=con.createStatement();
            rs=stmt.executeQuery(PSQLQuery.UsersQuery.SELECT_ALL);
            while (rs.next()) {
                list.add(new User.UserBuilder().setId(rs.getInt(Fields.ID)).setLogin(rs.getString(Fields.LOGIN))
                        .setPassword(rs.getString(Fields.PASSWORD)).setRole_id(rs.getInt(Fields.ROLE_ID)).
                        setAccount(rs.getInt(Fields.ACCOUNT)).build());
            }
            PSQLConnectionPool.releaseConnection(con);
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
