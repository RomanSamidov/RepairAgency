package com.myCompany.RepairAgency.model.db.abstractDB.repository.primitive;


public interface iRepositoryForGetAble<T> {
    T getById(long id);
}
