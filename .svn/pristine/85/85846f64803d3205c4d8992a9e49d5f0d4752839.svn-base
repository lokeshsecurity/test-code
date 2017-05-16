package bh.gov.cio.gbs.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Date;

public class MemberSearchResult extends BaseTo implements FillFromResultSet {

	/** The member id. */
	private Integer memberId;

	private Integer boardId;

	private String boardName;

	private Integer cprNumber;

	private Integer statusId;

	private String statusName;

	private Integer roleId;

	private String roleName;

	/** The member name. */
	private String memberName;

	/** The member occupation */
	private String memberOccupation;

	private Integer oranizationId;

	private String organizationName;

	private Integer organizationOnBehalfId;

	private String organizationOnBehalfName;

	/** The member duration */
	private Date memberStartDate;

	/** The member expiry date */
	private Date memberEndDate;

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

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
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

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberOccupation() {
		return memberOccupation;
	}

	public void setMemberOccupation(String memberOccupation) {
		this.memberOccupation = memberOccupation;
	}

	public Integer getOranizationId() {
		return oranizationId;
	}

	public void setOranizationId(Integer oranizationId) {
		this.oranizationId = oranizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public Integer getOrganizationOnBehalfId() {
		return organizationOnBehalfId;
	}

	public void setOrganizationOnBehalfId(Integer organizationOnBehalfId) {
		this.organizationOnBehalfId = organizationOnBehalfId;
	}

	public String getOrganizationOnBehalfName() {
		return organizationOnBehalfName;
	}

	public void setOrganizationOnBehalfName(String organizationOnBehalfName) {
		this.organizationOnBehalfName = organizationOnBehalfName;
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

	public void fillFromResultSet(ResultSet resultSet) throws Throwable {
		
		ResultSetMetaData metaData = resultSet.getMetaData();
		for (int i = 1; i <= metaData.getColumnCount(); ++i) {
			String columnLabel = metaData.getColumnLabel(i);
			if (columnLabel.equalsIgnoreCase("MEMBER_ID")) {
				this.setMemberId(resultSet.getInt("MEMBER_ID"));
			} else if (columnLabel.equalsIgnoreCase("MEMBER_NAME")) {
				this.setMemberName(resultSet.getString("MEMBER_NAME"));
			} else if (columnLabel.equalsIgnoreCase("MEMBER_OCCUPATION")) {
				this.setMemberOccupation(resultSet.getString("MEMBER_OCCUPATION"));	
			} else if (columnLabel.equalsIgnoreCase("ORGANIZATION_ID")) {
				this.setOranizationId(Integer.valueOf(resultSet.getInt("ORGANIZATION_ID")).equals(0) ? null : Integer.valueOf(resultSet.getInt("ORGANIZATION_ID")));		
			} else if (columnLabel.equalsIgnoreCase("BOARD_ID")) {
				this.setBoardId(resultSet.getInt("BOARD_ID"));	
			} else if (columnLabel.equalsIgnoreCase("STATUS_ID")) {
				this.setStatusId(Integer.valueOf(resultSet.getInt("STATUS_ID")).equals(0) ? null : Integer.valueOf(resultSet.getInt("STATUS_ID")));	
			} else if (columnLabel.equalsIgnoreCase("ROLE_ID")) {
				this.setRoleId(Integer.valueOf(resultSet.getInt("ROLE_ID")).equals(0) ? null : Integer.valueOf(resultSet.getInt("ROLE_ID")));	
			} else if (columnLabel.equalsIgnoreCase("BOARD_NAME")) {
				this.setBoardName(resultSet.getString("BOARD_NAME"));	
			} else if (columnLabel.equalsIgnoreCase("CPR_NUMBER")) {
				this.setCprNumber(Integer.valueOf(resultSet.getInt("CPR_NUMBER")).equals(0) ? null : Integer.valueOf(resultSet.getInt("CPR_NUMBER")));
			} else if (columnLabel.equalsIgnoreCase("MEMBER_START_DATE")) {
				this.setMemberStartDate(resultSet.getDate("MEMBER_START_DATE"));
			} else if (columnLabel.equalsIgnoreCase("STATUS_NAME")) {
				this.setStatusName(resultSet.getString("STATUS_NAME"));
			} else if (columnLabel.equalsIgnoreCase("ROLE_NAME")) {
				this.setRoleName(resultSet.getString("ROLE_NAME"));
			}else if (columnLabel.equalsIgnoreCase("ORGANIZATION_NAME")) {
				this.setOrganizationName(resultSet.getString("ORGANIZATION_NAME"));
			}else if (columnLabel.equalsIgnoreCase("ORGANIZATION_ID_OBF")) {
				this.setOrganizationOnBehalfId(Integer.valueOf(resultSet.getInt("ORGANIZATION_ID_OBF")).equals(0) ? null : Integer.valueOf(resultSet.getInt("ORGANIZATION_ID_OBF")));
			}else if (columnLabel.equalsIgnoreCase("ORGANIZATION_NAME_OBF")) {
				this.setOrganizationOnBehalfName(resultSet.getString("ORGANIZATION_NAME_OBF"));
			}else if (columnLabel.equalsIgnoreCase("MEMBER_END_DATE")) {
				this.setMemberEndDate(resultSet.getDate("MEMBER_END_DATE"));
			}
		}

	}

}
