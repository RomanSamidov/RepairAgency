package com.myCompany.RepairAgency.model.entity;

public class User extends Entity {

    private String login;
    private String password;
    private String email;
    private boolean allow_letters;
    private boolean confirmed;
    private int role_id;
    private int account;
    private int locale_id;

    protected User() {
        allow_letters = true;
        confirmed = false;
        role_id = 4;
        locale_id = 1;
    }

    public int getLocale_id() {
        return locale_id;
    }

    public User setLocale_id(int locale_id) {
        this.locale_id = locale_id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public User setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public int getRole_id() {
        return role_id;
    }

    public User setRole_id(int role_id) {
        this.role_id = role_id;
        return this;
    }

    public int getAccount() {
        return account;
    }

    public User setAccount(int account) {
        this.account = account;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) return false;
        return this.login.equals(((User) obj).login);
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public boolean isAllow_letters() {
        return allow_letters;
    }

    public User setAllow_letters(boolean allow_letters) {
        this.allow_letters = allow_letters;
        return this;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public User setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
        return this;
    }

    public static class UserBuilder {
        private User onConstructUser;

        public UserBuilder() {
            this.onConstructUser = new User();
        }

        public User build() {
            if (isReady()) {
                User ready = onConstructUser;
                this.onConstructUser = new User();
                return ready;
            } else {
                throw new IllegalArgumentException("User not ready");
            }

        }

        public boolean isReady() {
            return onConstructUser.login != null && !onConstructUser.login.isBlank() && onConstructUser.password != null
                    && !onConstructUser.password.isBlank() && onConstructUser.role_id != 0;
        }

        public long getId() {
            return onConstructUser.id;
        }

        public UserBuilder setId(int id) {
            this.onConstructUser.id = id;
            return this;
        }

        public String getLogin() {
            return onConstructUser.login;
        }

        public UserBuilder setLogin(String login) {
            this.onConstructUser.login = login;
            return this;
        }

        public String getPassword() {
            return onConstructUser.password;
        }

        public UserBuilder setPassword(String password) {
            this.onConstructUser.password = password;
            return this;
        }

        public String getEmail() {
            return onConstructUser.email;
        }

        public UserBuilder setEmail(String email) {
            onConstructUser.email = email;
            return this;
        }

        public boolean isAllow_letters() {
            return onConstructUser.allow_letters;
        }

        public UserBuilder setAllow_letters(boolean allow_letters) {
            this.onConstructUser.allow_letters = allow_letters;
            return this;
        }

        public boolean isConfirmed() {
            return onConstructUser.confirmed;
        }

        public UserBuilder setConfirmed(boolean confirmed) {
            this.onConstructUser.confirmed = confirmed;
            return this;
        }

        public int getRole_id() {
            return onConstructUser.role_id;
        }

        public UserBuilder setRole_id(int role_id) {
            this.onConstructUser.role_id = role_id;
            return this;
        }

        public int getAccount() {
            return onConstructUser.account;
        }

        public UserBuilder setAccount(int account) {
            this.onConstructUser.account = account;
            return this;
        }

        public int getLocale_id() {
            return onConstructUser.locale_id;
        }

        public UserBuilder setLocale_id(int locale_id) {
            this.onConstructUser.locale_id = locale_id;
            return this;
        }

    }
}