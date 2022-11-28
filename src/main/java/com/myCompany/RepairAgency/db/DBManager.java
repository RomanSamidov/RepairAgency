package com.myCompany.RepairAgency.db;


import com.myCompany.RepairAgency.db.dao.abstractDAO.abstractDAOFactory;
import com.myCompany.RepairAgency.db.dao.abstractDAO.abstractDAOFactory.DAOType;
import com.myCompany.RepairAgency.db.entity.OrderStatus;
import com.myCompany.RepairAgency.db.entity.RepairOrder;
import com.myCompany.RepairAgency.db.entity.Role;
import com.myCompany.RepairAgency.db.entity.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;


public class DBManager {

    public final DAOType DAO_TYPE;
    private final abstractDAOFactory DAO_FACTORY;

    public DBManager() {
        DAO_TYPE=initializeDAOType();
        DAO_FACTORY=abstractDAOFactory.getDAOFactory(DAO_TYPE);
    }

    public DBManager(DAOType DAOType) {
        DAO_TYPE=DAOType;
        DAO_FACTORY=abstractDAOFactory.getDAOFactory(DAO_TYPE);
    }


    private static DAOType initializeDAOType() {
        Properties properties=new Properties();
        try {
            properties.load(new FileInputStream("app.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return DAOType.valueOf(properties.getProperty("DAOType"));
    }


    public ArrayList<OrderStatus> getAllOrderStatuses() {
        return DAO_FACTORY.getOrderStatusDAO().getAll();
    }

    public OrderStatus getOrderStatus(int id) {
        return DAO_FACTORY.getOrderStatusDAO().getById(id);
    }

    public User getUser(int id) {
        return DAO_FACTORY.getUserDAO().getById(id);
    }

    public void insertUser(User user) {
        DAO_FACTORY.getUserDAO().insert(user);
    }

    public void updateUser(User user) {
        DAO_FACTORY.getUserDAO().update(user);
    }

    public void deleteUser(User user) {
        DAO_FACTORY.getUserDAO().delete(user);
    }

    public ArrayList<User> getAllUsers() {
        return DAO_FACTORY.getUserDAO().getAll();
    }

    public ArrayList<Role> getAllRoles() {
        return DAO_FACTORY.getRoleDAO().getAll();
    }

    public Role getRole(int id) {
        return DAO_FACTORY.getRoleDAO().getById(id);
    }


    public RepairOrder getRepairOrder(int id) {
        return DAO_FACTORY.getRepairOrderDAO().getById(id);
    }

    public void insertRepairOrder(RepairOrder repairOrder) {
        DAO_FACTORY.getRepairOrderDAO().insert(repairOrder);
    }

    public void updateRepairOrder(RepairOrder repairOrder) {
        DAO_FACTORY.getRepairOrderDAO().update(repairOrder);
    }

    public void deleteRepairOrder(RepairOrder repairOrder) {
        DAO_FACTORY.getRepairOrderDAO().delete(repairOrder);
    }

    public ArrayList<RepairOrder> getAllRepairOrders() {
        return DAO_FACTORY.getRepairOrderDAO().getAll();
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
