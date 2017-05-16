package bh.gov.cio.gbs.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * The Class NotificationPeriod.
 */
public class NotificationPeriod extends BaseTo implements FillFromResultSet {

	/** The Notification period id. */
	private Integer notificationPeriodId;

	/** The Notification period name. */
	private String notificationPeriodName;

	/** The Notification period day. */
	private String notificationPeriodDay;

	/**
	 * Gets the notification period id.
	 *
	 * @return the notification period id
	 */
	public Integer getNotificationPeriodId() {
		return notificationPeriodId;
	}

	/**
	 * Sets the notification period id.
	 *
	 * @param notificationPeriodId the new notification period id
	 */
	public void setNotificationPeriodId(Integer notificationPeriodId) {
		this.notificationPeriodId = notificationPeriodId;
	}

	/**
	 * Gets the notification period name.
	 *
	 * @return the notification period name
	 */
	public String getNotificationPeriodName() {
		return notificationPeriodName;
	}

	/**
	 * Sets the notification period name.
	 *
	 * @param notificationPeriodName the new notification period name
	 */
	public void setNotificationPeriodName(String notificationPeriodName) {
		this.notificationPeriodName = notificationPeriodName;
	}

	/**
	 * Gets the notification period day.
	 *
	 * @return the notification period day
	 */
	public String getNotificationPeriodDay() {
		return notificationPeriodDay;
	}

	/**
	 * Sets the notification period day.
	 *
	 * @param notificationPeriodDay the new notification period day
	 */
	public void setNotificationPeriodDay(String notificationPeriodDay) {
		this.notificationPeriodDay = notificationPeriodDay;
	}

	/* (non-Javadoc)
	 * @see bh.gov.cio.gbs.model.FillFromResultSet#fillFromResultSet(java.sql.ResultSet)
	 */
	public void fillFromResultSet(ResultSet resultSet) throws Throwable {

		ResultSetMetaData metaData = resultSet.getMetaData();
		for (int i = 1; i <= metaData.getColumnCount(); ++i) {
			String columnLabel = metaData.getColumnLabel(i);
			if (columnLabel.equalsIgnoreCase("NOTIFICATION_PERIOD_ID")) {
				this.setNotificationPeriodId(resultSet
						.getInt("NOTIFICATION_PERIOD_ID"));
			} else if (columnLabel.equalsIgnoreCase("NOTIFICATION_PERIOD_NAME")) {
				this.setNotificationPeriodName(resultSet
						.getString("NOTIFICATION_PERIOD_NAME"));
			} else if (columnLabel.equalsIgnoreCase("NOTIFICATION_PERIOD_DAY")) {
				this.setNotificationPeriodDay(resultSet
						.getString("NOTIFICATION_PERIOD_DAY"));
			} else if (columnLabel.equalsIgnoreCase("CREATED_BY")) {
				this.setCreatedBy(resultSet.getInt("CREATED_BY"));
			} else if (columnLabel.equalsIgnoreCase("CREATED_ON")) {
				this.setCreatedOn(resultSet.getDate("CREATED_ON"));
			} else if (columnLabel.equalsIgnoreCase("UPDATED_BY")) {
				this.setUpdatedBy(resultSet.getInt("UPDATED_BY"));
			} else if (columnLabel.equalsIgnoreCase("UPDATED_ON")) {
				this.setUpdatedOn(resultSet.getDate("UPDATED_ON"));
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NotificationPeriod [notificationPeriodId="
				+ notificationPeriodId + ", notificationPeriodName="
				+ notificationPeriodName + ", notificationPeriodDay="
				+ notificationPeriodDay + "]";
	}
	
	
	

}
