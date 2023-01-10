package com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.entity;

import com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.primitive.iRepositoryForChangeAble;
import com.myCompany.RepairAgency.model.entity.User;

import java.util.ArrayList;

public interface iUserRepository extends iRepositoryForChangeAble<User> {
    User getByLogin(String login);

    ArrayList<User> getByRole(long roleId, int skip, int quantity);
    long countWhereRoleIs(long id);

}
