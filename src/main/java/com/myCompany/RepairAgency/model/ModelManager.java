package com.myCompany.RepairAgency.model;


import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.abstractRepositoryFactory;
import com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.abstractRepositoryFactory.DAOType;
import com.myCompany.RepairAgency.model.entity.OrderStatus;
import com.myCompany.RepairAgency.model.entity.RepairOrder;
import com.myCompany.RepairAgency.model.entity.Role;
import com.myCompany.RepairAgency.model.entity.User;

import java.util.ArrayList;
import java.util.ResourceBundle;


public class ModelManager {

    public static final ModelManager ins = new ModelManager();

    public final DAOType DAO_TYPE;
    private final abstractRepositoryFactory DAO_FACTORY;

    public ModelManager() {
        DAO_TYPE=initializeDAOType();
        DAO_FACTORY= abstractRepositoryFactory.getDAOFactory(DAO_TYPE);
    }

    public ModelManager(DAOType DAO_TYPE) {
        this.DAO_TYPE=DAO_TYPE;
        DAO_FACTORY= abstractRepositoryFactory.getDAOFactory(DAO_TYPE);
    }

    private static DAOType initializeDAOType() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(Constants.DB_SETTINGS_BUNDLE);
        return DAOType.valueOf(resourceBundle.getString(Constants.DAOType));
    }


    public ArrayList<OrderStatus> getAllOrderStatuses() {
        return DAO_FACTORY.getOrderStatusService().getAll();
    }

    public OrderStatus getOrderStatus(int id) {
        return DAO_FACTORY.getOrderStatusService().getById(id);
    }

    public User getUser(long id) {
        return DAO_FACTORY.getUserService().getById(id);
    }
    public User getUser(String login) {
        return DAO_FACTORY.getUserService().getByLogin(login);
    }

    public void insertUser(User user) {
        DAO_FACTORY.getUserService().insert(user);
    }

    public void updateUser(User user) {
        DAO_FACTORY.getUserService().update(user);
    }

    public void deleteUser(User user) {
        DAO_FACTORY.getUserService().delete(user);
    }

    public ArrayList<User> getAllUsers() {
        return DAO_FACTORY.getUserService().getAll();
    }
    public ArrayList<User> getAllUsersByRole(long roleId, int skip, int quantity) { return DAO_FACTORY.getUserService().getByRole(roleId, skip, quantity);}

    public void incrementUserAccount(long userId, int increment){
        DAO_FACTORY.getUserService().incrementUserAccount(userId, increment);
    }

    public long getCountUsersWhereRoleIs(long roleId) {
        return DAO_FACTORY.getUserService().getCountWhereRoleIs(roleId);
    }

    public ArrayList<User> getUsersWithPagination(int skip, int quantity) {
        return DAO_FACTORY.getUserService().getWithPagination(skip, quantity);
    }

    public ArrayList<Role> getAllRoles() {
        return DAO_FACTORY.getRoleService().getAll();
    }

    public Role getRole(int id) {
        return DAO_FACTORY.getRoleService().getById(id);
    }


    public RepairOrder getRepairOrder(long id) {
        return DAO_FACTORY.getRepairOrderService().getById(id);
    }

    public void insertRepairOrder(RepairOrder repairOrder) {
        DAO_FACTORY.getRepairOrderService().insert(repairOrder);
    }

    public void updateRepairOrder(RepairOrder repairOrder) {
        DAO_FACTORY.getRepairOrderService().update(repairOrder);
    }

    public void deleteRepairOrder(RepairOrder repairOrder) {
        DAO_FACTORY.getRepairOrderService().delete(repairOrder);
    }

    public ArrayList<RepairOrder> getAllRepairOrders() {
        return DAO_FACTORY.getRepairOrderService().getAll();
    }

    public ArrayList<RepairOrder> getRepairOrdersWithPagination(int skip, int quantity) {
        return DAO_FACTORY.getRepairOrderService().getWithPagination(skip, quantity);
    }

    public ArrayList<RepairOrder> getAllRepairOrdersWhereUserIdIs(long id, int skip, int quantity) {
        return DAO_FACTORY.getRepairOrderService().getAllWhereUserIdIs(id, skip, quantity);
    }

    public long getCountRepairOrdersWhereUserIdIs(long id) {
        return DAO_FACTORY.getRepairOrderService().getCountWhereUserIdIs(id);
    }

    public long getCountRepairOrders() {
        return DAO_FACTORY.getRepairOrderService().getCount();
    }


}
