package com.myCompany.RepairAgency.model.db.PostgeSQL.service;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.db.PostgeSQL.ConnectionPool;
import com.myCompany.RepairAgency.model.db.PostgeSQL.Query;
import com.myCompany.RepairAgency.model.db.PostgeSQL.QueryExecutioner;
import com.myCompany.RepairAgency.model.db.PostgeSQL.repository.RepairOrderRepository;
import com.myCompany.RepairAgency.model.db.PostgeSQL.repository.UserRepository;
import com.myCompany.RepairAgency.model.db.abstractDB.exception.MyDBException;
import com.myCompany.RepairAgency.model.db.abstractDB.service.iOrderUserService;
import com.myCompany.RepairAgency.model.entity.RepairOrder;
import com.myCompany.RepairAgency.model.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class OrderUserService implements iOrderUserService {

    @Override
    public void cancelOrderWithMoneyReturn(long orderId) {
        Connection conn = ConnectionPool.getConnection();
        try {
            conn.setAutoCommit(false);

            RepairOrderRepository orderRepository = new RepairOrderRepository();
            RepairOrder order = orderRepository.getById(orderId);
            order.setStatus_id(Constants.ORDER_STATUS.CANCELED.ordinal());
            order.setFinish_date(LocalDateTime.now());

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
    public boolean payOrder(long orderId) {
        Connection conn = ConnectionPool.getConnection();
        try {
            conn.setAutoCommit(false);
            RepairOrderRepository orderRepository = new RepairOrderRepository();
            RepairOrder order = orderRepository.getById(orderId);
            order.setStatus_id(Constants.ORDER_STATUS.PAID.ordinal());
            User user = new UserRepository().getById(order.getUser_id());
            if (user.getAccount() < order.getPrice()) {
                conn.rollback();
                conn.setAutoCommit(true);
                ConnectionPool.releaseConnection(conn);
                return false;
            }
            user.setAccount(user.getAccount() - order.getPrice());
            QueryExecutioner.executeUpdate(conn, Query.UsersQuery.UPDATE,
                    UserRepository.extractFields(user, user.getId()));
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
