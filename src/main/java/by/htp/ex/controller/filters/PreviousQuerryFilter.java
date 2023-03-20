package by.htp.ex.controller.filters;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.HttpMethod;

public class PreviousQuerryFilter implements Filter {

	private static final String PREVIOUS_REQUEST_NAME = "previous request name";
	private static final String PREVIOUS_REQUEST_VALUE = "previous request value";
	private static final String CURRENT_REQUEST_NAME = "current request name";
	private static final String CURRENT_REQUEST_VALUE = "current request value";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		if (req.getMethod().equals(HttpMethod.GET)) {
			HttpSession session = req.getSession();

			Map<String, String[]> requestMap = req.getParameterMap();

			if (!requestMap.isEmpty()) {

				session.setAttribute(PREVIOUS_REQUEST_NAME, session.getAttribute(CURRENT_REQUEST_NAME));
				session.setAttribute(PREVIOUS_REQUEST_VALUE, session.getAttribute(CURRENT_REQUEST_VALUE));

				session.setAttribute(CURRENT_REQUEST_NAME, req.getRequestURL().toString());
				session.setAttribute(CURRENT_REQUEST_VALUE, requestMap);

				if (session.getAttribute(PREVIOUS_REQUEST_NAME) == null) {
					session.setAttribute(PREVIOUS_REQUEST_NAME, session.getAttribute(CURRENT_REQUEST_NAME));
					session.setAttribute(PREVIOUS_REQUEST_VALUE, session.getAttribute(CURRENT_REQUEST_VALUE));
				}
			}

		}

		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {
		Filter.super.destroy();
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
	}

}
