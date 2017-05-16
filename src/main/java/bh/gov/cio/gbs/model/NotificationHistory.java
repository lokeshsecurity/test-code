package bh.gov.cio.gbs.model;

import java.sql.ResultSet;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class NotificationHistory.
 */
public class NotificationHistory implements FillFromResultSet {
	
	/** The id. */
	private Integer id;
	
	/** The Notification id. */
	private Integer NotificationId;
	
	/** The run date. */
	private Date runDate;
	
	/** The action. */
	private NotificationStatus status;
	
	/** The description. */
	private String description;
	
	/** The job runnable type. */
	private JobRunnableType jobRunnableType;
	
	/** The created by. */
	private String createdBy;
	
	/** The created on. */
	private Date createdOn;
	
	/** The updated by. */
	private String updatedBy;
	
	/** The updated on. */
	private Date updatedOn;

	/* (non-Javadoc)
	 * @see bh.gov.cio.gbs.model.FillFromResultSet#fillFromResultSet(java.sql.ResultSet)
	 */
	public void fillFromResultSet(ResultSet resultSet) throws Throwable {
		
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the notification id.
	 *
	 * @return the notification id
	 */
	public Integer getNotificationId() {
		return NotificationId;
	}

	/**
	 * Sets the notification id.
	 *
	 * @param nID the new notification id
	 */
	public void setNotificationId(Integer nID) {
		NotificationId = nID;
	}

	/**
	 * Gets the run date.
	 *
	 * @return the run date
	 */
	public Date getRunDate() {
		return runDate;
	}

	/**
	 * Sets the run date.
	 *
	 * @param runDate the new run date
	 */
	public void setRunDate(Date runDate) {
		this.runDate = runDate;
	}

	public NotificationStatus getStatus() {
		return status;
	}

	public void setStatus(NotificationStatus status) {
		this.status = status;
	}

	/**
	 * Gets the job runnable type.
	 *
	 * @return the job runnable type
	 */
	public JobRunnableType getJobRunnableType() {
		return jobRunnableType;
	}

	/**
	 * Sets the job runnable type.
	 *
	 * @param jobRunnableType the new job runnable type
	 */
	public void setJobRunnableType(JobRunnableType jobRunnableType) {
		this.jobRunnableType = jobRunnableType;
	}


	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the created by.
	 *
	 * @return the created by
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the created by.
	 *
	 * @param createdBy the new created by
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets the created on.
	 *
	 * @return the created on
	 */
	public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * Sets the created on.
	 *
	 * @param createdOn the new created on
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * Gets the updated by.
	 *
	 * @return the updated by
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * Sets the updated by.
	 *
	 * @param updatedBy the new updated by
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * Gets the updated on.
	 *
	 * @return the updated on
	 */
	public Date getUpdatedOn() {
		return updatedOn;
	}

	/**
	 * Sets the updated on.
	 *
	 * @param updatedOn the new updated on
	 */
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NotificationHistory [id=" + id + ", NotificationId="
				+ NotificationId + ", runDate=" + runDate + ", status="
				+ status + ", description=" + description
				+ ", jobRunnableType=" + jobRunnableType + "]";
	}

}
