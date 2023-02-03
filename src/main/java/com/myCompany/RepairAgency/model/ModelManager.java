package com.myCompany.RepairAgency.model;


import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.abstractRepositoryFactory;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.DAOTypeEnum;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iRepairOrderRepository;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iUserRepository;
import com.myCompany.RepairAgency.model.db.abstractDB.service.iOrderUserService;

import java.util.ResourceBundle;


public class ModelManager {

    private static ModelManager ins;
    public final DAOTypeEnum DAO_TYPE;
    private final abstractRepositoryFactory DAO_FACTORY;

    private ModelManager() {
        DAO_TYPE = initializeDAOType();
        DAO_FACTORY = DAO_TYPE.getDAOFactory();
    }

    public ModelManager(DAOTypeEnum DAO_TYPE) {
        this.DAO_TYPE = DAO_TYPE;
        DAO_FACTORY = DAO_TYPE.getDAOFactory();
    }

    public static ModelManager getInstance() {
        if (ins == null) ins = new ModelManager();
        return ins;
    }

    private static DAOTypeEnum initializeDAOType() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(Constants.DB_SETTINGS_BUNDLE);
        return DAOTypeEnum.valueOf(resourceBundle.getString(Constants.DAOType));
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
