package com.myCompany.RepairAgency;


import java.util.Locale;
import java.util.ResourceBundle;

public class Constants {

    public static final String DB_SETTINGS_BUNDLE = "db";
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    public static final String PASSWORD_REPEAT = "passwordRepeat";
    public static final String LOGIN = "login";
    public static final String EMAIL = "email";
    public static final String DAOType = "DAOType";
    public static final String CONNECTION_URL = "connection.url";


    public enum ORDER_STATUS {
        NULL(null), CREATED("Created"),
        PENDING_PAYMENT("Pending payment"), PAID("Paid"),
        CANCELED("Canceled"), IN_PROGRESS("In progress"),
        COMPLETED("Completed");

        private final String toString;
        private final long ordinal;

        ORDER_STATUS(String toString) {
            this.toString = toString;
            this.ordinal = ordinal();
        }

        public long getOrdinal() {
            return ordinal;
        }

        public String getToString() {
            return toString;
        }
    }

    public enum ROLE {
        Guest, Admin, Manager, Craftsman, Client;

        private final String toString;
        private final long ordinal;

        ROLE() {
            this.toString = this.toString();
            this.ordinal = ordinal();
        }

        public long getOrdinal() {
            return ordinal;
        }

        public String getToString() {
            return toString;
        }
    }

    public enum REPORT_FORMAT {
        PDF, XLSX, XLS;

        private final String toString;

        REPORT_FORMAT() {
            this.toString = this.toString();
        }


        public String getToString() {
            return toString;
        }
    }


    public enum LOCALE {
        NULL, en_US, uk_UA;
        private final String toString = this.toString();
        private final ResourceBundle resourceBundle =
                ResourceBundle.getBundle("LocalStrings", new Locale(getToString()));
//        LOCALE() {
//            this.toString = this.toString();
//        }
        public String getToString() {
            return toString;
        }
        public String getString(String key) {
            return resourceBundle.getString(key);
        }
    }
}
