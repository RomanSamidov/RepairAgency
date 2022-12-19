package com.myCompany.RepairAgency.model.entity;


public class OrderStatus extends Entity {
    private String name;

    private OrderStatus() {
    }

    public String getName() {
        return name;
    }

    public OrderStatus setName(String name) {
        this.name=name;
        return this;
    }

    public static class OrderStatusBuilder {
        private OrderStatus onConstructOrderStatus;

        public OrderStatusBuilder() {
            this.onConstructOrderStatus=new OrderStatus();
        }

        public OrderStatus build() {
            if (isReady()) {
                OrderStatus ready=onConstructOrderStatus;
                this.onConstructOrderStatus=new OrderStatus();
                return ready;
            } else {
                throw new IllegalArgumentException("OrderStatus not ready");
            }
        }

        public boolean isReady() {
            return onConstructOrderStatus.name != null && !onConstructOrderStatus.name.isBlank();
        }

        public OrderStatusBuilder setId(int id) {
            this.onConstructOrderStatus.id=id;
            return this;
        }

        public long getID() {
            return onConstructOrderStatus.id;
        }

        public String getName() {
            return onConstructOrderStatus.name;
        }

        public OrderStatusBuilder setName(String name) {
            this.onConstructOrderStatus.name=name;
            return this;
        }

    }

}
