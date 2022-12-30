package com.myCompany.RepairAgency.model;


import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.abstractRepositoryFactory;
import com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.abstractRepositoryFactory.DAOType;
import com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.entity.iRepairOrderRepository;
import com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.entity.iUserRepository;

import java.util.ResourceBundle;


public class ModelManager {

    public static final ModelManager ins = new ModelManager();

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

    private static DAOType initializeDAOType() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(Constants.DB_SETTINGS_BUNDLE);
        return DAOType.valueOf(resourceBundle.getString(Constants.DAOType));
    }

    public iRepairOrderRepository getRepairOrderRepository() {
        return DAO_FACTORY.getRepairOrderRepository();
    }

    public iUserRepository getUserRepository() {
        return DAO_FACTORY.getUserRepository();
    }

}
