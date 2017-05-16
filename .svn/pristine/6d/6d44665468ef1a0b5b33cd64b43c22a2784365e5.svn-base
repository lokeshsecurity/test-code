package bh.gov.cio.gbs.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

// TODO: Auto-generated Javadoc
/**
 * The Class DecreeType.
 */
public class DecreeType extends BaseTo implements FillFromResultSet {

	/** The decree type id. */
	private Long decreeTypeId;
	
	/** The decree type name. */
	private String decreeTypeName;
	
	/** The decree type name normalized. */
	private String decreeTypeNameNormalized;

	/**
	 * Gets the decree type id.
	 *
	 * @return the decree type id
	 */
	public Long getDecreeTypeId() {
		return decreeTypeId;
	}

	/**
	 * Sets the decree type id.
	 *
	 * @param decreeTypeId the new decree type id
	 */
	public void setDecreeTypeId(Long decreeTypeId) {
		this.decreeTypeId = decreeTypeId;
	}

	/**
	 * Gets the decree type name.
	 *
	 * @return the decree type name
	 */
	public String getDecreeTypeName() {
		return decreeTypeName;
	}

	/**
	 * Sets the decree type name.
	 *
	 * @param decreeTypeName the new decree type name
	 */
	public void setDecreeTypeName(String decreeTypeName) {
		this.decreeTypeName = decreeTypeName;
	}

	/**
	 * Gets the decree type name normalized.
	 *
	 * @return the decree type name normalized
	 */
	public String getDecreeTypeNameNormalized() {
		return decreeTypeNameNormalized;
	}

	/**
	 * Sets the decree type name normalized.
	 *
	 * @param decreeTypeNameNormalized the new decree type name normalized
	 */
	public void setDecreeTypeNameNormalized(String decreeTypeNameNormalized) {
		this.decreeTypeNameNormalized = decreeTypeNameNormalized;
	}

	/* (non-Javadoc)
	 * @see bh.gov.cio.gbs.model.FillFromResultSet#fillFromResultSet(java.sql.ResultSet)
	 */
	public void fillFromResultSet(ResultSet resultSet) throws Throwable {
		// TODO Auto-generated method stub
		ResultSetMetaData metaData = resultSet.getMetaData();
		for (int i = 1; i <= metaData.getColumnCount(); ++i) {
			String columnLabel = metaData.getColumnLabel(i);
			if (columnLabel.equalsIgnoreCase("DECREE_TYPE_ID")) {
				this.setDecreeTypeId(resultSet.getLong("DECREE_TYPE_ID"));
			} else if (columnLabel.equalsIgnoreCase("DECREE_TYPE_NAME")) {
				this.setDecreeTypeName(resultSet
						.getString("DECREE_TYPE_NAME"));
			} else if (columnLabel
					.equalsIgnoreCase("DECREE_TYPE_NAME_NORMALIZED")) {
				this.setDecreeTypeNameNormalized(resultSet
						.getString("DECREE_TYPE_NAME_NORMALIZED"));
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
