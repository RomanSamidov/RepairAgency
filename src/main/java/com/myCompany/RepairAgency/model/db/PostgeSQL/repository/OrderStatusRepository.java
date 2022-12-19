package com.myCompany.RepairAgency.model.db.PostgeSQL.repository;

import com.myCompany.RepairAgency.model.db.PostgeSQL.ConnectionPool;
import com.myCompany.RepairAgency.model.db.PostgeSQL.Query;
import com.myCompany.RepairAgency.model.db.PostgeSQL.QueryExecutioner;
import com.myCompany.RepairAgency.model.db.PostgeSQL.factory.OrderStatusFactory;
import com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.entity.iOrderStatusRepository;
import com.myCompany.RepairAgency.model.entity.OrderStatus;

import java.sql.Connection;
import java.util.ArrayList;

public class OrderStatusRepository implements iOrderStatusRepository {
    @Override
    public OrderStatus getById(int id) {
        Connection conn = ConnectionPool.getConnection();
        OrderStatus res = QueryExecutioner.readEntity(OrderStatusFactory.ins, conn, Query.Orders_statusesQuery.SELECT_BY_ID, id);
        ConnectionPool.releaseConnection(conn);
        return res;
    }

    @Override
    public ArrayList<OrderStatus> getAll() {
        Connection conn = ConnectionPool.getConnection();
        ArrayList<OrderStatus> res = QueryExecutioner.readList(OrderStatusFactory.ins, conn, Query.Orders_statusesQuery.SELECT_ALL);
        ConnectionPool.releaseConnection(conn);
        return res;
    }

    @Override
    public ArrayList<OrderStatus> getWithPagination(int skip, int quantity) {
        Connection conn = ConnectionPool.getConnection();
        ArrayList<OrderStatus> res = QueryExecutioner.readList(OrderStatusFactory.ins, conn, Query.Orders_statusesQuery.SELECT_WITH_PAGINATION, skip, quantity);
        ConnectionPool.releaseConnection(conn);
        return res;
    }
}
