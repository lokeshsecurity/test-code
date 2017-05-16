package bh.gov.cio.gbs.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import bh.gov.cio.gbs.util.DateUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class Attachment.
 */
public class Attachment extends BaseTo implements FillFromResultSet{

	/** The id. */
	private Long id;

	/** The mime. */
	private String mime;

	/** The size. */
	private Integer size;

	/** The name. */
	private String name;
	
	private String nameNormalized;
	
	private Integer typeId;
	
	private Long boardId;


	

	/**
	 * Gets the mime.
	 *
	 * @return the mime
	 */
	public String getMime() {
		return mime;
	}

	/**
	 * Sets the mime.
	 *
	 * @param mime the new mime
	 */
	public void setMime(String mime) {
		this.mime = mime;
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public Integer getSize() {
		return size;
	}

	/**
	 * Sets the size.
	 *
	 * @param size the new size
	 */
	public void setSize(Integer size) {
		this.size = size;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public void fillFromResultSet(ResultSet resultSet) throws Throwable {


		ResultSetMetaData metaData = resultSet.getMetaData();
		for (int i = 1; i <= metaData.getColumnCount(); ++i) {
			String columnLabel = metaData.getColumnLabel(i);
			if (columnLabel.equalsIgnoreCase("ATTACHMENT_ID")) {
				this.setId(resultSet.getLong("ATTACHMENT_ID"));
			} else if (columnLabel.equalsIgnoreCase("ATTACHMENT_NAME")) {
				this.setName(resultSet.getString("ATTACHMENT_NAME"));
			} else if (columnLabel.equalsIgnoreCase("ATTACHMENT_NAME_NORMALIZED")) {
				this.setNameNormalized(resultSet.getString("ATTACHMENT_NAME_NORMALIZED"));
			} else if (columnLabel.equalsIgnoreCase("ATTACHMENT_MIME")) {
				this.setMime(resultSet.getString("ATTACHMENT_MIME"));
			} else if (columnLabel.equalsIgnoreCase("ATTACHMENT_SIZE")) {
				this.setSize(resultSet.getInt("ATTACHMENT_SIZE"));
			}else if (columnLabel.equalsIgnoreCase("ATTACHMENT_TYPE_ID")) {
				this.setTypeId(Integer.valueOf(resultSet.getInt("ATTACHMENT_TYPE_ID")).equals(0) ? null : Integer.valueOf(resultSet.getInt("ATTACHMENT_TYPE_ID")));
			}else if (columnLabel.equalsIgnoreCase("BOARD_ID")) {
				this.setBoardId(resultSet.getLong("BOARD_ID"));
			}else if (columnLabel.equalsIgnoreCase("CREATED_ON")) {
				this.setCreatedOn(resultSet.getTimestamp("CREATED_ON")!=null ? DateUtil.getDateCalendar(resultSet.getTimestamp("CREATED_ON")).getTime() : null);
			}
		}
	
		
	}

	public String getNameNormalized() {
		return nameNormalized;
	}

	public void setNameNormalized(String nameNormalized) {
		this.nameNormalized = nameNormalized;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Long getBoardId() {
		return boardId;
	}

	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
