package com.myCompany.RepairAgency.model.db.PostgeSQL;



import com.myCompany.RepairAgency.model.db.PostgeSQL.repository.OrderStatusRepository;
import com.myCompany.RepairAgency.model.db.PostgeSQL.repository.RepairOrderRepository;
import com.myCompany.RepairAgency.model.db.PostgeSQL.repository.RoleRepository;
import com.myCompany.RepairAgency.model.db.PostgeSQL.repository.UserRepository;
import com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.abstractRepositoryFactory;
import com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.entity.iOrderStatusRepository;
import com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.entity.iRepairOrderRepository;
import com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.entity.iRoleRepository;
import com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.entity.iUserRepository;

public class PSQLRepositoryFactory extends abstractRepositoryFactory {
    @Override
    public iOrderStatusRepository getOrderStatusService() {
        return new OrderStatusRepository();
    }

    @Override
    public iRepairOrderRepository getRepairOrderService() {
        return new RepairOrderRepository();
    }

    @Override
    public iRoleRepository getRoleService() {
        return new RoleRepository();
    }

    @Override
    public iUserRepository getUserService() {
        return new UserRepository();
    }
}
