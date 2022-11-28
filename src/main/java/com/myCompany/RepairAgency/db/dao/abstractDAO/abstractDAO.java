package com.myCompany.RepairAgency.db.dao.abstractDAO;

import java.util.ArrayList;

public abstract class abstractDAO<T> {
    public abstract void insert(T obj);
    public abstract T getById(int id);
    public abstract void update(T obj);
    public abstract void delete(T obj);
    public abstract ArrayList<T> getAll();
}