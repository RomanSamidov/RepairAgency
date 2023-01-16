package com.myCompany.RepairAgency.model.db.PostgeSQL;


import com.myCompany.RepairAgency.model.db.PostgeSQL.repository.RepairOrderRepository;
import com.myCompany.RepairAgency.model.db.PostgeSQL.repository.UserRepository;
import com.myCompany.RepairAgency.model.db.PostgeSQL.service.OrderUserService;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.abstractRepositoryFactory;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iRepairOrderRepository;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iUserRepository;
import com.myCompany.RepairAgency.model.db.abstractDB.service.iOrderUserService;

public class PSQLRepositoryFactory extends abstractRepositoryFactory {

    @Override
    public iRepairOrderRepository getRepairOrderRepository() {
        return new RepairOrderRepository();
    }

    @Override
    public iUserRepository getUserRepository() {
        return new UserRepository();
    }

    @Override
    public iOrderUserService getOrderUserService() {
        return new OrderUserService();
    }
}
