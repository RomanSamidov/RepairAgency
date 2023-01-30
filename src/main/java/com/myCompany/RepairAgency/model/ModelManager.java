package com.myCompany.RepairAgency.model;


import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.abstractRepositoryFactory;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.abstractRepositoryFactory.DAOType;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iRepairOrderRepository;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iUserRepository;
import com.myCompany.RepairAgency.model.db.abstractDB.service.iOrderUserService;

import java.util.ResourceBundle;


public class ModelManager {

    private static ModelManager ins;
    public final DAOType DAO_TYPE;
    private final abstractRepositoryFactory DAO_FACTORY;

    private ModelManager() {
        DAO_TYPE = initializeDAOType();
        DAO_FACTORY = abstractRepositoryFactory.getDAOFactory(DAO_TYPE);
    }

    public ModelManager(DAOType DAO_TYPE) {
        this.DAO_TYPE = DAO_TYPE;
        DAO_FACTORY = abstractRepositoryFactory.getDAOFactory(DAO_TYPE);
    }

    public static ModelManager getInstance() {
        if (ins == null) ins = new ModelManager();
        return ins;
    }

    private static DAOType initializeDAOType() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(Constants.DB_SETTINGS_BUNDLE);
        return DAOType.valueOf(resourceBundle.getString(Constants.DAOType));
    }

    public iRepairOrderRepository getRepairOrderRepository() {
        return DAO_FACTORY.getRepairOrderRepository();
    }

    public iOrderUserService getOrderUserService() {
        return DAO_FACTORY.getOrderUserService();
    }

    public iUserRepository getUserRepository() {
        return DAO_FACTORY.getUserRepository();
    }

}
