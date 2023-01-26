package com.myCompany.RepairAgency.model.db.abstractDB.repository.primitive;


import com.myCompany.RepairAgency.model.db.abstractDB.exception.MyDBException;

public interface iRepositoryForChangeAble<T> extends iRepositoryForGetAble<T> {
    void update(T obj) throws MyDBException;

    void delete(T obj) throws MyDBException;

    void delete(long id) throws MyDBException;

    void insert(T obj) throws MyDBException;
}