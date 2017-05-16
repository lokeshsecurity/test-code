package bh.gov.cio.gbs.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import bh.gov.cio.gbs.util.JSONUtil;

public class MemberHistory implements FillFromResultSet {

	private Integer memberId;
	private String memberName;
	private Integer cprNumber;
	private String memberOccuption;
	private Integer organizationId;
	private Integer roleId;
	private Integer organizationOnBehalfId;
	private String loggedInUser;
	private Date createdOn;
	private Date memberStartDate;
	private Date memberEndDate;
	private Integer boardId;
	private String boardName;
	private Integer boardTypeId;
	private String boardTypeName;
	private Date boardExpiryDate;
	private Date boardStartDate;
	private List<Decree> decrees;
	private Integer parentBoardId;
	private String status;
	private Integer memberStatusId;
	private ActionOperation actionOperation;
	private Integer notificationBefore;
	private Integer notificationRepeat;
	private String action;
	private Date notificationDate;

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Integer getCprNumber() {
		return cprNumber;
	}

	public void setCprNumber(Integer cprNumber) {
		this.cprNumber = cprNumber;
	}

	public String getMemberOccuption() {
		return memberOccuption;
	}

	public void setMemberOccuption(String memberOccuption) {
		this.memberOccuption = memberOccuption;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getOrganizationOnBehalfId() {
		return organizationOnBehalfId;
	}

	public void setOrganizationOnBehalfId(Integer organizationOnBehalfId) {
		this.organizationOnBehalfId = organizationOnBehalfId;
	}

	public String getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(String loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getMemberStartDate() {
		return memberStartDate;
	}

	public void setMemberStartDate(Date memberStartDate) {
		this.memberStartDate = memberStartDate;
	}

	public Date getMemberEndDate() {
		return memberEndDate;
	}

	public void setMemberEndDate(Date memberEndDate) {
		this.memberEndDate = memberEndDate;
	}

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

	public Integer getBoardTypeId() {
		return boardTypeId;
	}

	public void setBoardTypeId(Integer boardTypeId) {
		this.boardTypeId = boardTypeId;
	}
	
	public List<Decree> getDecrees() {
		return decrees;
	}

	public void setDecrees(List<Decree> decrees) {
		this.decrees = decrees;
	}

	public Date getBoardExpiryDate() {
		return boardExpiryDate;
	}

	public void setBoardExpiryDate(Date boardExpiryDate) {
		this.boardExpiryDate = boardExpiryDate;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public ActionOperation getActionOperation() {
		return actionOperation;
	}

	public void setActionOperation(ActionOperation actionOperation) {
		this.actionOperation = actionOperation;
	}
	
	public Integer getMemberStatusId() {
		return memberStatusId;
	}

	public void setMemberStatusId(Integer memberStatusId) {
		this.memberStatusId = memberStatusId;
	}

	@Override
	public String toString() {
		return "MemberHistory [memberId=" + memberId + ", memberName="
				+ memberName + ", cprNumber=" + cprNumber
				+ ", memberOccuption=" + memberOccuption + ", organizationId="
				+ organizationId + ", roleId=" + roleId
				+ ", organizationOnBehalfId=" + organizationOnBehalfId
				+ ", loggedInUser=" + loggedInUser + ", createdOn=" + createdOn
				+ ", memberStartDate=" + memberStartDate + ", memberEndDate="
				+ memberEndDate + ", boardId=" + boardId + ", boardName="
				+ boardName + ", boardTypeId=" + boardTypeId
				+ ", boardTypeName=" + boardTypeName
				+ ", boardExpiryDate=" + boardExpiryDate + ", boardStartDate="
				+ boardStartDate + ", decrees=" + decrees + ", parentBoardId="
				+ parentBoardId + ", status=" + status + ", memberStatusId="
				+ memberStatusId + ", actionOperation=" + actionOperation
				+ ", boardTypeName=" + boardTypeName 
				+ ", notificationRepeat=" + notificationRepeat 
				+ ", notificationBefore=" + notificationBefore + ", notificationDate=" + notificationDate +"]";
	}

	public String getBoardTypeName() {
		return boardTypeName;
	}

	public void setBoardTypeName(String boardTypeName) {
		this.boardTypeName = boardTypeName;
	}

	public Integer getNotificationRepeat() {
		return notificationRepeat;
	}

	public void setNotificationRepeat(Integer notificationRepeat) {
		this.notificationRepeat = notificationRepeat;
	}

	public Integer getNotificationBefore() {
		return notificationBefore;
	}

	public void setNotificationBefore(Integer notificationBefore) {
		this.notificationBefore = notificationBefore;
	}

	public void fillFromResultSet(ResultSet resultSet) throws Throwable {
		ResultSetMetaData metaData = resultSet.getMetaData();
		for (int i = 1; i <= metaData.getColumnCount(); ++i) {
			String columnLabel = metaData.getColumnLabel(i);
			if (columnLabel.equalsIgnoreCase("MEMBER_ID")) {
				this.setMemberId(resultSet.getInt("MEMBER_ID"));
			} else if(columnLabel.equalsIgnoreCase("MEMBER_NAME")){
				this.setMemberName(resultSet.getString("MEMBER_NAME"));
			} else if(columnLabel.equalsIgnoreCase("CPR_NUMBER")){
				this.setCprNumber(Integer.valueOf(resultSet.getInt("CPR_NUMBER")).equals(0) ? null : Integer.valueOf(resultSet.getInt("CPR_NUMBER")));
			} else if(columnLabel.equalsIgnoreCase("DECREE_LIST")){
				String decreeStr = resultSet.getString("DECREE_LIST");
				if(!StringUtils.isEmpty(decreeStr)){
					List<Decree> decrees = JSONUtil.convertJSONToList(decreeStr, Decree.class);
					this.setDecrees(decrees);
				}
			} else if(columnLabel.equalsIgnoreCase("MEMBER_OCCUPATION")){
				this.setMemberOccuption(resultSet.getString("MEMBER_OCCUPATION"));
			} else if(columnLabel.equalsIgnoreCase("ORGANIZATION_ID")){
				this.setOrganizationId(Integer.valueOf(resultSet.getInt("ORGANIZATION_ID")).equals(0) ? null : Integer.valueOf(resultSet.getInt("ORGANIZATION_ID")));
			} else if(columnLabel.equalsIgnoreCase("ROLE_ID")){
				this.setRoleId(Integer.valueOf(resultSet.getInt("ROLE_ID")).equals(0) ? null : Integer.valueOf(resultSet.getInt("ROLE_ID")));	
			} else if(columnLabel.equalsIgnoreCase("ORGANIZATION_ON_BEHALF_ID")){
				this.setOrganizationOnBehalfId(Integer.valueOf(resultSet.getInt("ORGANIZATION_ON_BEHALF_ID")).equals(0) ? null : Integer.valueOf(resultSet.getInt("ORGANIZATION_ON_BEHALF_ID")));
			} else if(columnLabel.equalsIgnoreCase("MEMBER_START_DATE")){
				this.setMemberStartDate(resultSet.getTimestamp("MEMBER_START_DATE"));
			} else if(columnLabel.equalsIgnoreCase("MEMBER_END_DATE")){
				this.setMemberEndDate(resultSet.getTimestamp("MEMBER_END_DATE"));
			} else if(columnLabel.equalsIgnoreCase("BOARD_ID")){
				this.setBoardId(resultSet.getInt("BOARD_ID"));
			} else if(columnLabel.equalsIgnoreCase("BOARD_NAME")){
				this.setBoardName(resultSet.getString("BOARD_NAME"));
			} else if(columnLabel.equalsIgnoreCase("BOARD_TYPE_ID")){
				this.setBoardTypeId(resultSet.getInt("BOARD_TYPE_ID"));
			} else if(columnLabel.equalsIgnoreCase("BOARD_TYPE_NAME")){
				this.setBoardTypeName(resultSet.getString("BOARD_TYPE_NAME"));
			} else if(columnLabel.equalsIgnoreCase("BOARD_EXPIRY_DATE")){
				this.setBoardExpiryDate(resultSet.getTimestamp("BOARD_EXPIRY_DATE"));
			} else if(columnLabel.equalsIgnoreCase("BOARD_START_DATE")){
				this.setBoardStartDate(resultSet.getTimestamp("BOARD_START_DATE"));
			} else if(columnLabel.equalsIgnoreCase("PARENT_BOARD_ID")){
				this.setParentBoardId(resultSet.getInt("PARENT_BOARD_ID"));
			} else if(columnLabel.equalsIgnoreCase("MEMBER_STATUS_ID")){
				this.setMemberStatusId(Integer.valueOf(resultSet.getInt("MEMBER_STATUS_ID")).equals(0) ? null : Integer.valueOf(resultSet.getInt("MEMBER_STATUS_ID")));	
			} else if(columnLabel.equalsIgnoreCase("STATUS")){
				this.setStatus(resultSet.getString("STATUS"));
			} else if(columnLabel.equalsIgnoreCase("CREATED_ON")){
				this.setCreatedOn(resultSet.getTimestamp("CREATED_ON"));
			} else if(columnLabel.equalsIgnoreCase("CREATED_BY")){
				this.setLoggedInUser(resultSet.getString("CREATED_BY"));
			} else if(columnLabel.equalsIgnoreCase("ACTION_OPERATION")){
				ActionOperation operation= ActionOperation.getActionOperation(resultSet.getString("ACTION_OPERATION"));
				this.setActionOperation(operation);
				this.setAction(operation.nameAr());
			} else if(columnLabel.equalsIgnoreCase("NOTIFICATION_BEFORE")){
				this.setNotificationBefore(resultSet.getInt("NOTIFICATION_BEFORE"));
			} else if(columnLabel.equalsIgnoreCase("NOTIFICATION_EVERY")){
				this.setNotificationRepeat(resultSet.getInt("NOTIFICATION_EVERY"));
			} else if(columnLabel.equalsIgnoreCase("NOTIFICATION_DATE")){
				this.setNotificationDate(resultSet.getTimestamp("NOTIFICATION_DATE"));
			}
		}
		
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getNotificationDate() {
		return notificationDate;
	}

	public void setNotificationDate(Date notificationDate) {
		this.notificationDate = notificationDate;
	}

}
