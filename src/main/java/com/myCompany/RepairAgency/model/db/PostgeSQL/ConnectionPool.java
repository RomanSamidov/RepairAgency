package com.myCompany.RepairAgency.model.db.PostgeSQL;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.db.abstractDB.exception.MyDBException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public class ConnectionPool {

    private static final int INITIAL_POOL_SIZE = 10;
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(Constants.DB_SETTINGS_BUNDLE);
    private static final String URL = initializeUrl();
    private static final String USER = initializeUser();
    private static final String PASSWORD = initializePassword();
    private static final ArrayList<Connection> connectionPool = initializePool();
    private static final LinkedList<Connection> usedConnections = new LinkedList<>();

    private ConnectionPool() {
    }

    private static String initializeUrl() {
        return resourceBundle.getString(Constants.CONNECTION_URL);
    }

    private static String initializePassword() {
        return resourceBundle.getString(Constants.PASSWORD);
    }

    private static String initializeUser() {
        return resourceBundle.getString(Constants.USER);
    }

    private static ArrayList<Connection> initializePool() throws MyDBException {
        ArrayList<Connection> list = new ArrayList<>(INITIAL_POOL_SIZE);
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            throw new MyDBException(e.getMessage(), e);
        }
        Stream.generate(ConnectionPool::createConnection).limit(INITIAL_POOL_SIZE).forEach(c -> {
            try {
                c.setAutoCommit(true);
            } catch (SQLException e) {
                throw new MyDBException(e.getMessage(), e);
            }
            list.add(c);
        });
        return list;
    }

    private static Connection createConnection() throws MyDBException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new MyDBException(e.getMessage(), e);
        }
    }

    public static synchronized Connection getConnection() throws MyDBException {
        Connection con = connectionPool.remove(connectionPool.size() - 1);
        try {
            if (!con.isValid(1000))
                con = createConnection();
        } catch (SQLException e) {
            throw new MyDBException(e.getMessage(), e);
        }
        usedConnections.add(con);
        return con;
    }

    public static synchronized void releaseConnection(Connection connection) throws MyDBException {
        try {
            if (!connection.getAutoCommit()) {
                connection.rollback();
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new MyDBException(e.getMessage(), e);
        }
        usedConnections.remove(connection);
        connectionPool.add(connection);
    }

}