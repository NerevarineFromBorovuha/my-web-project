package by.htp.ex.controller.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import by.htp.ex.controller.CommandName;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SecurityFilter implements Filter {

	private final static List<CommandName> commandForGuest = new ArrayList<CommandName>();

	static {
		commandForGuest.add(CommandName.GO_TO_BASE_PAGE);
		commandForGuest.add(CommandName.DO_REGISTRATION);
		commandForGuest.add(CommandName.DO_SIGN_IN);
		commandForGuest.add(CommandName.GO_TO_NEWS_LIST);
		commandForGuest.add(CommandName.GO_TO_LATEST_NEWS_LIST);
		commandForGuest.add(CommandName.GO_TO_ERROR_PAGE);
		commandForGuest.add(CommandName.GO_TO_REGISTRATION_PAGE);
		commandForGuest.add(CommandName.DO_CHANGE_LOCALE);
		commandForGuest.add(CommandName.DO_SIGN_OUT);
	}

	private final static List<CommandName> commandForUser = new ArrayList<CommandName>();

	static {
		commandForUser.add(CommandName.GO_TO_BASE_PAGE);
		commandForUser.add(CommandName.DO_SIGN_OUT);
		commandForUser.add(CommandName.GO_TO_VIEW_NEWS);
		commandForUser.add(CommandName.GO_TO_NEWS_LIST);
		commandForUser.add(CommandName.GO_TO_LATEST_NEWS_LIST);
		commandForUser.add(CommandName.GO_TO_ERROR_PAGE);
		commandForUser.add(CommandName.DO_CHANGE_LOCALE);
	}

	private final static List<CommandName> commandForAdmin = new ArrayList<CommandName>();

	static {
		commandForAdmin.add(CommandName.GO_TO_BASE_PAGE);
		commandForAdmin.add(CommandName.DO_SIGN_OUT);
		commandForAdmin.add(CommandName.GO_TO_NEWS_LIST);
		commandForAdmin.add(CommandName.GO_TO_VIEW_NEWS);
		commandForAdmin.add(CommandName.GO_TO_LATEST_NEWS_LIST);
		commandForAdmin.add(CommandName.GO_TO_ERROR_PAGE);
		commandForAdmin.add(CommandName.DO_ADD_NEWS);
		commandForAdmin.add(CommandName.GO_TO_ADD_NEWS_PAGE);
		commandForAdmin.add(CommandName.GO_TO_EDIT_NEWS);
		commandForAdmin.add(CommandName.DO_EDIT_NEWS);
		commandForAdmin.add(CommandName.DO_DELETE_NEWS);
		commandForAdmin.add(CommandName.DO_CHANGE_LOCALE);

	}

	private final static String ROLE = "role";
	private final static String GUEST_ROLE = "guest";
	private final static String USER_ROLE = "user";
	private final static String ADMIN_ROLE = "admin";
	private final static String ERROR_MESSAGE = "errorMessage";
	private final static String COMMAND = "command";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		if (session.getAttribute(ROLE) == null) {
			session.setAttribute(ROLE, GUEST_ROLE);
		}
		if(req.getParameter(COMMAND)==null) {
			ServletContext servletContext = request.getServletContext();
			RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/index.jsp");
			requestDispatcher.forward(request, response);
			return;
		}
		

		boolean checkPermission = permission(session.getAttribute(ROLE).toString(), req.getParameter(COMMAND));
		if (!checkPermission) {
			session.setAttribute(ERROR_MESSAGE, "you don't have permission for this action");
			ServletContext servletContext = request.getServletContext();
			RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/WEB-INF/pages/tiles/error.jsp");
			requestDispatcher.forward(request, response);

		} else {
			chain.doFilter(request, response);
		}

	}

	private static boolean permission(String role, String command) {

		switch (role) {

		case GUEST_ROLE:
			for (CommandName s : commandForGuest) {
				if (command.toUpperCase().equals(s.toString())) {
					return true;
				}
			}

			return false;

		case USER_ROLE:
			for (CommandName s : commandForUser) {
				if (command.toUpperCase().equals(s.toString())) {
					return true;
				}
			}

			return false;

		case ADMIN_ROLE:
			for (CommandName s : commandForAdmin) {
				if (command.toUpperCase().equals(s.toString())) {
					return true;
				}
			}

			return false;
		default:
			return false;
		}

	}

}
