package bh.gov.cio.gbs.systemevent;

import org.springframework.context.ApplicationEvent;

import bh.gov.cio.gbs.model.NotificationHistory;

// TODO: Auto-generated Javadoc
/**
 * The Class NotificationEvent.
 */
public class NotificationEvent extends ApplicationEvent {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7798824210031342756L;

	/**
	 * Instantiates a new notification event.
	 *
	 * @param notificationHistory the notification history
	 */
	public NotificationEvent(NotificationHistory notificationHistory) {
		super(notificationHistory);
	}
	
	/**
	 * Gets the notification history.
	 *
	 * @return the notification history
	 */
	public NotificationHistory getNotificationHistory() {
        return (NotificationHistory) getSource();
    }

	

}
