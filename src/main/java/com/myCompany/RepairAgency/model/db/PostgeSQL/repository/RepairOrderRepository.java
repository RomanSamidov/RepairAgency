package com.myCompany.RepairAgency.model.db.PostgeSQL.repository;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.db.PostgeSQL.ConnectionPool;
import com.myCompany.RepairAgency.model.db.PostgeSQL.Query;
import com.myCompany.RepairAgency.model.db.PostgeSQL.QueryExecutioner;
import com.myCompany.RepairAgency.model.db.PostgeSQL.factory.RepairOrderFactory;
import com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.entity.iRepairOrderRepository;
import com.myCompany.RepairAgency.model.entity.RepairOrder;
import com.myCompany.RepairAgency.model.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class RepairOrderRepository implements iRepairOrderRepository {
    public static Object[] extractFields(RepairOrder order, Object... args) {
        Object[] arr1 = new Object[]{order.getUser_id(),
                order.getCraftsman_id() == 0 ? null : order.getCraftsman_id(),
                order.getCreation_date(),
                order.getText(),
                order.getPrice(),
                order.getFinish_date(),
                order.getStatus_id(),
                order.getFeedback_date(),
                order.getFeedback_text(),
                order.getFeedback_mark()};

        return Stream.concat(Arrays.stream(arr1), Arrays.stream(args)).toArray();
    }

    @Override
    public void update(RepairOrder order) {
        Connection conn = ConnectionPool.getConnection();
        QueryExecutioner.executeUpdate(conn, Query.RepairOrdersQuery.UPDATE,
                RepairOrderRepository.extractFields(order, order.getId()));
        ConnectionPool.releaseConnection(conn);
    }

    @Override
    public void cancelOrderWithMoneyReturn(long orderId) {
        Connection conn = ConnectionPool.getConnection();
        try {
            conn.setAutoCommit(false);

            RepairOrder order = getById(orderId);
            order.setStatus_id(Constants.ORDER_STATUS.CANCELED.ordinal());
            order.setFinish_date(LocalDateTime.now());
            User user = new UserRepository().getById(order.getUser_id());
            user.setAccount(user.getAccount()+order.getPrice());

            QueryExecutioner.executeUpdate(conn, Query.RepairOrdersQuery.UPDATE,
                    RepairOrderRepository.extractFields(order, order.getId()));
            QueryExecutioner.executeUpdate(conn, Query.UsersQuery.UPDATE,
                    UserRepository.extractFields(user,user.getId()));

            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ConnectionPool.releaseConnection(conn);
    }

    @Override
    public boolean payOrder(long orderId) {
        Connection conn = ConnectionPool.getConnection();
        try {
            conn.setAutoCommit(false);

            RepairOrder order = getById(orderId);
            order.setStatus_id(Constants.ORDER_STATUS.PAID.ordinal());
            User user = new UserRepository().getById(order.getUser_id());
            if(user.getAccount() < order.getPrice()) {
                conn.rollback();
                conn.setAutoCommit(true);
                ConnectionPool.releaseConnection(conn);
                return false;
            }
            user.setAccount(user.getAccount()-order.getPrice());
            QueryExecutioner.executeUpdate(conn, Query.UsersQuery.UPDATE,
                    UserRepository.extractFields(user,user.getId()));
            QueryExecutioner.executeUpdate(conn, Query.RepairOrdersQuery.UPDATE,
                    RepairOrderRepository.extractFields(order, order.getId()));
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ConnectionPool.releaseConnection(conn);
        return true;
    }


    @Override
    public void delete(RepairOrder order) {
        Connection conn = ConnectionPool.getConnection();
        QueryExecutioner.executeUpdate(conn, Query.RepairOrdersQuery.DELETE, order.getId());
        ConnectionPool.releaseConnection(conn);
    }

    @Override
    public void insert(RepairOrder order) {
        Connection conn = ConnectionPool.getConnection();
        QueryExecutioner.executeUpdate(conn, Query.RepairOrdersQuery.INSERT,
                RepairOrderRepository.extractFields(order));
        ConnectionPool.releaseConnection(conn);
    }

    @Override
    public RepairOrder getById(long id) {
        Connection conn = ConnectionPool.getConnection();
        RepairOrder res = QueryExecutioner.readEntity(RepairOrderFactory.ins, conn,
                Query.RepairOrdersQuery.SELECT_BY_ID, id);
        ConnectionPool.releaseConnection(conn);
        return res;
    }

    @Override
    public ArrayList<RepairOrder> getByCraftUserStatus(long[] craftIds, long userId,
                                                       long[] statusIds, SORT_TYPE sort, long skip, long quantity) {
        Connection conn = ConnectionPool.getConnection();
        ArrayList<RepairOrder> res = QueryExecutioner.readList(RepairOrderFactory.ins, conn
                , Query.RepairOrdersQuery.SELECT_BY_CRAFT_USER_STATUS +
                        Query.RepairOrdersQuery.getSortQuery(sort)
                , craftIds, new long[]{}, craftIds, userId, userId, statusIds, new long[]{},
                statusIds, skip, quantity);
        ConnectionPool.releaseConnection(conn);
        return res;
    }

    @Override
    public long countByCraftUserStatus(long[] craftIds, long userId, long[] statusIds) {
        Connection conn = ConnectionPool.getConnection();
        long res = QueryExecutioner.readNumber(conn, Query.RepairOrdersQuery.COUNT_BY_CRAFT_USER_STATUS
                , craftIds, new long[]{}, craftIds, userId, userId, statusIds, new long[]{}, statusIds);
        ConnectionPool.releaseConnection(conn);
        return res;
    }



}
