package com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository;

import com.myCompany.RepairAgency.model.db.PostgeSQL.PSQLRepositoryFactory;
import com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.entity.iOrderStatusRepository;
import com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.entity.iRepairOrderRepository;
import com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.entity.iRoleRepository;
import com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.entity.iUserRepository;

public abstract class abstractRepositoryFactory {
    public static abstractRepositoryFactory getDAOFactory(DAOType type) {
        if (type == DAOType.Postgresql) {
            return new PSQLRepositoryFactory();
        }
        throw new RuntimeException("Wrong DAOType for PSQLRepositoryFactory");
    }

    public enum DAOType {
        Postgresql
    }

    public abstract iOrderStatusRepository getOrderStatusService();

    public abstract iRepairOrderRepository getRepairOrderService();

    public abstract iRoleRepository getRoleService();

    public abstract iUserRepository getUserService();



}
