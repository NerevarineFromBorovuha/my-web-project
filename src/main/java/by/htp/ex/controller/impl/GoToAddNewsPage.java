package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToAddNewsPage implements Command {

	private static final String JSP_ATRIBUTE_PRESENTATION = "presentation";
	private static final String JSP_ATRIBUTE_ADD_NEWS = "addNews";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(JSP_ATRIBUTE_PRESENTATION, JSP_ATRIBUTE_ADD_NEWS);
		request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);

	}

}
