package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToRegistrationPage implements Command {

	private static final String JSP_ATRIBUTE_USER = "user";
	private static final String JSP_ATRIBUTE_PRESENTATION = "presentation";
	private static final String JSP_ATRIBUTE_REGISTRATION = "registration";
	private static final String JSP_SESSION_NOT_ACTIVE = "not active";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getSession(true).setAttribute(JSP_ATRIBUTE_USER, JSP_SESSION_NOT_ACTIVE);
		request.setAttribute(JSP_ATRIBUTE_PRESENTATION, JSP_ATRIBUTE_REGISTRATION);
		request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);

	}

}
