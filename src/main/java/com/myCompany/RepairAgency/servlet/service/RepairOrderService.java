package com.myCompany.RepairAgency.servlet.service;


import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.db.abstractDB.exception.MyDBException;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iRepairOrderRepository;
import com.myCompany.RepairAgency.model.entity.RepairOrder;

import java.util.ArrayList;

public class RepairOrderService {
    public static RepairOrder get(long id) throws MyDBException {
        iRepairOrderRepository orderRepository = ModelManager.getInstance().getRepairOrderRepository();
        return orderRepository.getById(id);
    }

    public static long countByCraftUserStatus(long[] craftIds, long userId, long[] statusIds) throws MyDBException {
        iRepairOrderRepository orderRepository = ModelManager.getInstance().getRepairOrderRepository();
        return orderRepository.countByCraftUserStatus(craftIds, userId, statusIds);
    }

    public static ArrayList<RepairOrder> getByCraftUserStatus(long[] craftIds, long userId, long[] statusIds, iRepairOrderRepository.SORT_TYPE sortType, long skip, long quantity) throws MyDBException {
        iRepairOrderRepository orderRepository = ModelManager.getInstance().getRepairOrderRepository();
        return orderRepository.getByCraftUserStatus(craftIds, userId, statusIds, sortType, skip, quantity);
    }

    public static void update(RepairOrder order) throws MyDBException {
        ModelManager.getInstance().getRepairOrderRepository().update(order);
    }

    public static void delete(long orderId) throws MyDBException {
        ModelManager.getInstance().getRepairOrderRepository().delete(orderId);
    }
}
