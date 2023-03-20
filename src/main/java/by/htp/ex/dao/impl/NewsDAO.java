package by.htp.ex.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.NewsDAOException;
import by.htp.ex.dao.db_connection_pool.ConnectionPool;
import by.htp.ex.dao.db_connection_pool.ConnectionPoolException;

public class NewsDAO implements INewsDAO {

	private static final String NEWS_ID_PARAM = "id";
	private static final String NEWS_TITLE_PARAM = "title";
	private static final String NEWS_BRIEF_PARAM = "brief";
	private static final String NEWS_CONTENT_PARAM = "content";
	private static final String NEWS_DATE_PUBLICATION_PARAM = "date_publication";

	private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

	private final static String QUERY_FOR_LATEST_NEWS = "SELECT * FROM news WHERE news_status_id=1  ORDER BY date_publication DESC LIMIT ?";

	@Override
	public List<News> getLatestsList(int count) throws NewsDAOException {
		List<News> result = new ArrayList<News>();

		try (Connection connection = CONNECTION_POOL.takeConnection();
				PreparedStatement ps = connection.prepareStatement(QUERY_FOR_LATEST_NEWS)) {

			ps.setInt(1, count);
			ResultSet res = ps.executeQuery();

			while (res.next()) {
				result.add(new News(res.getInt(NEWS_ID_PARAM), res.getString(NEWS_TITLE_PARAM),
						res.getString(NEWS_BRIEF_PARAM), res.getString(NEWS_CONTENT_PARAM),
						res.getString(NEWS_DATE_PUBLICATION_PARAM)));
			}

		} catch (SQLException e) {
			throw new NewsDAOException("SQL Exception", e);
		} catch (ConnectionPoolException e) {
			throw new NewsDAOException("SQL connection Exception", e);
		}
		if (result.isEmpty()) {
			return null;
		}

		return result;
	}

	private final static String QUERY_FOR_ALL_NEWS = "SELECT * FROM news WHERE news_status_id=1 ORDER BY date_publication DESC";

	@Override
	public List<News> getList() throws NewsDAOException {
		List<News> result = new ArrayList<News>();

		try (Connection connection = CONNECTION_POOL.takeConnection();
				PreparedStatement ps = connection.prepareStatement(QUERY_FOR_ALL_NEWS)) {

			ResultSet res = ps.executeQuery();

			while (res.next()) {
				result.add(new News(res.getInt(NEWS_ID_PARAM), res.getString(NEWS_TITLE_PARAM),
						res.getString(NEWS_BRIEF_PARAM), res.getString(NEWS_CONTENT_PARAM),
						res.getString(NEWS_DATE_PUBLICATION_PARAM)));
			}

		} catch (SQLException e) {
			throw new NewsDAOException("SQL Exception", e);
		} catch (ConnectionPoolException e) {
			throw new NewsDAOException("SQL connection Exception", e);
		}

		if (result.isEmpty()) {
			return null;
		}

		return result;
	}

	private final static String QUERY_FOR_VIEW_NEWS = "SELECT * FROM news WHERE id=?";

	@Override
	public News fetchById(int id) throws NewsDAOException {

		News news = null;

		try (Connection connection = CONNECTION_POOL.takeConnection();
				PreparedStatement ps = connection.prepareStatement(QUERY_FOR_VIEW_NEWS)) {

			ps.setInt(1, id);
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				news = new News(res.getInt(NEWS_ID_PARAM), res.getString(NEWS_TITLE_PARAM),
						res.getString(NEWS_BRIEF_PARAM), res.getString(NEWS_CONTENT_PARAM),
						res.getString(NEWS_DATE_PUBLICATION_PARAM));
			}

		} catch (SQLException e) {
			throw new NewsDAOException("SQL Exception", e);
		} catch (ConnectionPoolException e) {
			throw new NewsDAOException("SQL connection Exception", e);
		}

		return news;
	}

	private final static String QUERY_FOR_ADD_NEWS = "INSERT INTO news (title,brief,content,date_publication,users_id,news_status_id) VALUES(?,?,?,?,?,?)";

	@Override
	public void addNews(News news) throws NewsDAOException {

		try (Connection connection = CONNECTION_POOL.takeConnection();
				PreparedStatement ps = connection.prepareStatement(QUERY_FOR_ADD_NEWS)) {

			ps.setString(1, news.getTitle());
			ps.setString(2, news.getBriefNews());
			ps.setString(3, news.getContent());
			ps.setString(4, news.getNewsDate());
			ps.setInt(5, 16); 
			ps.setInt(6, 1); 
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new NewsDAOException("SQL Exception", e);
		} catch (ConnectionPoolException e) {
			throw new NewsDAOException("SQL connection Exception", e);
		}

	}

	private final static String QUERY_FOR_UPDATE_NEWS = "UPDATE news SET title=?,brief=?,content=?,date_publication=?,users_id=?,news_status_id=? WHERE id=?";

	@Override
	public void updateNews(News news) throws NewsDAOException {

		try (Connection connection = CONNECTION_POOL.takeConnection();
				PreparedStatement ps = connection.prepareStatement(QUERY_FOR_UPDATE_NEWS)) {

			ps.setString(1, news.getTitle());
			ps.setString(2, news.getBriefNews());
			ps.setString(3, news.getContent());
			ps.setString(4, news.getNewsDate());
			ps.setInt(5, 16); 
			ps.setInt(6, 1);
			ps.setInt(7, news.getIdNews());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new NewsDAOException("SQL Exception", e);
		} catch (ConnectionPoolException e) {
			throw new NewsDAOException("SQL connection Exception", e);
		}

	}

	private final static String QUERY_FOR_DELETE_NEWSES = "UPDATE news SET news_status_id=2 WHERE id=?";

	@Override
	public void deleteNewses(String[] idNewses) throws NewsDAOException {
		try (Connection connection = CONNECTION_POOL.takeConnection();
				PreparedStatement ps = connection.prepareStatement(QUERY_FOR_DELETE_NEWSES)) {

			for (int i = 0; i < idNewses.length; i++) {
				ps.setString(1, idNewses[i]);
				ps.executeUpdate();
			}

		} catch (SQLException e) {
			throw new NewsDAOException("SQL Exception", e);
		} catch (ConnectionPoolException e) {
			throw new NewsDAOException("SQL connection Exception", e);
		}

	}

}
