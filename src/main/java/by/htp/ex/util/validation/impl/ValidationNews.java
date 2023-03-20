package by.htp.ex.util.validation.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.util.validation.ValidationBuilder;

public class ValidationNews {

	private List<String> errorsList;

	private ValidationNews(ValidBuilder valBuilder) {

		errorsList = valBuilder.errorsList;
	}

	public List<String> getErrorsList() {
		return errorsList;
	}

	public void setErrorsList(List<String> errorsList) {
		this.errorsList = errorsList;
	}

	public static class ValidBuilder implements ValidationBuilder<ValidationNews> {

		private static final String MESSAGE_TITLE = "incorrect title";
		private static final String MESSAGE_BRIEF = "incorrect brief";
		private static final String MESSAGE_CONTENT = "incorrect content";
		private static final String MESSAGE_DATE = "incorrect date";

		private List<String> errorsList = new ArrayList<String>();

		public ValidBuilder checkTitle(String title) {
			if (title == null || title.isEmpty() || title.length() > 45) {
				errorsList.add(MESSAGE_TITLE);
			}
			return this;
		}

		public ValidBuilder checkBrief(String brief) {
			if (brief == null || brief.isEmpty() || brief.length() > 200) {
				errorsList.add(MESSAGE_BRIEF);
			}
			return this;
		}

		public ValidBuilder checkContent(String content) {
			if (content == null || content.isEmpty() || content.length() > 800) {
				errorsList.add(MESSAGE_CONTENT);
			}
			return this;
		}

		private static final String DATE_FORMAT = "yyyy-MM-dd";

		public ValidBuilder checkDate(String date) {

			Calendar calendar = new GregorianCalendar();
			SimpleDateFormat sd = new SimpleDateFormat(DATE_FORMAT);
			sd.setLenient(false);

			try {
				calendar.setTime(sd.parse(date));
			} catch (ParseException e) {
				errorsList.add(MESSAGE_DATE);
			}
			return this;
		}

		public ValidBuilder check(News news) {

			return this.checkTitle(news.getTitle()).checkBrief(news.getBriefNews()).checkDate(news.getNewsDate())
					.checkContent(news.getContent());
		}

		@Override
		public ValidationNews isPermit() {
			return new ValidationNews(this);
		}

	}

}
