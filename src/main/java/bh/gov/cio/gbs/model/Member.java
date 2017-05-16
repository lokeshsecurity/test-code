package bh.gov.cio.gbs.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Date;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Member.
 */
public class Member extends BaseTo implements FillFromResultSet {
	
	/** The member id. */
	private Integer memberId;
	
	private Integer boardId;
	
	private Integer cprNumber;
	
	private Integer statusId;
	
	private Integer roleId;
	
	/** The member name. */
	private String memberName;
	
	/** The member name normalized. */
	private String memberNameNormalized;
	
	/** The member occupation */
	private String memberOccupation;
	
	/** The member occupation normalized */
	private String memberOccupationNormalized;
	
	private Integer oranizationId;
	
	private Integer organizationOnBehalfId;
	
	/** The member duration */
	private Date memberStartDate;
	
	/** The member expiry date */
	private Date memberEndDate;
	
	private ActionOperation actionOperation;

	private List<Decree> memberDecreesList;

	private Integer notificationBefore;
	
	private Integer notificationEvery;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * bh.gov.cio.gbs.model.FillFromResultSet#fillFromResultSet(java.sql.ResultSet
	 * )
	 */
	public void fillFromResultSet(ResultSet resultSet) throws Throwable {

		ResultSetMetaData metaData = resultSet.getMetaData();
		for (int i = 1; i <= metaData.getColumnCount(); ++i) {
			String columnLabel = metaData.getColumnLabel(i);
			if (columnLabel.equalsIgnoreCase("MEMBER_ID")) {
				this.setMemberId(resultSet.getInt("MEMBER_ID"));
			} else if (columnLabel.equalsIgnoreCase("MEMBER_NAME")) {
				this.setMemberName(resultSet.getString("MEMBER_NAME"));
			} else if (columnLabel.equalsIgnoreCase("MEMBER_NAME_NORMALIZED")) {
				this.setMemberNameNormalized(resultSet.getString("MEMBER_NAME_NORMALIZED"));
			} else if (columnLabel.equalsIgnoreCase("MEMBER_OCCUPATION")) {
				this.setMemberOccupation(resultSet.getString("MEMBER_OCCUPATION"));	
			} else if (columnLabel.equalsIgnoreCase("MEMBER_OCCUPATION_NORMLIZED")) {
				this.setMemberOccupationNormalized(resultSet.getString("MEMBER_OCCUPATION_NORMLIZED"));	
			} else if (columnLabel.equalsIgnoreCase("ORGANIZATION_ID")) {
				this.setOranizationId(Integer.valueOf(resultSet.getInt("ORGANIZATION_ID")).equals(0) ? null : Integer.valueOf(resultSet.getInt("ORGANIZATION_ID")));		
			} else if (columnLabel.equalsIgnoreCase("BOARD_ID")) {
				this.setBoardId(resultSet.getInt("BOARD_ID"));	
			} else if (columnLabel.equalsIgnoreCase("STATUS_ID")) {
				this.setStatusId(Integer.valueOf(resultSet.getInt("STATUS_ID")).equals(0) ? null : Integer.valueOf(resultSet.getInt("STATUS_ID")));	
			} else if (columnLabel.equalsIgnoreCase("ROLE_ID")) {
				this.setRoleId(Integer.valueOf(resultSet.getInt("ROLE_ID")).equals(0) ? null : Integer.valueOf(resultSet.getInt("ROLE_ID")));	
			} else if (columnLabel.equalsIgnoreCase("CPR_NUMBER")) {
				this.setCprNumber(Integer.valueOf(resultSet.getInt("CPR_NUMBER")).equals(0) ? null : Integer.valueOf(resultSet.getInt("CPR_NUMBER")));	
			} else if (columnLabel.equalsIgnoreCase("ORGANIZATION_ON_BEHALF_ID")) {
				this.setOrganizationOnBehalfId(Integer.valueOf(resultSet.getInt("ORGANIZATION_ON_BEHALF_ID")).equals(0) ? null : Integer.valueOf(resultSet.getInt("ORGANIZATION_ON_BEHALF_ID")));	
			} else if (columnLabel.equalsIgnoreCase("MEMBER_START_DATE")) {
				this.setMemberStartDate(resultSet.getDate("MEMBER_START_DATE"));	
			} else if (columnLabel.equalsIgnoreCase("MEMBER_END_DATE")) {
				this.setMemberEndDate(resultSet.getDate("MEMBER_END_DATE"));	
			} else if (columnLabel.equalsIgnoreCase("NOTIFICATION_BEFORE")) {
				this.setNotificationBefore(Integer.valueOf(resultSet.getInt("NOTIFICATION_BEFORE")).equals(0) ? null : Integer.valueOf(resultSet.getInt("NOTIFICATION_BEFORE")));	
			} else if (columnLabel.equalsIgnoreCase("NOTIFICATION_EVERY")) {
				this.setNotificationEvery(Integer.valueOf(resultSet.getInt("NOTIFICATION_EVERY")).equals(0) ? null : Integer.valueOf(resultSet.getInt("NOTIFICATION_EVERY")));	
			}
		}
	}



	public Integer getMemberId() {
		return memberId;
	}





	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}





	public Integer getBoardId() {
		return boardId;
	}





	public void setBoardId(Integer boardId) {
		this.boardId = boardId;
	}





	public Integer getCprNumber() {
		return cprNumber;
	}





	public void setCprNumber(Integer cprNumber) {
		this.cprNumber = cprNumber;
	}





	public Integer getStatusId() {
		return statusId;
	}





	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}





	public Integer getRoleId() {
		return roleId;
	}





	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}





	public String getMemberName() {
		return memberName;
	}





	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}





	public String getMemberNameNormalized() {
		return memberNameNormalized;
	}





	public void setMemberNameNormalized(String memberNameNormalized) {
		this.memberNameNormalized = memberNameNormalized;
	}





	public String getMemberOccupation() {
		return memberOccupation;
	}





	public void setMemberOccupation(String memberOccupation) {
		this.memberOccupation = memberOccupation;
	}





	public String getMemberOccupationNormalized() {
		return memberOccupationNormalized;
	}





	public void setMemberOccupationNormalized(String memberOccupationNormalized) {
		this.memberOccupationNormalized = memberOccupationNormalized;
	}





	public Integer getOranizationId() {
		return oranizationId;
	}





	public void setOranizationId(Integer oranizationId) {
		this.oranizationId = oranizationId;
	}





	public Integer getOrganizationOnBehalfId() {
		return organizationOnBehalfId;
	}





	public void setOrganizationOnBehalfId(Integer organizationOnBehalfId) {
		this.organizationOnBehalfId = organizationOnBehalfId;
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



	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", cprNumber=" + cprNumber
				+ ", statusId=" + statusId + ", roleId=" + roleId
				+ ", memberName=" + memberName + ", memberOccupation="
				+ memberOccupation + ", oranizationId=" + oranizationId
				+ ", organizationOnBehalfId=" + organizationOnBehalfId
				+ ", memberStartDate=" + memberStartDate 
				+ ", memberEndDate="+ memberEndDate  
				+ ", notificationBefore="+ notificationBefore  
				+ ", notificationEvery="+ notificationEvery  
				+ "]";
	}


	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((actionOperation == null) ? 0 : actionOperation.hashCode());
		result = prime * result + ((boardId == null) ? 0 : boardId.hashCode());
		result = prime * result
				+ ((cprNumber == null) ? 0 : cprNumber.hashCode());
		result = prime * result
				+ ((memberEndDate == null) ? 0 : memberEndDate.hashCode());
		result = prime * result
				+ ((memberId == null) ? 0 : memberId.hashCode());
		result = prime * result
				+ ((memberName == null) ? 0 : memberName.hashCode());
		result = prime
				* result
				+ ((memberNameNormalized == null) ? 0 : memberNameNormalized
						.hashCode());
		result = prime
				* result
				+ ((memberOccupation == null) ? 0 : memberOccupation.hashCode());
		result = prime
				* result
				+ ((memberOccupationNormalized == null) ? 0
						: memberOccupationNormalized.hashCode());
		result = prime * result
				+ ((memberStartDate == null) ? 0 : memberStartDate.hashCode());
		result = prime * result
				+ ((oranizationId == null) ? 0 : oranizationId.hashCode());
		result = prime
				* result
				+ ((organizationOnBehalfId == null) ? 0
						: organizationOnBehalfId.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		result = prime * result
				+ ((statusId == null) ? 0 : statusId.hashCode());
		result = prime * result
				+ ((notificationBefore == null) ? 0 : notificationBefore.hashCode());
		result = prime * result
				+ ((notificationEvery == null) ? 0 : notificationEvery.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		if (actionOperation != other.actionOperation)
			return false;
		if (boardId == null) {
			if (other.boardId != null)
				return false;
		} else if (!boardId.equals(other.boardId))
			return false;
		if (cprNumber == null) {
			if (other.cprNumber != null)
				return false;
		} else if (!cprNumber.equals(other.cprNumber))
			return false;
		if (memberEndDate == null) {
			if (other.memberEndDate != null)
				return false;
		} else if (!memberEndDate.equals(other.memberEndDate))
			return false;
		if (memberId == null) {
			if (other.memberId != null)
				return false;
		} else if (!memberId.equals(other.memberId))
			return false;
		if (memberName == null) {
			if (other.memberName != null)
				return false;
		} else if (!memberName.equals(other.memberName))
			return false;
		if (memberNameNormalized == null) {
			if (other.memberNameNormalized != null)
				return false;
		} else if (!memberNameNormalized.equals(other.memberNameNormalized))
			return false;
		if (memberOccupation == null) {
			if (other.memberOccupation != null)
				return false;
		} else if (!memberOccupation.equals(other.memberOccupation))
			return false;
		if (memberOccupationNormalized == null) {
			if (other.memberOccupationNormalized != null)
				return false;
		} else if (!memberOccupationNormalized
				.equals(other.memberOccupationNormalized))
			return false;
		if (memberStartDate == null) {
			if (other.memberStartDate != null)
				return false;
		} else if (!memberStartDate.equals(other.memberStartDate))
			return false;
		if (oranizationId == null) {
			if (other.oranizationId != null)
				return false;
		} else if (!oranizationId.equals(other.oranizationId))
			return false;
		if (organizationOnBehalfId == null) {
			if (other.organizationOnBehalfId != null)
				return false;
		} else if (!organizationOnBehalfId.equals(other.organizationOnBehalfId))
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		if (statusId == null) {
			if (other.statusId != null)
				return false;
		} else if (!statusId.equals(other.statusId))
			return false;
		if (notificationBefore == null) {
			if (other.notificationBefore != null)
				return false;
		} else if (!notificationBefore.equals(other.notificationBefore))
			return false;
		if (notificationEvery == null) {
			if (other.notificationEvery != null)
				return false;
		} else if (!notificationEvery.equals(other.notificationEvery))
			return false;
		
		return true;
	}



	public ActionOperation getActionOperation() {
		return actionOperation;
	}



	public void setActionOperation(ActionOperation actionOperation) {
		this.actionOperation = actionOperation;
	}
	
	public List<Decree> getMemberDecreesList() {
		return memberDecreesList;
	}



	public void setMemberDecreesList(List<Decree> memberDecreesList) {
		this.memberDecreesList = memberDecreesList;
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

}
