package bh.gov.cio.gbs.model;

import java.util.Arrays;
import java.util.Date;

import bh.gov.cio.gbs.util.DateUtil;

public class BoardJSON {

	private String name;
	private Integer sourceOrganizationId;
	private Date startDate;
	private Date endDate;
	private Integer[] destinationOrganizationIds;
	private Integer boardTypeId;
	private Integer parentBoardId;
	private Integer notificationBefore;
	private Integer notificationRepaet;
	private Integer boardId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSourceOrganizationId() {
		return sourceOrganizationId;
	}

	public void setSourceOrganizationId(Integer sourceOrganizationId) {
		this.sourceOrganizationId = sourceOrganizationId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		
		this.startDate = DateUtil.getStartOfDay(startDate);
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = DateUtil.getEndOfDay(endDate);
	}

	public Integer[] getDestinationOrganizationIds() {
		return destinationOrganizationIds;
	}

	public void setDestinationOrganizationIds(
			Integer[] destinationOrganizationIds) {
		this.destinationOrganizationIds = destinationOrganizationIds;
	}

	public Integer getBoardTypeId() {
		return boardTypeId;
	}

	public void setBoardTypeId(Integer boardTypeId) {
		this.boardTypeId = boardTypeId;
	}

	public Integer getNotificationBefore() {
		return notificationBefore;
	}

	public void setNotificationBefore(Integer notificationBefore) {
		this.notificationBefore = notificationBefore;
	}
	
	public Integer getParentBoardId() {
		return parentBoardId;
	}

	public void setParentBoardId(Integer parentBoardId) {
		this.parentBoardId = parentBoardId;
	}

	public Integer getNotificationRepaet() {
		return notificationRepaet;
	}

	public void setNotificationRepaet(Integer notificationRepaet) {
		this.notificationRepaet = notificationRepaet;
	}

	public Integer getBoardId() {
		return boardId;
	}

	public void setBoardID(Integer boardId) {
		this.boardId = boardId;
	}

	@Override
	public String toString() {
		return "BoardJSON [name=" + name + ", sourceOrganizationId="
				+ sourceOrganizationId + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", destinationOrganizationIds="
				+ Arrays.toString(destinationOrganizationIds)
				+ ", boardTypeId=" + boardTypeId + ", parentBoardId="
				+ parentBoardId + ", notificationBefore=" + notificationBefore
				+ ", notificationRepaet=" + notificationRepaet + ", boardId="
				+ boardId + "]";
	}
}
