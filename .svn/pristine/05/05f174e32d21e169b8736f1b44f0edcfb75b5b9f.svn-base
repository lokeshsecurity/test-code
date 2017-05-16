package bh.gov.cio.gbs.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class Board.
 */
/**
 * @author csdvdnm
 *
 */
public class Notification extends BaseTo implements FillFromResultSet{

	private Long notificationId;
	
	private Long boardId;

	private String notificationName;
		
	private Date actualDate;
	
	private Date notificationDate;
	
	/*
	 * Day = 1
	 * Week = 7
	 * Month = 30
	 * Year = 360
	 */
	private Integer repeatEvery;
	
	private Date lastRunDate;
	
	
	public  Long getNotificationId() {
		return notificationId;
	}


	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}


	public Long getBoardId() {
		return boardId;
	}

	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}

	public String getNotificationName() {
		return notificationName;
	}

	public void setNotificationName(String notificationName) {
		this.notificationName = notificationName;
	}

	public Date getActualDate() {
		return actualDate;
	}

	public void setActualDate(Date actualDate) {
		this.actualDate = actualDate;
	}

	public Date getNotificationDate() {
		return notificationDate;
	}

	public void setNotificationDate(Date notificationDate) {
		this.notificationDate = notificationDate;
	}

	public Integer getRepeatEvery() {
		return repeatEvery;
	}

	public void setRepeatEvery(Integer repeatEvery) {
		this.repeatEvery = repeatEvery;
	}

	public Date getLastRunDate() {
		return lastRunDate;
	}

	public void setLastRunDate(Date lastRunDate) {
		this.lastRunDate = lastRunDate;
	}

	/* (non-Javadoc)
	 * @see bh.gov.cio.gbs.model.FillFromResultSet#fillFromResultSet(java.sql.ResultSet)
	 */
	public void fillFromResultSet(ResultSet resultSet) throws Throwable {
		// TODO Auto-generated method stub
		ResultSetMetaData metaData = resultSet.getMetaData();
		for (int i = 1; i <= metaData.getColumnCount(); ++i) {
			String columnLabel = metaData.getColumnLabel(i);
			if (columnLabel.equalsIgnoreCase("NOTIFICATION_ID")) {
				this.setNotificationId(resultSet.getLong("NOTIFICATION_ID"));
			} else if (columnLabel.equalsIgnoreCase("BOARD_ID")) {
				this.setBoardId(resultSet.getLong("BOARD_ID"));
			} else if (columnLabel.equalsIgnoreCase("ACTUAL_DATE")) {
				this.setActualDate(resultSet.getDate("ACTUAL_DATE"));
			} else if (columnLabel.equalsIgnoreCase("REPEAT_EVERY")) {
				this.setRepeatEvery(resultSet.getInt("REPEAT_EVERY"));
			} else if (columnLabel.equalsIgnoreCase("LAST_RUN_DATE")) {
				this.setLastRunDate(resultSet.getDate("LAST_RUN_DATE"));
			} else if (columnLabel.equalsIgnoreCase("NOTIFICATION_DATE")) {
				this.setNotificationDate(resultSet.getDate("NOTIFICATION_DATE"));
			}
		}
	}
	
	
}
