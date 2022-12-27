package com.myCompany.RepairAgency.model.db.PostgeSQL;



import com.myCompany.RepairAgency.model.db.PostgeSQL.repository.RepairOrderRepository;
import com.myCompany.RepairAgency.model.db.PostgeSQL.repository.UserRepository;
import com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.abstractRepositoryFactory;
import com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.entity.iRepairOrderRepository;
import com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.entity.iUserRepository;

public class PSQLRepositoryFactory extends abstractRepositoryFactory {

    @Override
    public iRepairOrderRepository getRepairOrderService() {
        return new RepairOrderRepository();
    }

    @Override
    public iUserRepository getUserService() {
        return new UserRepository();
    }
}
