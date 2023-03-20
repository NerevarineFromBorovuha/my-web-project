package by.htp.ex.service.impl;

import by.htp.ex.bean.UserInfo;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.service.ServiceException;
import by.htp.ex.util.validation.ValidationException;
import by.htp.ex.util.validation.impl.ValidationNews;
import by.htp.ex.util.validation.impl.ValidationUser;
import by.htp.ex.service.IUserService;

public class UserServiceImpl implements IUserService {

	private final IUserDAO userDAO = DaoProvider.getInstance().getUserDao();
	private static final String USER_ROLE_GUEST = "guest";


	@Override
	public String signIn(String login, String password) throws ServiceException, ValidationException {

		ValidationUser.ValidBuilder validBuilder = new ValidationUser.ValidBuilder();
		ValidationUser validUser = validBuilder.checkSignIn(login, password).isPermit();
		if (!validUser.getErrorsList().isEmpty()) {
			throw new ValidationException(validUser.getErrorsList().toString());
		}

		try {
			if (userDAO.authorization(login, password)) {
				return userDAO.getRole(login, password);
			} else {
				return USER_ROLE_GUEST;
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public boolean registration(UserInfo user) throws ServiceException, ValidationException {

		ValidationUser.ValidBuilder validBuilder = new ValidationUser.ValidBuilder();
		ValidationUser validUser = validBuilder.checkRegistration(user).isPermit();
		if (!validUser.getErrorsList().isEmpty()) {
			throw new ValidationException(validUser.getErrorsList().toString());
		}

		try {
			if (userDAO.registration(user)) {
				return true;
			} else {
				return false;
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

	}

}
