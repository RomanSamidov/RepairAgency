package com.myCompany.RepairAgency.model.db.PostgeSQL;

public abstract class Query {


    public static class UsersQuery {
        public static final String INSERT="INSERT INTO users VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?);";
        public static final String UPDATE="UPDATE users SET login = ?, password = ?, email = ?, allow_letters = ?, confirmed = ?, role_id = ?, account = ? WHERE id = ?;";
        public static final String DELETE="DELETE FROM users WHERE id = ?;";
        public static final String SELECT_BY_ID="SELECT * FROM users WHERE id = ?;";
        public static final String SELECT_BY_LOGIN="SELECT * FROM users WHERE login = ?;";
        public static final String SELECT_ALL_BY_ROLE="SELECT * FROM users WHERE role_id = ? ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;;";
        public static final String SELECT_WITH_PAGINATION="SELECT * FROM users ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        public static final String COUNT_BY_ROLE="SELECT COUNT(id) AS result FROM users WHERE role_id = ?";
        public static final String COUNT="SELECT COUNT(id) AS result FROM users";
    }

    public static class RepairOrdersQuery {
        public static final String INSERT="INSERT INTO repair_orders VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        public static final String UPDATE="UPDATE repair_orders SET user_id = ?, craftsman_id = ?, creation_date = ?, text = ?, price = ?, finish_date = ?, status_id = ?, feedback_date = ?, feedback_text = ?, feedback_mark = ?  WHERE id = ?;";
        public static final String DELETE="DELETE FROM repair_orders WHERE id = ?;";
        public static final String SELECT_BY_ID="SELECT * FROM repair_orders WHERE id = ?;";
        public static final String SELECT_BY_USER_ID="SELECT * FROM repair_orders WHERE user_id = ? ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        public static final String COUNT_BY_USER_ID="SELECT COUNT(id) AS result FROM repair_orders WHERE user_id = ?";
        public static final String SELECT_BY_CRAFTSMAN_ID="SELECT * FROM repair_orders WHERE craftsman_id = ? ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        public static final String COUNT_BY_CRAFTSMAN_ID="SELECT COUNT(id) AS result FROM repair_orders WHERE craftsman_id = ?";
       public static final String SELECT_BY_CRAFT_USER_STATUS="SELECT * FROM repair_orders WHERE craftsman_id IS NULL OR craftsman_id = ANY(?) AND  user_id = ? AND  status_id = ANY(?)  ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        public static final String COUNT_BY_CRAFT_USER_STATUS="SELECT COUNT(id) AS result FROM repair_orders WHERE craftsman_id IS NULL OR craftsman_id = ANY(?) AND  user_id = ? AND  status_id = ANY(?);";
        public static final String SELECT_BY_CRAFT_STATUS="SELECT * FROM repair_orders WHERE (craftsman_id IS NULL OR craftsman_id = ANY(?)) AND  status_id = ANY(?)  ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        public static final String COUNT_BY_CRAFT_STATUS="SELECT COUNT(id) AS result FROM repair_orders WHERE (craftsman_id IS NULL OR craftsman_id = ANY(?)) AND  status_id = ANY(?);";
        public static final String SELECT_BY_USER_STATUS="SELECT * FROM repair_orders WHERE user_id == ? AND  status_id = ANY(?)  ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        public static final String COUNT_BY_USER_STATUS="SELECT COUNT(id) AS result FROM repair_orders WHERE user_id == ? AND  status_id = ANY(?);";
        public static final String SELECT_BY_STATUS="SELECT * FROM repair_orders WHERE status_id = ANY(?)  ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        public static final String COUNT_BY_STATUS="SELECT COUNT(id) AS result FROM repair_orders WHERE status_id = ANY(?);";
        public static final String COUNT="SELECT COUNT(id) AS result FROM repair_orders";
     public static final String SELECT_WITH_PAGINATION="SELECT * FROM repair_orders ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
    }

}
