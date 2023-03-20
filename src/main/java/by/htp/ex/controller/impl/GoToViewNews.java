package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToViewNews implements Command {

	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

	private static final String JSP_ATRIBUTE_NEWS = "news";
	private static final String JSP_ATRIBUTE_PRESENTATION = "presentation";
	private static final String JSP_ATRIBUTE_ERROR = "errorMessage";
	private static final String JSP_ATRIBUTE_VIEW = "viewNews";
	private static final String JSP_NEWS_ID_PARAM = "id";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		News news;

		String id;

		id = request.getParameter(JSP_NEWS_ID_PARAM);

		try {
			news = newsService.findById(Integer.parseInt(id));
			request.setAttribute(JSP_ATRIBUTE_NEWS, news);
			request.setAttribute(JSP_ATRIBUTE_PRESENTATION, JSP_ATRIBUTE_VIEW);

			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		} catch (ServiceException e) {
			session.setAttribute(JSP_ATRIBUTE_ERROR, "can't go to view news, please try again later");
			response.sendRedirect("controller?command=go_to_error_page");

		}

	}

}
