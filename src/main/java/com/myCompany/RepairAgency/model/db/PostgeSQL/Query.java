package com.myCompany.RepairAgency.model.db.PostgeSQL;

public abstract class Query {

//    PreparedStatement pstmt = con.prepareStatement("UPDATE EMPLOYEES
//            SET SALARY = ? WHERE ID = ?");
//            pstmt.setBigDecimal(1, 153833.00);
//            pstmt.setInt(2, 110592);
//            pstmt.executeUpdate();


    public static class UsersQuery {
        public static final String INSERT="INSERT INTO users VALUES (DEFAULT, ?, ?, ?, ?);";
        public static final String UPDATE="UPDATE users SET login = ?, password = ?, role_id = ?, account = ? WHERE id = ?;";
        public static final String DELETE="DELETE FROM users WHERE id = ?;";
        public static final String SELECT_BY_ID="SELECT * FROM users WHERE id = ?;";
        public static final String SELECT_BY_LOGIN="SELECT * FROM users WHERE login = ?;";
        public static final String SELECT_ALL="SELECT * FROM users";
        public static final String SELECT_ALL_BY_ROLE="SELECT * FROM users WHERE role_id = ? ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;;";
        public static final String SELECT_WITH_PAGINATION="SELECT * FROM users ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        public static final String COUNT_BY_ROLE="SELECT COUNT(id) AS result FROM users WHERE role_id = ?";
    }

    public static class RepairOrdersQuery {
        public static final String INSERT="INSERT INTO repair_orders VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?);";
        public static final String UPDATE="UPDATE repair_orders SET user_id = ?, craftsman_id = ?, text = ?, price = ?, status_id = ?, feedback_text = ?, feedback_mark = ?  WHERE id = ?;";
        public static final String DELETE="DELETE FROM repair_orders WHERE id = ?;";
        public static final String SELECT_BY_ID="SELECT * FROM repair_orders WHERE id = ?;";
        public static final String SELECT_BY_CRAFTSMAN_ID="SELECT * FROM repair_orders WHERE craftsman_id = ? ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        public static final String SELECT_BY_USER_ID="SELECT * FROM repair_orders WHERE user_id = ? ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        public static final String COUNT_BY_USER_ID="SELECT COUNT(id) AS result FROM repair_orders WHERE user_id = ?";
        public static final String COUNT="SELECT COUNT(id) AS result FROM repair_orders";
        public static final String SELECT_BY_STATUS="SELECT * FROM repair_orders WHERE status_id = ? ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        public static final String SELECT_ALL="SELECT * FROM repair_orders;";
        public static final String SELECT_WITH_PAGINATION="SELECT * FROM repair_orders ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
    }

    public static class Orders_statusesQuery {
        public static final String SELECT_BY_ID="SELECT * FROM orders_statuses WHERE id = ?;";
        public static final String SELECT_ALL="SELECT * FROM orders_statuses";
        public static final String SELECT_WITH_PAGINATION="SELECT * FROM orders_statuses ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

    }

    public static class RolesQuery {
        public static final String SELECT_BY_ID="SELECT * FROM roles WHERE id = ?;";
        public static final String SELECT_ALL="SELECT * FROM roles";
        public static final String SELECT_WITH_PAGINATION="SELECT * FROM roles ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

    }
}
