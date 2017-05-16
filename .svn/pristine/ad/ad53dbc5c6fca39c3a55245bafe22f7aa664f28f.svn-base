package bh.gov.cio.gbs.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import bh.gov.cio.gbs.util.DateUtil;


// TODO: Auto-generated Javadoc
/**
 * The Class Decree.
 */
public class Decree extends BaseTo implements FillFromResultSet {

	/** The decree id. */
	private Long decreeId;
	
	/** The decree number. */
	private Long decreeNumber;
	
	/** The decree year. */
	private Long decreeYear;
	
	/** The decree description. */
	private String decreeDescription;
	
	/** The decree description normalized. */
	private String decreeDescriptionNormalized;
	
	/** The decree type id. */
	private Long decreeTypeId;
	
	private Long attachmentId;
	
	/**
	 * Gets the decree id.
	 *
	 * @return the decree id
	 */
	public Long getDecreeId() {
		return decreeId;
	}

	/**
	 * Sets the decree id.
	 *
	 * @param decreeId the new decree id
	 */
	public void setDecreeId(Long decreeId) {
		this.decreeId = decreeId;
	}

	/**
	 * Gets the decree number.
	 *
	 * @return the decree number
	 */
	public Long getDecreeNumber() {
		return decreeNumber;
	}

	/**
	 * Sets the decree number.
	 *
	 * @param decreeNumber the new decree number
	 */
	public void setDecreeNumber(Long decreeNumber) {
		this.decreeNumber = decreeNumber;
	}

	/**
	 * Gets the decree year.
	 *
	 * @return the decree year
	 */
	public Long getDecreeYear() {
		return decreeYear;
	}

	/**
	 * Sets the decree year.
	 *
	 * @param decreeYear the new decree year
	 */
	public void setDecreeYear(Long decreeYear) {
		this.decreeYear = decreeYear;
	}

	/**
	 * Gets the decree description.
	 *
	 * @return the decree description
	 */
	public String getDecreeDescription() {
		return decreeDescription;
	}

	/**
	 * Sets the decree description.
	 *
	 * @param decreeDescription the new decree description
	 */
	public void setDecreeDescription(String decreeDescription) {
		this.decreeDescription = decreeDescription;
	}

	/**
	 * Gets the decree description normalized.
	 *
	 * @return the decree description normalized
	 */
	public String getDecreeDescriptionNormalized() {
		return decreeDescriptionNormalized;
	}

	/**
	 * Sets the decree description normalized.
	 *
	 * @param decreeDescriptionNormalized the new decree description normalized
	 */
	public void setDecreeDescriptionNormalized(
			String decreeDescriptionNormalized) {
		this.decreeDescriptionNormalized = decreeDescriptionNormalized;
	}

	/**
	 * @return the decreeTypeId
	 */
	public Long getDecreeTypeId() {
		return decreeTypeId;
	}

	/**
	 * @param decreeTypeId the decreeTypeId to set
	 */
	public void setDecreeTypeId(Long decreeTypeId) {
		this.decreeTypeId = decreeTypeId;
	}

	/**
	 * @return the decreeTypeName
	 */


	@Override
	public String toString() {
		return "Decree [decreeId=" + decreeId + ", decreeNumber="
				+ decreeNumber + ", decreeYear=" + decreeYear
				+ ", decreeDescription=" + decreeDescription
				+ ", decreeDescriptionNormalized="
				+ decreeDescriptionNormalized + ", decreeTypeId="
				+ decreeTypeId  + ", attachmentId="
				+ attachmentId  + "]";
	}
	
	public void fillFromResultSet(ResultSet resultSet) throws Throwable {

		ResultSetMetaData metaData = resultSet.getMetaData();
		for (int i = 1; i <= metaData.getColumnCount(); ++i) {
			String columnLabel = metaData.getColumnLabel(i);
			if (columnLabel.equalsIgnoreCase("DECREE_ID")) {
				this.setDecreeId(resultSet.getLong("DECREE_ID"));
			} else if (columnLabel.equalsIgnoreCase("DECREE_NUMBER")) {
				this.setDecreeNumber(resultSet.getLong("DECREE_NUMBER"));
			} else if (columnLabel.equalsIgnoreCase("DECREE_YEAR")) {
				this.setDecreeYear(resultSet.getLong("DECREE_YEAR"));
			} else if (columnLabel.equalsIgnoreCase("DECREE_DESCRIPTION")) {
				this.setDecreeDescription(resultSet.getString("DECREE_DESCRIPTION"));
			} else if (columnLabel.equalsIgnoreCase("DECREE_DESCRIPTION_NORMALIZE")) {
				this.setDecreeDescriptionNormalized(resultSet.getString("DECREE_DESCRIPTION_NORMALIZE"));
			} else if (columnLabel.equalsIgnoreCase("DECREE_TYPE_ID")) {
				this.setDecreeTypeId(resultSet.getLong("DECREE_TYPE_ID"));
			} else if (columnLabel.equalsIgnoreCase("ATTACHMENT_ID")) {
				this.setAttachmentId(resultSet.getLong("ATTACHMENT_ID"));
			}
		}
	}

	public Long getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(Long attachmentId) {
		this.attachmentId = attachmentId;
	}
	

}
