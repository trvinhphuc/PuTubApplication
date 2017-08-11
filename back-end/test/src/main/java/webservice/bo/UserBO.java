package webservice.bo;

import java.sql.Connection;
import java.util.ArrayList;

import webservice.dao.UserDAOImpl;
import webservice.pojo.UserVO;
import webservice.server.DataSource;

public class UserBO extends BaseBO {

	public ArrayList<UserVO> getAllUsersList() throws Exception {
		ArrayList<UserVO> userList = null;

		try {
			DataSource database = new DataSource();
			Connection connection = database.getInstance().getConnection();
			UserDAOImpl dao = new UserDAOImpl();
			userList = dao.getAllUsers(connection);
			// System.out.println(userList);
			connection.close();
		} catch (Exception e) {
			System.out.println("Unable connect DB");
			throw e;
		}
		return userList;
	}

	public boolean addUser(Object user) throws Exception {

		boolean addSuccess = false;

		try {
			DataSource database = new DataSource();
			Connection connection = database.getInstance().getConnection();
			UserDAOImpl dao = new UserDAOImpl();
			addSuccess = dao.setUserInfo(user, connection);
			connection.close();
		} catch (Exception e) {
			System.out.println("Unable connect DB");
			throw e;
		}
		return addSuccess;

	}

	public UserVO getAvatar(int id) throws Exception {

		UserVO user = new UserVO();
		// Image img = null;
		try {
			DataSource database = new DataSource();
			Connection connection = database.getInstance().getConnection();
			UserDAOImpl dao = new UserDAOImpl();
			user.setId(id);
			user = (UserVO) dao.getImage(user, connection);
			connection.close();
		} catch (Exception e) {
			System.out.println("Unable connect DB");
			throw e;
		}
		return user;

	}

	public boolean setSubcriber(UserVO user) throws Exception {

		boolean addSuccess = false;

		try {
			DataSource database = new DataSource();
			Connection connection = database.getInstance().getConnection();
			UserDAOImpl dao = new UserDAOImpl();
			addSuccess = dao.setSubcriber(user.getID(),user.getSubname(), connection);
			connection.close();
		} catch (Exception e) {
			System.out.println("Unable connect DB");
			throw e;
		}
		return addSuccess;

	}

	public boolean delSubcriber(UserVO user) throws Exception {

		boolean delSuccess = false;

		try {
			DataSource database = new DataSource();
			Connection connection = database.getInstance().getConnection();
			UserDAOImpl dao = new UserDAOImpl();
			delSuccess = dao.setSubcriber(user.getID(),user.getSubname(), connection);
			connection.close();
		} catch (Exception e) {
			System.out.println("Unable connect DB");
			throw e;
		}
		return delSuccess;

	}
	public ArrayList<UserVO> getSubcriberList(String id) throws Exception {
		ArrayList<UserVO> userList = null;

		try {
			DataSource database = new DataSource();
			Connection connection = database.getInstance().getConnection();
			UserDAOImpl dao = new UserDAOImpl();
			userList = dao.getSubcribers(id, connection);
			// System.out.println(userList);
			connection.close();
		} catch (Exception e) {
			System.out.println("Unable connect DB");
			throw e;
		}
		return userList;
	}
	public boolean updateUser(Object user) throws Exception {

		boolean addSuccess = false;

		try {
			DataSource database = new DataSource();
			Connection connection = database.getInstance().getConnection();
			UserDAOImpl dao = new UserDAOImpl();
			addSuccess = dao.updateUserInfo(user, connection);
			connection.close();
		} catch (Exception e) {
			System.out.println("Unable connect DB");
			throw e;
		}
		return addSuccess;

	}
	public UserVO getUserInfo(int id) throws Exception {

		UserVO user = new UserVO();
		try {
			DataSource database = new DataSource();
			Connection connection = database.getInstance().getConnection();
			UserDAOImpl dao = new UserDAOImpl();
			user = dao.getUserInfo(id, connection);
			connection.close();
		} catch (Exception e) {
			System.out.println("Unable connect DB");
			throw e;
		}
		return user;

	}
	public UserVO getUserID(String username) throws Exception {

		UserVO user = new UserVO();
		try {
			DataSource database = new DataSource();
			Connection connection = database.getInstance().getConnection();
			UserDAOImpl dao = new UserDAOImpl();
			user = dao.getUserID(username, connection);
			connection.close();
		} catch (Exception e) {
			System.out.println("Unable connect DB");
			throw e;
		}
		return user;

	}
	public boolean updatePass(Object user) throws Exception {

		boolean addSuccess = false;

		try {
			DataSource database = new DataSource();
			Connection connection = database.getInstance().getConnection();
			UserDAOImpl dao = new UserDAOImpl();
			addSuccess = dao.changePassword(user, connection);
			connection.close();
		} catch (Exception e) {
			System.out.println("Unable connect DB");
			throw e;
		}
		return addSuccess;

	}
}
