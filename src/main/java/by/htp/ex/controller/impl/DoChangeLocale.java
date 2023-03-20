package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.Map;

import by.htp.ex.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoChangeLocale implements Command {

	private static final String JSP_ATRIBUTE_ERROR = "errorMessage";
	private static final String JSP_SESSION_LOCAL = "local";
	private static final String JSP_SESSION_PREVIOUS_NAME = "previous request name";
	private static final String JSP_SESSION_PREVIOUS_VALUE = "previous request value";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.setAttribute(JSP_SESSION_LOCAL, request.getParameter(JSP_SESSION_LOCAL));

		try {

			String previousRequestName = (String) session.getAttribute(JSP_SESSION_PREVIOUS_NAME);
			Map<String, String[]> requestMap = (Map<String, String[]>) session.getAttribute(JSP_SESSION_PREVIOUS_VALUE);
			StringBuilder previousRequest = new StringBuilder(previousRequestName);
			previousRequest.append("?");

			for (var mapParam : requestMap.entrySet()) {
				for (var param : mapParam.getValue()) {
					previousRequest.append(mapParam.getKey()).append("=").append(param).append("&");
				}
			}

			previousRequest.deleteCharAt(previousRequest.length() - 1);
			String finalPequest = previousRequest.toString();

			response.sendRedirect(finalPequest);

		} catch (Exception e) {
			session.setAttribute(JSP_ATRIBUTE_ERROR, "can't get the previous request");
			response.sendRedirect("controller?command=go_to_error_page");
		}

	}

}
