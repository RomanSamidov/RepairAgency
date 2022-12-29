package com.myCompany.RepairAgency;

import java.util.ResourceBundle;

public class Constants {

    public static final String DB_SETTINGS_BUNDLE = "db";
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    public static final String PASSWORD_REPEAT = "passwordRepeat";
    public static final String LOGIN = "login";
    public static final String EMAIL = "email";
    public static final String DAOType = "DAOType";
    private static final String CONNECTION_URL = "connection.url";
    private static final String CONNECTION_URL_TEST = "connection.url.test";
    private static boolean test = false;

    public static void nowIsTest() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(Constants.DB_SETTINGS_BUNDLE);
        if(resourceBundle.getString(CONNECTION_URL).equals(resourceBundle.getString(CONNECTION_URL_TEST)))
            throw new IllegalArgumentException();
        Constants.test = true;
    }
    public static String getConnectionUrl() {
        if (test) return CONNECTION_URL_TEST;
        else  return  CONNECTION_URL;
    }


    public enum ORDER_STATUS {
        NULL(null), CREATED("Created"),
        PENDING_PAYMENT("Pending payment"), PAID("Paid"),
        CANCELED("Canceled"), IN_PROGRESS("In progress"),
        COMPLETED("Completed");

        private final String toString;
        private final long ordinal;

        ORDER_STATUS(String toString) {
            this.toString=toString;
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
        Guest, Admin, Manager, Craftsman, Client
    }


}
