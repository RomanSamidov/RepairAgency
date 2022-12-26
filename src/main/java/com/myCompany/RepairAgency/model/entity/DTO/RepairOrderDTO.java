package com.myCompany.RepairAgency.model.entity.DTO;

import com.myCompany.RepairAgency.model.entity.Entity;

public class RepairOrderDTO extends Entity {
    private long user_id;
    private long craftsman_id;
    private String text;
    private int price=-10;
    private String status;
    private String feedback_text;
    private int feedback_mark;

    private RepairOrderDTO() {
    }


    public long getUser_id() {
        return user_id;
    }

    public RepairOrderDTO setUser_id(long user_id) {
        this.user_id=user_id;
        return this;
    }

    public long getCraftsman_id() {
        return craftsman_id;
    }

    public RepairOrderDTO setCraftsman_id(long craftsman_id) {
        this.craftsman_id=craftsman_id;
        return this;
    }

    public String getText() {
        return text;
    }

    public RepairOrderDTO setText(String text) {
        this.text=text;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public RepairOrderDTO setPrice(int price) {
        this.price=price;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public RepairOrderDTO setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getFeedback_text() {
        return feedback_text;
    }

    public RepairOrderDTO setFeedback_text(String feedback_text) {
        this.feedback_text=feedback_text;
        return this;
    }

    public int getFeedback_mark() {
        return feedback_mark;
    }

    public RepairOrderDTO setFeedback_mark(int feedback_mark) {
        this.feedback_mark=feedback_mark;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RepairOrderDTO)) return false;
        return this.id == ((RepairOrderDTO) obj).id;
    }

    public static class RepairOrderBuilder {
        private RepairOrderDTO onConstructRepairOrderDTO;

        public RepairOrderBuilder() {
            this.onConstructRepairOrderDTO =new RepairOrderDTO();
        }

        public RepairOrderDTO build() {
            if (isReady()) {
                RepairOrderDTO ready= onConstructRepairOrderDTO;
                this.onConstructRepairOrderDTO =new RepairOrderDTO();
                return ready;
            } else {
                throw new IllegalArgumentException("Role not ready");
            }
        }

        public boolean isReady() {
            return onConstructRepairOrderDTO.user_id != 0;
        }

        public long getId() {
            return onConstructRepairOrderDTO.id;
        }


        public RepairOrderBuilder setId(long id) {
            this.onConstructRepairOrderDTO.id=id;
            return this;
        }

        public long getUser_id() {
            return onConstructRepairOrderDTO.user_id;
        }

        public RepairOrderBuilder setUser_id(long user_id) {
            this.onConstructRepairOrderDTO.user_id=user_id;
            return this;
        }

        public long getCraftsman_id() {
            return onConstructRepairOrderDTO.craftsman_id;
        }

        public RepairOrderBuilder setCraftsman_id(long craftsman_id) {
            this.onConstructRepairOrderDTO.craftsman_id=craftsman_id;
            return this;
        }

        public String getText() {
            return onConstructRepairOrderDTO.text;
        }

        public RepairOrderBuilder setText(String text) {
            this.onConstructRepairOrderDTO.text=text;
            return this;
        }

        public int getPrice() {
            return onConstructRepairOrderDTO.price;
        }

        public RepairOrderBuilder setPrice(int price) {
            this.onConstructRepairOrderDTO.price=price;
            return this;
        }

        public String getStatus() {
            return onConstructRepairOrderDTO.status;
        }

        public RepairOrderBuilder setStatus(String status_id) {
            this.onConstructRepairOrderDTO.status =status_id;
            return this;
        }

        public String getFeedback_text() {
            return onConstructRepairOrderDTO.feedback_text;
        }

        public RepairOrderBuilder setFeedback_text(String feedback_text) {
            this.onConstructRepairOrderDTO.feedback_text=feedback_text;
            return this;
        }

        public int getFeedback_mark() {
            return onConstructRepairOrderDTO.feedback_mark;
        }

        public RepairOrderBuilder setFeedback_mark(int feedback_mark) {
            this.onConstructRepairOrderDTO.feedback_mark=feedback_mark;
            return this;
        }

    }

}
