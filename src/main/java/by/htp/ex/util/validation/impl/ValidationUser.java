package by.htp.ex.util.validation.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import by.htp.ex.bean.UserInfo;
import by.htp.ex.util.validation.ValidationBuilder;

public class ValidationUser {

	private List<String> errorsList;

	private ValidationUser(ValidBuilder valBuilder) {

		errorsList = valBuilder.errorsList;
	}

	public List<String> getErrorsList() {
		return errorsList;
	}

	public void setErrorsList(List<String> errorsList) {
		this.errorsList = errorsList;
	}

	public static class ValidBuilder implements ValidationBuilder<ValidationUser> {

		private List<String> errorsList = new ArrayList<String>();

		private static final String REG_EXPR_FOR_LOGIN = "^[^0-9][a-zA-Z0-9][^\\s]{3,20}";
		private static final String MESSAGE_LOGIN = "incorrect login";

		public ValidBuilder checkLogin(String login) {
			if (login == null || !login.matches(REG_EXPR_FOR_LOGIN)) {
				errorsList.add(MESSAGE_LOGIN);
			}
			return this;
		}

		private static final String REG_EXPR_FOR_PASSWORD = "[^\\s]{3,20}";
		private static final String MESSAGE_PASSWORD = "incorrect password";

		public ValidBuilder checkPassword(String password) {
			if (password == null || !password.matches(REG_EXPR_FOR_PASSWORD)) {
				errorsList.add(MESSAGE_PASSWORD);
			}
			return this;
		}

		private static final String REG_EXPR_FOR_MAIL = "^[a-zA-Z]+@[a-zA-Z0-9][^\s]{3,20}";
		private static final String MESSAGE_MAIL = "incorrect mail";

		public ValidBuilder checkMail(String mail) {
			if (mail == null || !mail.matches(REG_EXPR_FOR_MAIL)) {
				errorsList.add(MESSAGE_MAIL);
			}
			return this;
		}

		private static final String REG_EXPR_FOR_NAME = "[a-zA-Z]{1,20}";
		private static final String MESSAGE_NAME = "incorrect name";

		public ValidBuilder checkName(String name) {
			if (name == null || !name.matches(REG_EXPR_FOR_NAME)) {
				errorsList.add(MESSAGE_NAME);
			}
			return this;
		}

		private static final String REG_EXPR_FOR_SURNAME = "[a-zA-Z]{1,20}";
		private static final String MESSAGE_SURNAME = "incorrect surname";

		public ValidBuilder checkSurname(String surname) {
			if (surname == null || !surname.matches(REG_EXPR_FOR_SURNAME)) {
				errorsList.add(MESSAGE_SURNAME);
			}
			return this;
		}

		private static final String GENDER_MALE = "male";
		private static final String GENDER_FEMALE = "female";
		private static final String GENDER_TRANSFORMER = "transformer";
		private static final String MESSAGE_GENDER = "incorrect gender";

		public ValidBuilder checkGender(String gender) {
			if (gender == null) {
				errorsList.add(MESSAGE_GENDER);
			}
			switch (gender) {
			case GENDER_MALE:
				break;
			case GENDER_FEMALE:
				break;
			case GENDER_TRANSFORMER:
				break;
			default:
				errorsList.add(MESSAGE_GENDER);
			}
			return this;
		}

		private static final String DATE_FORMAT = "yyyy-MM-dd";
		private static final String MESSAGE_BIRTHDAY = "incorrect birthday";

		public ValidBuilder checkBirthday(String birthday) {
			Calendar calendar = new GregorianCalendar();
			SimpleDateFormat sd = new SimpleDateFormat(DATE_FORMAT);
			sd.setLenient(false);
			try {
				calendar.setTime(sd.parse(birthday));
			} catch (ParseException e) {
				errorsList.add(MESSAGE_BIRTHDAY);
			}
			return this;
		}

		public ValidBuilder checkSignIn(String login, String password) {

			return this.checkLogin(login).checkPassword(password);
		}

		public ValidBuilder checkRegistration(UserInfo user) {

			return this.checkLogin(user.getLogin()).checkPassword(user.getPassword()).checkMail(user.getEmail())
					.checkName(user.getName()).checkSurname(user.getSurname()).checkBirthday(user.getBirthday())
					.checkGender(user.getGender());
		}

		@Override
		public ValidationUser isPermit() {
			return new ValidationUser(this);
		}

	}

}
