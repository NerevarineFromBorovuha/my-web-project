package by.htp.ex.service;

import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.util.validation.ValidationException;

public interface INewsService {
	void save(News newNews) throws ServiceException, ValidationException;

	void delete(String[] idNewses) throws ServiceException;

	void update(News editNews) throws ServiceException, ValidationException;

	List<News> latestList(int count) throws ServiceException;

	List<News> allList() throws ServiceException;

	News findById(int id) throws ServiceException;
}
