package bh.gov.cio.gbs.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class GovernmentBoardType extends BaseTo implements FillFromResultSet {

	private Integer governmentTypeId;
	private String governmentTypeName;

	public void fillFromResultSet(ResultSet resultSet) throws Throwable {

		ResultSetMetaData metaData = resultSet.getMetaData();
		for (int i = 1; i <= metaData.getColumnCount(); ++i) {
			String columnLabel = metaData.getColumnLabel(i);
			if (columnLabel.equalsIgnoreCase("GOVERNMENT_BOARD_TYPE_ID")) {
				this.setGovernmentTypeId(resultSet
						.getInt("GOVERNMENT_BOARD_TYPE_ID"));
			} else if (columnLabel
					.equalsIgnoreCase("GOVERNMENT_BOARD_TYPE_NAME")) {
				this.setGovernmentTypeName(resultSet
						.getString("GOVERNMENT_BOARD_TYPE_NAME"));
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

	public Integer getGovernmentTypeId() {
		return governmentTypeId;
	}

	public void setGovernmentTypeId(Integer governmentTypeId) {
		this.governmentTypeId = governmentTypeId;
	}

	public String getGovernmentTypeName() {
		return governmentTypeName;
	}

	public void setGovernmentTypeName(String governmentTypeName) {
		this.governmentTypeName = governmentTypeName;
	}

}
