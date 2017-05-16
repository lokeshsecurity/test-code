package bh.gov.cio.gbs.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

// TODO: Auto-generated Javadoc
/**
 * The Class BoardType.
 */
public class BoardType extends BaseTo implements FillFromResultSet {

	/** The board type id. */
	private Integer boardTypeId;
	
	/** The board type name. */
	private String boardTypeName;
	
	/** The board type name normalized. */
	private String boardTypeNameNormalized;
	
	private short haveExpiryDate;
	
	private short requiredLegislativeTool;

	/**
	 * Gets the board type id.
	 *
	 * @return the board type id
	 */
	public Integer getBoardTypeId() {
		return boardTypeId;
	}

	/**
	 * Sets the board type id.
	 *
	 * @param boardTypeId the new board type id
	 */
	public void setBoardTypeId(Integer boardTypeId) {
		this.boardTypeId = boardTypeId;
	}

	/**
	 * Gets the board type name.
	 *
	 * @return the board type name
	 */
	public String getBoardTypeName() {
		return boardTypeName;
	}

	/**
	 * Sets the board type name.
	 *
	 * @param boardTypeName the new board type name
	 */
	public void setBoardTypeName(String boardTypeName) {
		this.boardTypeName = boardTypeName;
	}

	/**
	 * Gets the board type name normalized.
	 *
	 * @return the board type name normalized
	 */
	public String getBoardTypeNameNormalized() {
		return boardTypeNameNormalized;
	}

	/**
	 * Sets the board type name normalized.
	 *
	 * @param boardTypeNameNormalized the new board type name normalized
	 */
	public void setBoardTypeNameNormalized(String boardTypeNameNormalized) {
		this.boardTypeNameNormalized = boardTypeNameNormalized;
	}

	/* (non-Javadoc)
	 * @see bh.gov.cio.gbs.model.FillFromResultSet#fillFromResultSet(java.sql.ResultSet)
	 */
	public void fillFromResultSet(ResultSet resultSet) throws Throwable {
		ResultSetMetaData metaData = resultSet.getMetaData();
		for (int i = 1; i <= metaData.getColumnCount(); ++i) {
			String columnLabel = metaData.getColumnLabel(i);
			if (columnLabel.equalsIgnoreCase("BOARD_TYPE_ID")) {
				this.setBoardTypeId(resultSet.getInt("BOARD_TYPE_ID"));
			} else if (columnLabel.equalsIgnoreCase("BOARD_TYPE_NAME")) {
				this.setBoardTypeName(resultSet.getString("BOARD_TYPE_NAME"));
			} else if (columnLabel.equalsIgnoreCase("BOARD_TYPE_NAME_NORMALIZE")) {
				this.setBoardTypeNameNormalized(resultSet.getString("BOARD_TYPE_NAME_NORMALIZE"));
			} else if (columnLabel.equalsIgnoreCase("CREATED_BY")) {
				this.setCreatedBy(resultSet.getInt("CREATED_BY"));
			} else if (columnLabel.equalsIgnoreCase("CREATED_ON")) {
				this.setCreatedOn(resultSet.getDate("CREATED_ON"));
			} else if (columnLabel.equalsIgnoreCase("UPDATED_BY")) {
				this.setUpdatedBy(resultSet.getInt("UPDATED_BY"));
			} else if (columnLabel.equalsIgnoreCase("UPDATED_ON")) {
				this.setUpdatedOn(resultSet.getDate("UPDATED_ON"));
			} else if (columnLabel.equalsIgnoreCase("HAVE_EXPIRY_DATE")) {
				this.setHaveExpiryDate(resultSet.getShort("HAVE_EXPIRY_DATE"));
			} else if (columnLabel.equalsIgnoreCase("REQUIRED_LEGISLATIVE_TOOL")) {
				this.setRequiredLegislativeTool(resultSet.getShort("REQUIRED_LEGISLATIVE_TOOL"));
			}
		}

	}

	public short getHaveExpiryDate() {
		return haveExpiryDate;
	}

	public void setHaveExpiryDate(short haveExpiryDate) {
		this.haveExpiryDate = haveExpiryDate;
	}

	public short getRequiredLegislativeTool() {
		return requiredLegislativeTool;
	}

	public void setRequiredLegislativeTool(short requiredLegislativeTool) {
		this.requiredLegislativeTool = requiredLegislativeTool;
	}
	

}
