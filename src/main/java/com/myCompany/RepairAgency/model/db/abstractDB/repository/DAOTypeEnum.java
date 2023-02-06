package com.myCompany.RepairAgency.model.db.abstractDB.repository;

import com.myCompany.RepairAgency.model.db.PostgeSQL.PSQLRepositoryFactory;

public enum DAOTypeEnum {
    Postgresql(new PSQLRepositoryFactory());

    private final abstractRepositoryFactory repositoryFactory;

    DAOTypeEnum(PSQLRepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
    }

    public abstractRepositoryFactory getDAOFactory() {
        return repositoryFactory;
    }
}
