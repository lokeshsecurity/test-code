package bh.gov.cio.gbs.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

// TODO: Auto-generated Javadoc
/**
 * The Class AttachmentType.
 */
public class AttachmentType extends BaseTo implements FillFromResultSet {

	/** The attachment type id. */
	private Integer attachmentTypeId;
	
	/** The attachment type name. */
	private String attachmentTypeName;
	
	private short showFields;

	/**
	 * Gets the attachment type id.
	 *
	 * @return the attachment type id
	 */
	public Integer getAttachmentTypeId() {
		return attachmentTypeId;
	}

	/**
	 * Sets the attachment type id.
	 *
	 * @param attachmentTypeId the new attachment type id
	 */
	public void setAttachmentTypeId(Integer attachmentTypeId) {
		this.attachmentTypeId = attachmentTypeId;
	}

	/**
	 * Gets the attachment type name.
	 *
	 * @return the attachment type name
	 */
	public String getAttachmentTypeName() {
		return attachmentTypeName;
	}

	/**
	 * Sets the attachment type name.
	 *
	 * @param attachmentTypeName the new attachment type name
	 */
	public void setAttachmentTypeName(String attachmentTypeName) {
		this.attachmentTypeName = attachmentTypeName;
	}

	/* (non-Javadoc)
	 * @see bh.gov.cio.gbs.model.FillFromResultSet#fillFromResultSet(java.sql.ResultSet)
	 */
	public void fillFromResultSet(ResultSet resultSet) throws Throwable {
		// TODO Auto-generated method stub

		ResultSetMetaData metaData = resultSet.getMetaData();
		for (int i = 1; i <= metaData.getColumnCount(); ++i) {
			String columnLabel = metaData.getColumnLabel(i);
			if (columnLabel.equalsIgnoreCase("ATTACHMENT_TYPE_ID")) {
				this.setAttachmentTypeId(resultSet.getInt("ATTACHMENT_TYPE_ID"));
			} else if (columnLabel.equalsIgnoreCase("ATTACHMENT_TYPE_NAME")) {
				this.setAttachmentTypeName(resultSet.getString("ATTACHMENT_TYPE_NAME"));
			} else if (columnLabel.equalsIgnoreCase("SHOW_FIELDS")) {
				this.setShowFields(resultSet.getShort("SHOW_FIELDS"));
			}
			
			
			
		}
	}



	public short getShowFields() {
		return showFields;
	}

	public void setShowFields(short showFields) {
		this.showFields = showFields;
	}

	@Override
	public String toString() {
		return "AttachmentType [attachmentTypeId=" + attachmentTypeId
				+ ", attachmentTypeName=" + attachmentTypeName
				+ ", showFields=" + showFields + "]";
	}

	
	
}
