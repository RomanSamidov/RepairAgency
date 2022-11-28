package com.myCompany.RepairAgency.db;

public class Fields {

    public static final String ID="id";
    public static final String LOGIN="login";
    public static final String PASSWORD="password";
    public static final String ROLE_ID="role_id";
    public static final String ACCOUNT="account";
    public static final String NAME="name";
    public static final String USER_ID="user_id";
    public static final String CRAFTSMAN_ID="craftsman_id";
    public static final String TEXT="text";
    public static final String PRICE="price";
    public static final String STATUS_ID="status_id";
    public static final String FEEDBACK_TEXT="feedback_text";
    public static final String FEEDBACK_MARK="feedback_mark";

    public enum ORDER_STATUS {
        NULL(null), CREATED("Created"),
        PENDING_PAYMENT("Pending payment"), PAID("Paid"),
        CANCELED("Canceled"), IN_PROGRESS("In progress"),
        COMPLETED("Completed");

        private final String toString;

        ORDER_STATUS(String toString) {
            this.toString=toString;
        }

        public String getToString() {
            return toString;
        }
    }

    public enum ROLE {
        Null, Admin, Manager, Craftsman, Client
	}

}
