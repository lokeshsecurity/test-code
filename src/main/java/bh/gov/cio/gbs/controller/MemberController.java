package bh.gov.cio.gbs.controller;

import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import bh.gov.cio.gbs.exception.BoardException;
import bh.gov.cio.gbs.exception.DatabaseException;
import bh.gov.cio.gbs.model.Member;
import bh.gov.cio.gbs.model.MemberHistory;
import bh.gov.cio.gbs.model.MemberSearchResult;
import bh.gov.cio.gbs.service.BoardManager;
import bh.gov.cio.gbs.util.CommonUtil;
import bh.gov.cio.gbs.util.DateUtil;


@Controller 
@RequestMapping("/member")
@RolesAllowed(value={"ROLE_ADMIN" , "ROLE_USER"})
public class MemberController {
	
	
private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private BoardManager boardManager;
	
	
	@RequestMapping(value="/search/{text}" , method=RequestMethod.GET , produces="application/json")
	public @ResponseBody List<Member> searchMembers(@PathVariable("text") String text) throws Throwable{
		logger.info("searchMembers()" , text);
		return boardManager.searchMembers(URLDecoder.decode(text,"UTF-8"));
	}
	
	@RequestMapping(value="/isvalidcpr/{CPR}" , method=RequestMethod.GET , produces="application/json")
	public @ResponseBody Boolean isValidCPRNumber(@PathVariable("CPR") String cprNumberStr) throws Throwable{
		logger.info("isValidCPRNumber()" , cprNumberStr);
		Integer cprNumber =CommonUtil.getInt(cprNumberStr);
		return boardManager.isValidCPRNumber(cprNumber);
	}
	
	@RequestMapping(value="/all" , method=RequestMethod.GET , produces="application/json")
	public @ResponseBody List<Member> getMembers(){
		logger.info("getMembers()");
		return boardManager.getMembers();
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(value = HttpStatus.CREATED)
	public @ResponseBody List<MemberSearchResult> searchMember(@RequestBody Map<String, ?> map , HttpServletResponse response) throws Throwable{
		logger.info("searchMember({})" , map.toString());
		try {
			
			Integer cprNumber = map.get("cprNumber") != null ? Integer.valueOf((String)map
					.get("cprNumber"))  : null;
					
			String memberName = !StringUtils.isBlank((String) map.get("name")) ? (String) map
					.get("name") : null;
					
			String occupation = !StringUtils.isBlank((String) map.get("occupation")) ? (String) map
					.get("occupation") : null;

			Integer organizationId = map.get("organization") != null ? (Integer) map
					.get("organization") : null;
					
			Integer organizaionOnBehalfId = map.get("organizaionOnBehalf") != null ? (Integer) map
					.get("organizaionOnBehalf") : null;
					
			Integer roleId = map.get("role") != null ? (Integer) map
					.get("role") : null;

			Date startDateFrom = (String) map.get("startDateFrom") != null ? DateUtil
					.getDate((String) map.get("startDateFrom")) : null;
					
			Date startDateTo = (String) map.get("startDateTo") != null ? DateUtil
					.getDate((String) map.get("startDateTo")) : null;
					
			Date endDateFrom = (String) map.get("endDateFrom") != null ? DateUtil
					.getDate((String) map.get("endDateFrom")) : null;
					
			Date endDateTo = (String) map.get("endDateTo") != null ? DateUtil
					.getDate((String) map.get("endDateTo")) : null;

			Integer statusId = map.get("status") != null ? (Integer) map
					.get("status") : null;		
					
					
			return boardManager.searchMember(cprNumber , memberName , occupation , organizationId , organizaionOnBehalfId , roleId , startDateFrom , startDateTo , endDateFrom , endDateTo , statusId);
		} catch (Throwable e) {
			e.printStackTrace();
			if(e instanceof DatabaseException){
				logger.info("Failed to searchMember Due to database error" , e.getMessage());
			}else if(e instanceof BoardException){
				logger.info("Failed to searchMember Due to service error" , e.getMessage());
			}else{
				logger.info("Failed to searchMember Due to other error" , e.getMessage());
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
			return null;
		}
	}
	
	@RequestMapping(value="/history/{memberId}" , method=RequestMethod.GET , produces="application/json")
	public @ResponseBody List<MemberHistory> getMemberHistoryLog(@PathVariable("memberId") Integer memberId){
		logger.info("getMemberHistoryLog : start()");
		return boardManager.getMemberHistoryLog(memberId);
	}

}
