package bh.gov.cio.gbs.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Timestamp;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class Board.
 */
/**
 * @author csdvdnm
 *
 */
public class Subscriber extends BaseTo implements FillFromResultSet{
	
	private Long subscriberId;
	
	private Long notificationId;

	private String email;
			
	
	

	public Long getSubscriberId() {
		return subscriberId;
	}




	public void setSubscriberId(Long subscriberId) {
		this.subscriberId = subscriberId;
	}




	public Long getNotificationId() {
		return notificationId;
	}




	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	/* (non-Javadoc)
	 * @see bh.gov.cio.gbs.model.FillFromResultSet#fillFromResultSet(java.sql.ResultSet)
	 */
	public void fillFromResultSet(ResultSet resultSet) throws Throwable {
		// TODO Auto-generated method stub
		ResultSetMetaData metaData = resultSet.getMetaData();
		for (int i = 1; i <= metaData.getColumnCount(); ++i) {
			String columnLabel = metaData.getColumnLabel(i);
			if (columnLabel.equalsIgnoreCase("SUBSCRIBER_ID")) {
				this.setSubscriberId(resultSet.getLong("SUBSCRIBER_ID"));
			} else if (columnLabel.equalsIgnoreCase("EMAIL")) {
				this.setEmail(resultSet.getString("EMAIL"));
			} else if (columnLabel.equalsIgnoreCase("NOTIFICATION_ID")) {
				this.setNotificationId(resultSet.getLong("NOTIFICATION_ID"));
			}
		}
	}
	
	
}
