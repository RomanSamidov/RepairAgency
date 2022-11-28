package com.myCompany.RepairAgency.db.dao.abstractDAO;

import com.myCompany.RepairAgency.db.dao.PSQLDAO.PSQLDAOFactory;

public abstract class abstractDAOFactory {
    public static abstractDAOFactory getDAOFactory(DAOType type) {
        if (type == DAOType.Postgresql) {
            return new PSQLDAOFactory();
        }
        throw new RuntimeException("Wrong DAOType for DAOFactory");
    }

    public abstract abstractOrderStatusDAO getOrderStatusDAO();

    public abstract abstractRepairOrderDAO getRepairOrderDAO();

    public abstract abstractRoleDAO getRoleDAO();

    public abstract abstractUserDAO getUserDAO();

    public enum DAOType {
        Postgresql
    }

}
