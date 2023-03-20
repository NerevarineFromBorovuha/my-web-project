package by.htp.ex.bean;

import java.util.Objects;

public class UserInfo {

	private String login;
	private String password;
	private String email;
	private String name;
	private String surname;
	private String birthday;
	private String gender;
	private String dateRegistration;
	private int userId;

	public UserInfo() {

	}
	
	

	public UserInfo(String login, String password, String email, String name, String surname, String birthday,
			String gender, String dateRegistration, int userId) {
		super();
		this.login = login;
		this.password = password;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
		this.gender = gender;
		this.dateRegistration = dateRegistration;
		this.userId = userId;
	}
	
	public UserInfo(String login, String password, String email, String name, String surname, String birthday,
			String gender) {
		super();
		this.login = login;
		this.password = password;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
		this.gender = gender;
		this.dateRegistration = null;
		this.userId = 0;
	}



	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateRegistration() {
		return dateRegistration;
	}

	public void setDateRegistration(String dateRegistration) {
		this.dateRegistration = dateRegistration;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthday, dateRegistration, email, gender, login, name, password, surname, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserInfo other = (UserInfo) obj;
		return Objects.equals(birthday, other.birthday) && Objects.equals(dateRegistration, other.dateRegistration)
				&& Objects.equals(email, other.email) && Objects.equals(gender, other.gender)
				&& Objects.equals(login, other.login) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && Objects.equals(surname, other.surname)
				&& userId == other.userId;
	}

	@Override
	public String toString() {
		return "UserInfo [login=" + login + ", password=" + password + ", email=" + email + ", name=" + name
				+ ", surname=" + surname + ", birthday=" + birthday + ", gender=" + gender + ", dateRegistration="
				+ dateRegistration + ", userId=" + userId + "]";
	}

	

}
