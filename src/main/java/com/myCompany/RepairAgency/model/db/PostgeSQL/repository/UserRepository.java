package com.myCompany.RepairAgency.model.db.PostgeSQL.repository;


import com.myCompany.RepairAgency.model.db.PostgeSQL.ConnectionPool;
import com.myCompany.RepairAgency.model.db.PostgeSQL.Query;
import com.myCompany.RepairAgency.model.db.PostgeSQL.QueryExecutioner;
import com.myCompany.RepairAgency.model.db.PostgeSQL.factory.UserFactory;
import com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.entity.iUserRepository;
import com.myCompany.RepairAgency.model.entity.User;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class UserRepository implements iUserRepository {
    public static Object[] extractFields(User user, Object... args) {
        Object[] arr1 = new Object[]{ user.getLogin(),
                             user.getPassword(),
                             user.getRole_id(),
                             user.getAccount()};

        return Stream.concat(Arrays.stream(arr1), Arrays.stream(args)).toArray();
    }

    @Override
    public void update(User user) {
        Connection conn = ConnectionPool.getConnection();
        QueryExecutioner.executeUpdate(conn, Query.UsersQuery.UPDATE,
                UserRepository.extractFields(user, user.getId()));
        ConnectionPool.releaseConnection(conn);
    }

    @Override
    public void delete(User user) {
        Connection conn = ConnectionPool.getConnection();
        QueryExecutioner.executeUpdate(conn, Query.UsersQuery.DELETE, user.getId());
        ConnectionPool.releaseConnection(conn);
    }

    @Override
    public void insert(User user) {
        Connection conn = ConnectionPool.getConnection();
        QueryExecutioner.executeUpdate(conn, Query.UsersQuery.INSERT,
                UserRepository.extractFields(user));
        ConnectionPool.releaseConnection(conn);
    }

    @Override
    public User getById(int id) {
        Connection conn = ConnectionPool.getConnection();
        User res = QueryExecutioner.readEntity(UserFactory.ins, conn, Query.UsersQuery.SELECT_BY_ID, id);
        ConnectionPool.releaseConnection(conn);
        return res;
    }

    @Override
    public User getByLogin(String login) {
        Connection conn = ConnectionPool.getConnection();
        User res = QueryExecutioner.readEntity(UserFactory.ins, conn, Query.UsersQuery.SELECT_BY_LOGIN, login);
        ConnectionPool.releaseConnection(conn);
        return res;
    }

    @Override
    public ArrayList<User> getAll() {
        Connection conn = ConnectionPool.getConnection();
        ArrayList<User> res = QueryExecutioner.readList(UserFactory.ins, conn, Query.UsersQuery.SELECT_ALL);
        ConnectionPool.releaseConnection(conn);
        return res;
    }

    @Override
    public ArrayList<User> getWithPagination(int skip, int quantity) {
        Connection conn = ConnectionPool.getConnection();
        ArrayList<User> res = QueryExecutioner.readList(UserFactory.ins, conn,
                Query.UsersQuery.SELECT_WITH_PAGINATION, skip, quantity);
        ConnectionPool.releaseConnection(conn);
        return res;
    }
}