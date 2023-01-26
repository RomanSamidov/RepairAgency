package com.myCompany.RepairAgency.model.db.abstractDB.repository;

import com.myCompany.RepairAgency.model.db.PostgeSQL.PSQLRepositoryFactory;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iRepairOrderRepository;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iUserRepository;
import com.myCompany.RepairAgency.model.db.abstractDB.service.iOrderUserService;

public abstract class abstractRepositoryFactory {
    public static abstractRepositoryFactory getDAOFactory(DAOType type) {
        if (type == DAOType.Postgresql) {
            return new PSQLRepositoryFactory();
        }
        throw new RuntimeException("Wrong DAOType for PSQLRepositoryFactory");
    }

    public abstract iRepairOrderRepository getRepairOrderRepository();

    public abstract iUserRepository getUserRepository();

    public abstract iOrderUserService getOrderUserService();

    public enum DAOType {
        Postgresql
    }


}
