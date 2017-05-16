package bh.gov.cio.gbs.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Date;

public class MemberNotification implements FillFromResultSet {

	private Integer boardId;
	private String boardName;
	private Integer parentBoardId;
	private Integer boardTypeId;
	private String boardTypeName;
	private Integer statusId;
	private String statusName;
	private String sourceOrganizationName;
	private String sourceOrganizationId;
	private Date boardStartDate;
	private Date boardExpiryDate;
	private Integer notificationId;
	private Date notificationActualDate;
	private Date notificationLastRunDate;
	private Integer notificationRepaetEvery;
	private Date notificationDate;
	private Long memberId;
	private String memberName;
	private String memberRole;
	private Date memberEndDate;
	private String notificationType;
	private String email;
	
	public Integer getBoardId() {
		return boardId;
	}

	public void setBoardId(Integer boardId) {
		this.boardId = boardId;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public Integer getParentBoardId() {
		return parentBoardId;
	}

	public void setParentBoardId(Integer parentBoardId) {
		this.parentBoardId = parentBoardId;
	}

	public Integer getBoardTypeId() {
		return boardTypeId;
	}

	public void setBoardTypeId(Integer boardTypeId) {
		this.boardTypeId = boardTypeId;
	}

	public String getBoardTypeName() {
		return boardTypeName;
	}

	public void setBoardTypeName(String boardTypeName) {
		this.boardTypeName = boardTypeName;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getSourceOrganizationName() {
		return sourceOrganizationName;
	}

	public void setSourceOrganizationName(String sourceOrganizationName) {
		this.sourceOrganizationName = sourceOrganizationName;
	}

	public String getSourceOrganizationId() {
		return sourceOrganizationId;
	}

	public void setSourceOrganizationId(String sourceOrganizationId) {
		this.sourceOrganizationId = sourceOrganizationId;
	}

	public Date getBoardStartDate() {
		return boardStartDate;
	}

	public void setBoardStartDate(Date boardStartDate) {
		this.boardStartDate = boardStartDate;
	}

	public Date getBoardExpiryDate() {
		return boardExpiryDate;
	}

	public void setBoardExpiryDate(Date boardExpiryDate) {
		this.boardExpiryDate = boardExpiryDate;
	}

	public Integer getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}

	public Date getNotificationActualDate() {
		return notificationActualDate;
	}

	public void setNotificationActualDate(Date notificationActualDate) {
		this.notificationActualDate = notificationActualDate;
	}

	public Date getNotificationLastRunDate() {
		return notificationLastRunDate;
	}

	public void setNotificationLastRunDate(Date notificationLastRunDate) {
		this.notificationLastRunDate = notificationLastRunDate;
	}

	public Integer getNotificationRepaetEvery() {
		return notificationRepaetEvery;
	}

	public void setNotificationRepaetEvery(Integer notificationRepaetEvery) {
		this.notificationRepaetEvery = notificationRepaetEvery;
	}

	public Date getNotificationDate() {
		return notificationDate;
	}

	public void setNotificationDate(Date notificationDate) {
		this.notificationDate = notificationDate;
	}

	public void fillFromResultSet(ResultSet resultSet) throws Throwable {

		ResultSetMetaData metaData = resultSet.getMetaData();
		for (int i = 1; i <= metaData.getColumnCount(); ++i) {
			String columnLabel = metaData.getColumnLabel(i);
			if (columnLabel.equalsIgnoreCase("BOARD_TYPE_ID")) {
				this.setBoardTypeId(resultSet.getInt("BOARD_TYPE_ID"));
			} else if (columnLabel.equalsIgnoreCase("BOARD_TYPE_NAME")) {
				this.setBoardTypeName(resultSet.getString("BOARD_TYPE_NAME"));
			} else if (columnLabel.equalsIgnoreCase("BOARD_ID")) {
				this.setBoardId(resultSet.getInt("BOARD_ID"));
			} else if (columnLabel.equalsIgnoreCase("BOARD_NAME")) {
				this.setBoardName(resultSet.getString("BOARD_NAME"));
			} else if (columnLabel.equalsIgnoreCase("PARENT_BOARD_ID")) {
				this.setParentBoardId(resultSet.getInt("PARENT_BOARD_ID"));
			} else if (columnLabel.equalsIgnoreCase("STATUS_ID")) {
				this.setStatusId(resultSet.getInt("STATUS_ID"));
			} else if (columnLabel.equalsIgnoreCase("STATUS_NAME")) {
				this.setStatusName(resultSet.getString("STATUS_NAME"));
			} else if (columnLabel.equalsIgnoreCase("SOURCE_ORGANIZATION_NAME")) {
				this.setSourceOrganizationName(resultSet
						.getString("SOURCE_ORGANIZATION_NAME"));
			} else if (columnLabel.equalsIgnoreCase("SOURCE_ORGANIZATION_NAME")) {
				this.setSourceOrganizationName(resultSet
						.getString("SOURCE_ORGANIZATION_NAME"));
			} else if (columnLabel.equalsIgnoreCase("BOARD_EXPIRY_DATE")) {
				this.setBoardExpiryDate(resultSet.getDate("BOARD_EXPIRY_DATE"));
			} else if (columnLabel.equalsIgnoreCase("BOARD_START_DATE")) {
				this.setBoardStartDate(resultSet.getDate("BOARD_START_DATE"));
			} else if (columnLabel.equalsIgnoreCase("ACTUAL_DATE")) {
				this.setNotificationActualDate(resultSet.getDate("ACTUAL_DATE"));
			} else if (columnLabel.equalsIgnoreCase("LAST_RUN_DATE")) {
				this.setNotificationLastRunDate(resultSet
						.getDate("LAST_RUN_DATE"));
			} else if (columnLabel.equalsIgnoreCase("NOTIFICATION_DATE")) {
				this.setNotificationDate(resultSet.getDate("NOTIFICATION_DATE"));
			} else if (columnLabel.equalsIgnoreCase("NOTIFICATION_ID")) {
				this.setNotificationId(resultSet.getInt("NOTIFICATION_ID"));
			} else if (columnLabel.equalsIgnoreCase("REPEAT_EVERY")) {
				this.setNotificationRepaetEvery(resultSet
						.getInt("REPEAT_EVERY"));
			} else if (columnLabel.equalsIgnoreCase("MEMBER_ID")) {
				this.setMemberId(resultSet.getLong("MEMBER_ID"));
			} else if (columnLabel.equalsIgnoreCase("NOTIFICATION_TYPE")) {
				this.setNotificationType(resultSet.getString("NOTIFICATION_TYPE"));
			}else if (columnLabel.equalsIgnoreCase("MEMBER_NAME")) {
				this.setMemberName(resultSet.getString("MEMBER_NAME"));
			} else if (columnLabel.equalsIgnoreCase("ROLE_NAME")) {
				this.setMemberRole(resultSet.getString("ROLE_NAME"));
			}else if (columnLabel.equalsIgnoreCase("MEMBER_END_DATE")) {
				this.setMemberEndDate(resultSet.getDate("MEMBER_END_DATE"));
			}else if (columnLabel.equalsIgnoreCase("SUBSCRIBER_EMAIL")) {
				this.setEmail(resultSet.getString("SUBSCRIBER_EMAIL"));
			}
		}
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberRole() {
		return memberRole;
	}

	public void setMemberRole(String memberRole) {
		this.memberRole = memberRole;
	}

	public Date getMemberEndDate() {
		return memberEndDate;
	}

	public void setMemberEndDate(Date memberEndDate) {
		this.memberEndDate = memberEndDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
