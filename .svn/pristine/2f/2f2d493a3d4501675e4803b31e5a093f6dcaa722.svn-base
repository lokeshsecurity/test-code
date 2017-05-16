package bh.gov.cio.gbs.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

// TODO: Auto-generated Javadoc
/**
 * The Class Role.
 */
public class Role extends BaseTo  implements FillFromResultSet {
	
	/** The role id. */
	private Long roleId;
	
	/** The role name. */
	private String roleName;
	
	/** The role name normalized. */
	private String roleNameNormalized;
	
	private String validCount;
	
	

	/**
	 * Gets the role id.
	 *
	 * @return the role id
	 */
	public Long getRoleId() {
		return roleId;
	}



	/**
	 * Sets the role id.
	 *
	 * @param roleId the new role id
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}



	/**
	 * Gets the role name.
	 *
	 * @return the role name
	 */
	public String getRoleName() {
		return roleName;
	}



	/**
	 * Sets the role name.
	 *
	 * @param roleName the new role name
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}



	/**
	 * Gets the role name normalized.
	 *
	 * @return the role name normalized
	 */
	public String getRoleNameNormalized() {
		return roleNameNormalized;
	}



	/**
	 * Sets the role name normalized.
	 *
	 * @param roleNameNormalized the new role name normalized
	 */
	public void setRoleNameNormalized(String roleNameNormalized) {
		this.roleNameNormalized = roleNameNormalized;
	}



	/* (non-Javadoc)
	 * @see bh.gov.cio.gbs.model.FillFromResultSet#fillFromResultSet(java.sql.ResultSet)
	 */
	public void fillFromResultSet(ResultSet resultSet) throws Throwable {
		
		ResultSetMetaData metaData = resultSet.getMetaData();
		for (int i = 1; i <= metaData.getColumnCount(); ++i) {
			String columnLabel = metaData.getColumnLabel(i);
			if (columnLabel.equalsIgnoreCase("ROLE_ID")) {
				this.setRoleId(resultSet.getLong("ROLE_ID"));
			} else if (columnLabel.equalsIgnoreCase("ROLE_NAME")) {
				this.setRoleName(resultSet
						.getString("ROLE_NAME"));
			} else if (columnLabel
					.equalsIgnoreCase("ROLE_NORMALIZED")) {
				this.setRoleNameNormalized(resultSet
						.getString("ROLE_NORMALIZED"));
			} else if (columnLabel.equalsIgnoreCase("CREATED_BY")) {
				this.setCreatedBy(resultSet.getInt("CREATED_BY"));
			} else if (columnLabel.equalsIgnoreCase("CREATED_ON")) {
				this.setCreatedOn(resultSet.getDate("CREATED_ON"));
			} else if (columnLabel.equalsIgnoreCase("UPDATED_BY")) {
				this.setUpdatedBy(resultSet.getInt("UPDATED_BY"));
			} else if (columnLabel.equalsIgnoreCase("UPDATED_ON")) {
				this.setUpdatedOn(resultSet.getDate("UPDATED_ON"));
			}else if (columnLabel.equalsIgnoreCase("COUNT_VALID")) {
				this.setValidCount(resultSet.getString("COUNT_VALID"));
			}
		}
	}


	public String getValidCount() {
		return validCount;
	}



	public void setValidCount(String validCount) {
		this.validCount = validCount;
	}



	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName
				+ ", roleNameNormalized=" + roleNameNormalized
				+ ", validCount=" + validCount + "]";
	}

	
	
}
