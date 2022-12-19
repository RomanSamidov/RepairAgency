package com.myCompany.RepairAgency.model;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.entity.OrderStatus;
import com.myCompany.RepairAgency.model.entity.RepairOrder;
import com.myCompany.RepairAgency.model.entity.Role;
import com.myCompany.RepairAgency.model.entity.User;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ModelManagerTest {



    private static final String CREATE_USERS_TABLE=
            "CREATE TABLE users (\n" +
                    "\tid SERIAL PRIMARY KEY,\n" +
                    "\tlogin VARCHAR(30) UNIQUE,\n" +
                    "\tpassword VARCHAR(63),\n" +
                    "\trole_id INT NOT NULL REFERENCES roles(id) on delete cascade,\n" +
                    "\taccount INT DEFAULT(0)\n" +
                    ");";

    private static final String CREATE_ROLES_TABLE=
            "CREATE TABLE roles (\n" +
                    "\tid SERIAL PRIMARY KEY,\n" +
                    "\tname VARCHAR(30) UNIQUE\n" +
                    ");";

    private static final String CREATE_ORDER_STATUSES_TABLE=
            "CREATE TABLE orders_statuses (\n" +
                    "\tid SERIAL PRIMARY KEY,\n" +
                    "\tname VARCHAR(30) UNIQUE\n" +
                    ");";


    private static final String CREATE_REPAIR_ORDERS_TABLE=
            "CREATE TABLE repair_orders (\n" +
                    "\tid SERIAL PRIMARY KEY,\n" +
                    "\tuser_id INT REFERENCES users(id) on delete cascade,\n" +
                    "\tcraftsman_id INT REFERENCES users(id) on delete CASCADE,\n" +
                    "\ttext VARCHAR(255),\n" +
                    "\tprice INT,\n" +
                    "\tstatus_id INT REFERENCES orders_statuses(id) on delete cascade,\n" +
                    "\tfeedback_text VARCHAR(255),\n" +
                    "\tfeedback_mark INT\n" +
                    ");";

    private static final String DROP_REPAIR_ORDERS_TABLE="drop table repair_orders;";
    private static final String DROP_USERS_TABLE="drop table users;";
    private static final String DROP_ROLES_TABLE="drop table roles;";
    private static final String DROP_ORDER_STATUSES_TABLE="drop table orders_statuses;";


    private static final String FILL_ROLES_TABLE="INSERT INTO roles VALUES (DEFAULT, 'Admin');\n" +
            "INSERT INTO roles VALUES (DEFAULT, 'Manager');\n" +
            "INSERT INTO roles VALUES (DEFAULT, 'Craftsman');\n" +
            "INSERT INTO roles VALUES (DEFAULT, 'Client');";

    private static final String FILL_ORDERS_STATUSES="INSERT INTO orders_statuses VALUES (DEFAULT, 'Created');\n" +
            "INSERT INTO orders_statuses VALUES (DEFAULT, 'Pending payment');\n" +
            "INSERT INTO orders_statuses VALUES (DEFAULT, 'Paid');\n" +
            "INSERT INTO orders_statuses VALUES (DEFAULT, 'Canceled');\n" +
            "INSERT INTO orders_statuses VALUES (DEFAULT, 'In progress');\n" +
            "INSERT INTO orders_statuses VALUES (DEFAULT, 'Completed');";


//    private static final String DERBY_LOG_FILE = "derby.log";

    private static Connection con;
    private ModelManager dbm;

    @BeforeAll
    static void globalSetUp() throws SQLException, IOException {
        Constants.nowIsTest();
        ResourceBundle resourceBundle = ResourceBundle.getBundle(Constants.DB_SETTINGS_BUNDLE);
        con=DriverManager.getConnection(resourceBundle.getString(Constants.getConnectionUrl()),
                resourceBundle.getString(Constants.USER),
                resourceBundle.getString(Constants.PASSWORD));
        resourceBundle.toString();
    }

    @AfterAll
    static void globalTearDown() throws SQLException, IOException {
        con.close();
//        try {
//            DriverManager.getConnection(SHUTDOWN_URL);
//        } catch (SQLException ex) {
//            System.err.println("Derby shutdown");
//        }
//        Files.delete(Path.of(DERBY_LOG_FILE));
    }

    @BeforeEach
    void setUp() throws SQLException {

        con.createStatement().executeUpdate(CREATE_ORDER_STATUSES_TABLE);
        con.createStatement().executeUpdate(CREATE_ROLES_TABLE);
        con.createStatement().executeUpdate(CREATE_USERS_TABLE);
        con.createStatement().executeUpdate(CREATE_REPAIR_ORDERS_TABLE);

        con.createStatement().executeUpdate(FILL_ROLES_TABLE);
        con.createStatement().executeUpdate(FILL_ORDERS_STATUSES);

        dbm=new ModelManager();
    }

    @AfterEach
    void tearDown() throws SQLException {
        con.createStatement().executeUpdate(DROP_REPAIR_ORDERS_TABLE);
        con.createStatement().executeUpdate(DROP_USERS_TABLE);
        con.createStatement().executeUpdate(DROP_ORDER_STATUSES_TABLE);
        con.createStatement().executeUpdate(DROP_ROLES_TABLE);

    }

//    @Test
//    void testDemo() throws DBException, SQLException {


//    @Test
//    void test1() {
//        assertEquals("testUser",  user.getLogin());
//        assertTrue(user.equals(user2), "Two users must be equaled if their logins are equaled");
//    }

    @Test
    void getAllOrderStatuses() {
        ArrayList<OrderStatus> statuses;
        statuses=dbm.getAllOrderStatuses();
        checkListOfOrderStatuses(statuses);
    }

    @Test
    void getOrderStatus() {
        ArrayList<OrderStatus> statuses=new ArrayList<>();
        IntStream.range(1, 7).forEach(x -> statuses.add(dbm.getOrderStatus(x)));
        checkListOfOrderStatuses(statuses);
    }

    private void checkListOfOrderStatuses(ArrayList<OrderStatus> statuses) {
        assertTrue(statuses.get(0).getId() == 1 && statuses.get(0).getName().equals("Created"), "First status is incorrect.");
        assertTrue(statuses.get(1).getId() == 2 && statuses.get(1).getName().equals("Pending payment"), "Second status is incorrect.");
        assertTrue(statuses.get(2).getId() == 3 && statuses.get(2).getName().equals("Paid"), "Third status is incorrect.");
        assertTrue(statuses.get(3).getId() == 4 && statuses.get(3).getName().equals("Canceled"), "Fourth status is incorrect.");
        assertTrue(statuses.get(4).getId() == 5 && statuses.get(4).getName().equals("In progress"), "Fifth status is incorrect.");
        assertTrue(statuses.get(5).getId() == 6 && statuses.get(5).getName().equals("Completed"), "Sixth status is incorrect.");
    }

    @Test
    void getAllRoles() {
        ArrayList<Role> roles;
        roles=dbm.getAllRoles();
        checkListOfRoles(roles);
    }

    @Test
    void getRole() {
        ArrayList<Role> roles=new ArrayList<>();
        IntStream.range(1, 5).forEach(x -> roles.add(dbm.getRole(x)));
        checkListOfRoles(roles);
    }

    private void checkListOfRoles(ArrayList<Role> roles) {
        assertTrue(roles.get(0).getId() == 1 && roles.get(0).getName().equals("Admin"), "First role is incorrect.");
        assertTrue(roles.get(1).getId() == 2 && roles.get(1).getName().equals("Manager"), "Second role is incorrect.");
        assertTrue(roles.get(2).getId() == 3 && roles.get(2).getName().equals("Craftsman"), "Third role is incorrect.");
        assertTrue(roles.get(3).getId() == 4 && roles.get(3).getName().equals("Client"), "Fourth role is incorrect.");
    }

    @Test
    void testUsersAndRepairOrders() {
        ArrayList<User> users=new ArrayList<>();
        ArrayList<RepairOrder> repairOrders=new ArrayList<>();
        IntStream.range(0, 100).forEach(x -> users.add(new User.UserBuilder().setLogin("user" + x)
                .setPassword("user" + x).setRole_id(x % 4 + 1).setId(x + 1).build()));
        IntStream.range(0, 100).forEach(x -> repairOrders.add(new RepairOrder.RepairOrderBuilder().setId(x + 1)
                .setUser_id(x + 1).setCraftsman_id(3).setText("text " + x).build()));

        users.forEach(u -> dbm.insertUser(u));
        repairOrders.forEach(o -> dbm.insertRepairOrder(o));

        ArrayList<User> usersFromDB=dbm.getAllUsers();
        assertEquals(users, usersFromDB);
        ArrayList<RepairOrder> repairOrdersFromDB=dbm.getAllRepairOrders();
        assertEquals(repairOrders, repairOrdersFromDB);

        usersFromDB.forEach(u -> {
            u.setAccount(100);
            dbm.updateUser(u);
        });
        IntStream.range(1, 101).forEach(x -> assertEquals(100, dbm.getUser(x).getAccount(), "User account not updated"));
        repairOrdersFromDB.forEach(o -> {
            o.setPrice(100);
            dbm.updateRepairOrder(o);
        });
        IntStream.range(1, 101).forEach(x -> assertEquals(100, dbm.getRepairOrder(x).getPrice(), "RepairOrder not updated"));


        IntStream.range(1, 101).forEach(x -> dbm.deleteRepairOrder(dbm.getRepairOrder(x)));
        repairOrdersFromDB=dbm.getAllRepairOrders();
        assertTrue(repairOrdersFromDB.isEmpty());
        IntStream.range(1, 101).forEach(x -> dbm.deleteUser(dbm.getUser(x)));
        usersFromDB=dbm.getAllUsers();
        assertTrue(usersFromDB.isEmpty());

    }
}