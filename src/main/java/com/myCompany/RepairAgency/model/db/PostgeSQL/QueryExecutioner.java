package com.myCompany.RepairAgency.model.db.PostgeSQL;

import com.myCompany.RepairAgency.model.Fields;
import com.myCompany.RepairAgency.model.db.PostgeSQL.factory.abstractEntityFactory;
import com.myCompany.RepairAgency.model.db.abstractDB.exception.MyDBException;
import com.myCompany.RepairAgency.model.entity.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class QueryExecutioner {
    private static final Logger logger = LogManager.getLogger(QueryExecutioner.class);


    public static long executeUpdate(Connection conn, String query, Object... args) throws MyDBException {
        try (PreparedStatement st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            long id = 0;
            if (args != null) setArgsForPreparedStatement(st, args);
            int i = st.executeUpdate();
            if (i != 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getLong("id");
                }
            }
            return id;
        } catch (SQLException e) {
            logger.debug(e);
            throw new MyDBException("Something wrong with DB ", e);
        }
    }

    public static long readNumber(Connection conn, String query, Object... args) throws MyDBException {
        try (PreparedStatement st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            if (args != null) setArgsForPreparedStatement(st, args);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getLong(Fields.RESULT);
            }
        } catch (SQLException e) {
            logger.debug(e);
            throw new MyDBException("Something wrong with DB ", e);
        }
        return 0;
    }

    private static void setArgsForPreparedStatement(PreparedStatement statement, Object... args)
            throws SQLException {
        for (int i = 0; i < args.length; i++) {
            statement.setObject(i + 1, args[i]);
        }
    }

    public static <E extends Entity, F extends abstractEntityFactory<E>>
    E readEntity(F factory, Connection conn, final String query, Object... args) throws MyDBException {
        try (PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            setArgsForPreparedStatement(statement, args);
            ResultSet ser = statement.executeQuery();
            E answer = factory.getResult(ser);
            ser.close();
            return answer;
        } catch (SQLException e) {
            logger.debug(e);
            throw new MyDBException("Something wrong with DB ", e);
        }
    }

    public static <E extends Entity, F extends abstractEntityFactory<E>>
    ArrayList<E> readList(F factory, Connection conn, final String query, Object... args) throws MyDBException {
        try (PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            setArgsForPreparedStatement(statement, args);
            ResultSet ser = statement.executeQuery();
            ArrayList<E> answer = factory.getListOfResult(ser);
            ser.close();
            return answer;
        } catch (SQLException e) {
            logger.debug(e);
            throw new MyDBException("Something wrong with DB ", e);
        }
    }

}