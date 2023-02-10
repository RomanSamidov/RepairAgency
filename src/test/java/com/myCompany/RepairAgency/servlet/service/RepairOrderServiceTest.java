package com.myCompany.RepairAgency.servlet.service;

import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iRepairOrderRepository;
import com.myCompany.RepairAgency.model.entity.RepairOrder;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;

class RepairOrderServiceTest {

    @Test
    void get() {
        try (MockedStatic<ModelManager> ignored0 = Mockito.mockStatic(ModelManager.class)) {

            RepairOrder order = Mockito.mock(RepairOrder.class);

            iRepairOrderRepository orderRepository = Mockito.mock(iRepairOrderRepository.class);
            ModelManager manager = Mockito.mock(ModelManager.class);
            Mockito.when(ModelManager.getInstance()).thenReturn(manager);
            Mockito.when(manager.getRepairOrderRepository()).thenReturn(orderRepository);

            Mockito.when(orderRepository.getById(Mockito.anyLong())).thenReturn(order);

            RepairOrderService.get(1L);
        }
    }

    @Test
    void countByCraftUserStatus() {
        try (MockedStatic<ModelManager> ignored0 = Mockito.mockStatic(ModelManager.class)) {

            iRepairOrderRepository orderRepository = Mockito.mock(iRepairOrderRepository.class);
            ModelManager manager = Mockito.mock(ModelManager.class);
            Mockito.when(ModelManager.getInstance()).thenReturn(manager);
            Mockito.when(manager.getRepairOrderRepository()).thenReturn(orderRepository);

            Mockito.when(orderRepository.countByCraftUserStatus(
                    Mockito.any(), Mockito.anyLong(), Mockito.any()
            )).thenReturn(10L);

            RepairOrderService.countByCraftUserStatus(new long[]{0L}, 0L, new long[]{0L});
        }
    }

    @Test
    void getByCraftUserStatus() {
        try (MockedStatic<ModelManager> ignored0 = Mockito.mockStatic(ModelManager.class)) {

            iRepairOrderRepository orderRepository = Mockito.mock(iRepairOrderRepository.class);
            ModelManager manager = Mockito.mock(ModelManager.class);
            Mockito.when(ModelManager.getInstance()).thenReturn(manager);
            Mockito.when(manager.getRepairOrderRepository()).thenReturn(orderRepository);

            Mockito.when(orderRepository.getByCraftUserStatus(
                    Mockito.any(), Mockito.anyLong(), Mockito.any(), Mockito.any(), Mockito.anyLong(), Mockito.anyLong()
            )).thenReturn(new ArrayList<>());

            RepairOrderService.getByCraftUserStatus(new long[]{0L}, 0L, new long[]{0L},
                    iRepairOrderRepository.SORT_TYPE.ORDER_BY_ID_ASC, 10L, 10L);
        }
    }

    @Test
    void update() {
        try (MockedStatic<ModelManager> ignored0 = Mockito.mockStatic(ModelManager.class)) {

            RepairOrder order = Mockito.mock(RepairOrder.class);

            iRepairOrderRepository orderRepository = Mockito.mock(iRepairOrderRepository.class);
            ModelManager manager = Mockito.mock(ModelManager.class);
            Mockito.when(ModelManager.getInstance()).thenReturn(manager);
            Mockito.when(manager.getRepairOrderRepository()).thenReturn(orderRepository);

            Mockito.doNothing().when(orderRepository).update(Mockito.any());

            RepairOrderService.update(order);
        }
    }

    @Test
    void delete() {
        try (MockedStatic<ModelManager> ignored0 = Mockito.mockStatic(ModelManager.class)) {

            iRepairOrderRepository orderRepository = Mockito.mock(iRepairOrderRepository.class);
            ModelManager manager = Mockito.mock(ModelManager.class);
            Mockito.when(ModelManager.getInstance()).thenReturn(manager);
            Mockito.when(manager.getRepairOrderRepository()).thenReturn(orderRepository);

            Mockito.doNothing().when(orderRepository).delete(Mockito.anyLong());

            RepairOrderService.delete(0L);
        }
    }
}