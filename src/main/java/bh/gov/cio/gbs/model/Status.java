package bh.gov.cio.gbs.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class Status extends BaseTo implements FillFromResultSet {

	private Integer statusId;
	private String statusName;
	private Integer statusTypeId;

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

	public Integer getStatusTypeId() {
		return statusTypeId;
	}

	public void setStatusTypeId(Integer statusTypeId) {
		this.statusTypeId = statusTypeId;
	}

	public void fillFromResultSet(ResultSet resultSet) throws Throwable {
		ResultSetMetaData metaData = resultSet.getMetaData();
		for (int i = 1; i <= metaData.getColumnCount(); ++i) {
			String columnLabel = metaData.getColumnLabel(i);
			if(columnLabel.equalsIgnoreCase("STATUS_ID")){
				this.setStatusId(resultSet.getInt("STATUS_ID"));
			}else if (columnLabel.equalsIgnoreCase("STATUS_NAME")) {
				this.setStatusName(resultSet.getString("STATUS_NAME"));
			}else if (columnLabel.equalsIgnoreCase("STATUS_TYPE_ID")) {
				this.setStatusTypeId(resultSet.getInt("STATUS_TYPE_ID"));
			}else if (columnLabel.equalsIgnoreCase("CREATED_BY")) {
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
