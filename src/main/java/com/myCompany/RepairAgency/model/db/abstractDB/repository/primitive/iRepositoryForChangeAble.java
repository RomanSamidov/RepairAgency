package com.myCompany.RepairAgency.model.db.abstractDB.repository.primitive;


public interface iRepositoryForChangeAble<T> extends iRepositoryForGetAble<T> {
    void update(T obj);

    void delete(T obj);

    void insert(T obj);
}