package by.htp.ex.service;

import by.htp.ex.bean.UserInfo;
import by.htp.ex.util.validation.ValidationException;

public interface IUserService {
	
	String signIn(String login, String password) throws ServiceException, ValidationException;
	boolean registration(UserInfo user) throws ServiceException, ValidationException;

}
