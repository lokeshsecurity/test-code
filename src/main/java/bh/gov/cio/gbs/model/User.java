package bh.gov.cio.gbs.model;

import java.sql.ResultSet;

public class User extends BaseTo implements FillFromResultSet {

	private Integer id;

	private String username;

	private String email;

	private String password;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void fillFromResultSet(ResultSet resultSet) throws Throwable {

	}

}
