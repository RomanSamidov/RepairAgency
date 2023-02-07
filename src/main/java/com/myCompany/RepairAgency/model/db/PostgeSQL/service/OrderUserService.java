package com.myCompany.RepairAgency.model.db.PostgeSQL.service;

import com.myCompany.RepairAgency.model.db.PostgeSQL.ConnectionPool;
import com.myCompany.RepairAgency.model.db.PostgeSQL.Query;
import com.myCompany.RepairAgency.model.db.PostgeSQL.QueryExecutioner;
import com.myCompany.RepairAgency.model.db.PostgeSQL.repository.RepairOrderRepository;
import com.myCompany.RepairAgency.model.db.abstractDB.exception.MyDBException;
import com.myCompany.RepairAgency.model.db.abstractDB.service.iOrderUserService;
import com.myCompany.RepairAgency.model.entity.RepairOrder;
import com.myCompany.RepairAgency.model.entity.User;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderUserService implements iOrderUserService {

    @Override
    public void cancelOrderWithMoneyReturn(RepairOrder order, User user) {
        Connection conn = ConnectionPool.getConnection();
        try {
            conn.setAutoCommit(false);

            QueryExecutioner.executeUpdate(conn, Query.RepairOrdersQuery.UPDATE,
                    RepairOrderRepository.extractFields(order, order.getId()));

            QueryExecutioner.executeUpdate(conn, Query.UsersQuery.INCREASE_ACCOUNT,
                    order.getPrice(), order.getUser_id());

            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            throw new MyDBException(e.getMessage(), e);
        } finally {
            ConnectionPool.releaseConnection(conn);
        }
    }

    @Override
    public boolean payOrder(RepairOrder order, User user) {
        Connection conn = ConnectionPool.getConnection();
        try {
            conn.setAutoCommit(false);

            QueryExecutioner.executeUpdate(conn, Query.UsersQuery.INCREASE_ACCOUNT,
                    -order.getPrice(), order.getUser_id());

            QueryExecutioner.executeUpdate(conn, Query.RepairOrdersQuery.UPDATE,
                    RepairOrderRepository.extractFields(order, order.getId()));

            conn.commit();
            conn.setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            throw new MyDBException(e.getMessage(), e);
        } finally {
            ConnectionPool.releaseConnection(conn);
        }
    }


}
