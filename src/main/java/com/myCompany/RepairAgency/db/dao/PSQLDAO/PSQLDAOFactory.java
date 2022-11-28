package com.myCompany.RepairAgency.db.dao.PSQLDAO;

import com.myCompany.RepairAgency.db.dao.abstractDAO.*;

public class PSQLDAOFactory extends abstractDAOFactory {
    @Override
    public abstractOrderStatusDAO getOrderStatusDAO() {
        return new PSQLOrderStatusDAO();
    }

    @Override
    public abstractRepairOrderDAO getRepairOrderDAO() {
        return new PSQLRepairOrderDAO();
    }

    @Override
    public abstractRoleDAO getRoleDAO() {
        return new PSQLRoleDAO();
    }

    @Override
    public abstractUserDAO getUserDAO() {
        return new PSQLUserDAO();
    }
}
