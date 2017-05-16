package bh.gov.cio.gbs.model;

import java.sql.ResultSet;

// TODO: Auto-generated Javadoc
/**
 * The Class Role.
 */
public class SystemRole  extends BaseTo  implements FillFromResultSet{
	
	
	/** The id. */
	private Long id;

	/** The name. */
	private String name;


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
	public Long getId() {
		return id;
	}


	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
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
	
	

}
