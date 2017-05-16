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
public class Board extends BaseTo implements FillFromResultSet{

	/** The id. */
	private Long boardId;
	
	/** The name. */
	private String boardName;
	
	/** The name normalize. */
	private String boardNameNormalize;
	
	private Date boardStartDate;
	/** The expiry. */
	private Date boardExpiryDate;
	
	private Long statusId;
	
	private String statusName;
	
	private Long sourceOrganizationId;
	
	private String sourceOrganizationName;
	
	private String sourceOrganizationNameNormalized;
	
	private String destinationOrganizationName;
	
	private Integer boardTypeId;
	
	private Integer parentBoardId;
	
	private Integer[] destinationOrganizationIds;
	
	private Integer notificationBefore;
	
	private Integer notificationEvery;
	
	private String parentBoardName;
	
	
	
	
	
	/**
	 * @return the boardId
	 */
	/**
	 * @return
	 */
	public Long getBoardId() {
		return boardId;
	}


	/**
	 * @param boardId the boardId to set
	 */
	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}


	/**
	 * @return the boardName
	 */
	public String getBoardName() {
		return boardName;
	}


	/**
	 * @param boardName the boardName to set
	 */
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}


	/**
	 * @return the boardNameNormalize
	 */
	public String getBoardNameNormalize() {
		return boardNameNormalize;
	}


	/**
	 * @param boardNameNormalize the boardNameNormalize to set
	 */
	public void setBoardNameNormalize(String boardNameNormalize) {
		this.boardNameNormalize = boardNameNormalize;
	}

	/**
	 * @return the boardExpiryDate
	 */
	public Date getBoardExpiryDate() {
		return boardExpiryDate;
	}


	/**
	 * @param boardExpiryDate the boardExpiryDate to set
	 */
	public void setBoardExpiryDate(Date boardExpiryDate) {
		this.boardExpiryDate = boardExpiryDate;
	}

	/**
	 * @return the statusId
	 */
	public Long getStatusId() {
		return statusId;
	}


	/**
	 * @param statusId the statusId to set
	 */
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}


	/**
	 * @return the statusName
	 */
	public String getStatusName() {
		return statusName;
	}


	/**
	 * @param statusName the statusName to set
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}


	public Long getSourceOrganizationId() {
		return sourceOrganizationId;
	}


	public void setSourceOrganizationId(Long sourceOrganizationId) {
		this.sourceOrganizationId = sourceOrganizationId;
	}


	public String getSourceOrganizationName() {
		return sourceOrganizationName;
	}


	public void setSourceOrganizationName(String sourceOrganizationName) {
		this.sourceOrganizationName = sourceOrganizationName;
	}


	public String getSourceOrganizationNameNormalized() {
		return sourceOrganizationNameNormalized;
	}


	public void setSourceOrganizationNameNormalized(
			String sourceOrganizationNameNormalized) {
		this.sourceOrganizationNameNormalized = sourceOrganizationNameNormalized;
	}
	
	public String getDestinationOrganizationName() {
		return destinationOrganizationName;
	}


	public void setDestinationOrganizationName(String destinationOrganizationName) {
		this.destinationOrganizationName = destinationOrganizationName;
	}
	
	public Integer getBoardTypeId() {
		return boardTypeId;
	}


	public void setBoardTypeId(Integer boardTypeId) {
		this.boardTypeId = boardTypeId;
	}
	
	public Date getBoardStartDate() {
		return boardStartDate;
	}


	public void setBoardStartDate(Date boardStartDate) {
		this.boardStartDate = boardStartDate;
	}


	public Integer getParentBoardId() {
		return parentBoardId;
	}


	public void setParentBoardId(Integer parentBoardId) {
		this.parentBoardId = parentBoardId;
	}

	
	public Integer[] getDestinationOrganizationIds() {
		return destinationOrganizationIds;
	}


	public void setDestinationOrganizationIds(
			Integer[] destinationOrganizationIds) {
		this.destinationOrganizationIds = destinationOrganizationIds;
	}
	
	public Integer getNotificationBefore() {
		return notificationBefore;
	}


	public void setNotificationBefore(Integer notificationBefore) {
		this.notificationBefore = notificationBefore;
	}


	public Integer getNotificationEvery() {
		return notificationEvery;
	}


	public void setNotificationEvery(Integer notificationEvery) {
		this.notificationEvery = notificationEvery;
	}


	/* (non-Javadoc)
	 * @see bh.gov.cio.gbs.model.FillFromResultSet#fillFromResultSet(java.sql.ResultSet)
	 */
	public void fillFromResultSet(ResultSet resultSet) throws Throwable {
		// TODO Auto-generated method stub
		ResultSetMetaData metaData = resultSet.getMetaData();
		for (int i = 1; i <= metaData.getColumnCount(); ++i) {
			String columnLabel = metaData.getColumnLabel(i);
			if (columnLabel.equalsIgnoreCase("BOARD_ID")) {
				this.setBoardId(resultSet.getLong("BOARD_ID"));
			} else if (columnLabel.equalsIgnoreCase("BOARD_NAME")) {
				this.setBoardName(resultSet.getString("BOARD_NAME"));
			} else if (columnLabel.equalsIgnoreCase("BOARD_NAME_NORMALIZED")) {
				this.setBoardNameNormalize(resultSet.getString("BOARD_NAME_NORMALIZED"));
			} else if (columnLabel.equalsIgnoreCase("STATUS_ID")) {
				this.setStatusId(resultSet.getLong("STATUS_ID"));	
			} else if (columnLabel.equalsIgnoreCase("STATUS_NAME")) {
				this.setStatusName(resultSet.getString("STATUS_NAME"));
			} else if (columnLabel.equalsIgnoreCase("SOURCE_ORGANIZATION_ID")) {
				this.setSourceOrganizationId(resultSet.getLong("SOURCE_ORGANIZATION_ID"));	
			} else if (columnLabel.equalsIgnoreCase("SOURCE_ORGANIZATION_NAME")) {
				this.setSourceOrganizationName(resultSet.getString("SOURCE_ORGANIZATION_NAME"));	
			} else if (columnLabel.equalsIgnoreCase("SOURCE_ORGANIZATION_NAME_NORMALIZED")) {
				this.setSourceOrganizationNameNormalized(resultSet.getString("SOURCE_ORGANIZATION_NAME_NORMALIZED"));	
			} else if (columnLabel.equalsIgnoreCase("PRIMARY_DESTINATION_ORGANIZATION")) {
				this.setDestinationOrganizationName(resultSet.getString("PRIMARY_DESTINATION_ORGANIZATION"));	
			} else if (columnLabel.equalsIgnoreCase("BOARD_TYPE_ID")) {
				this.setBoardTypeId(resultSet.getInt("BOARD_TYPE_ID"));	
			} else if (columnLabel.equalsIgnoreCase("PARENT_BOARD_ID")) {
				this.setParentBoardId(Integer.valueOf(resultSet.getInt("PARENT_BOARD_ID")).equals(0) ? null : Integer.valueOf(resultSet.getInt("PARENT_BOARD_ID")));	
			} else if (columnLabel.equalsIgnoreCase("BOARD_START_DATE")) {
				this.setBoardStartDate(resultSet.getDate("BOARD_START_DATE"));	
			} else if (columnLabel.equalsIgnoreCase("BOARD_EXPIRY_DATE")) {
				this.setBoardExpiryDate(resultSet.getDate("BOARD_EXPIRY_DATE"));	
			} else if (columnLabel.equalsIgnoreCase("NOTIFICATION_BEFORE")) {
				this.setNotificationBefore(Integer.valueOf(resultSet.getInt("NOTIFICATION_BEFORE")).equals(0) ? null : Integer.valueOf(resultSet.getInt("NOTIFICATION_BEFORE")));	
			} else if (columnLabel.equalsIgnoreCase("NOTIFICATION_EVERY")) {
				this.setNotificationEvery(Integer.valueOf(resultSet.getInt("NOTIFICATION_EVERY")).equals(0) ? null : Integer.valueOf(resultSet.getInt("NOTIFICATION_EVERY")));	
			} else if (columnLabel.equalsIgnoreCase("PARENT_BOARD_NAME")) {
				this.setParentBoardName(resultSet.getString("PARENT_BOARD_NAME"));
			}
		}
	}


	public String getParentBoardName() {
		return parentBoardName;
	}


	public void setParentBoardName(String parentBoardName) {
		this.parentBoardName = parentBoardName;
	}


	
	
	
}
