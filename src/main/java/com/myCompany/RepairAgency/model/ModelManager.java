package com.myCompany.RepairAgency.model;


import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.abstractRepositoryFactory;
import com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.abstractRepositoryFactory.DAOType;
import com.myCompany.RepairAgency.model.entity.OrderStatus;
import com.myCompany.RepairAgency.model.entity.RepairOrder;
import com.myCompany.RepairAgency.model.entity.Role;
import com.myCompany.RepairAgency.model.entity.User;

import java.util.ArrayList;
import java.util.ResourceBundle;


public class ModelManager {

    public static final ModelManager ins = new ModelManager();

    public final DAOType DAO_TYPE;
    private final abstractRepositoryFactory DAO_FACTORY;

    public ModelManager() {
        DAO_TYPE=initializeDAOType();
        DAO_FACTORY= abstractRepositoryFactory.getDAOFactory(DAO_TYPE);
    }

    public ModelManager(DAOType DAO_TYPE) {
        this.DAO_TYPE=DAO_TYPE;
        DAO_FACTORY= abstractRepositoryFactory.getDAOFactory(DAO_TYPE);
    }

    private static DAOType initializeDAOType() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(Constants.DB_SETTINGS_BUNDLE);
        return DAOType.valueOf(resourceBundle.getString(Constants.DAOType));
    }


    public ArrayList<OrderStatus> getAllOrderStatuses() {
        return DAO_FACTORY.getOrderStatusService().getAll();
    }

    public OrderStatus getOrderStatus(int id) {
        return DAO_FACTORY.getOrderStatusService().getById(id);
    }

    public User getUser(int id) {
        return DAO_FACTORY.getUserService().getById(id);
    }
    public User getUser(String login) {
        return DAO_FACTORY.getUserService().getByLogin(login);
    }

    public void insertUser(User user) {
        DAO_FACTORY.getUserService().insert(user);
    }

    public void updateUser(User user) {
        DAO_FACTORY.getUserService().update(user);
    }

    public void deleteUser(User user) {
        DAO_FACTORY.getUserService().delete(user);
    }

    public ArrayList<User> getAllUsers() {
        return DAO_FACTORY.getUserService().getAll();
    }

    public ArrayList<User> getUsersWithPagination(int skip, int quantity) {
        return DAO_FACTORY.getUserService().getWithPagination(skip, quantity);
    }

    public ArrayList<Role> getAllRoles() {
        return DAO_FACTORY.getRoleService().getAll();
    }

    public Role getRole(int id) {
        return DAO_FACTORY.getRoleService().getById(id);
    }


    public RepairOrder getRepairOrder(int id) {
        return DAO_FACTORY.getRepairOrderService().getById(id);
    }

    public void insertRepairOrder(RepairOrder repairOrder) {
        DAO_FACTORY.getRepairOrderService().insert(repairOrder);
    }

    public void updateRepairOrder(RepairOrder repairOrder) {
        DAO_FACTORY.getRepairOrderService().update(repairOrder);
    }

    public void deleteRepairOrder(RepairOrder repairOrder) {
        DAO_FACTORY.getRepairOrderService().delete(repairOrder);
    }

    public ArrayList<RepairOrder> getAllRepairOrders() {
        return DAO_FACTORY.getRepairOrderService().getAll();
    }

    public ArrayList<RepairOrder> getRepairOrdersWithPagination(int skip, int quantity) {
        return DAO_FACTORY.getRepairOrderService().getWithPagination(skip, quantity);
    }

//	public boolean setTeamsForUser(User user, Team... teams) throws DBException {
//		try {
//			con.setAutoCommit(false);
//			Statement stmt = con.createStatement();
//			Arrays.stream(teams).forEach(team -> {
//				try {
//					String sql = "INSERT INTO users_teams VALUES (" + getUser(user.getLogin()).getId() + ", " + getTeam(team.getName()).getId() + ")";
//					stmt.executeUpdate(sql);
//				} catch (Exception e) {
//					throw new RuntimeException(e);
//				}
//			});
//			con.commit();
//			con.setAutoCommit(true);
//		} catch (SQLException e) {
//			try {
//				con.rollback();
//				con.setAutoCommit(true);
//			} catch (SQLException ex) {
//				throw new RuntimeException(ex);
//			}
//			return false;
//		}
//		return true;
//	}

//
//	public List<User> getTeamUsers(Team team) throws DBException {
//		ResultSet rs;
//		ArrayList<User> arrayList = new ArrayList<>();
//
//		try {
//			Statement stmt = con.createStatement();
//			rs = stmt.executeQuery("SELECT users.* FROM users JOIN users_teams ON users_teams.user_id = users.id WHERE users_teams.team_id = " + getTeam(team.getName()).getId() + "");
//			while (rs.next()) {
//				arrayList.add(User.createUser(rs.getInt("id"), rs.getString("login")));
//			}
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
//
//		return arrayList;
//	}


}