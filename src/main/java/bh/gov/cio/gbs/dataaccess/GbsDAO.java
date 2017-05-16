package bh.gov.cio.gbs.dataaccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import bh.gov.cio.gbs.dataaccess.BaseDAO;
import bh.gov.cio.gbs.dataaccess.QueryStore;
import bh.gov.cio.gbs.exception.DatabaseException;
import bh.gov.cio.gbs.model.Attachment;
import bh.gov.cio.gbs.model.AttachmentJSON;
import bh.gov.cio.gbs.model.Board;
import bh.gov.cio.gbs.model.BoardHistrory;
import bh.gov.cio.gbs.model.BoardJSON;
import bh.gov.cio.gbs.model.BoardMembers;
import bh.gov.cio.gbs.model.BoardDecrees;
import bh.gov.cio.gbs.model.BoardNotification;
import bh.gov.cio.gbs.model.BoardSearchCriteria;
import bh.gov.cio.gbs.model.Decree;
import bh.gov.cio.gbs.model.Member;
import bh.gov.cio.gbs.model.MemberHistory;
import bh.gov.cio.gbs.model.MemberSearchCriteria;
import bh.gov.cio.gbs.model.MemberSearchResult;
import bh.gov.cio.gbs.model.Notifications;
import bh.gov.cio.gbs.model.UserLoginHistory;
import bh.gov.cio.gbs.util.APP_Constant;
import bh.gov.cio.gbs.util.CommonUtil;
import bh.gov.cio.gbs.util.DataBaseUtil;
import bh.gov.cio.gbs.util.DateUtil;
import bh.gov.cio.gbs.util.JSONUtil;


public class GbsDAO extends BaseDAO implements QueryStore{

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(GbsDAO.class);

	/**
	 * Test transaction.
	 * 
	 * @param input
	 *            the input
	 * @throws DataAccessException
	 *             the data access exception
	 */
	
	
	public List<Member> getMembers() throws DatabaseException{
		logger.info("getMembers()");
		return getList(QueryStore.Queries.GET_MEMBERS_QUERY, Member.class);
	}
	
	public int getBoardValidStatus() throws DatabaseException{
		logger.info("getBoardValidStatus()");
		return getInt(QueryStore.Queries.GET_BOARD_VALID_STATUS_QUERY, null);
	}
	
	public int getBoardNewStatus() throws DatabaseException{
		logger.info("getBoardNewStatus()");
		return getInt(QueryStore.Queries.GET_BOARD_NEW_STATUS_QUERY, null);
	}
	
	public int getMemberValidStatus() throws DatabaseException{
		logger.info("getMemberValidStatus()");
		return getInt(QueryStore.Queries.GET_MEMBER_VALID_STATUS_QUERY, null);
	}
	
	public int getMemberIncompleteStatus() throws DatabaseException{
		logger.info("getMemberIncompleteStatus()");
		return getInt(QueryStore.Queries.GET_MEMBER_INCOMPLETE_STATUS_QUERY, null);
	}
		
