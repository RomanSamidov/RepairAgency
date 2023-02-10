package com.myCompany.RepairAgency.servlet.service;

import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iRepairOrderRepository;
import com.myCompany.RepairAgency.model.db.abstractDB.service.iOrderUserService;
import com.myCompany.RepairAgency.model.entity.RepairOrder;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.util.SendEmail;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

class OrderUserServiceTest {

    @Test
    void cancelOrder1() {
        try (MockedStatic<RepairOrderService> ignored2 = Mockito.mockStatic(RepairOrderService.class);
             MockedStatic<UserService> ignored3 = Mockito.mockStatic(UserService.class);
             MockedStatic<SendEmail> ignored1 = Mockito.mockStatic(SendEmail.class);
             MockedStatic<ModelManager> ignored0 = Mockito.mockStatic(ModelManager.class)) {

            User user = Mockito.mock(User.class);
            RepairOrder order = Mockito.mock(RepairOrder.class);
            Mockito.when(RepairOrderService.get(Mockito.anyLong())).thenReturn(order);
            Mockito.when(UserService.get(Mockito.anyLong())).thenReturn(user);

            iOrderUserService orderUserService = Mockito.mock(iOrderUserService.class);
            ModelManager manager = Mockito.mock(ModelManager.class);
            Mockito.when(ModelManager.getInstance()).thenReturn(manager);
            Mockito.when(manager.getOrderUserService()).thenReturn(orderUserService);

            Mockito.when(order.getStatus_id()).thenReturn(5L);

            OrderUserService.cancelOrder(1L);
        }
    }

    @Test
    void cancelOrder2() {
        try (MockedStatic<RepairOrderService> ignored2 = Mockito.mockStatic(RepairOrderService.class);
             MockedStatic<UserService> ignored3 = Mockito.mockStatic(UserService.class);
             MockedStatic<SendEmail> ignored1 = Mockito.mockStatic(SendEmail.class);
             MockedStatic<ModelManager> ignored0 = Mockito.mockStatic(ModelManager.class)) {

            User user = Mockito.mock(User.class);
            RepairOrder order = Mockito.mock(RepairOrder.class);
            Mockito.when(RepairOrderService.get(Mockito.anyLong())).thenReturn(order);
            Mockito.when(UserService.get(Mockito.anyLong())).thenReturn(user);

            Mockito.when(order.getStatus_id()).thenReturn(6L);

            OrderUserService.cancelOrder(1L);
        }
    }

    @Test
    void createOrder1() {
        RepairOrder.RepairOrderBuilder builder = Mockito.mock(RepairOrder.RepairOrderBuilder.class);


        try (MockedStatic<UserService> ignored3 = Mockito.mockStatic(UserService.class);
             MockedStatic<SendEmail> ignored1 = Mockito.mockStatic(SendEmail.class);
             MockedStatic<ModelManager> ignored0 = Mockito.mockStatic(ModelManager.class);
             MockedConstruction<RepairOrder.RepairOrderBuilder> ignored4 =
                     Mockito.mockConstructionWithAnswer(RepairOrder.RepairOrderBuilder.class,
                             invocation -> builder)) {

            User user = Mockito.mock(User.class);
            Mockito.when(user.getRole_id()).thenReturn(4);
            RepairOrder order = Mockito.mock(RepairOrder.class);
            Mockito.when(UserService.get(Mockito.anyLong())).thenReturn(user);

            Mockito.when(builder.build()).thenReturn(order);
            Mockito.when(builder.setText(Mockito.anyString())).thenReturn(builder);
            Mockito.when(builder.setCreation_date(Mockito.any())).thenReturn(builder);
            Mockito.when(builder.setUser_id(Mockito.anyLong())).thenReturn(builder);

            iRepairOrderRepository orderRepository = Mockito.mock(iRepairOrderRepository.class);
            ModelManager manager = Mockito.mock(ModelManager.class);
            Mockito.when(ModelManager.getInstance()).thenReturn(manager);
            Mockito.when(manager.getRepairOrderRepository()).thenReturn(orderRepository);

            Mockito.doNothing().when(orderRepository).insert(Mockito.any());

            OrderUserService.createOrder(1L, "ds");
        }
    }

