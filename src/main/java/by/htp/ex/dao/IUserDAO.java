package by.htp.ex.dao;

import by.htp.ex.bean.UserInfo;

public interface IUserDAO {
	
	boolean authorization(String login, String password) throws DaoException;
	boolean registration(UserInfo user) throws DaoException;
	boolean checkLogin(UserInfo user) throws DaoException;
	int findId(UserInfo user) throws DaoException;
	String getRole(String login, String password) throws DaoException;

}
