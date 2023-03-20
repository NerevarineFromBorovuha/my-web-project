package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.controller.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoDeleteNews implements Command {

	private final INewsService service = ServiceProvider.getInstance().getNewsService();

	private static final String JSP_NEWS_ID_PARAM = "id";
	private static final String JSP_ATRIBUTE_ERROR = "errorMessage";
	private static final String JSP_ATRIBUTE_DELETE = "DeleteNewsStatus";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		String[] idNews = request.getParameterValues(JSP_NEWS_ID_PARAM);

		if (idNews != null) {
			try {
				service.delete(idNews);
				response.sendRedirect("controller?command=go_to_news_list");
			} catch (ServiceException e) {
				session.setAttribute(JSP_ATRIBUTE_ERROR, "can't delete news, please try again later ");
				response.sendRedirect("controller?command=go_to_error_page");
			}
		} else {
			request.setAttribute(JSP_ATRIBUTE_DELETE, "choose news");
			request.getRequestDispatcher("controller?command=go_to_news_list").forward(request, response);
		}

	}

}