	public List<Member> searchMember(String text) throws DatabaseException{
		logger.info("searchMember({})" , text);
		String queryParam = DataBaseUtil.queryParamLike(DataBaseUtil.normalize(text));
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				queryParam), new String[] {"MEMBER_NAME_NORMALIZED"});
		return getList(QueryStore.Queries.SEARCH_MEMBER_QUERY,
				Member.class, params);
	}
	
	
	public List<Board> searchBoard(String text) throws DatabaseException{
		logger.info("searchBoard({})" , text);
		String queryParam = DataBaseUtil.queryParamLike(DataBaseUtil.normalize(text));
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				queryParam), new String[] {"BOARD_NAME_NORMALIZED"});
		return getList(QueryStore.Queries.SEARCH_BOARD_TEXT_QUERY,
				Board.class, params);
	}
	
	public Integer isBoardHaveExpiryDate(int boardTypeId) throws DatabaseException{
		logger.info("isBoardHaveExpiryDate({})" , boardTypeId);
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				boardTypeId), new String[] {"BOARD_TYPE_ID"});
		return getInt(QueryStore.Queries.GET_BOARD_TYPE_QUERY, params);
	}
	
	public List<BoardMembers> getBoardMembers() throws DatabaseException{
		logger.info("getBoardMembers()");
		return getList(QueryStore.Queries.GET_BOARD_MEMBERS_QUERY, BoardMembers.class);
	}
	
	public List<BoardDecrees> getBoardDecrees() throws DatabaseException{
		logger.info("getBoardsDecree()");
		return getList(QueryStore.Queries.GET_BOARDS_DECREE_QUERY, BoardDecrees.class);
	}
	
	public void testTransaction(String input) throws DatabaseException {
		logger.debug("testTransaction ({})", input);
		HashMap<String, Object> params = new HashMap<String, Object>();
		String query = queryById(QueryStore.Queries.TEST_QUERY);
		List<Object> result = namedParameterJdbcTemplate.query(query, params,
				new RowMapper<Object>() {
					public Object mapRow(ResultSet rs, int index)
							throws SQLException {
						logger.debug("Found result!");
						return null;
					}
				});

		logger.info("********************************" + (result == null));
	}

	public void recordLoginEvent(UserLoginHistory userLoginHistory) throws DatabaseException{
		logger.info("recordLoginEvent({})" , userLoginHistory);
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				userLoginHistory.getUsername(),
				userLoginHistory.getSessionId()!=null ? userLoginHistory.getSessionId() : null, userLoginHistory
						.getLoginStatus().name(), userLoginHistory
						.getLoginDate() != null ? new Timestamp(
						userLoginHistory.getLoginDate().getTime()) : null,
				userLoginHistory.getLogoutDate() != null ? new Timestamp(
						userLoginHistory.getLogoutDate().getTime()) : null , userLoginHistory.getAction()),
				new String[] { "USER_NAME", "SESSION_ID", "LOGIN_STATUS",
						"LOGIN_DATE", "LOGOUT_DATE" , "ACTION"});
		executeQuery(QueryStore.Queries.RECORD_LOGIN_HISTORY_QUERY, params);
	}


	public int addBoard(BoardJSON boardJSON, int statusID, String loggerUser, Date date) throws DatabaseException{
		logger.info("addBoard({})" , boardJSON , statusID , loggerUser , date);
		
		SqlParameterSource  params = fillSqlParams(
				DataBaseUtil.paramList(
						boardJSON.getName(),
						DataBaseUtil.normalize(boardJSON.getName()),
						boardJSON.getSourceOrganizationId(),
						boardJSON.getStartDate()!=null ? new Timestamp(boardJSON.getStartDate().getTime()) : null,
						boardJSON.getEndDate()!=null ? new Timestamp(boardJSON.getEndDate().getTime()) : null,
						boardJSON.getBoardTypeId(),
						boardJSON.getParentBoardId()!=null ? boardJSON.getParentBoardId() : null,
						statusID,
						boardJSON.getNotificationBefore(),
						boardJSON.getNotificationRepaet(),
						loggerUser,
						date), new String[] { "BOARD_NAME",
						"BOARD_NAME_NORMALIZED", "SOURCE_ORGANIZATION_ID",
						"BOARD_START_DATE", "BOARD_EXPIRY_DATE", "BOARD_TYPE_ID", "PARENT_BOARD_ID","STATUS_ID","NOTIFICATION_BEFORE","NOTIFICATION_EVERY", "CREATED_BY", "CREATED_ON" });
		
//		return executeQueryReturnInt(QueryStore.Queries.ADD_BOARD_QUERY, params);
		return executeQueryReturnInteger(QueryStore.Queries.ADD_BOARD_QUERY, params , new String[]{"board_id"});
	}
	
	public int addDecree(int decreeNumber, int decreeYear,int decreeTypeId,
						Date issueDateMelady, String issueDateHigry,String notes, String loggerUser, Date date) throws DatabaseException{
		logger.info("addDecree({})" , decreeNumber , decreeYear , decreeTypeId , issueDateMelady , issueDateHigry , loggerUser , date);
		
		SqlParameterSource  params = fillSqlParams(
				DataBaseUtil.paramList(
						decreeNumber,
						decreeYear,
						decreeTypeId,
						issueDateMelady,
						issueDateHigry,
						notes,
						DataBaseUtil.normalize(notes),
						loggerUser,
						date), new String[] { "DECREE_NUMBER","DECREE_YEAR",
						"DECREE_TYPE_ID", "ISSUE_DATE_MELADY", "ISSUE_DATE_HIGRY","DECREE_DESCRIPTION","DECREE_DESCRIPTION_NORMALIZE", "CREATED_BY", "CREATED_ON" });
		
//		return executeQueryReturnInt(QueryStore.Queries.ADD_DECREE_QUERY, params);
		return executeQueryReturnInteger(QueryStore.Queries.ADD_DECREE_QUERY, params , new String[]{"decree_id"});
	}

	public void addBoardDecree(int boardId, Integer[] decreeIds) throws DatabaseException{
		logger.info("addBoardDecree({})" , decreeIds.toString());
		
		List<Object[]> parameters = new ArrayList<Object[]>();
		for (Integer decreeId : decreeIds) {
		                     parameters.add(new Object[] { boardId, decreeId});

		}
	executeBatchUpdate(QueryStore.Queries.ADD_BOARD_DECREE_QUERY, parameters);
	}
	
	public int addMember(int boardId, Integer cprNumber, Integer roleId,
			String name, String nameNormalized, String occupation,
			String occupationNormlized, Integer organizationId,
			Integer organizaionOnBehalfId, Date startDate, Date endDate, int memberStatusID, String loggerUser, Date date, Integer notificationBefore, Integer notificationAfter) throws DatabaseException{
		logger.info("addMember({})" , boardId, cprNumber, roleId, name, nameNormalized, occupation, occupationNormlized, organizationId, organizaionOnBehalfId,startDate,endDate,memberStatusID,
				loggerUser,date);
		SqlParameterSource  params = fillSqlParams(
				DataBaseUtil.paramList(boardId, cprNumber, roleId, name, DataBaseUtil.normalize(nameNormalized), occupation, DataBaseUtil.normalize(occupationNormlized), organizationId, organizaionOnBehalfId,startDate,endDate,memberStatusID,
						loggerUser,
						date,notificationBefore,notificationAfter), 
							new String[] {"BOARD_ID",
										"CPR_NUMBER",
										"ROLE_ID",
										"MEMBER_NAME",
										"MEMBER_NAME_NORMALIZED",
										"MEMBER_OCCUPATION",
										"MEMBER_OCCUPATION_NORMLIZED",
										"ORGANIZATION_ID",
										"ORGANIZATION_ON_BEHALF_ID",
										"MEMBER_START_DATE",
										"MEMBER_END_DATE",
										"STATUS_ID","CREATED_BY", "CREATED_ON",
										"NOTIFICATION_BEFORE", "NOTIFICATION_EVERY" });
		
//		return executeQueryReturnInt(QueryStore.Queries.ADD_MEMBER_QUERY, params);
		return executeQueryReturnInteger(QueryStore.Queries.ADD_MEMBER_QUERY, params , new String[]{"member_id"});
	}
	
	public void addMemberDecree(Integer[] memberIds, Long decreeId) throws DatabaseException{
		logger.info("addMember({})" , memberIds.toString() , decreeId);
		
		List<Object[]> parameters = new ArrayList<Object[]>();
		for (Integer memberId : memberIds) {
		                     parameters.add(new Object[] { memberId, decreeId});

		}
	executeBatchUpdate(QueryStore.Queries.ADD_MEMBER_DECREE_QUERY, parameters);
	}
	
	public Boolean isDecreeExists(Integer decreeTypeId, Integer decreeNumber,
			Integer decreeYear) throws DatabaseException{
		logger.info("isDecreeExists({})" , decreeTypeId , decreeNumber , decreeYear);
		
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				decreeTypeId , decreeNumber , decreeYear), new String[] {"DECREE_TYPE_ID" , "DECREE_NUMBER" , "DECREE_YEAR"});
		
		return isExist(QueryStore.Queries.IS_DECREE_EXISTS_QUERY, params, true);
	}


	public void addDestinationOrganization(int boardId, Integer[] destinationOrganizationIds) throws DatabaseException{
			logger.info("addDestinationOrganization({})" , boardId , destinationOrganizationIds.toString());
			
			int isPrimary = 1;
			
			List<Object[]> parameters = new ArrayList<Object[]>();
			for (Integer destinationOrganizationId : destinationOrganizationIds) {
			                     parameters.add(new Object[] { boardId, destinationOrganizationId,isPrimary});
			     isPrimary = 0;

			}
		executeBatchUpdate(QueryStore.Queries.ADD_DESTINATION_ORGNIZATION_QUERY, parameters);
			
		}


	public int addNotification(int boardId, BoardJSON boardJSON, String loggerUser, Date date, String type) throws DatabaseException{
				logger.info("addNotification({})" , boardId , boardJSON , loggerUser , date, type);
				
				Integer actualDays = DateUtil.getActualDaysFromWorkingDays(boardJSON.getNotificationBefore());
				Date actualNotificationDate = DateUtil.getFirstActualDateOfDeliveryDate(boardJSON.getEndDate(), actualDays);
				
				SqlParameterSource  params = fillSqlParams(
						DataBaseUtil.paramList(boardId,boardJSON.getEndDate(),boardJSON.getNotificationRepaet(),actualNotificationDate,loggerUser, date, type), 
								new String[] { 	"BOARD_ID",
												"ACTUAL_DATE",
												"REPEAT_EVERY",
												"NOTIFICATION_DATE","CREATED_BY", "CREATED_ON","NOTIFICATION_TYPE"});
				
//				return executeQueryReturnInt(QueryStore.Queries.ADD_NOTIFICATION_QUERY, params);
				return executeQueryReturnInteger(QueryStore.Queries.ADD_NOTIFICATION_QUERY, params , new String[]{"notification_id"});
		
	}
	
	public int addMemberNotification(int boardId,int memberId, int notificationBefore, int notificationRepeat, Date memberEndDate, String loggerUser, Date date, String type) throws DatabaseException{
		logger.info("addMemberNotification({})" , boardId , memberId , loggerUser , date, type);
		
		Integer actualDays = DateUtil.getActualDaysFromWorkingDays(notificationBefore);
		Date actualNotificationDate = DateUtil.getFirstActualDateOfDeliveryDate(memberEndDate, actualDays);
		
		SqlParameterSource  params = fillSqlParams(
				DataBaseUtil.paramList(boardId,memberId,memberEndDate,notificationRepeat,actualNotificationDate,loggerUser, date, type), 
						new String[] { 	"BOARD_ID",
										"MEMBER_ID",
										"ACTUAL_DATE",
										"REPEAT_EVERY",
										"NOTIFICATION_DATE","CREATED_BY", "CREATED_ON","NOTIFICATION_TYPE"});
		
//		return executeQueryReturnInt(QueryStore.Queries.ADD_MEMBER_NOTIFICATION_QUERY, params);
		return executeQueryReturnInteger(QueryStore.Queries.ADD_MEMBER_NOTIFICATION_QUERY, params , new String[]{"notification_id"});

}
	
	public void updateNotification(int boardId, Integer notificationBefore, Integer notificationRepeat, Date endDate, String loggerUser, Date updateOn) throws DatabaseException{
		logger.info("updateNotification({})" , boardId, notificationBefore, endDate, loggerUser, updateOn);
		
		Integer actualDays = DateUtil.getActualDaysFromWorkingDays(notificationBefore);
		Date actualNotificationDate = DateUtil.getFirstActualDateOfDeliveryDate(endDate, actualDays);
		
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
						boardId,notificationRepeat,actualNotificationDate,loggerUser, updateOn), 
						new String[] { 	"BOARD_ID",
										"REPEAT_EVERY",
										"NOTIFICATION_DATE",
										"UPDATED_BY",
										"UPDATED_ON"});
		
		executeQuery(QueryStore.Queries.UPDATE_NOTIFICATION_QUERY, params);
}

	public int addAttachment(AttachmentJSON attachment, int boardId, int decreeId, Integer attachmentTypeId,String fileNameWithTimeStamp, String loggerUser, Date date) {
	logger.info("addAttachment({})" , attachment , boardId , decreeId , attachmentTypeId , fileNameWithTimeStamp , loggerUser , date);
		
	SqlParameterSource params = fillSqlParams(
				DataBaseUtil.paramList(
						decreeId == 0 ? null : decreeId,
						boardId == 0 ? null : boardId,
						fileNameWithTimeStamp,
						DataBaseUtil.normalize(fileNameWithTimeStamp),
						attachment.getSize(),
						attachment.getType(),
						attachmentTypeId,loggerUser,date), 
						new String[] { 	"DECREE_ID",
										"BOARD_ID",
										"ATTACHMENT_NAME",
										"ATTACHMENT_NAME_NORMALIZED",
										"ATTACHMENT_SIZE",
										"ATTACHMENT_MIME",
										"ATTACHMENT_TYPE_ID",
										"CREATED_BY",
										"CREATED_ON"});
		
//			return executeQueryReturnInt(QueryStore.Queries.ADD_ATTACHMENT_QUERY, params); used in DB2
			return executeQueryReturnInteger(QueryStore.Queries.ADD_ATTACHMENT_QUERY, params , new String[]{"attachment_id"});
		
	}


	public Boolean isBoardNameExists(String boardName, Integer boardTypeId) throws DatabaseException{
	logger.info("isBoardNameExists({}) : ",boardName ,  boardTypeId);
		
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				DataBaseUtil.normalize(boardName) , boardTypeId), new String[] {"BOARD_NAME_NORMALIZED" , "BOARD_TYPE_ID"});
		
		return isExist(QueryStore.Queries.IS_BOARD_NAME_EXISTS_QUERY, params, true);
	}


	public List<BoardDecrees> searchBoard(Integer governmentBoardId, String boardName,
			Integer sourceOrganizationId, Integer decreeNumber,
			Integer decreeYear, Integer decreeTypeId, Date startDateFrom,
			Date startDateTo, Date endDateFrom, Date endDateTo,
			Integer destinationOrganizationPrimaryId , Integer statusId) throws DatabaseException{
		
		logger.info("searchBoard({})" , DataBaseUtil.paramList(governmentBoardId, boardName,
				sourceOrganizationId, decreeNumber, decreeYear, decreeTypeId,
				startDateFrom, startDateTo, endDateFrom, endDateTo,
				destinationOrganizationPrimaryId , statusId));
		
		String query = queryById(QueryStore.Queries.SEARCH_BOARD_QUERY);
		
		Map <String,Object> values = new HashMap<String, Object>();
		values.put(BoardSearchCriteria.GOVERNMENT_BOARD.getParam(), governmentBoardId);
		values.put(BoardSearchCriteria.BOARD_NAME.getParam(), boardName!=null ? DataBaseUtil.queryParamLike(DataBaseUtil.normalize(boardName)) :null);
		values.put(BoardSearchCriteria.SOURCE_ORGANIZATION.getParam(), sourceOrganizationId);
		values.put(BoardSearchCriteria.DECREE_NUMBER.getParam(), decreeNumber);
		values.put(BoardSearchCriteria.DECREE_YEAR.getParam(), decreeYear);
		values.put(BoardSearchCriteria.DECREE_TYPE.getParam(), decreeTypeId);
		values.put(BoardSearchCriteria.FROM_START_DATE.getParam(), startDateFrom!=null ? new Timestamp(DateUtil.getStartOfDay(startDateFrom).getTime())  : null);
		values.put(BoardSearchCriteria.TO_START_DATE.getParam(), startDateTo!=null ? new Timestamp(DateUtil.getEndOfDay(startDateTo).getTime()) : null);
		values.put(BoardSearchCriteria.FROM_END_DATE.getParam(), endDateFrom!=null ? new Timestamp(DateUtil.getStartOfDay(endDateFrom).getTime()) : null);
		values.put(BoardSearchCriteria.TO_END_DATE.getParam(), endDateTo!=null ? new Timestamp(DateUtil.getEndOfDay(endDateTo).getTime()) : null);
		values.put(BoardSearchCriteria.PRIMARY_DESTINATION_ORGANIZATION.getParam(), destinationOrganizationPrimaryId);
		values.put(BoardSearchCriteria.BOARD_STATUS.getParam(), statusId);
		values.put(BoardSearchCriteria.ATTACHMENT_TYPE.getParam() , APP_Constant.NOT_AVAILABLE); // ADDED TO LIMIT RESULT TO GET ONLY BOARDS OF TYPE "مرجع"
		
		String executeQuery = DataBaseUtil.createSearchBoardQuery(query,values);
		
		logger.info("executeQuery : " + executeQuery);
		
		Map <String,Object> params = new HashMap<String, Object>();
		for(Entry<String, Object> entry :  values.entrySet()){
			if(entry.getValue()!=null && !entry.getValue().equals(APP_Constant.NOT_AVAILABLE))
				params.put(entry.getKey(), entry.getValue());
		}
		
		logger.info("params({})", params.toString());
		
		return getList(executeQuery, BoardDecrees.class, params, false);
	}


	public void recordBoardEvent(BoardHistrory boardHistrory) throws DatabaseException{
		
		logger.debug("recordBoardEvent({})" , boardHistrory);
		
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				boardHistrory.getBoardId(), boardHistrory.getBoardName(),
				boardHistrory.getBoardTypeId(), boardHistrory
						.getBoardStartDate() != null ? new Timestamp(
						boardHistrory.getBoardStartDate().getTime()) : null,
				boardHistrory.getBoardExpiryDate() != null ? new Timestamp(
						boardHistrory.getBoardExpiryDate().getTime()) : null,
						JSONUtil.convertListToJSON(boardHistrory.getDecrees()), boardHistrory
						.getLoggedInUser(), JSONUtil.convertListToJSON(boardHistrory.getMembers()),
				boardHistrory.getParentBoardId(), boardHistrory
						.getSourceOrganizationId(), Arrays.asList(boardHistrory
						.getDestinationOrganizationIds()).toString(), boardHistrory
						.getCreatedOn() != null ? new Timestamp(boardHistrory
						.getCreatedOn().getTime()) : null, boardHistrory
						.getStatus(),
				boardHistrory.getActionOperation().name(), boardHistrory
						.getBoardStatusId() , boardHistrory.getNotificationBefore() , boardHistrory.getNotificationRepeat(),boardHistrory.getNotificationDate()), new String[] { "BOARD_ID",
				"BOARD_NAME", "BOARD_TYPE_ID", "BOARD_START_DATE",
				"BOARD_EXPIRY_DATE", "DECREE_LIST",
				"CREATED_BY", "MEMBER_LIST", "PARENT_BOARD_ID",
				"SOURCE_ORGANIZATION_ID", "DESTINATION_ORGANIZATION_IDS",
				"CREATED_ON", "STATUS", "ACTION_OPERATION", "BOARD_STATUS_ID" , "NOTIFICATION_BEFORE" , "NOTIFICATION_EVERY", "NOTIFICATION_DATE"});
		executeQuery(QueryStore.Queries.RECORD_BOARD_HISTORY_QUERY, params);
	}


	public void recordMemberEvent(MemberHistory memberHistory) throws DatabaseException{
		
		logger.info("recordMemberEvent({})" , memberHistory);
		
		
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				memberHistory.getBoardId(), memberHistory.getBoardName(),
				memberHistory.getBoardTypeId(), memberHistory
						.getBoardStartDate() != null ? new Timestamp(
				memberHistory.getBoardStartDate().getTime()) : null,
				memberHistory.getBoardExpiryDate() != null ? new Timestamp(
				memberHistory.getBoardExpiryDate().getTime()) : null,
				memberHistory.getLoggedInUser(), memberHistory.getCprNumber(),
				memberHistory.getParentBoardId(), memberHistory.getMemberId(),
				memberHistory.getCreatedOn() != null ? new Timestamp(
				memberHistory.getCreatedOn().getTime()) : null,
				memberHistory.getMemberEndDate() != null ? new Timestamp(
				memberHistory.getMemberEndDate().getTime()) : null,
				memberHistory.getMemberName(), memberHistory
						.getMemberOccuption(), memberHistory
						.getMemberStartDate() != null ? new Timestamp(
						memberHistory.getMemberStartDate().getTime()) : null,
				memberHistory.getOrganizationId(), memberHistory
						.getOrganizationOnBehalfId(),
				memberHistory.getRoleId(), memberHistory.getStatus() , memberHistory.getActionOperation().name() , memberHistory.getMemberStatusId() , JSONUtil.convertListToJSON(memberHistory.getDecrees()) , memberHistory.getNotificationBefore() , memberHistory.getNotificationRepeat(),memberHistory.getNotificationDate()),
				new String[] { "BOARD_ID", "BOARD_NAME", "BOARD_TYPE_ID", "BOARD_START_DATE","BOARD_EXPIRY_DATE",
								"CREATED_BY","CPR_NUMBER","PARENT_BOARD_ID","MEMBER_ID","CREATED_ON","MEMBER_END_DATE","MEMBER_NAME","MEMBER_OCCUPATION","MEMBER_START_DATE","ORGANIZATION_ID","ORGANIZATION_ON_BEHALF_ID",
								"ROLE_ID","STATUS","ACTION_OPERATION","MEMBER_STATUS_ID","DECREE_LIST", "NOTIFICATION_BEFORE","NOTIFICATION_EVERY","NOTIFICATION_DATE"});
		executeQuery(QueryStore.Queries.RECORD_MEMBER_HISTORY_QUERY, params);
	}

	public String getUserBySessionId(String sessionId) {
		logger.info("getUserBySessionId({})" , sessionId);
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(sessionId) , new String[] {"SESSION_ID"});
		
		return getString(QueryStore.Queries.GET_USERNAME_BY_SESSION_ID_QUERY, params);
	}

	public int getAttachmentTypeDecree() {
		logger.info("getAttachmentTypeDecree()");
		return getInt(QueryStore.Queries.GET_ATTACHMENT_TYPE_DECREE_QUERY, null);
	}

	public void addSubscriber(Integer notificationId, String loggedInUsername, Date createdon) {
		logger.info("isBoardNameExists({}) : ",notificationId ,  loggedInUsername,createdon);
		
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(loggedInUsername, notificationId, loggedInUsername,createdon), new String[] {"SUBSCRIBER_EMAIL","NOTIFICATION_ID","CREATED_BY","CREATED_ON"});
		
		executeQuery(QueryStore.Queries.ADD_SUBSCRIBER_QUERY, params);
		
	}

	public List<MemberSearchResult> searchMember(Integer cprNumber,
			String memberName, String occupation, Integer organizationId,
			Integer organizaionOnBehalfId, Integer roleId, Date startDateFrom,
			Date startDateTo, Date endDateFrom, Date endDateTo, Integer statusId) {
		
		
		logger.info("searchMember({}) ", DataBaseUtil.paramList(
				cprNumber, memberName, occupation,
				organizationId, organizaionOnBehalfId, roleId, startDateFrom,
				startDateTo, endDateFrom, endDateTo,statusId));
		
		String query = queryById(QueryStore.Queries.SEARCH_MEMBER_FORM_QUERY);
		
		Map <String,Object> values = new HashMap<String, Object>();
		values.put(MemberSearchCriteria.CPR_NUMBER.getParam(), cprNumber);
		values.put(MemberSearchCriteria.MEMBER_NAME.getParam(), memberName!=null ? DataBaseUtil.queryParamLike(DataBaseUtil.normalize(memberName)) :null);
		values.put(MemberSearchCriteria.OCCUPATION.getParam(), occupation!=null ? DataBaseUtil.queryParamLike(DataBaseUtil.normalize(occupation)) :null);
		values.put(MemberSearchCriteria.ORGANIZATION.getParam(), organizationId);
		values.put(MemberSearchCriteria.ORGANIZATION_OBF.getParam(), organizaionOnBehalfId);
		values.put(MemberSearchCriteria.ROLE.getParam(), roleId);
		values.put(MemberSearchCriteria.FROM_START_DATE.getParam(), startDateFrom!=null ? new Timestamp(DateUtil.getStartOfDay(startDateFrom).getTime())  : null);
		values.put(MemberSearchCriteria.TO_START_DATE.getParam(), startDateTo!=null ? new Timestamp(DateUtil.getEndOfDay(startDateTo).getTime()) : null);
		values.put(MemberSearchCriteria.FROM_END_DATE.getParam(), endDateFrom!=null ? new Timestamp(DateUtil.getStartOfDay(endDateFrom).getTime()) : null);
		values.put(MemberSearchCriteria.TO_END_DATE.getParam(), endDateTo!=null ? new Timestamp(DateUtil.getEndOfDay(endDateTo).getTime()) : null);
		values.put(MemberSearchCriteria.MEMBER_STATUS.getParam(), statusId);
		
		
		String executeQuery = DataBaseUtil.createSearchMemberQuery(query,values);
		
		logger.info("executeQuery : " + executeQuery);
		
		Map <String,Object> params = new HashMap<String, Object>();
		for(Entry<String, Object> entry :  values.entrySet()){
			if(entry.getValue()!=null)
				params.put(entry.getKey(), entry.getValue());
		}
		
		logger.info("params({})", params.toString());
		
		return getList(executeQuery, MemberSearchResult.class, params, false);
	}

	public BoardNotification getNotificationByBoardId(Integer boardId) {
		logger.info("getNotificationByBoardId()");
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(boardId) , new String[] {"BOARD_ID"});
		BoardNotification boardNotification = getObject(QueryStore.Queries.GET_BOARD_NOTIFICATION_BY_ID_QUERY, params, BoardNotification.class);
		return boardNotification;
	}

	public List<BoardDecrees> getDecreesByBoardId(Integer boardId) {
		logger.info("getDecreesByBoardId()");
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(boardId) , new String[] {"BOARD_ID"});
		return getList(QueryStore.Queries.GET_DECREES_BY_BOARD_ID_QUERY,
				BoardDecrees.class, params);
	}

	public Board getBoard(Integer boardId) {
		logger.info("getBoard({}) : ", boardId);
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(boardId) , new String[] {"BOARD_ID"});
		Board board = getObject(QueryStore.Queries.GET_BOARD_QUERY, params, Board.class);
		Integer[] destorganizations = getBoardDestinationOrganizations(boardId);
		if(destorganizations!= null && destorganizations.length > 0){
			board.setDestinationOrganizationIds(destorganizations);
		}
		return board;
	}

	private Integer[] getBoardDestinationOrganizations(Integer boardId) {
		logger.info("getBoardDestinationOrganizations({}) : ", boardId);
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(boardId) , new String[] {"BOARD_ID"});
		List<Integer> destOrganizaionIds=getListInteger(QueryStore.Queries.GET_BOARD_DESTINATION_ORGANIZATIONS_QUERY, Integer.class, params);
		if(destOrganizaionIds!=null && destOrganizaionIds.size() > 0){
			return CommonUtil.convertToIntegerArray(destOrganizaionIds);
		}
		return null;
	}

	public List<Member> getBoardMembers(Integer boardId) {
		logger.info("getBoardMembers({}) : ", boardId);
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(boardId) , new String[] {"BOARD_ID"});
		return  getList(QueryStore.Queries.GET_BOARD_MEMBER_QUERY, Member.class , params);
	}

	public void updateBoard(Integer boardID, Integer notificationBefore,
			Integer notificationEvery, String loggedInUsername, Date updateOn, Integer boardStatusID) {
		logger.info("updateBoard() " , boardID, notificationBefore , notificationEvery,loggedInUsername, updateOn,boardStatusID );
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(boardID, notificationBefore , notificationEvery,loggedInUsername, updateOn,boardStatusID), new String[] {"BOARD_ID","NOTIFICATION_BEFORE" ,"NOTIFICATION_EVERY", "LOGGEDIN_USERNAME","UPDATE_ON","BOARD_STATUS_ID"});		
		executeQuery(QueryStore.Queries.UPDATE_BOARD_QUERY,params);
		

	}

	public void updateSubscriber(Long notificationId, String loggedInUsername, Date updateOn) {
			logger.info("updateSubscriber({}) : ", notificationId ,  loggedInUsername, updateOn);
		
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(loggedInUsername, notificationId, loggedInUsername,updateOn), new String[] {"SUBSCRIBER_EMAIL","NOTIFICATION_ID","UPDATED_BY","UPDATED_ON"});
		
		executeQuery(QueryStore.Queries.UPDATE_SUBSCRIBER_QUERY, params);
		
	}

	public void renewMember(Integer boardID, Integer memberID,
			Integer cprNumber, Integer roleId, String name,
			String nameNormalized, String occupation,
			String occupationNormlized, Integer organizationId,
			Integer organizaionOnBehalfId, Date startDate, Date memberEndDate,
			int memberStatusID, String loggedInUsername, Date updateOn, Integer memberNotificationBefore, Integer memberNotificationAfter) {
		logger.info("renewMember({}) : ", boardID, memberEndDate, loggedInUsername, updateOn);
		
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				boardID,memberID,
				cprNumber, roleId,
				name,DataBaseUtil.normalize(nameNormalized),
				occupation,DataBaseUtil.normalize(occupationNormlized) ,
				organizationId,organizaionOnBehalfId,
				startDate,  memberEndDate,
				 memberStatusID,  loggedInUsername,  updateOn,memberNotificationBefore,memberNotificationAfter),
				 new String[] {"BOARD_ID", "MEMBER_ID",
								"CPR_NUMBER", "ROLE_ID",
								"MEMBER_NAME","MEMBER_NAME_NORMALIZED",
								"MEMBER_OCCUPATION","MEMBER_OCCUPATION_NORMLIZED",
								"ORGANIZATION_ID","ORGANIZATION_ON_BEHALF_ID",
								"MEMBER_START_DATE","MEMBER_END_DATE",
								"STATUS_ID","UPDATED_BY","UPDATED_ON","NOTIFICATION_BEFORE","NOTIFICATION_EVERY"});
		
		executeQuery(QueryStore.Queries.UPDATE_MEMBER_QUERY, params);
		
	}

	public void updateMember(Integer boardID, Integer memberID,
			Integer cprNumber, Integer roleId, String name,
			String nameNormalized, String occupation,
			String occupationNormlized, Integer organizationId,
			Integer organizaionOnBehalfId, Date startDate, Date memberEndDate,
			String loggedInUsername, Date updateOn, Integer memberNotificationBefore, Integer memberNotificationAfter, Integer memberStatusID) {
		
		logger.info("updateMember({}) : ", boardID, memberID, loggedInUsername, updateOn);
		
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				boardID,memberID,
				cprNumber, roleId,
				name,DataBaseUtil.normalize(nameNormalized),
				occupation,DataBaseUtil.normalize(occupationNormlized) ,
				organizationId,organizaionOnBehalfId,
				startDate,  memberEndDate,
				loggedInUsername,  updateOn,memberNotificationBefore,memberNotificationAfter,memberStatusID),
				 new String[] {"BOARD_ID", "MEMBER_ID",
								"CPR_NUMBER", "ROLE_ID",
								"MEMBER_NAME","MEMBER_NAME_NORMALIZED",
								"MEMBER_OCCUPATION","MEMBER_OCCUPATION_NORMLIZED",
								"ORGANIZATION_ID","ORGANIZATION_ON_BEHALF_ID",
								"MEMBER_START_DATE","MEMBER_END_DATE",
								"UPDATED_BY","UPDATED_ON","NOTIFICATION_BEFORE","NOTIFICATION_EVERY","STATUS_ID"});
		
		executeQuery(QueryStore.Queries.UPDATE_MEMBER_QUERY, params);
		
	}

	public List<Notifications> getNotificationByBoardID(Integer boardID) {
		logger.info("getNotificationByBoardID({}) : ",boardID);

		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				boardID),
				 new String[] {"BOARD_ID"});
		return getList(QueryStore.Queries.GET_NOTIFICATION_BY_BOARD_ID_QUERY, Notifications.class , params);
	}

	public boolean isSubscriberExists(Integer notificationId,
			String loggedInUsername) {
		logger.info("isSubscriberExists({}) : ",notificationId ,  loggedInUsername);
		
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				notificationId , loggedInUsername), new String[] {"NOTIFICATION_ID" , "SUBSCRIBER_EMAIL"});
		
		return isExist(QueryStore.Queries.IS_SUBSCRIBER_EXISTS_QUERY, params, true);
	}
	
	public Integer[] getAttachmentTypesForDecree() {
		logger.info("getAttachmentTypesForDecree() ");
		List<Integer> attachmentTypeIds=getListInteger(QueryStore.Queries.GET_ATTACHMENT_TYPE_DECREE_QUERY, Integer.class, null);
		if(attachmentTypeIds!=null && attachmentTypeIds.size() > 0){
			return CommonUtil.convertToIntegerArray(attachmentTypeIds);
		}
		return null;
	}

	public Member getMemberById(Integer memberID) {
		logger.info("getMemberById({}) : ", memberID);
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(memberID) , new String[] {"MEMBER_ID"});
		return getObject(QueryStore.Queries.GET_MEMBER_QUERY, params, Member.class);		
	}

	public String getBoardTypeName(Integer boardTypeId) {
		logger.info("getBoardTypeName({}) : ", boardTypeId);
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(boardTypeId) , new String[] {"BOARD_TYPE_ID"});
		return getString(QueryStore.Queries.GET_BOARD_TYPE_NAME_QUERY, params);
	}

	public List<Decree> getMembersDecrees(Integer memberId,
			List<Long> decreesIdList) {
		logger.info("getMembersDecrees({}) : ",memberId);

		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				memberId,decreesIdList),
				 new String[] {"MEMBER_ID","DECREE_ID"});
		return getList(QueryStore.Queries.GET_MEMBERS_DECREES_QUERY, Decree.class , params);
	}

	public List<Decree> getboardDecrees(Integer boardID) {
		logger.info("getboardDecrees({}) : ",boardID);

		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				boardID),
				 new String[] {"BOARD_ID"});
		return getList(QueryStore.Queries.GET_BOARD_DECREES_QUERY, Decree.class , params);
	}

	public void updateExpiredBoardsStatus(Long boardId, String jobLoggedinUser, Date updatedOn) {
		logger.info("updateExpiredBoardsStatus() : ");
		
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				boardId,jobLoggedinUser,updatedOn),
				new String[] {"BOARD_ID","UPDATED_BY","UPDATED_ON"});
		
		executeQuery(QueryStore.Queries.UPDATE_EXPIRED_BOARDS_STATUS_QUERY, params);		
	}
	
	public List<Board> getExpiredBoards() {

		logger.info("getExpiredBoards() : ");
		
		Date today = new Date();

		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				new Timestamp(today.getTime())),
				new String[] {"TODAY"});
		
		List<Board> boards = getList(QueryStore.Queries.GET_EXPIRED_BOARDS_QUERY,Board.class, params);
	
		for(Board board : boards ){
			Integer[] destorganizations = getBoardDestinationOrganizations(board.getBoardId().intValue());
			if(destorganizations!= null && destorganizations.length > 0){
				board.setDestinationOrganizationIds(destorganizations);
			}
		}
		return boards;
	}

	public Integer getMemberStatus(Integer memberId) {
		logger.info("getMemberStatus()");
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				memberId),
				new String[] {"MEMBER_ID"});
		return getInt(QueryStore.Queries.GET_MEMBER_STATUS_QUERY, params);
	}

	public Integer getBoardStatus(Integer boardID) {
		logger.info("getBoardStatus()");
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(boardID),new String[] {"BOARD_ID"});
		return getInt(QueryStore.Queries.GET_BOARD_STATUS_QUERY, params);
	}
	
	public int getBoardExpiredStatus() throws DatabaseException{
		logger.info("getBoardExpiredStatus()");
		return getInt(QueryStore.Queries.GET_BOARD_EXPIRED_STATUS_QUERY, null);
	}

	public List<MemberHistory> getMemberHistoryLog(Integer memberId) {
		logger.info("getMemberHistoryLog()");
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				memberId),
				new String[] {"MEMBER_ID"});
		return getList(QueryStore.Queries.GET_MEMBER_HISTORY_LOG_QUERY, MemberHistory.class , params);
	}
		
	public List<BoardHistrory> getBoardHistoryLog(Integer boardId) {
		logger.info("getBoardHistoryLog({}) : ", boardId);
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				boardId),
				 new String[] {"BOARD_ID"});
		return getList(QueryStore.Queries.GET_BOARD_HISTORY_LOG_QUERY, BoardHistrory.class , params);
	}

	public List<Member> getExpiredMembers() {
		logger.info("getExpiredMembers() : ");
		
		Date today = new Date();

		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				new Timestamp(today.getTime())),
				new String[] {"TODAY"});
		
		return getList(QueryStore.Queries.GET_EXPIRED_MEMBERS_QUERY,Member.class, params);
	}

	public int getMemberExpiredStatus() {
		logger.info("getMemberExpiredStatus()");
		return getInt(QueryStore.Queries.GET_MEMBER_EXPIRED_STATUS_QUERY, null);
	}

	public void updateExpiredMembersStatus(Integer memberId,
			String jobLoggedinUser, Date updatedOn) {
		logger.info("updateExpiredMembersStatus() : ");
		
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				memberId,jobLoggedinUser,updatedOn),
				new String[] {"MEMBER_ID","UPDATED_BY","UPDATED_ON"});
		
		executeQuery(QueryStore.Queries.UPDATE_EXPIRED_MEMBERS_STATUS_QUERY, params);
		
	}

	public List<Decree> getmemberDecrees(int memberId) {
		logger.info("getmemberDecrees({}) : ",memberId);

		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				memberId),
				 new String[] {"MEMBER_ID"});
		return getList(QueryStore.Queries.GET_MEMBER_DECREE_QUERY, Decree.class , params);
	}

	public void deleteSubscriber(Integer boardID) {
		logger.info("deleteSubscriber()");	
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(boardID),new String[] {"BOARD_ID"});
		executeQuery(QueryStore.Queries.DELETE_SUBSCRIBER_QUERY, params);

	}

	public void deleteNotificationByBoardId(Integer boardID) {
		logger.info("deleteNotificationByBoardId()");	
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(boardID),new String[] {"BOARD_ID"});
		executeQuery(QueryStore.Queries.DELETE_NOTIFICATION_QUERY, params);
	}

	public void updateMemberNotification(Integer memberID,
			Integer notificationBefore, Integer notificationEvery,
			Date memberEndDate, String loggedInUsername, Date updateOn) {
		logger.info("updateMemberNotification({})", memberID , loggedInUsername , updateOn);
		
		Integer actualDays = DateUtil.getActualDaysFromWorkingDays(notificationBefore);
		Date actualNotificationDate = DateUtil.getFirstActualDateOfDeliveryDate(memberEndDate, actualDays);
		
		Map<String, Object> params = fillParams(
				DataBaseUtil.paramList(memberID,notificationEvery,actualNotificationDate,loggedInUsername, updateOn), 
						new String[] { 	"MEMBER_ID",
										"REPEAT_EVERY",
										"NOTIFICATION_DATE","UPDATED_BY", "UPDATED_ON"});
		executeQuery(QueryStore.Queries.UPDATE_MEMBER_NOTIFICATION_QUERY, params);
		
	}

	public List<Notifications> getNotificationByMemberID(Integer memberID) {
		logger.info("getNotificationByMemberID({}) : ",memberID);
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				memberID),
				 new String[] {"MEMBER_ID"});
		return getList(QueryStore.Queries.GET_NOTIFICATION_BY_MEMBER_ID_QUERY, Notifications.class , params);
	}

	public void deleteSubscriberByMemberId(Integer memberID) {
		logger.info("deleteSubscriberByMemberId()");	
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(memberID),new String[] {"MEMBER_ID"});
		executeQuery(QueryStore.Queries.DELETE_MEMBER_SUBSCRIBER_QUERY, params);
	}

	public void deleteNotificationByMemberId(Integer memberID) {
		logger.info("deleteNotificationByMemberId()");	
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(memberID),new String[] {"MEMBER_ID"});
		executeQuery(QueryStore.Queries.DELETE_MEMBER_NOTIFICATION_QUERY, params);
		
	}

	public Attachment getAttachmentbyBoardId(Integer boardId) {
		logger.info("getAttachmentbyBoardId()");	
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(boardId),new String[] {"BOARD_ID"});
		return getObject(QueryStore.Queries.GET_ATTACHMENT_BY_BOARD_ID_QUERY, params, Attachment.class);		
	}

	public void updateBoardStatus(Integer boardID, String updatedBy, Date updateOn) {
			logger.info("updateBoardStatus(boardID) : ",boardID);
			
			Map<String, Object> params = fillParams(DataBaseUtil.paramList(
					boardID,updatedBy,updateOn),
					new String[] {"BOARD_ID","UPDATED_BY","UPDATED_ON"});
			
			executeQuery(QueryStore.Queries.UPDATE_BOARD_STATUS_QUERY, params);
		
	}

	public void addBoardWithoutDecree(Integer boardId) {
		logger.info("addBoardWithoutDecree()" , boardId);
		
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				boardId),
				new String[] {"BOARD_ID"});
		
		executeQuery(QueryStore.Queries.ADD_BOARD_WITHOUT_DECREE_QUERY, params);

		
	}
}
