package by.htp.ex.service.impl;

import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.NewsDAOException;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.util.validation.ValidationException;
import by.htp.ex.util.validation.impl.ValidationNews;

public class NewsServiceImpl implements INewsService {

	private final INewsDAO newsDAO = DaoProvider.getInstance().getNewsDAO();

	@Override
	public void save(News newNews) throws ServiceException, ValidationException {

		ValidationNews.ValidBuilder validBuilder = new ValidationNews.ValidBuilder();
		ValidationNews validNews = validBuilder.check(newNews).isPermit();
		if (!validNews.getErrorsList().isEmpty()) {
			throw new ValidationException(validNews.getErrorsList().toString());
		}

		try {
			newsDAO.addNews(newNews);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public void update(News editNews) throws ServiceException, ValidationException {
		ValidationNews.ValidBuilder test = new ValidationNews.ValidBuilder();
		ValidationNews valid = test.check(editNews).isPermit();
		if (!valid.getErrorsList().isEmpty()) {
			throw new ValidationException(valid.getErrorsList().toString());
		}

		try {
			newsDAO.updateNews(editNews);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public List<News> latestList(int count) throws ServiceException {

		try {

			return newsDAO.getLatestsList(count);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<News> allList() throws ServiceException {
		try {
			return newsDAO.getList();
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public News findById(int id) throws ServiceException {
		try {
			return newsDAO.fetchById(id);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(String[] idNewses) throws ServiceException {
		try {
			newsDAO.deleteNewses(idNewses);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}

	}

}
