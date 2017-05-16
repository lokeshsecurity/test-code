package bh.gov.cio.gbs.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import bh.gov.cio.gbs.util.CommonUtil;
import bh.gov.cio.gbs.util.JSONUtil;

public class BoardHistrory implements FillFromResultSet {

	private Integer boardId;
	private String boardName;
	private Integer boardTypeId;
	private List<Decree> decrees;
	private String loggedInUser;
	private Integer sourceOrganizationId;
	private Integer[] destinationOrganizationIds;
	private Date createdOn;
	private Date boardStartDate;
	private Date boardExpiryDate;
	private Integer parentBoardId;
	private String status;
	private List<Member> members; 
	private Integer boardStatusId;
	private ActionOperation actionOperation;
	private Integer notificationBefore;
	private Integer notificationRepeat;
	private String action;
	private Date notificationDate;

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

	public Integer getSourceOrganizationId() {
		return sourceOrganizationId;
	}

	public void setSourceOrganizationId(Integer sourceOrganizationId) {
		this.sourceOrganizationId = sourceOrganizationId;
	}

	public Integer[] getDestinationOrganizationIds() {
		return destinationOrganizationIds;
	}

	public void setDestinationOrganizationIds(Integer[] destinationOrganizationIds) {
		this.destinationOrganizationIds = destinationOrganizationIds;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
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

	public Integer getParentBoardId() {
		return parentBoardId;
	}

	public void setParentBoardId(Integer parentBoardId) {
		this.parentBoardId = parentBoardId;
	}

	public String getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(String loggedInUser) {
		this.loggedInUser = loggedInUser;
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
	
	public Integer getBoardStatusId() {
		return boardStatusId;
	}

	public void setBoardStatusId(Integer boardStatusId) {
		this.boardStatusId = boardStatusId;
	}

	public void fillFromResultSet(ResultSet resultSet) throws Throwable {

		
		ResultSetMetaData metaData = resultSet.getMetaData();
		for (int i = 1; i <= metaData.getColumnCount(); ++i) {
			String columnLabel = metaData.getColumnLabel(i);
			if (columnLabel.equalsIgnoreCase("BOARD_ID")) {
				this.setBoardId(resultSet.getInt("BOARD_ID"));
			} else if(columnLabel.equalsIgnoreCase("BOARD_NAME")){
				this.setBoardName(resultSet.getString("BOARD_NAME"));
			} else if(columnLabel.equalsIgnoreCase("BOARD_TYPE_ID")){
				this.setBoardTypeId(resultSet.getInt("BOARD_TYPE_ID"));
			} else if(columnLabel.equalsIgnoreCase("DECREE_LIST")){
				String decreeStr = resultSet.getString("DECREE_LIST");
				if(!StringUtils.isEmpty(decreeStr)){
					List<Decree> decrees = JSONUtil.convertJSONToList(decreeStr, Decree.class);
					this.setDecrees(decrees);
				}
			} else if(columnLabel.equalsIgnoreCase("PARENT_BOARD_ID")){
				this.setParentBoardId(resultSet.getInt("PARENT_BOARD_ID"));
			} else if(columnLabel.equalsIgnoreCase("SOURCE_ORGANIZATION_ID")){
				this.setSourceOrganizationId(resultSet.getInt("SOURCE_ORGANIZATION_ID"));
			} else if(columnLabel.equalsIgnoreCase("DESTINATION_ORGANIZATION_IDS")){
				String ids = resultSet.getString("DESTINATION_ORGANIZATION_IDS");
				String newStr = ids.substring(0, ids.length()-1).substring(1).replaceAll("\\s+","");
				if(!StringUtils.isEmpty(newStr)){
					String [] idsArray = newStr.split(",");
					Integer[] destinationOrgsIds  = CommonUtil.convertToIntegerArray(idsArray);
					this.setDestinationOrganizationIds(destinationOrgsIds);
				}
			} else if(columnLabel.equalsIgnoreCase("CREATED_ON")){
				this.setCreatedOn(resultSet.getTimestamp("CREATED_ON"));
			} else if(columnLabel.equalsIgnoreCase("CREATED_BY")){
				this.setLoggedInUser(resultSet.getString("CREATED_BY"));
			} else if(columnLabel.equalsIgnoreCase("ACTION_OPERATION")){
				ActionOperation operation= ActionOperation.getActionOperation(resultSet.getString("ACTION_OPERATION"));
				this.setActionOperation(operation);
				this.setAction(operation.nameAr());
			} else if(columnLabel.equalsIgnoreCase("NOTIFICATION_BEFORE")){
				this.setNotificationBefore(Integer.valueOf(resultSet.getInt("NOTIFICATION_BEFORE")).equals(0) ? null : Integer.valueOf(resultSet.getInt("NOTIFICATION_BEFORE")));
			} else if(columnLabel.equalsIgnoreCase("NOTIFICATION_EVERY")){
				this.setNotificationRepeat(resultSet.getInt("NOTIFICATION_EVERY"));
				this.setNotificationRepeat(Integer.valueOf(resultSet.getInt("NOTIFICATION_EVERY")).equals(0) ? null : Integer.valueOf(resultSet.getInt("NOTIFICATION_EVERY")));
			} else if(columnLabel.equalsIgnoreCase("NOTIFICATION_DATE")){
				this.setNotificationDate(resultSet.getTimestamp("NOTIFICATION_DATE"));
			}
		}
	}

	public List<Decree> getDecrees() {
		return decrees;
	}

	public void setDecrees(List<Decree> decrees) {
		this.decrees = decrees;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	@Override
	public String toString() {
		return "BoardHistrory [boardId=" + boardId + ", boardName=" + boardName
				+ ", boardTypeId=" + boardTypeId + ", decrees=" + decrees
				+ ", loggedInUser=" + loggedInUser + ", sourceOrganizationId="
				+ sourceOrganizationId + ", destinationOrganizationIds="
				+ Arrays.toString(destinationOrganizationIds) + ", createdOn="
				+ createdOn + ", boardStartDate=" + boardStartDate
				+ ", boardExpiryDate=" + boardExpiryDate + ", parentBoardId="
				+ parentBoardId + ", status=" + status + ", members=" + members
				+ ", boardStatusId=" + boardStatusId + ", actionOperation="
				+ actionOperation + ", notificationBefore="
				+ notificationBefore + ", notificationRepeat=" + notificationRepeat  
				+ ", notificationDate=" + notificationDate +"]";
	}

	public Integer getNotificationBefore() {
		return notificationBefore;
	}

	public void setNotificationBefore(Integer notificationBefore) {
		this.notificationBefore = notificationBefore;
	}

	public Integer getNotificationRepeat() {
		return notificationRepeat;
	}

	public void setNotificationRepeat(Integer notificationRepeat) {
		this.notificationRepeat = notificationRepeat;
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
