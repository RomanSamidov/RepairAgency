package com.myCompany.RepairAgency.model.db.abstractDB.repository;

import com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iRepairOrderRepository;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iUserRepository;
import com.myCompany.RepairAgency.model.db.abstractDB.service.iOrderUserService;

public abstract class abstractRepositoryFactory {
    public abstract iRepairOrderRepository getRepairOrderRepository();

    public abstract iUserRepository getUserRepository();

    public abstract iOrderUserService getOrderUserService();
}
