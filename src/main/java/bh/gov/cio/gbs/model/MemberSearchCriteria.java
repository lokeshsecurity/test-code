package bh.gov.cio.gbs.model;

public enum MemberSearchCriteria {
	
	
	CPR_NUMBER("CPR_NUMBER"," CPR_NUMBER = :CPR_NUMBER "),
	MEMBER_NAME("MEMBER_NAME"," MEMBER_NAME_NORMALIZED LIKE :MEMBER_NAME "),
	OCCUPATION("OCCUPATION"," MEMBER_OCCUPATION_NORMLIZED LIKE :OCCUPATION "),
	ORGANIZATION("ORGANIZATION"," ORGANIZATION_ID =:ORGANIZATION "),
	ROLE("ROLE"," ROLE_ID =:ROLE "),
	ORGANIZATION_OBF("ORGANIZATION_OBF"," ORGANIZATION_ID_OBF =:ORGANIZATION_OBF "),
	FROM_START_DATE("FROM_START_DATE"," DATE(MEMBER_START_DATE) >= :FROM_START_DATE "),
	TO_START_DATE("TO_START_DATE"," DATE(MEMBER_START_DATE) <= :TO_START_DATE  "),
	FROM_END_DATE("FROM_END_DATE"," DATE(MEMBER_END_DATE) >= :FROM_END_DATE "),
	TO_END_DATE("TO_END_DATE"," DATE(MEMBER_END_DATE) <= :TO_END_DATE "),
	MEMBER_STATUS("MEMBER_STATUS" , " STATUS_ID = :MEMBER_STATUS ");
	
	
	private String param;
	private String query;
	
	MemberSearchCriteria(String param , String query){
		this.param = param;
		this.query = query;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

}
