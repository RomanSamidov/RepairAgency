package com.myCompany.RepairAgency.model.entity;

public class RepairOrder  extends Entity {
    private int user_id;
    private int craftsman_id;
    private String text;
    private int price;
    private int status_id=1;
    private String feedback_text;
    private int feedback_mark;

    private RepairOrder() {
    }


    public int getUser_id() {
        return user_id;
    }

    public RepairOrder setUser_id(int user_id) {
        this.user_id=user_id;
        return this;
    }

    public int getCraftsman_id() {
        return craftsman_id;
    }

    public RepairOrder setCraftsman_id(int craftsman_id) {
        this.craftsman_id=craftsman_id;
        return this;
    }

    public String getText() {
        return text;
    }

    public RepairOrder setText(String text) {
        this.text=text;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public RepairOrder setPrice(int price) {
        this.price=price;
        return this;
    }

    public int getStatus_id() {
        return status_id;
    }

    public RepairOrder setStatus_id(int status_id) {
        this.status_id=status_id;
        return this;
    }

    public String getFeedback_text() {
        return feedback_text;
    }

    public RepairOrder setFeedback_text(String feedback_text) {
        this.feedback_text=feedback_text;
        return this;
    }

    public int getFeedback_mark() {
        return feedback_mark;
    }

    public RepairOrder setFeedback_mark(int feedback_mark) {
        this.feedback_mark=feedback_mark;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RepairOrder)) return false;
        return this.id == ((RepairOrder) obj).id;
    }

    public static class RepairOrderBuilder {
        private RepairOrder onConstructRepairOrder;

        public RepairOrderBuilder() {
            this.onConstructRepairOrder=new RepairOrder();
        }

        public RepairOrder build() {
            if (isReady()) {
                RepairOrder ready=onConstructRepairOrder;
                this.onConstructRepairOrder=new RepairOrder();
                return ready;
            } else {
                throw new IllegalArgumentException("Role not ready");
            }
        }

        public boolean isReady() {
            return onConstructRepairOrder.user_id != 0;
        }

        public long getId() {
            return onConstructRepairOrder.id;
        }


        public RepairOrderBuilder setId(int id) {
            this.onConstructRepairOrder.id=id;
            return this;
        }

        public int getUser_id() {
            return onConstructRepairOrder.user_id;
        }

        public RepairOrderBuilder setUser_id(int user_id) {
            this.onConstructRepairOrder.user_id=user_id;
            return this;
        }

        public int getCraftsman_id() {
            return onConstructRepairOrder.craftsman_id;
        }

        public RepairOrderBuilder setCraftsman_id(int craftsman_id) {
            this.onConstructRepairOrder.craftsman_id=craftsman_id;
            return this;
        }

        public String getText() {
            return onConstructRepairOrder.text;
        }

        public RepairOrderBuilder setText(String text) {
            this.onConstructRepairOrder.text=text;
            return this;
        }

        public int getPrice() {
            return onConstructRepairOrder.price;
        }

        public RepairOrderBuilder setPrice(int price) {
            this.onConstructRepairOrder.price=price;
            return this;
        }

        public int getStatus_id() {
            return onConstructRepairOrder.status_id;
        }

        public RepairOrderBuilder setStatus_id(int status_id) {
            this.onConstructRepairOrder.status_id=status_id;
            return this;
        }

        public String getFeedback_text() {
            return onConstructRepairOrder.feedback_text;
        }

        public RepairOrderBuilder setFeedback_text(String feedback_text) {
            this.onConstructRepairOrder.feedback_text=feedback_text;
            return this;
        }

        public int getFeedback_mark() {
            return onConstructRepairOrder.feedback_mark;
        }

        public RepairOrderBuilder setFeedback_mark(int feedback_mark) {
            this.onConstructRepairOrder.feedback_mark=feedback_mark;
            return this;
        }

    }

}
