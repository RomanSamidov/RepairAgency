package com.myCompany.RepairAgency.db.dao.PSQLDAO;

import com.myCompany.RepairAgency.Constants;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Properties;
import java.util.stream.Stream;

public class PSQLConnectionPool {
    private static final int INITIAL_POOL_SIZE=10;
    private static final String URL=loadURL();
    private static final ArrayList<Connection> connectionPool=initializePool();
    private static final LinkedList<Connection> usedConnections=new LinkedList<>();

    private PSQLConnectionPool() {
    }

    private static String loadURL() {
        Properties properties=new Properties();
        try {
            properties.load(new FileInputStream(Constants.SETTINGS_FILE));
            return properties.getProperty(Constants.CONNECTION_URL);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private static ArrayList<Connection> initializePool() {
        ArrayList<Connection> list=new ArrayList<>(INITIAL_POOL_SIZE);
        Stream.generate(PSQLConnectionPool::createConnection).limit(INITIAL_POOL_SIZE).forEach(list::add);
        return list;
    }

    private static Connection createConnection() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        Connection con=connectionPool.remove(connectionPool.size() - 1);
        try {
            if (!con.isValid(1000))
                con=createConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        usedConnections.add(con);
        return con;
    }

    public static void releaseConnection(Connection connection) {
        usedConnections.remove(connection);
        connectionPool.add(connection);
    }


}
