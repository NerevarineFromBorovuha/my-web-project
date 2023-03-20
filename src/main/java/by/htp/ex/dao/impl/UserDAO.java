package by.htp.ex.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

import org.mindrot.jbcrypt.BCrypt;

import by.htp.ex.bean.UserInfo;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.dao.db_connection_pool.ConnectionPool;
import by.htp.ex.dao.db_connection_pool.ConnectionPoolException;

public class UserDAO implements IUserDAO {

	private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
	private static final ReentrantLock LOCKER = new ReentrantLock();

	private static final String USER_ID = "id";
	private static final String USER_PASSWORD = "password";
	private static final String USER_ROLE_GUEST = "guest";
	private static final String USER_DATE_FORMAT = "yyyy.MM.dd";

	private final static String QUERY_FOR_AUTHORIZATION = "SELECT * FROM users WHERE login = ?";

	@Override
	public boolean authorization(String login, String password) throws DaoException {

		boolean statusAuthorization = false;

		try (Connection connection = CONNECTION_POOL.takeConnection();
				PreparedStatement ps = connection.prepareStatement(QUERY_FOR_AUTHORIZATION)) {

			ps.setString(1, login);
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				if (BCrypt.checkpw(password, res.getString(USER_PASSWORD))) {
					statusAuthorization = true;
				}
			}
		} catch (SQLException e) {
			throw new DaoException("SQL Exception", e);
		} catch (ConnectionPoolException e) {
			throw new DaoException("SQL connection Exception", e);
		}
		return statusAuthorization;
	}

	private final static String QUERY_FOR_ROLE = "SELECT title FROM roles, users WHERE login = ? AND roles.id=roles_id";

	@Override
	public String getRole(String login, String password) throws DaoException {

		String role = USER_ROLE_GUEST;

		try (Connection connection = CONNECTION_POOL.takeConnection();
				PreparedStatement ps = connection.prepareStatement(QUERY_FOR_ROLE)) {
			ps.setString(1, login);
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				role = res.getString(1);
			}
		} catch (SQLException e) {
			throw new DaoException("SQL Exception", e);
		} catch (ConnectionPoolException e) {
			throw new DaoException("SQL connection Exception", e);
		}
		return role;
	}

	private final static String QUERY_FOR_ADD_USER = "INSERT INTO users (login,password) VALUES (?,?)";
	private final static String QUERY_FOR_ADD_USER_INFO = "INSERT INTO users_details(users_id,mail,name,surname,gender,burthday,registration_date) VALUES (?,?,?,?,?,?,?) ";

	@Override
	public boolean registration(UserInfo user) throws DaoException {

		boolean registrationStatus = false;

		if (!checkLogin(user)) {
			return registrationStatus;
		}

		LOCKER.lock();

		String hashPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

		try (Connection connection = CONNECTION_POOL.takeConnection();
				PreparedStatement psForAddUser = connection.prepareStatement(QUERY_FOR_ADD_USER);
				PreparedStatement psForAddUserInfo = connection.prepareStatement(QUERY_FOR_ADD_USER_INFO)) {

			psForAddUser.setString(1, user.getLogin());
			psForAddUser.setString(2, hashPassword);
			psForAddUser.executeUpdate();

			SimpleDateFormat dateRegistration = new SimpleDateFormat(USER_DATE_FORMAT);
			user.setDateRegistration(dateRegistration.format(new Date()).toString());
			user.setUserId(findId(user));

			psForAddUserInfo.setInt(1, user.getUserId());
			psForAddUserInfo.setString(2, user.getEmail());
			psForAddUserInfo.setString(3, user.getName());
			psForAddUserInfo.setString(4, user.getSurname());
			psForAddUserInfo.setString(5, user.getGender());
			psForAddUserInfo.setString(6, user.getBirthday());
			psForAddUserInfo.setString(7, user.getDateRegistration());
			psForAddUserInfo.executeUpdate();
			registrationStatus = true;

		} catch (SQLException e) {
			throw new DaoException("SQL Exception", e);
		} catch (ConnectionPoolException e) {
			throw new DaoException("SQL connection Exception", e);
		} finally {
			LOCKER.unlock();
		}

		return registrationStatus;

	}

	private final static String QUERY_FOR_CHECK_LOGIN = "SELECT login FROM users WHERE login = ?";

	@Override
	public boolean checkLogin(UserInfo user) throws DaoException {

		try (Connection connection = CONNECTION_POOL.takeConnection();
				PreparedStatement ps = connection.prepareStatement(QUERY_FOR_CHECK_LOGIN)) {
			ps.setString(1, user.getLogin());
			ResultSet res = ps.executeQuery();
			if (!res.next()) {
				return true;
			}

		} catch (SQLException e) {
			throw new DaoException("SQL Exception", e);
		} catch (ConnectionPoolException e) {
			throw new DaoException("SQL connection Exception", e);
		}

		return false;
	}

	private final static String QUERRY_FOR_GET_ID = "SELECT id FROM users WHERE login=?";

	@Override
	public int findId(UserInfo user) throws DaoException {
		int id = 0;

		try (Connection connection = CONNECTION_POOL.takeConnection();
				PreparedStatement ps = connection.prepareStatement(QUERRY_FOR_GET_ID)) {
			ps.setString(1, user.getLogin());
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				id = res.getInt(USER_ID);
			}

		} catch (SQLException e) {
			throw new DaoException("SQL Exception", e);
		} catch (ConnectionPoolException e) {
			throw new DaoException("SQL connection Exception", e);
		}
		return id;
	}

}