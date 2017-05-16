package bh.gov.cio.gbs.systemevent;

import org.springframework.context.ApplicationEvent;
import bh.gov.cio.gbs.model.UserLoginHistory;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginEvent.
 */
public class LoginEvent extends ApplicationEvent {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6654973253277741509L;

	/**
	 * Instantiates a new login event.
	 *
	 * @param user the user
	 */
	public LoginEvent(UserLoginHistory userLoginHistory) {
		super(userLoginHistory);
	}
	
	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public UserLoginHistory getUserLoginHistory() {
        return (UserLoginHistory) getSource();
    }

	

}
