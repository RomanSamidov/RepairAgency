package com.myCompany.RepairAgency.model.db.PostgeSQL.factory;

import com.myCompany.RepairAgency.model.entity.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class for reading Entity or list of entities from {@link java.sql.ResultSet}
 * @param <T> Entity type
 */
public abstract class abstractEntityFactory<T extends Entity> {


    public ArrayList<T> getListOfResult(ResultSet rs) {
        ArrayList<T> entities = new ArrayList<>();
        T entity = getResult(rs);
        while (entity != null) {
            entities.add(entity);
            entity = getResult(rs);
        }
        return entities;
    }

    public T getResult(ResultSet rs) {
        try {
            if (rs.next()) {
                return readEntity(rs);
            } else return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method for reading one Entity from {@link java.sql.ResultSet}
     * @param rs From which read Entity
     * @return Entity
     */
    protected abstract T readEntity(ResultSet rs) throws SQLException;

}
