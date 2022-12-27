package com.myCompany.RepairAgency.model.entity;

import java.time.LocalDateTime;

public class RepairOrder  extends Entity {
    private long user_id;
    private long craftsman_id;
    private LocalDateTime creation_date;
    private String text;
    private int price=-10;
    private LocalDateTime finish_date;
    private long status_id=1;
    private LocalDateTime feedback_date;

    private String feedback_text;
    private int feedback_mark;

    private RepairOrder() {
    }

    public LocalDateTime getCreation_date() {
        return creation_date;
    }

    public RepairOrder setCreation_date(LocalDateTime creation_date) {
        this.creation_date = creation_date;
        return this;
    }

    public LocalDateTime getFinish_date() {
        return finish_date;
    }

    public RepairOrder setFinish_date(LocalDateTime finish_date) {
        this.finish_date = finish_date;
        return this;
    }

    public LocalDateTime getFeedback_date() {
        return feedback_date;
    }

    public RepairOrder setFeedback_date(LocalDateTime feedback_date) {
        this.feedback_date = feedback_date;
        return this;
    }

    public long getUser_id() {
        return user_id;
    }

    public RepairOrder setUser_id(long user_id) {
        this.user_id=user_id;
        return this;
    }

    public long getCraftsman_id() {
        return craftsman_id;
    }

    public RepairOrder setCraftsman_id(long craftsman_id) {
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

    public long getStatus_id() {
        return status_id;
    }

    public RepairOrder setStatus_id(long status_id) {
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
            return onConstructRepairOrder.user_id != 0 && onConstructRepairOrder.creation_date != null;
        }

        public long getId() {
            return onConstructRepairOrder.id;
        }


        public RepairOrderBuilder setId(long id) {
            this.onConstructRepairOrder.id=id;
            return this;
        }

        public long getUser_id() {
            return onConstructRepairOrder.user_id;
        }

        public RepairOrderBuilder setUser_id(long user_id) {
            this.onConstructRepairOrder.user_id=user_id;
            return this;
        }

        public long getCraftsman_id() {
            return onConstructRepairOrder.craftsman_id;
        }

        public RepairOrderBuilder setCraftsman_id(long craftsman_id) {
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

        public long getStatus_id() {
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
        public LocalDateTime getCreation_date() {
            return onConstructRepairOrder.creation_date;
        }

        public RepairOrderBuilder setCreation_date(LocalDateTime creation_date) {
            this.onConstructRepairOrder.creation_date = creation_date;
            return this;
        }

        public LocalDateTime getFinish_date() {
            return onConstructRepairOrder.finish_date;
        }

        public RepairOrderBuilder setFinish_date(LocalDateTime finish_date) {
            this.onConstructRepairOrder.finish_date = finish_date;
            return this;
        }

        public LocalDateTime getFeedback_date() {
            return onConstructRepairOrder.feedback_date;
        }

        public RepairOrderBuilder setFeedback_date(LocalDateTime feedback_date) {
            this.onConstructRepairOrder.feedback_date = feedback_date;
            return this;
        }

    }

}
