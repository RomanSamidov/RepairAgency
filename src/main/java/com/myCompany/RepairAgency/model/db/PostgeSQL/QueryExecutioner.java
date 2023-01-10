package com.myCompany.RepairAgency.model.db.PostgeSQL;

import com.myCompany.RepairAgency.model.Fields;
import com.myCompany.RepairAgency.model.db.PostgeSQL.factory.abstractEntityFactory;
import com.myCompany.RepairAgency.model.entity.Entity;

import java.sql.*;
import java.util.ArrayList;

public class QueryExecutioner {

    public static void executeUpdate(Connection conn, String query, Object... args) {
        try (PreparedStatement st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            if (args != null) setArgsForPreparedStatement(st, args);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static long readNumber(Connection conn, String query, Object... args) {
        try (PreparedStatement st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            if (args != null) setArgsForPreparedStatement(st, args);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getLong(Fields.RESULT);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
    E readEntity(F factory, Connection conn, final String query, Object... args) {
        E answer = null;
        try (PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            setArgsForPreparedStatement(statement, args);
            ResultSet ser = statement.executeQuery();
            answer = factory.getResult(ser);
            ser.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;
    }

    public static <E extends Entity, F extends abstractEntityFactory<E>>
    ArrayList<E> readList(F factory, Connection conn, final String query, Object... args) {
        ArrayList<E> answer = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            setArgsForPreparedStatement(statement, args);
            ResultSet ser = statement.executeQuery();
            answer = factory.getListOfResult(ser);
            ser.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;
    }

}