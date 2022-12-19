package com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.entity;

import com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.primitive.iRepositoryForChangeAble;
import com.myCompany.RepairAgency.model.entity.User;

public interface iUserRepository extends iRepositoryForChangeAble<User> {
    public User getByLogin(String login);
}
