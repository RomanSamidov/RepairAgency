package com.myCompany.RepairAgency;


import java.util.Locale;
import java.util.ResourceBundle;

public class Constants {

    public static final String DB_SETTINGS_BUNDLE = "db";
    public static final String RECAPTCHA_SETTINGS_BUNDLE = "recaptcha";
    public static final String EMAIL_SETTINGS_BUNDLE = "email";
    public static final String PATHS_BUNDLE = "paths";
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    public static final String PASSWORD_REPEAT = "passwordRepeat";
    public static final String LOGIN = "login";
    public static final String EMAIL = "email";
    public static final String DAOType = "DAOType";
    public static final String CONNECTION_URL = "connection.url";


    public enum ORDER_STATUS {
        NULL(null),
        CREATED("Created"),
        PENDING_PAYMENT("Pending payment"),
        PAID("Paid"),
        CANCELED("Canceled"),
        IN_PROGRESS("In progress"),
        COMPLETED("Completed");
        private final String toString;
        private final int ordinal = ordinal();

        ORDER_STATUS(String name) {
            this.toString = name;
        }

        public String getToString() {
            return toString;
        }

        public int getOrdinal() {
            return ordinal;
        }
    }


    public enum ROLE {
        Guest,
        Admin,
        Manager,
        Craftsman,
        Client;
        private final String toString = this.toString();
        private final int ordinal = ordinal();

        public String getToString() {
            return toString;
        }

        public int getOrdinal() {
            return ordinal;
        }
    }


    public enum REPORT_FORMAT {
        PDF,
        XLSX,
        XLS;
        private final String toString;

        REPORT_FORMAT() {
            this.toString = this.toString();
        }

        public String getToString() {
            return toString;
        }
    }


    public enum LOCALE {
        NULL("en","US"),
        en_US("en","US"),
        uk_UA("uk","UA");
        private final String toString = this.toString();
        private final ResourceBundle resourceBundle;

        LOCALE(String language, String country) {
            resourceBundle =
                ResourceBundle.getBundle("LocalStrings",
                        new Locale(language, country));
        }

        public String getToString() {
            return toString;
        }

        public String getString(String key) {
            return resourceBundle.getString(key);
        }
    }
}
