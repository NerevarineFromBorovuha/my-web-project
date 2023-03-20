package by.htp.ex.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final CommandProvider provider = new CommandProvider();
	private static final String stringCommand = "command";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		executeCommand(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		executeCommand(request, response);

	}

	private void executeCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String commandName = request.getParameter(stringCommand);
			Command command = provider.getCommand(commandName);
			command.execute(request, response);

		} catch (Exception e) {
			request.getRequestDispatcher("WEB-INF/pages/tiles/error.jsp").forward(request, response);

		}

	}

}
