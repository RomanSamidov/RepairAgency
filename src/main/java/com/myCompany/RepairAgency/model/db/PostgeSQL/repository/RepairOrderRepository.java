package com.myCompany.RepairAgency.model.db.PostgeSQL.repository;


import com.myCompany.RepairAgency.model.db.PostgeSQL.ConnectionPool;
import com.myCompany.RepairAgency.model.db.PostgeSQL.Query;
import com.myCompany.RepairAgency.model.db.PostgeSQL.QueryExecutioner;
import com.myCompany.RepairAgency.model.db.PostgeSQL.factory.RepairOrderFactory;
import com.myCompany.RepairAgency.model.db.abstractDB.exception.MyDBException;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iRepairOrderRepository;
import com.myCompany.RepairAgency.model.entity.RepairOrder;

import java.sql.Connection;
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
    public void update(RepairOrder order) throws MyDBException {
        Connection conn = ConnectionPool.getConnection();
        try {
            QueryExecutioner.executeUpdate(conn, Query.RepairOrdersQuery.UPDATE,
                    RepairOrderRepository.extractFields(order, order.getId()));
        } finally {
            ConnectionPool.releaseConnection(conn);
        }
    }

    @Override
    public void delete(RepairOrder order) throws MyDBException {
        Connection conn = ConnectionPool.getConnection();
        try {
            QueryExecutioner.executeUpdate(conn, Query.RepairOrdersQuery.DELETE, order.getId());
        } finally {
            ConnectionPool.releaseConnection(conn);
        }
    }

    @Override
    public void delete(long id) throws MyDBException {
        Connection conn = ConnectionPool.getConnection();
        try {
            QueryExecutioner.executeUpdate(conn, Query.RepairOrdersQuery.DELETE, id);
        } finally {
            ConnectionPool.releaseConnection(conn);
        }
    }

    @Override
    public void insert(RepairOrder order) throws MyDBException {
        Connection conn = ConnectionPool.getConnection();
        try {
            long id = QueryExecutioner.executeUpdate(conn, Query.RepairOrdersQuery.INSERT,
                    RepairOrderRepository.extractFields(order));
            order.setId(id);
        } finally {
            ConnectionPool.releaseConnection(conn);
        }
    }

    @Override
    public RepairOrder getById(long id) throws MyDBException {
        Connection conn = ConnectionPool.getConnection();
        try {
            return QueryExecutioner.readEntity(RepairOrderFactory.ins, conn,
                    Query.RepairOrdersQuery.SELECT_BY_ID, id);
        } finally {
            ConnectionPool.releaseConnection(conn);
        }
    }

    @Override
    public ArrayList<RepairOrder> getByCraftUserStatus(long[] craftIds, long userId,
                                                       long[] statusIds, SORT_TYPE sort,
                                                       long skip, long quantity) throws MyDBException {
        Connection conn = ConnectionPool.getConnection();
        try {
            return QueryExecutioner.readList(RepairOrderFactory.ins, conn
                    , Query.RepairOrdersQuery.SELECT_BY_CRAFT_USER_STATUS +
                            Query.RepairOrdersQuery.getSortQuery(sort)
                    , craftIds, new long[]{}, craftIds,
                    userId, userId,
                    statusIds, new long[]{}, statusIds,
                    skip, quantity);
        } finally {
            ConnectionPool.releaseConnection(conn);
        }
    }

    @Override
    public long countByCraftUserStatus(long[] craftIds, long userId, long[] statusIds) throws MyDBException {
        Connection conn = ConnectionPool.getConnection();
        try {
            return QueryExecutioner.readNumber(conn, Query.RepairOrdersQuery.COUNT_BY_CRAFT_USER_STATUS
                    , craftIds, new long[]{}, craftIds,
                    userId, userId,
                    statusIds, new long[]{}, statusIds);
        } finally {
            ConnectionPool.releaseConnection(conn);
        }
    }
}
