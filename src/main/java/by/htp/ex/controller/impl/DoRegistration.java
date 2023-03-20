package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.bean.UserInfo;
import by.htp.ex.controller.Command;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.util.validation.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoRegistration implements Command {

	private final IUserService service = ServiceProvider.getInstance().getUserService();

	private static final String JSP_ATRIBUTE_USER = "user";
	private static final String JSP_ATRIBUTE_NEWUSER = "newUser";
	private static final String JSP_ATRIBUTE_PRESENTATION = "presentation";
	private static final String JSP_ATRIBUTE_REGISTRATION = "registration";
	private static final String JSP_ATRIBUTE_ERROR = "errorMessage";
	private static final String JSP_ATRIBUTE_MESSAGE_REG = "messageUserRegistration";
	private static final String JSP_SESSION_NOT_ACTIVE = "not active";
	private static final String JSP_SESSION_GOOD_REG = "RegistrationStatusGood";
	private static final String JSP_SESSION_BAD_REG = "RegistrationStatusBad";
	private static final String JSP_USER_LOGIN_PARAM = "login";
	private static final String JSP_USER_PASSWORD_PARAM = "password";
	private static final String JSP_USER_EMAIL_PARAM = "email";
	private static final String JSP_USER_NAME_PARAM = "name";
	private static final String JSP_USER_SURNAME_PARAM = "surname";
	private static final String JSP_USER_BIRTHDAY_PARAM = "birthday";
	private static final String JSP_USER_GENDER_PARAM = "gender";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		String login;
		String password;
		String email;
		String name;
		String surname;
		String birthday;
		String gender;

		login = request.getParameter(JSP_USER_LOGIN_PARAM);
		password = request.getParameter(JSP_USER_PASSWORD_PARAM);
		email = request.getParameter(JSP_USER_EMAIL_PARAM);
		name = request.getParameter(JSP_USER_NAME_PARAM);
		surname = request.getParameter(JSP_USER_SURNAME_PARAM);
		birthday = request.getParameter(JSP_USER_BIRTHDAY_PARAM);
		gender = request.getParameter(JSP_USER_GENDER_PARAM);

		UserInfo newUser = new UserInfo(login, password, email, name, surname, birthday, gender);

		try {
			if (service.registration(newUser)) {
				request.getSession(true).setAttribute(JSP_ATRIBUTE_USER, JSP_SESSION_NOT_ACTIVE);
				session.setAttribute(JSP_SESSION_GOOD_REG, "successful registration");
				response.sendRedirect("controller?command=go_to_news_list");
			} else {

				request.setAttribute(JSP_ATRIBUTE_PRESENTATION, JSP_ATRIBUTE_REGISTRATION);
				request.setAttribute(JSP_SESSION_BAD_REG, "unsuccessful registration");
				request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
			}
		} catch (ServiceException e) {
			session.setAttribute(JSP_ATRIBUTE_ERROR, "can't sign up, please try again later ");
			response.sendRedirect("controller?command=go_to_error_page");
		} catch (ValidationException e) {
			request.setAttribute(JSP_ATRIBUTE_NEWUSER, newUser);
			request.setAttribute(JSP_ATRIBUTE_PRESENTATION, JSP_ATRIBUTE_REGISTRATION);
			request.setAttribute(JSP_ATRIBUTE_MESSAGE_REG, e.getMessage());
			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		}

	}

}