    @Test
    void createOrder2() {
        RepairOrder.RepairOrderBuilder builder = Mockito.mock(RepairOrder.RepairOrderBuilder.class);


        try (MockedStatic<UserService> ignored3 = Mockito.mockStatic(UserService.class);
             MockedStatic<SendEmail> ignored1 = Mockito.mockStatic(SendEmail.class);
             MockedStatic<ModelManager> ignored0 = Mockito.mockStatic(ModelManager.class);
             MockedConstruction<RepairOrder.RepairOrderBuilder> ignored4 =
                     Mockito.mockConstructionWithAnswer(RepairOrder.RepairOrderBuilder.class,
                             invocation -> builder)) {

            User user = Mockito.mock(User.class);
            Mockito.when(user.getRole_id()).thenReturn(5);
            RepairOrder order = Mockito.mock(RepairOrder.class);
            Mockito.when(UserService.get(Mockito.anyLong())).thenReturn(user);

            Mockito.when(builder.build()).thenReturn(order);

            iRepairOrderRepository orderRepository = Mockito.mock(iRepairOrderRepository.class);
            ModelManager manager = Mockito.mock(ModelManager.class);
            Mockito.when(ModelManager.getInstance()).thenReturn(manager);
            Mockito.when(manager.getRepairOrderRepository()).thenReturn(orderRepository);

            Mockito.doNothing().when(orderRepository).insert(Mockito.any());

            OrderUserService.createOrder(1L, "ds");
        }
    }

    @Test
    void payOrder1() {
        try (MockedStatic<RepairOrderService> ignored2 = Mockito.mockStatic(RepairOrderService.class);
             MockedStatic<UserService> ignored3 = Mockito.mockStatic(UserService.class);
             MockedStatic<SendEmail> ignored1 = Mockito.mockStatic(SendEmail.class);
             MockedStatic<ModelManager> ignored0 = Mockito.mockStatic(ModelManager.class)) {

            User user = Mockito.mock(User.class);
            RepairOrder order = Mockito.mock(RepairOrder.class);
            Mockito.when(RepairOrderService.get(Mockito.anyLong())).thenReturn(order);
            Mockito.when(UserService.get(Mockito.anyLong())).thenReturn(user);

            Mockito.when(user.getAccount()).thenReturn(100);
            Mockito.when(order.getPrice()).thenReturn(10);

            iOrderUserService orderUserService = Mockito.mock(iOrderUserService.class);
            ModelManager manager = Mockito.mock(ModelManager.class);
            Mockito.when(ModelManager.getInstance()).thenReturn(manager);
            Mockito.when(manager.getOrderUserService()).thenReturn(orderUserService);

            Mockito.when(orderUserService.payOrder(Mockito.any(), Mockito.any())).thenReturn(true);

            OrderUserService.payOrder(1L);
        }
    }

    @Test
    void payOrder2() {
        try (MockedStatic<RepairOrderService> ignored2 = Mockito.mockStatic(RepairOrderService.class);
             MockedStatic<UserService> ignored3 = Mockito.mockStatic(UserService.class);
             MockedStatic<SendEmail> ignored1 = Mockito.mockStatic(SendEmail.class);
             MockedStatic<ModelManager> ignored0 = Mockito.mockStatic(ModelManager.class)) {

            User user = Mockito.mock(User.class);
            RepairOrder order = Mockito.mock(RepairOrder.class);
            Mockito.when(RepairOrderService.get(Mockito.anyLong())).thenReturn(order);
            Mockito.when(UserService.get(Mockito.anyLong())).thenReturn(user);

            Mockito.when(user.getAccount()).thenReturn(10);
            Mockito.when(order.getPrice()).thenReturn(100);

            iOrderUserService orderUserService = Mockito.mock(iOrderUserService.class);
            ModelManager manager = Mockito.mock(ModelManager.class);
            Mockito.when(ModelManager.getInstance()).thenReturn(manager);
            Mockito.when(manager.getOrderUserService()).thenReturn(orderUserService);

            Mockito.when(orderUserService.payOrder(Mockito.any(), Mockito.any())).thenReturn(true);

            OrderUserService.payOrder(1L);
        }
    }
}