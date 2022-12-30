package com.myCompany.RepairAgency.model.entity.DTO;

import com.myCompany.RepairAgency.model.entity.Entity;

public class UserDTO extends Entity {

    private String login;
    private String email;
    private String role;
    private int account;

    private UserDTO() {
    }

    public String getLogin() {
        return login;
    }

    public UserDTO setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getRole() {
        return role;
    }

    public UserDTO setRole(String role) {
        this.role = role;
        return this;
    }

    public int getAccount() {
        return account;
    }

    public UserDTO setAccount(int account) {
        this.account = account;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UserDTO)) return false;
        return this.login.equals(((UserDTO) obj).login);
    }

    public static class UserBuilder {
        private UserDTO onConstructUserDTO;

        public UserBuilder() {
            this.onConstructUserDTO = new UserDTO();
        }

        public UserDTO build() {
            if (isReady()) {
                UserDTO ready = onConstructUserDTO;
                this.onConstructUserDTO = new UserDTO();
                return ready;
            } else {
                throw new IllegalArgumentException("UserDTO not ready");
            }

        }

        public boolean isReady() {
            return onConstructUserDTO.login != null && !onConstructUserDTO.login.isBlank()
                    && onConstructUserDTO.role != null && !onConstructUserDTO.role.isBlank();
        }

        public long getId() {
            return onConstructUserDTO.id;
        }

        public UserBuilder setId(long id) {
            this.onConstructUserDTO.id = id;
            return this;
        }

        public String getLogin() {
            return onConstructUserDTO.login;
        }

        public UserBuilder setLogin(String login) {
            this.onConstructUserDTO.login = login;
            return this;
        }

        public String getEmail() {
            return onConstructUserDTO.email;
        }

        public UserBuilder setEmail(String email) {
            this.onConstructUserDTO.email = email;
            return this;
        }

        public String getRole() {
            return onConstructUserDTO.role;
        }

        public UserBuilder setRole(String role_id) {
            this.onConstructUserDTO.role = role_id;
            return this;
        }

        public int getAccount() {
            return onConstructUserDTO.account;
        }

        public UserBuilder setAccount(int account) {
            this.onConstructUserDTO.account = account;
            return this;
        }


    }
}