package com.myCompany.RepairAgency.model;

import com.myCompany.RepairAgency.model.db.PostgeSQL.PSQLRepositoryFactory;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.abstractRepositoryFactory;

/**
 *  Enum with a list of all implemented db realizations
 */
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
