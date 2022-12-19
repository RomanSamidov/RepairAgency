package com.myCompany.RepairAgency.model.db.PostgeSQL;

import com.myCompany.RepairAgency.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public class ConnectionPool {
//    private static final PGConnectionPoolDataSource pool = new PGConnectionPoolDataSource();

//    private static final ConnectionPool ins = new ConnectionPool();
    private static final int INITIAL_POOL_SIZE=10;
    private static final String URL = initializeUrl();
    private static final String USER = initializeUser();;
    private static final String PASSWORD = initializePassword();;
    private static final ArrayList<Connection> connectionPool=initializePool();
    private static final LinkedList<Connection> usedConnections=new LinkedList<>();

    private String getUrl(){
        return URL;
    }

    private static String initializeUrl() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(Constants.DB_SETTINGS_BUNDLE);
        return resourceBundle.getString(Constants.getConnectionUrl());
    }
    private static String initializePassword() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(Constants.DB_SETTINGS_BUNDLE);
        return resourceBundle.getString(Constants.PASSWORD);
    }

    private static String initializeUser() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(Constants.DB_SETTINGS_BUNDLE);
        return resourceBundle.getString(Constants.USER);
    }

//    static {
//        ResourceBundle resourceBundle = ResourceBundle.getBundle(Constants.DB_SETTINGS_BUNDLE);
//        pool.setUrl(resourceBundle.getString(Constants.getConnectionUrl()));
//        pool.setUser(resourceBundle.getString(Constants.USER));
//        pool.setPassword(resourceBundle.getString(Constants.PASSWORD));
//        pool.setInitialSize(Integer.parseInt(resourceBundle.getString("sr.db.initialSize")));
//        pool.setMaxTotal(Integer.parseInt(resourceBundle.getString("sr.db.totalSize")));



//        source.setDataSourceName("Logistica");
//        source.setServerName(config.get("servidor_sql"));
//        source.setPortNumber(Integer.parseInt(config.get("puerto_sql")));
//        source.setDatabaseName(config.get("bd_sql"));
//        source.setUser(config.get("usuario_sql"));
//        source.setPassword(config.get("contrasena_sql"));
//        source.setMaxConnections(30);


//        OracleDataSource ods = new OracleDataSource();
//        ods.setURL("jdbc:oracle:thin:@server:1521/testphones ");
//        ods.setUser("root"); ods.setPassword("pass");
//        ods.setConnectionCacheName(CACHE_NAME); Properties
//        cacheProps = new Properties();
//        cacheProps.setProperty("MinLimit", "0");
//        cacheProps.setProperty("MaxLimit", "4");
//        cacheProps.setProperty("InitialLimit", "1");
//        cacheProps.setProperty("ConnectionWaitTimeout", "5");
//        cacheProps.setProperty("ValidateConnection", "true");
//        ods.setConnectionProperties(cacheProps); Connection cn =
//        ods.getConnection();
//    }


    private ConnectionPool() {


    }




    private static ArrayList<Connection> initializePool() {
        ArrayList<Connection> list=new ArrayList<>(INITIAL_POOL_SIZE);
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Stream.generate(ConnectionPool::createConnection).limit(INITIAL_POOL_SIZE).forEach(c -> {
            try {
                c.setAutoCommit(false);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            list.add(c);});
        return list;
    }

    private static Connection createConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
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
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        usedConnections.remove(connection);
        connectionPool.add(connection);
    }


}
