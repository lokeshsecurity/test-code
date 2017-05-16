package bh.gov.cio.gbs.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import bh.gov.cio.gbs.exception.BoardException;
import bh.gov.cio.gbs.exception.DatabaseException;
import bh.gov.cio.gbs.model.Board;
import bh.gov.cio.gbs.model.BoardDecrees;
import bh.gov.cio.gbs.model.BoardHistrory;
import bh.gov.cio.gbs.model.BoardJSON;
import bh.gov.cio.gbs.model.BoardMembers;
import bh.gov.cio.gbs.model.BoardNotification;
import bh.gov.cio.gbs.model.Member;
import bh.gov.cio.gbs.model.MemberHistory;
import bh.gov.cio.gbs.model.Notifications;
import bh.gov.cio.gbs.service.BoardManager;
import bh.gov.cio.gbs.util.CommonUtil;
import bh.gov.cio.gbs.util.DateUtil;
import bh.gov.cio.gbs.util.JSONUtil;

@Controller
@RequestMapping("/board")
@RolesAllowed(value={"ROLE_ADMIN" , "ROLE_USER"})
public class BoardController {

	private static final Logger logger = LoggerFactory
			.getLogger(BoardController.class);

	@Autowired
	private BoardManager boardManager;
	
	int boardID;
	int decreeID;
	
	@RequestMapping(value="/members" , method=RequestMethod.GET , produces="application/json")
	public @ResponseBody List<Member> getMembers(){
		logger.info("getMembers : start()");
		return boardManager.getMembers();
	}
	
	@RequestMapping(value="/board/members" , method=RequestMethod.GET , produces="application/json")
	public @ResponseBody List<BoardMembers> getBoardMembers(){
		logger.info("getBoardMembers : start()");
		return boardManager.getBoardMembers();
	}

