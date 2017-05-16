package bh.gov.cio.gbs.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

// TODO: Auto-generated Javadoc
/**
 * The Class NotificationRepeat.
 */
public class NotificationRepeat implements FillFromResultSet {

	private Integer childNotificationPeriodId;

	private Integer parentNotificationPeriodId;
	
	private String childNotificationPeriodName;

	private String childNotificationPeriodDay;

	
	public void fillFromResultSet(ResultSet resultSet) throws Throwable {
		
		ResultSetMetaData metaData = resultSet.getMetaData();
		for (int i = 1; i <= metaData.getColumnCount(); ++i) {
			String columnLabel = metaData.getColumnLabel(i);
			if (columnLabel.equalsIgnoreCase("PARENT_NOTIFICATION_PERIOD_ID")) {
				this.setParentNotificationPeriodId(resultSet
						.getInt("PARENT_NOTIFICATION_PERIOD_ID"));
			} else if (columnLabel.equalsIgnoreCase("CHILD_NOTIFICATION_PERIOD_ID")) {
				this.setChildNotificationPeriodId(resultSet
						.getInt("CHILD_NOTIFICATION_PERIOD_ID"));
			} else if (columnLabel.equalsIgnoreCase("CHILD_NOTIFICATION_PERIOD_NAME")) {
				this.setChildNotificationPeriodName(resultSet
						.getString("CHILD_NOTIFICATION_PERIOD_NAME"));
			} else if (columnLabel.equalsIgnoreCase("CHILD_NOTIFICATION_PERIOD_DAY")) {
					this.setChildNotificationPeriodDay(resultSet
							.getString("CHILD_NOTIFICATION_PERIOD_DAY"));
			} 
		}
	}


	public Integer getChildNotificationPeriodId() {
		return childNotificationPeriodId;
	}


	public void setChildNotificationPeriodId(Integer childNotificationPeriodId) {
		this.childNotificationPeriodId = childNotificationPeriodId;
	}


	public Integer getParentNotificationPeriodId() {
		return parentNotificationPeriodId;
	}


	public void setParentNotificationPeriodId(Integer parentNotificationPeriodId) {
		this.parentNotificationPeriodId = parentNotificationPeriodId;
	}


	public String getChildNotificationPeriodName() {
		return childNotificationPeriodName;
	}


	public void setChildNotificationPeriodName(String childNotificationPeriodName) {
		this.childNotificationPeriodName = childNotificationPeriodName;
	}


	public String getChildNotificationPeriodDay() {
		return childNotificationPeriodDay;
	}


	public void setChildNotificationPeriodDay(String childNotificationPeriodDay) {
		this.childNotificationPeriodDay = childNotificationPeriodDay;
	}


	@Override
	public String toString() {
		return "NotificationRepeat [childNotificationPeriodId="
				+ childNotificationPeriodId + ", parentNotificationPeriodId="
				+ parentNotificationPeriodId + ", childNotificationPeriodName="
				+ childNotificationPeriodName + ", childNotificationPeriodDay="
				+ childNotificationPeriodDay + "]";
	}

	

}
