package com.myCompany.RepairAgency.model.db.PostgeSQL.repository;


import com.myCompany.RepairAgency.model.db.PostgeSQL.ConnectionPool;
import com.myCompany.RepairAgency.model.db.PostgeSQL.Query;
import com.myCompany.RepairAgency.model.db.PostgeSQL.QueryExecutioner;
import com.myCompany.RepairAgency.model.db.PostgeSQL.factory.UserFactory;
import com.myCompany.RepairAgency.model.db.abstractDB.exception.MyDBException;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iUserRepository;
import com.myCompany.RepairAgency.model.entity.User;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class UserRepository implements iUserRepository {
    public static Object[] extractFields(User user, Object... args) {
        Object[] arr1 = new Object[]{user.getLogin(),
                user.getPassword(),
                user.getEmail(),
                user.isAllow_letters(),
                user.isConfirmed(),
                user.getRole_id(),
                user.getAccount(),
                user.getLocale_id()};

        return Stream.concat(Arrays.stream(arr1), Arrays.stream(args)).toArray();
    }

    @Override
    public void update(User user) throws MyDBException {
        Connection conn = ConnectionPool.getConnection();
        try {
            QueryExecutioner.executeUpdate(conn, Query.UsersQuery.UPDATE,
                    UserRepository.extractFields(user, user.getId()));
        } finally {
            ConnectionPool.releaseConnection(conn);
        }
    }

    @Override
    public void increaseAccount(long userId, long increment) throws MyDBException {
        Connection conn = ConnectionPool.getConnection();
        try {
            QueryExecutioner.executeUpdate(conn, Query.UsersQuery.INCREASE_ACCOUNT,
                    increment, userId);
        } finally {
            ConnectionPool.releaseConnection(conn);
        }
    }

    @Override
    public void delete(User user) throws MyDBException {
        Connection conn = ConnectionPool.getConnection();
        try {
            QueryExecutioner.executeUpdate(conn, Query.UsersQuery.DELETE, user.getId());
        } finally {
            ConnectionPool.releaseConnection(conn);
        }
    }

    @Override
    public void delete(long id) throws MyDBException {
        Connection conn = ConnectionPool.getConnection();
        try {
            QueryExecutioner.executeUpdate(conn, Query.UsersQuery.DELETE, id);
        } finally {
            ConnectionPool.releaseConnection(conn);
        }
    }

    @Override
    public void insert(User user) throws MyDBException {
        Connection conn = ConnectionPool.getConnection();
        try {
            long id = QueryExecutioner.executeUpdate(conn, Query.UsersQuery.INSERT,
                    UserRepository.extractFields(user));
            user.setId(id);
        } finally {
            ConnectionPool.releaseConnection(conn);
        }
    }

    @Override
    public User getById(long id) throws MyDBException {
        Connection conn = ConnectionPool.getConnection();
        User res;
        try {
            res = QueryExecutioner.readEntity(UserFactory.ins, conn, Query.UsersQuery.SELECT_BY_ID, id);
        } finally {
            ConnectionPool.releaseConnection(conn);
        }
        return res;
    }

    @Override
    public User getByLogin(String login) throws MyDBException {
        Connection conn = ConnectionPool.getConnection();
        try {
            return QueryExecutioner.readEntity(UserFactory.ins, conn, Query.UsersQuery.SELECT_BY_LOGIN, login);
        } finally {
            ConnectionPool.releaseConnection(conn);
        }
    }

    @Override
    public ArrayList<User> getByRole(long roleId, long skip, long quantity) throws MyDBException {
        Connection conn = ConnectionPool.getConnection();
        try {
            return QueryExecutioner.readList(UserFactory.ins, conn, Query.UsersQuery.SELECT_ALL_BY_ROLE,
                    roleId, roleId, skip, quantity);
        } finally {
            ConnectionPool.releaseConnection(conn);
        }
    }

    @Override
    public long countWhereRoleIs(long roleId) throws MyDBException {
        Connection conn = ConnectionPool.getConnection();
        try {
            return QueryExecutioner.readNumber(conn, Query.UsersQuery.COUNT_BY_ROLE, roleId, roleId);
        } finally {
            ConnectionPool.releaseConnection(conn);
        }
    }
}
