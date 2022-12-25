package com.myCompany.RepairAgency.model.db.PostgeSQL.repository;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.db.PostgeSQL.ConnectionPool;
import com.myCompany.RepairAgency.model.db.PostgeSQL.Query;
import com.myCompany.RepairAgency.model.db.PostgeSQL.QueryExecutioner;
import com.myCompany.RepairAgency.model.db.PostgeSQL.factory.RepairOrderFactory;
import com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.entity.iRepairOrderRepository;
import com.myCompany.RepairAgency.model.entity.RepairOrder;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class RepairOrderRepository implements iRepairOrderRepository {
    public static Object[] extractFields(RepairOrder order, Object... args) {
        Object[] arr1 = new Object[]{order.getUser_id(),
                            order.getCraftsman_id()==0?null:order.getCraftsman_id(),
                            order.getText(),
                            order.getPrice(),
                            order.getStatus_id(),
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
        RepairOrder res = QueryExecutioner.readEntity(RepairOrderFactory.ins, conn, Query.RepairOrdersQuery.SELECT_BY_ID, id);
        ConnectionPool.releaseConnection(conn);
        return res;
    }

    @Override
    public ArrayList<RepairOrder> getAll() {
        Connection conn = ConnectionPool.getConnection();
        ArrayList<RepairOrder> res = QueryExecutioner.readList(RepairOrderFactory.ins, conn,
                Query.RepairOrdersQuery.SELECT_ALL);
        ConnectionPool.releaseConnection(conn);
        return res;
    }

    @Override
    public ArrayList<RepairOrder> getWithPagination(int skip, int quantity) {
        Connection conn = ConnectionPool.getConnection();
        ArrayList<RepairOrder> res = QueryExecutioner.readList(RepairOrderFactory.ins, conn,
                Query.RepairOrdersQuery.SELECT_WITH_PAGINATION, skip, quantity);
        ConnectionPool.releaseConnection(conn);
        return res;
    }

    @Override
    public ArrayList<RepairOrder> getAllWhereStatusIs(Constants.ORDER_STATUS status, int skip, int quantity) {
        Connection conn = ConnectionPool.getConnection();
        ArrayList<RepairOrder> res = QueryExecutioner.readList(RepairOrderFactory.ins, conn, Query.RepairOrdersQuery.SELECT_BY_STATUS, status.ordinal(), skip, quantity);
        ConnectionPool.releaseConnection(conn);
        return res;
    }

    @Override
    public ArrayList<RepairOrder> getAllWhereCraftsmanIdIs(int id, int skip, int quantity) {
        Connection conn = ConnectionPool.getConnection();
        ArrayList<RepairOrder> res = QueryExecutioner.readList(RepairOrderFactory.ins, conn, Query.RepairOrdersQuery.SELECT_BY_CRAFTSMAN_ID, id, skip, quantity);
        ConnectionPool.releaseConnection(conn);
        return res;
    }

    @Override
    public ArrayList<RepairOrder> getAllWhereUserIdIs(long id, int skip, int quantity) {
        Connection conn = ConnectionPool.getConnection();
        ArrayList<RepairOrder> res = QueryExecutioner.readList(RepairOrderFactory.ins, conn, Query.RepairOrdersQuery.SELECT_BY_USER_ID, id, skip, quantity);
        ConnectionPool.releaseConnection(conn);
        return res;
    }

    @Override
    public long getCountWhereUserIdIs(long id) {
        Connection conn = ConnectionPool.getConnection();
        long res = QueryExecutioner.readNumber(conn, Query.RepairOrdersQuery.COUNT_BY_USER_ID, id);
        ConnectionPool.releaseConnection(conn);
        return res;
    }
}
