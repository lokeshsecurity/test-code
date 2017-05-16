package bh.gov.cio.gbs.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bh.gov.cio.gbs.model.BoardSearchCriteria;
import bh.gov.cio.gbs.model.MemberSearchCriteria;


public class DataBaseUtil {
	
	
	public static final  String AND_OPERATOR = " AND ";
	
	public static List<Object> paramList(Object... values) {
		List<Object> params = new ArrayList<Object>();
		for (Object value : values) {
			if (value == null)
				params.add(null);
			else
				params.add(value);
		}
		return params;
	}

	public static String queryParamLike(String queryParam) {
		return "%" + queryParam.replaceAll(" ", "%") + "%";
	}
	
	public static String normalize (String text){
		return new ArabicNormalizer(text).getOutput();
	}

	public static String createSearchBoardQuery(String query, Map <String,Object> values) {
		int countOperator = 0;
		StringBuilder buildQuery = new StringBuilder(query);
		for(int i=0 ; i < BoardSearchCriteria.values().length ; i++ ){
			BoardSearchCriteria criteria = BoardSearchCriteria.values()[i];
			if(values.containsKey(criteria.getParam())){
				if(values.get(criteria.getParam())!= null){
					if(countOperator > 0)
						buildQuery.append(AND_OPERATOR).append(criteria.getQuery());
					else
						buildQuery.append(criteria.getQuery());
					
					countOperator++;
				}
			}
		}
		
		return buildQuery.toString();
	}
	
	public static String createSearchMemberQuery(String query, Map <String,Object> values) {
		int countOperator = 0;
		StringBuilder buildQuery = new StringBuilder(query);
		for(int i=0 ; i < MemberSearchCriteria.values().length ; i++ ){
			MemberSearchCriteria criteria = MemberSearchCriteria.values()[i];
			if(values.containsKey(criteria.getParam())){
				if(values.get(criteria.getParam())!= null){
					if(countOperator > 0)
						buildQuery.append(AND_OPERATOR).append(criteria.getQuery());
					else
						buildQuery.append(criteria.getQuery());
					
					countOperator++;
				}
			}
		}
		
		return buildQuery.toString();
	}

}
