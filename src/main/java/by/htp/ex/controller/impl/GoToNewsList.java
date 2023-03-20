package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToNewsList implements Command {

	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

	private static final String JSP_ATRIBUTE_NEWS = "news";
	private static final String JSP_ATRIBUTE_PRESENTATION = "presentation";
	private static final String JSP_ATRIBUTE_ERROR = "errorMessage";
	private static final String JSP_ATRIBUTE_NEWSLIST = "newsList";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		List<News> newsList;
		try {
			newsList = newsService.allList();
			request.setAttribute(JSP_ATRIBUTE_NEWS, newsList);
			request.setAttribute(JSP_ATRIBUTE_PRESENTATION, JSP_ATRIBUTE_NEWSLIST);

			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		} catch (ServiceException e) {
			session.setAttribute(JSP_ATRIBUTE_ERROR, "can't go to news list, please try again later");
			response.sendRedirect("controller?command=go_to_error_page");
		}

	}

}
