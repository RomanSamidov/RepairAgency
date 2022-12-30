package com.myCompany.RepairAgency.model.db.PostgeSQL;

import com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.entity.iRepairOrderRepository;

public abstract class Query {


    public static class UsersQuery {
        public static final String INSERT = "INSERT INTO users VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?);";
        public static final String UPDATE = "UPDATE users SET login = ?, password = ?, email = ?, allow_letters = ?, confirmed = ?, role_id = ?, account = ? WHERE id = ?;";
        public static final String DELETE = "DELETE FROM users WHERE id = ?;";
        public static final String SELECT_BY_ID = "SELECT * FROM users WHERE id = ?;";
        public static final String SELECT_BY_LOGIN = "SELECT * FROM users WHERE login = ?;";
        public static final String SELECT_ALL_BY_ROLE = "SELECT * FROM users WHERE role_id = ? ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        public static final String SELECT_WITH_PAGINATION = "SELECT * FROM users ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        public static final String COUNT_BY_ROLE = "SELECT COUNT(id) AS result FROM users WHERE role_id = ?";
        public static final String COUNT = "SELECT COUNT(id) AS result FROM users";
    }

    public static class RepairOrdersQuery {
        public static final String INSERT = "INSERT INTO repair_orders VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        public static final String UPDATE = "UPDATE repair_orders SET user_id = ?, craftsman_id = ?, creation_date = ?, text = ?, price = ?, finish_date = ?, status_id = ?, feedback_date = ?, feedback_text = ?, feedback_mark = ?  WHERE id = ?;";
        public static final String DELETE = "DELETE FROM repair_orders WHERE id = ?;";
        public static final String SELECT_BY_ID = "SELECT * FROM repair_orders WHERE id = ?;";
        public static final String COUNT = "SELECT COUNT(id) AS result FROM repair_orders";
        public static final String SELECT = "SELECT * FROM repair_orders";
        public static final String SELECT_BY_STATUS = "SELECT * FROM repair_orders WHERE status_id = ANY(?)";
        public static final String COUNT_BY_STATUS = "SELECT COUNT(id) AS result FROM repair_orders WHERE status_id = ANY(?);";
        public static final String SELECT_BY_CRAFT_NN_STATUS = "SELECT * FROM repair_orders WHERE craftsman_id = ANY(?) AND  status_id = ANY(?)";
        public static final String COUNT_BY_CRAFT_NN_STATUS = "SELECT COUNT(id) AS result FROM repair_orders WHERE craftsman_id = ANY(?) AND  status_id = ANY(?);";
        public static final String SELECT_BY_CRAFT_STATUS = "SELECT * FROM repair_orders WHERE (craftsman_id IS NULL OR craftsman_id = ANY(?)) AND  status_id = ANY(?)";
        public static final String COUNT_BY_CRAFT_STATUS = "SELECT COUNT(id) AS result FROM repair_orders WHERE (craftsman_id IS NULL OR craftsman_id = ANY(?)) AND  status_id = ANY(?);";
        public static final String SELECT_BY_USER_STATUS = "SELECT * FROM repair_orders WHERE user_id = ? AND status_id = ANY(?)";
        public static final String COUNT_BY_USER_STATUS = "SELECT COUNT(id) AS result FROM repair_orders WHERE user_id = ? AND  status_id = ANY(?);";
        public static final String SELECT_BY_CRAFT_USER_STATUS = "SELECT * FROM repair_orders WHERE (craftsman_id IS NULL OR craftsman_id = ANY(?)) AND  user_id = ? AND  status_id = ANY(?)";
        public static final String COUNT_BY_CRAFT_USER_STATUS = "SELECT COUNT(id) AS result FROM repair_orders WHERE (craftsman_id IS NULL OR craftsman_id = ANY(?)) AND  user_id = ? AND  status_id = ANY(?);";
        private static final String PAGINATION = "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        private static final String ORDER_BY_ID_ASC = " ORDER BY id ASC " + PAGINATION;
        private static final String ORDER_BY_ID_DESC = " ORDER BY id DESC " + PAGINATION;
        private static final String ORDER_BY_CRAFTSMAN_ID_ASC = " ORDER BY craftsman_id ASC " + PAGINATION;
        private static final String ORDER_BY_CRAFTSMAN_ID_DESC = " ORDER BY craftsman_id DESC " + PAGINATION;
        private static final String ORDER_BY_CREATION_DATE_ASC = " ORDER BY creation_date ASC " + PAGINATION;
        private static final String ORDER_BY_CREATION_DATE_DESC = " ORDER BY creation_date DESC " + PAGINATION;
        private static final String ORDER_BY_STATUS_ASC = " ORDER BY status_id ASC " + PAGINATION;
        private static final String ORDER_BY_STATUS_DESC = " ORDER BY status_id DESC " + PAGINATION;
        private static final String ORDER_BY_PRICE_ASC = " ORDER BY price ASC " + PAGINATION;
        private static final String ORDER_BY_PRICE_DESC = " ORDER BY price DESC " + PAGINATION;

        public static String getSortQuery(iRepairOrderRepository.SORT_TYPE type) {
            switch (type) {
                case ORDER_BY_ID_ASC:
                    return ORDER_BY_ID_ASC;
                case ORDER_BY_ID_DESC:
                    return ORDER_BY_ID_DESC;
                case ORDER_BY_CREATION_DATE_ASC:
                    return ORDER_BY_CREATION_DATE_ASC;
                case ORDER_BY_CREATION_DATE_DESC:
                    return ORDER_BY_CREATION_DATE_DESC;
                case ORDER_BY_STATUS_ASC:
                    return ORDER_BY_STATUS_ASC;
                case ORDER_BY_STATUS_DESC:
                    return ORDER_BY_STATUS_DESC;
                case ORDER_BY_PRICE_ASC:
                    return ORDER_BY_PRICE_ASC;
                case ORDER_BY_PRICE_DESC:
                    return ORDER_BY_PRICE_DESC;
                case ORDER_BY_CRAFTSMAN_ID_ASC:
                    return ORDER_BY_CRAFTSMAN_ID_ASC;
                case ORDER_BY_CRAFTSMAN_ID_DESC:
                    return ORDER_BY_CRAFTSMAN_ID_DESC;

            }
            return ORDER_BY_ID_ASC;
        }

    }

}
