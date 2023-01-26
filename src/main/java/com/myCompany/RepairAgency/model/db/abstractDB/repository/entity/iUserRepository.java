package com.myCompany.RepairAgency.model.db.abstractDB.repository.entity;

import com.myCompany.RepairAgency.model.db.abstractDB.exception.MyDBException;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.primitive.iRepositoryForChangeAble;
import com.myCompany.RepairAgency.model.entity.User;

import java.util.ArrayList;

public interface iUserRepository extends iRepositoryForChangeAble<User> {
    User getByLogin(String login) throws MyDBException;

    ArrayList<User> getByRole(long roleId, long skip, long quantity) throws MyDBException;

    long countWhereRoleIs(long id) throws MyDBException;

}
