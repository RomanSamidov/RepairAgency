package com.myCompany.RepairAgency.model.db.abstractDB.repository;

import com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iRepairOrderRepository;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iUserRepository;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.service.iOrderUserService;
/**
 * DB implementation should implement this class and
 * {@link com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iUserRepository},
 * {@link com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iRepairOrderRepository},
 * {@link com.myCompany.RepairAgency.model.db.abstractDB.repository.service.iOrderUserService}
 */
public abstract class abstractRepositoryFactory {
    public abstract iRepairOrderRepository getRepairOrderRepository();

    public abstract iUserRepository getUserRepository();

    public abstract iOrderUserService getOrderUserService();
}
