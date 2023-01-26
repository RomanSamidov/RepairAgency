package com.myCompany.RepairAgency.model.db.abstractDB.repository.primitive;


import com.myCompany.RepairAgency.model.db.abstractDB.exception.MyDBException;

public interface iRepositoryForGetAble<T> {
    T getById(long id) throws MyDBException;
}