	@RequestMapping(value = "/decrees", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	List<BoardDecrees> getBoardDecree() {
		logger.info("getBoardDecrees : start()");
		return boardManager.getBoardDecrees();
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addBoard", method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(value = HttpStatus.CREATED)
	public @ResponseBody void addBoard(@RequestBody Map<String, ?> map , HttpServletResponse response) throws Throwable, JsonMappingException, IOException {
		logger.info("addBoard : start({})" , map.toString());
		try {
			
			Map<String,?> boardMap =(Map<String, ?>) map.get("board");
			List<?> decreeList = (List<?>) map.get("decreeList");
			List<?> memberList = (List<?>) map.get("memberList");
			String json = JSONUtil.converObjectToJSON(boardMap);
			BoardJSON boardJSON =JSONUtil.converJSONToObject(json, BoardJSON.class);
			boardManager.addBoard(boardJSON , decreeList , memberList);
			
		} catch (Throwable e) {
			 if(e instanceof BoardException){
				 BoardException  boardException = (BoardException) e;
				logger.info("Failed to add board ,  error " , e.getMessage() , e);
				response.sendError(boardException.getCode() , boardException.getMessage());
			}else{
				logger.info("Failed to add board , error " , e.getMessage() , e);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		}
		
	}
	
	@RequestMapping(value = "/isDecreeExists", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Boolean isDecreeExists(@RequestParam("decreeTypeId") Integer decreeTypeId,
			@RequestParam("decreeNumber") Integer decreeNumber,
			@RequestParam("decreeYear") Integer decreeYear) throws Throwable {
		
		return boardManager.isDecreeExists(decreeTypeId , decreeNumber , decreeYear);
	}
	
	@RequestMapping(value = "/isBoardNameExists", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Boolean isBoardNameExists(@RequestParam("boardName") String boardName,
			@RequestParam("boardTypeId") Integer boardTypeId) throws Throwable {
		
		return boardManager.isBoardNameExists(URLDecoder.decode(boardName,"UTF-8") , boardTypeId);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateBoard", method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(value = HttpStatus.CREATED)
	public @ResponseBody void updateBoard(@RequestBody Map<String, ?> map , HttpServletResponse response) throws Throwable, JsonMappingException, IOException {
		logger.info("updateBoard : start({})" , map.toString());
		try {
			
			Map<String,?> boardMap =(Map<String, ?>) map.get("board");
			List<?> decreeList = (List<?>) map.get("decreeList");
			List<?> memberList = (List<?>) map.get("memberList");
			String json = JSONUtil.converObjectToJSON(boardMap);
			BoardJSON boardJSON =JSONUtil.converJSONToObject(json, BoardJSON.class);
			boardManager.updateBoard(boardJSON , decreeList , memberList);
			
		} catch (Throwable e) {
			 if(e instanceof BoardException){
				 BoardException  boardException = (BoardException) e;
				logger.info("Failed to update board ,  error " , e.getMessage() , e);
				response.sendError(boardException.getCode() , boardException.getMessage());
			}else{
				logger.info("Failed to update board , error " , e.getMessage() , e);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		}
		
	}
	
	@RequestMapping(value="/notifications" , method=RequestMethod.GET , produces="application/json")
	public @ResponseBody List<Notifications> getRunnableNotifications(){
		logger.info("getRunnableNotifications()");
		return boardManager.getRunnableNotifications();
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(value = HttpStatus.CREATED)
	public @ResponseBody List<BoardDecrees> searchBoard(@RequestBody Map<String, ?> map , HttpServletResponse response) throws Throwable{
		logger.info("searchBoard({})" , map.toString());
		try {
			
			Integer governmentBoardId = map.get("governmentBoard") != null ? (Integer) map
					.get("governmentBoard") : null;
			String boardName = !StringUtils.isBlank((String) map.get("boardName")) ? (String) map
					.get("boardName") : null;

			Integer sourceOrganizationId = map.get("sourceOrganization") != null ? (Integer) map
					.get("sourceOrganization") : null;
			Integer decreeNumber = (String) map.get("decreeNumber") != null ? CommonUtil
					.getInt((String) map.get("decreeNumber")) : null;
			Integer decreeYear = (String) map.get("decreeYear") != null ? CommonUtil
					.getInt((String) map.get("decreeYear")) : null;
			Integer decreeTypeId = map.get("decreeType") != null ? (Integer) map
					.get("decreeType") : null;

			Date startDateFrom = (String) map.get("startDateFrom") != null ? DateUtil
					.getDate((String) map.get("startDateFrom")) : null;
			Date startDateTo = (String) map.get("startDateTo") != null ? DateUtil
					.getDate((String) map.get("startDateTo")) : null;
			Date endDateFrom = (String) map.get("endDateFrom") != null ? DateUtil
					.getDate((String) map.get("endDateFrom")) : null;
			Date endDateTo = (String) map.get("endDateTo") != null ? DateUtil
					.getDate((String) map.get("endDateTo")) : null;

			Integer destinationOrganizationPrimaryId = map
					.get("destinationOrganization") != null ? (Integer) map
					.get("destinationOrganization") : null;

			Integer statusId = map
					.get("status") != null ? (Integer) map
					.get("status") : null;		
					
			return boardManager.searchBoard(governmentBoardId, boardName,
					sourceOrganizationId, decreeNumber, decreeYear, decreeTypeId,
					startDateFrom, startDateTo, endDateFrom, endDateTo,
					destinationOrganizationPrimaryId , statusId);
		} catch (Throwable e) {
			if(e instanceof DatabaseException){
				logger.info("Failed to searchBoard Due to database error" , e.getMessage());
			}else if(e instanceof BoardException){
				logger.info("Failed to searchBoard Due to service error" , e.getMessage());
			}else{
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
			return null;
		}
	}
	
	@RequestMapping(value="/search/{text}" , method=RequestMethod.GET , produces="application/json")
	public @ResponseBody List<Board> searchMembers(@PathVariable("text") String text) throws Throwable{
		logger.info("searchMembers()" , text);
		return boardManager.searchBoard(URLDecoder.decode(text,"UTF-8"));
	}
	
	@RequestMapping(value="/notification/{boardId}" , method=RequestMethod.GET , produces="application/json")
	public @ResponseBody BoardNotification getNotificationByBoardId(@PathVariable("boardId") Integer boardId){
		logger.info("getNotificationByBoardId : start()");
		return boardManager.getNotificationByBoardId(boardId);
	}
	
	@RequestMapping(value="/decrees/{boardId}" , method=RequestMethod.GET , produces="application/json")
	public @ResponseBody List<BoardDecrees> getDecreesByBoardId(@PathVariable("boardId") Integer boardId){
		logger.info("getDecreesByBoardId : start()");
		return boardManager.getDecreesByBoardId(boardId);
	}
	
	
	@RequestMapping(value="/{boardId}" , method=RequestMethod.GET , produces="application/json")
	public @ResponseBody Board getBoard(@PathVariable("boardId") Integer boardId) throws Throwable{
		logger.info("getBoard()" , boardId);
		return boardManager.getBoard(boardId);
	}
	
	@RequestMapping(value="/members/{boardId}" , method=RequestMethod.GET , produces="application/json")
	public @ResponseBody List<Member> getBoardMember(@PathVariable("boardId") Integer boardId) throws Throwable{
		logger.info("getBoardMember()" , boardId);
		return boardManager.getBoardMembers(boardId);
	}
	
	@RequestMapping(value="/history/{boardId}" , method=RequestMethod.GET , produces="application/json")
	public @ResponseBody List<BoardHistrory> getBoardHistoryLog(@PathVariable("boardId") Integer boardId) throws Throwable{
		logger.info("getBoardHistoryLog()" , boardId);
		return boardManager.getBoardHistoryLog(boardId);
	}

}
