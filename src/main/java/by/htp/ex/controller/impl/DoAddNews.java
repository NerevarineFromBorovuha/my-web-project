package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.service.ServiceException;
import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.util.validation.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoAddNews implements Command {

	private final INewsService service = ServiceProvider.getInstance().getNewsService();

	private static final String JSP_NEWS_TITLE_PARAM = "news_title";
	private static final String JSP_NEWS_BRIEF_PARAM = "brief_news";
	private static final String JSP_NEWS_CONTENT_PARAM = "content_news";
	private static final String JSP_NEWS_DATE_PARAM = "date_news";
	private static final String JSP_ATRIBUTE_NEWS = "news";
	private static final String JSP_ATRIBUTE_PRESENTATION = "presentation";
	private static final String JSP_ATRIBUTE_ERROR = "errorMessage";
	private static final String JSP_ATRIBUTE_MESSAGE = "messageNewsValidation";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		String title;
		String brief;
		String content;
		String datePublication;

		title = request.getParameter(JSP_NEWS_TITLE_PARAM).trim();
		brief = request.getParameter(JSP_NEWS_BRIEF_PARAM).trim();
		content = request.getParameter(JSP_NEWS_CONTENT_PARAM).trim();
		datePublication = request.getParameter(JSP_NEWS_DATE_PARAM).trim();

		News newNews = new News(title, brief, content, datePublication);

		try {
			service.save(newNews);
			response.sendRedirect("controller?command=go_to_news_list");
		} catch (ValidationException e) {
			request.setAttribute(JSP_ATRIBUTE_PRESENTATION, "addNews");
			request.setAttribute(JSP_ATRIBUTE_NEWS, newNews);
			request.setAttribute(JSP_ATRIBUTE_MESSAGE, e.getMessage());
			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);

		} catch (ServiceException e) {
			session.setAttribute(JSP_ATRIBUTE_ERROR, "can't add news, please try again later ");
			response.sendRedirect("controller?command=go_to_error_page");
		}

	}

}
