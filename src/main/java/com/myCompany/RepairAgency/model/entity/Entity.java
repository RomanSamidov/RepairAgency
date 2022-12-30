package com.myCompany.RepairAgency.model.entity;

import java.io.Serializable;


public abstract class Entity implements Serializable {
    protected long id;

    public long getId() {
        return id;
    }

    public Entity setId(long id) {
        this.id = id;
        return this;
    }
}
