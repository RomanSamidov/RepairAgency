package com.myCompany.RepairAgency.db.entity;

public class Role {
    private int id;
    private String name;

    private Role() {
    }

    public int getId() {
        return id;
    }

    public Role setId(int id) {
        this.id=id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Role setName(String name) {
        this.name=name;
        return this;
    }


    public static class RoleBuilder {
        private Role onConstructRole;

        public RoleBuilder() {
            this.onConstructRole=new Role();
        }

        public Role build() {
            if (isReady()) {
                Role ready=onConstructRole;
                this.onConstructRole=new Role();
                return ready;
            } else {
                throw new IllegalArgumentException("Role not ready");
            }
        }

        public boolean isReady() {
            return onConstructRole.name != null && !onConstructRole.name.isBlank();
        }

        public int getId() {
            return onConstructRole.id;
        }

        public RoleBuilder setId(int id) {
            this.onConstructRole.id=id;
            return this;
        }

        public String getName() {
            return onConstructRole.name;
        }

        public RoleBuilder setName(String name) {
            this.onConstructRole.name=name;
            return this;
        }

    }
}
