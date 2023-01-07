package com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.primitive;


public interface iRepositoryForGetAble<T> {
    T getById(long id);
}
