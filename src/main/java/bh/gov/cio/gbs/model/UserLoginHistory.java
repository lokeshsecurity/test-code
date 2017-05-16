package bh.gov.cio.gbs.model;

import java.sql.ResultSet;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class UserLoginHistory.
 */
public class UserLoginHistory implements FillFromResultSet {

	/** The id. */
	private Integer id;

	/** The username. */
	private String username;
	
	/** The session id. */
	private String sessionId;
	
	/** The login status. */
	private LoginStatus loginStatus;
	
	/** The login date. */
	private Date loginDate;
	
	/** The logout date. */
	private Date logoutDate;
	
	private String action;
	
	

	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	
	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the session id.
	 *
	 * @return the session id
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * Sets the session id.
	 *
	 * @param sessionId the new session id
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * Gets the login status.
	 *
	 * @return the login status
	 */
	public LoginStatus getLoginStatus() {
		return loginStatus;
	}

	/**
	 * Sets the login status.
	 *
	 * @param loginStatus the new login status
	 */
	public void setLoginStatus(LoginStatus loginStatus) {
		this.loginStatus = loginStatus;
	}

	/**
	 * Gets the login date.
	 *
	 * @return the login date
	 */
	public Date getLoginDate() {
		return loginDate;
	}

	/**
	 * Sets the login date.
	 *
	 * @param loginDate the new login date
	 */
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	/**
	 * Gets the logout date.
	 *
	 * @return the logout date
	 */
	public Date getLogoutDate() {
		return logoutDate;
	}

	/**
	 * Sets the logout date.
	 *
	 * @param logoutDate the new logout date
	 */
	public void setLogoutDate(Date logoutDate) {
		this.logoutDate = logoutDate;
	}
	

	/* (non-Javadoc)
	 * @see bh.gov.cio.gbs.model.FillFromResultSet#fillFromResultSet(java.sql.ResultSet)
	 */
	public void fillFromResultSet(ResultSet resultSet) throws Throwable {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserLoginHistory [id=" + id + ", username=" + username
				+ ", sessionId=" + sessionId + ", loginStatus=" + loginStatus
				+ ", loginDate=" + loginDate + ", logoutDate=" + logoutDate
				+ "]";
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	
}
