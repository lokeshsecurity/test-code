package bh.gov.cio.gbs.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

// TODO: Auto-generated Javadoc
/**
 * The Class Organization.
 */
public class Organization extends BaseTo implements FillFromResultSet {

	/** The organization id. */
	private Long organizationId;

	/** The organization name. */
	private String organizationName;

	/** The organization name normalize. */
	private String organizationNameNormalize;
	
	/** The Organization Type Id. */
	private Long organizationTypeId;
	
	/** The organization type name. */
	private String organizationTypeName;
	
	/** The organization type description. */
	private String organizationTypeDescription;
	/**
	 * Gets the organization id.
	 * 
	 * @return the organization id
	 */
	public Long getOrganizationId() {
		return organizationId;
	}

	/**
	 * Sets the organization id.
	 * 
	 * @param organizationId
	 *            the new organization id
	 */
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	/**
	 * Gets the organization name.
	 * 
	 * @return the organization name
	 */
	public String getOrganizationName() {
		return organizationName;
	}
	
	/**
	 * Sets the organization name.
	 * 
	 * @param organizationName
	 *            the new organization name
	 */
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * Gets the organization name normalize.
	 * 
	 * @return the organization name normalize
	 */
	public String getOrganizationNameNormalize() {
		return organizationNameNormalize;
	}

	/**
	 * Sets the organization name normalize.
	 * 
	 * @param organizationNameNormalize
	 *            the new organization name normalize
	 */
	public void setOrganizationNameNormalize(String organizationNameNormalize) {
		this.organizationNameNormalize = organizationNameNormalize;
	}
	

	public Long getOrganizationTypeId() {
		return organizationTypeId;
	}

	public void setOrganizationTypeId(Long organizationTypeId) {
		this.organizationTypeId = organizationTypeId;
	}
	
	public String getOrganizationTypeName() {
		return organizationTypeName;
	}

	public void setOrganizationTypeName(String organizationTypeName) {
		this.organizationTypeName = organizationTypeName;
	}

	public String getOrganizationTypeDescription() {
		return organizationTypeDescription;
	}

	public void setOrganizationTypeDescription(String organizationTypeDescription) {
		this.organizationTypeDescription = organizationTypeDescription;
	}

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
			if (columnLabel.equalsIgnoreCase("ORGANIZATION_ID")) {
				this.setOrganizationId(resultSet.getLong("ORGANIZATION_ID"));
			} else if (columnLabel.equalsIgnoreCase("ORGANIZATION_NAME")) {
				this.setOrganizationName(resultSet.getString("ORGANIZATION_NAME"));
			} else if (columnLabel.equalsIgnoreCase("ORGANIZATION_NAME_NORMALIZED")) {
				this.setOrganizationNameNormalize(resultSet.getString("ORGANIZATION_NAME_NORMALIZED"));
			} else if (columnLabel.equalsIgnoreCase("ORGANIZATION_TYPE_ID")) {
				this.setOrganizationTypeId(resultSet.getLong("ORGANIZATION_TYPE_ID"));
			} else if (columnLabel.equalsIgnoreCase("ORGANIZATION_TYPE_NAME")) {
				this.setOrganizationTypeName(resultSet.getString("ORGANIZATION_TYPE_NAME"));
			} else if (columnLabel.equalsIgnoreCase("ORGANIZATION_TYPE_DESCRIPTION")) {
				this.setOrganizationTypeDescription(resultSet.getString("ORGANIZATION_TYPE_DESCRIPTION"));
			} else if (columnLabel.equalsIgnoreCase("CREATED_BY")) {
				this.setCreatedBy(resultSet.getInt("CREATED_BY"));
			} else if (columnLabel.equalsIgnoreCase("CREATED_ON")) {
				this.setCreatedOn(resultSet.getDate("CREATED_ON"));
			} else if (columnLabel.equalsIgnoreCase("UPDATED_BY")) {
				this.setUpdatedBy(resultSet.getInt("UPDATED_BY"));
			} else if (columnLabel.equalsIgnoreCase("UPDATED_ON")) {
				this.setUpdatedOn(resultSet.getDate("UPDATED_ON"));
			}
		}
	}

}
