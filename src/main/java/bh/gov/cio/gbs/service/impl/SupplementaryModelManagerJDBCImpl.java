package bh.gov.cio.gbs.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bh.gov.cio.gbs.dataaccess.GbsDAO;
import bh.gov.cio.gbs.dataaccess.LookupDAO;
import bh.gov.cio.gbs.dataaccess.NotificationDAO;
import bh.gov.cio.gbs.exception.DatabaseException;
import bh.gov.cio.gbs.model.Attachment;
import bh.gov.cio.gbs.model.AttachmentJSON;
import bh.gov.cio.gbs.model.AttachmentType;
import bh.gov.cio.gbs.model.Board;
import bh.gov.cio.gbs.model.BoardDecrees;
import bh.gov.cio.gbs.model.BoardHistrory;
import bh.gov.cio.gbs.model.BoardJSON;
import bh.gov.cio.gbs.model.BoardMembers;
import bh.gov.cio.gbs.model.BoardNotification;
import bh.gov.cio.gbs.model.BoardType;
import bh.gov.cio.gbs.model.Decree;
import bh.gov.cio.gbs.model.DecreeType;
import bh.gov.cio.gbs.model.GovernmentBoardType;
import bh.gov.cio.gbs.model.Member;
import bh.gov.cio.gbs.model.MemberHistory;
import bh.gov.cio.gbs.model.MemberNotification;
import bh.gov.cio.gbs.model.MemberSearchResult;
import bh.gov.cio.gbs.model.NotificationHistory;
import bh.gov.cio.gbs.model.NotificationPeriod;
import bh.gov.cio.gbs.model.NotificationRepeat;
import bh.gov.cio.gbs.model.NotificationType;
import bh.gov.cio.gbs.model.Notifications;
import bh.gov.cio.gbs.model.Organization;
import bh.gov.cio.gbs.model.Role;
import bh.gov.cio.gbs.model.Status;
import bh.gov.cio.gbs.model.Subscriber;
import bh.gov.cio.gbs.model.UserLoginHistory;
import bh.gov.cio.gbs.service.SupplementaryModelManager;
import bh.gov.cio.gbs.util.DataBaseUtil;

public class SupplementaryModelManagerJDBCImpl implements SupplementaryModelManager {
	
	/** The Constant logger. */

	private static final Logger logger = LoggerFactory
			.getLogger(SupplementaryModelManagerJDBCImpl.class);
	
	
	private GbsDAO gbsDao;
	
	private LookupDAO lookupDao;
	
	private NotificationDAO notificationDao;

	public GbsDAO getGbsDao() {
		return gbsDao;
	}

	public void setGbsDao(GbsDAO gbsDao) {
		this.gbsDao = gbsDao;
	}

	public LookupDAO getLookupDao() {
		return lookupDao;
	}

	public void setLookupDao(LookupDAO lookupDao) {
		this.lookupDao = lookupDao;
	}

	public NotificationDAO getNotificationDao() {
		return notificationDao;
	}

	public void setNotificationDao(NotificationDAO notificationDao) {
		this.notificationDao = notificationDao;
	}

	public List<Organization> getOrganizations() {
		logger.info("start getOrganizations() ");
		return lookupDao.getOrganzations();
	}
	
	public List<Organization> getSourceOrgnizations(){
		logger.info("start getSourceOrgnizations() ");
		return lookupDao.getSourceOrgnizations();
	}
	
	public List<Organization> getDestinationOrganizations(){
		logger.info("start getDestinationOrganizations() ");
		return lookupDao.getDestinationOrganizations();
	}
	
	public List<DecreeType> getDecreeTypes() {
		logger.info("start getDecreeTypes() ");
		return lookupDao.getDecreeTypes();
	}

	public void recordLoginEvent(UserLoginHistory userLoginHistory) {
		logger.info("start recordLoginEvent() " , userLoginHistory);
		gbsDao.recordLoginEvent(userLoginHistory);
	}


	public List<Member> getMembers() {
		logger.info("start getMembers() " );
		return gbsDao.getMembers();
	}
	public List<BoardMembers> getBoardMembers() {
		logger.info("start getBoardMembers() " );
		return gbsDao.getBoardMembers();
	}
	public List<BoardDecrees> getBoardDecrees() {
		logger.info("start getBoardDecrees() " );
		return gbsDao.getBoardDecrees();
	}

	public List<Role> getRoles() {
		logger.info("start getRoles() " );
		return lookupDao.getRoles();
	}
	
	public List <Notifications> getRunnableNotifications() {
		logger.info("start getRunnableNotifications() " );
		return notificationDao.getRunnableNotifications();
	}

	public List<AttachmentType> getAttachmentTypes() {
		logger.info("start getAttachmentTypes() " );
		return lookupDao.getAttachmentTypes();
	}

	public void recordNotificationEvent(NotificationHistory notificationHistory) {
		logger.info("start recordNotificationEvent() " , notificationHistory);
		notificationDao.recordNotificationEvent(notificationHistory);
	}

	public List<Subscriber> getSubscriberEmailByNID(Long notificationId) {
		logger.info("start getSubscriberEmailByNID() " , notificationId);
		return notificationDao.getSubscriberEmailByNID(notificationId);

	}

	public List<BoardType> getBoardTypes() {
		logger.info("start getBoardTypes() " );
		return lookupDao.getBoardTypes();
	}

	public List<NotificationPeriod> getNotificationPeriods() {
		logger.info("start getNotificationPeriods() " );
		return notificationDao.getNotificationPeriods();
	}

	public List<NotificationRepeat> getNotificationRepeats() {
		logger.info("start getNotificationPeriods() " );
		return notificationDao.getNotificationRepeats();
	}

	public void updateNotificationDate(Date updatedNotificationDate,Date updatedLastRunDate,Integer notificationID) throws DatabaseException{
		logger.info("start getNotificationPeriods() " , updatedNotificationDate , updatedLastRunDate , notificationID);
		 notificationDao.updateNotificationDate(updatedNotificationDate,updatedLastRunDate,notificationID);
	}

	public List<Member> searchMembers(String text) {
		logger.info("start searchMembers() " );
		return gbsDao.searchMember(text);
	}

	public int addBoard(BoardJSON boardJSON,int statusID, String loggerUser, Date date) throws DatabaseException{
		logger.info("start addBoard() " , boardJSON , statusID);
		return gbsDao.addBoard(boardJSON,statusID, loggerUser,date);
	}
	
	public int addDecree(int decreeNumber, int decreeYear, int decreeTypeId, Date issueDateMelady, String issueDateHigry, String notes, String loggerUser, Date date) throws DatabaseException{
		logger.info("start addDecree() " , decreeNumber , decreeYear , decreeTypeId , issueDateMelady , issueDateHigry);
		return gbsDao.addDecree(decreeNumber, decreeYear, decreeTypeId, issueDateMelady, issueDateHigry, notes, loggerUser,date);
	}
	
	public void addBoardDecree(int boardId, Integer[] decreeIds) throws DatabaseException{
		logger.info("start addBoardDecree() " , boardId , decreeIds);
		gbsDao.addBoardDecree(boardId, decreeIds);
	}
	
	public int addMember(int boardId, Integer cprNumber, Integer roleId,
			String name, String nameNormalized, String occupation,
			String occupationNormlized, Integer organizationId,
			Integer organizaionOnBehalfId, Date startDate, Date endDate,
			int memberStatusID, String loggerUser, Date date, Integer notificationBefore, Integer notificationAfter) throws DatabaseException {
		logger.info("start addMember() ", boardId, cprNumber, roleId, name,
				nameNormalized, occupation, occupationNormlized,
				organizationId, organizaionOnBehalfId, startDate, endDate,
				memberStatusID,notificationBefore,notificationAfter);
		
		return gbsDao.addMember(boardId, cprNumber, roleId, name,
				nameNormalized, occupation, occupationNormlized,
				organizationId, organizaionOnBehalfId, startDate, endDate,
				memberStatusID, loggerUser,date,notificationBefore,notificationAfter);
	}
	
	public void addMemberDecree(Integer[] memberIds, Long decreeId) throws DatabaseException{
		logger.info("start addMemberDecree() " , memberIds , decreeId);
		gbsDao.addMemberDecree(memberIds, decreeId);
	}
	
	public Boolean isDecreeExists(Integer decreeTypeId, Integer decreeNumber,
			Integer decreeYear) {
		logger.info("start isDecreeExists() " , decreeTypeId , decreeNumber , decreeYear);
		return gbsDao.isDecreeExists(decreeTypeId , decreeNumber , decreeYear);
	}

	public void addDestinationOrganization(int boardId, Integer[] destinationOrganizationIds) throws DatabaseException{
		logger.info("start addDestinationOrganization() " , boardId , destinationOrganizationIds);
		gbsDao.addDestinationOrganization(boardId, destinationOrganizationIds);
	}

	public int isBoardHaveExpiryDate(Integer boardTypeId) {
		logger.info("start isBoardHaveExpiryDate() " , boardTypeId);
		return gbsDao.isBoardHaveExpiryDate(boardTypeId);
	}

	public Integer addNotification(int boardId, BoardJSON boardJSON, String loggerUser, Date date, String type) throws DatabaseException{
		logger.info("start addNotification() " , boardId , boardJSON);
		return gbsDao.addNotification(boardId, boardJSON, loggerUser,date,type);		
	}

	public int addAttachment(AttachmentJSON attachment, int boardId,
			int decreeId, Integer attachmentTypeId, String fileNameWithTimeStamp, String loggerUser, Date date)
			throws DatabaseException {
		logger.info("start addAttachment() ", attachment, boardId, decreeId,
				attachmentTypeId, fileNameWithTimeStamp);
		return gbsDao.addAttachment(attachment, boardId, decreeId, attachmentTypeId,
				fileNameWithTimeStamp, loggerUser,date);

	}

	public Boolean isBoardNameExists(String boardName, Integer boardTypeId) {
		logger.info("start isBoardNameExists() " , boardName , boardTypeId);
		return gbsDao.isBoardNameExists(boardName , boardTypeId);
	}

	public List<BoardNotification> getBoardNotifications() {
		logger.info("start getBoardNotifications() " );
		return notificationDao.getBoardNotifications();
	}

	public List<GovernmentBoardType> getGovernmentBoardTypes() {
		logger.info("start getGovernmentBoardTypes() " );
		return lookupDao.getGovernmentBoardTypes();
	}

	public List<BoardDecrees> searchBoard(Integer governmentBoardId, String boardName,
			Integer sourceOrganizationId, Integer decreeNumber,
			Integer decreeYear, Integer decreeTypeId, Date startDateFrom,
			Date startDateTo, Date endDateFrom, Date endDateTo,
			Integer destinationOrganizationPrimaryId ,Integer statusId) {
		

		logger.info("start searchBoard() " , governmentBoardId, boardName,
				sourceOrganizationId, decreeNumber,
				decreeYear, decreeTypeId, startDateFrom,
				startDateTo, endDateFrom, endDateTo,
				destinationOrganizationPrimaryId , statusId );
		
		return gbsDao.searchBoard(governmentBoardId, boardName,
				sourceOrganizationId, decreeNumber,
				decreeYear, decreeTypeId, startDateFrom,
				startDateTo, endDateFrom, endDateTo,
				destinationOrganizationPrimaryId , statusId);
	}

	public List<Status> getBoardStatusList() {
		logger.info("start getBoardStatusList() " );
		return lookupDao.getBoardStatusList();
	}

	public List<Board> searchBoard(String text) {
		logger.info("start searchBoard() " );
		return gbsDao.searchBoard(text);
	}

	public void recordBoardEvent(BoardHistrory boardHistrory) {
		logger.info("start recordBoardEvent() " );
		gbsDao.recordBoardEvent(boardHistrory);
		
	}

	public void recordMemberEvent(MemberHistory memberHistory) {
		logger.info("start recordMemberEvent() " );
		gbsDao.recordMemberEvent(memberHistory);
		
	}

	public int getBoardValidStatus() {
		// TODO Auto-generated method stub
		return gbsDao.getBoardValidStatus();
	}

	public int getBoardNewStatus() {
		// TODO Auto-generated method stub
		return gbsDao.getBoardNewStatus();
	}

	public int getMemberValidStatus() {
		// TODO Auto-generated method stub
		return gbsDao.getMemberValidStatus();
	}

	public String getUserBySessionId(String sessionId) {
		return gbsDao.getUserBySessionId(sessionId);
	}

	public int getAttachmentTypeDecree() {
		return gbsDao.getAttachmentTypeDecree();
	}

	public void addSubscriber(Integer notificationId, String loggedInUsername,
			Date createdon) {
		gbsDao.addSubscriber(notificationId,loggedInUsername,createdon);
		
	}

	public List<Status> getMemberStatusList() {
		logger.info("start getMemberStatusList() " );
		return lookupDao.getMemberStatusList();
	}

	public List<MemberSearchResult> searchMember(Integer cprNumber,
			String memberName, String occupation, Integer organizationId,
			Integer organizaionOnBehalfId, Integer roleId, Date startDateFrom,
			Date startDateTo, Date endDateFrom, Date endDateTo, Integer statusId) {
		
		
		logger.info("start searchMember({}) ", DataBaseUtil.paramList(
				cprNumber, memberName, occupation,
				organizationId, organizaionOnBehalfId, roleId, startDateFrom,
				startDateTo, endDateFrom, endDateTo,statusId));
		
		return gbsDao.searchMember(cprNumber, memberName, occupation,
				organizationId, organizaionOnBehalfId, roleId, startDateFrom,
				startDateTo, endDateFrom, endDateTo,statusId);
	}

	public BoardNotification getNotificationByBoardId(Integer boardId) {
		logger.info("start getNotificationByBoardId() ", boardId);
		
		return gbsDao.getNotificationByBoardId(boardId);
	}

	public List<BoardDecrees> getDecreesByBoardId(Integer boardId) {
		logger.info("start getDecreesByBoardId() ", boardId);
		
		return gbsDao.getDecreesByBoardId(boardId);
	}

	public Board getBoard(Integer boardId) {
		logger.info("start getBoard() " , boardId );
		return gbsDao.getBoard(boardId);
	}

	public List<Member> getBoardMembers(Integer boardId) {
		logger.info("start getBoardMembers() " , boardId );
		return gbsDao.getBoardMembers(boardId);
	}

	public void updateBoard(Integer boardID, Integer notificationBefore,
			Integer notificationEvery, String loggedInUsername, Date updateOn,Integer boardStatusID) {
		logger.info("start updateBoard() " , boardID, notificationBefore , notificationEvery,loggedInUsername, updateOn,boardStatusID );
		gbsDao.updateBoard(boardID, notificationBefore, notificationEvery, loggedInUsername, updateOn,boardStatusID);
		
	}

	public void updateNotification(Integer boardID,
			Integer notificationBefore, Integer notificationEvery, Date endDate,
			String loggedInUsername, Date updateOn) {
		logger.info("start updateNotification() " , boardID, notificationBefore, notificationEvery, loggedInUsername, updateOn);
		gbsDao.updateNotification(boardID, notificationBefore, notificationEvery, endDate, loggedInUsername, updateOn);
	}

	public void updateSubscriber(Long notificationId,
			String loggedInUsername, Date updateOn) {
		logger.info("start updateSubscriber() " , notificationId, loggedInUsername, updateOn);
		gbsDao.updateSubscriber(notificationId, loggedInUsername, updateOn);
		
	}

	public void renewMember(Integer boardID, Integer memberID,
			Integer cprNumber, Integer roleId, String name,
			String nameNormalized, String occupation,
			String occupationNormlized, Integer organizationId,
			Integer organizaionOnBehalfId, Date startDate, Date memberEndDate,
			int memberStatusID, String loggedInUsername, Date updateOn,Integer memberNotificationBefore, Integer memberNotificationAfter) {
		logger.info("start renewMember() " , boardID, memberEndDate, loggedInUsername, updateOn);
		gbsDao.renewMember(boardID,memberID,
				cprNumber, roleId, name,
				nameNormalized,  occupation,
				 occupationNormlized,  organizationId,
				 organizaionOnBehalfId,  startDate,  memberEndDate,
				 memberStatusID,  loggedInUsername,  updateOn,memberNotificationBefore,memberNotificationAfter);
	}

	public void updateMember(Integer boardID, Integer memberID,
			Integer cprNumber, Integer roleId, String name,
			String nameNormalized, String occupation,
			String occupationNormlized, Integer organizationId,
			Integer organizaionOnBehalfId, Date startDate, Date memberEndDate,
			String loggedInUsername, Date updateOn,  Integer memberNotificationBefore, Integer memberNotificationAfter, Integer memberStatusID) {
		logger.info("start updateMember() " , boardID, memberID, loggedInUsername, updateOn);
		gbsDao.updateMember(boardID,memberID,
				cprNumber, roleId, name,
				nameNormalized,  occupation,
				 occupationNormlized,  organizationId,
				 organizaionOnBehalfId,  startDate,  memberEndDate,
				 loggedInUsername,  updateOn,memberNotificationBefore,memberNotificationAfter,memberStatusID);
		
	}

	public List<AttachmentType> getAttachmentTypesForMembers() {
		logger.info("start getAttachmentTypesForMembers() " );
		return lookupDao.getAttachmentTypesForMembers();
	}

	public List<Notifications> getNotificationByBoardID(Integer boardID) {
		// TODO Auto-generated method stub
		return gbsDao.getNotificationByBoardID(boardID);
	}

	public boolean isSubscriberExists(Integer notificationId,
			String loggedInUsername) {
		return gbsDao.isSubscriberExists(notificationId,loggedInUsername);

	}

	public Integer[] getAttachmentTypesForDecree() {
		// TODO Auto-generated method stub
		return gbsDao.getAttachmentTypesForDecree();
	}

	public Member getMemberById(Integer memberID) {
		return gbsDao.getMemberById(memberID);

	}

	public int getMemberIncompleteStatus() {
		// TODO Auto-generated method stub
		return gbsDao.getMemberIncompleteStatus();
	}

	public String getBoardTypeName(Integer boardTypeId) {
		// TODO Auto-generated method stub
		return gbsDao.getBoardTypeName(boardTypeId);
	}

	public List<Decree> getmemberDecrees(Integer memberId,
			List<Long> decreesIdList) {
		
		return gbsDao.getMembersDecrees(memberId,decreesIdList);
	}

	public List<Decree> getboardDecrees(Integer boardID) {
		return gbsDao.getboardDecrees(boardID);

	}

	public void updateExpiredBoardsStatus(Long boardId,String jobLoggedinUser, Date updatedOn) {
		gbsDao.updateExpiredBoardsStatus(boardId,jobLoggedinUser,updatedOn);

	}

	public Integer getMemberStatus(Integer memberId) {
		return gbsDao.getMemberStatus(memberId);
	}

	public Integer getBoardStatus(Integer boardID) {
		return gbsDao.getBoardStatus(boardID);
	}

	public List<Board> getExpiredBoards() {
		return gbsDao.getExpiredBoards();
	}

	public int getBoardExpiredStatus() {
		return gbsDao.getBoardExpiredStatus();

	}

	public Integer addMemberNotification(Integer boardId, int memberId,
			Integer memberNotificationBefore, Integer memberNotificationEvery,
			Date endDate, String loggedInUsername, Date createdon,
			String type) {
		return gbsDao.addMemberNotification(boardId,memberId,
				memberNotificationBefore,memberNotificationEvery,endDate, loggedInUsername, createdon,type);
	}

	public List<MemberHistory> getMemberHistoryLog(Integer memberId) {
		return gbsDao.getMemberHistoryLog(memberId);
	}

	public List<BoardHistrory> getBoardHistoryLog(Integer boardId) {
		return gbsDao.getBoardHistoryLog(boardId);
	}

	public List<MemberNotification> getMemberNotifications() {
		logger.info("start getMemberNotifications() " );
		return notificationDao.getMemberNotifications();
	}

	public List<Member> getExpiredMembers() {
		return gbsDao.getExpiredMembers();
	}

	public int getMemberExpiredStatus() {
		return gbsDao.getMemberExpiredStatus();

	}

	public void updateExpiredMembersStatus(Integer memberId,
			String jobLoggedinUser, Date updatedOn) {
		gbsDao.updateExpiredMembersStatus(memberId,jobLoggedinUser,updatedOn);
	}

	public List<Decree> getmemberDecrees(int memberId) {
		logger.info("start getmemberDecrees() " );
		return gbsDao.getmemberDecrees(memberId);
	}

	public void deleteSubscriber(Integer boardID) {
		logger.info("start deleteSubscriber() " );
		gbsDao.deleteSubscriber(boardID);
	}

	public void deleteNotificationByBoardId(Integer boardID) {
		logger.info("start deleteNotificationByBoardId() " );
		gbsDao.deleteNotificationByBoardId(boardID);
	}

	public void updateMemberNotification(Integer memberID,
			Integer notificationBefore, Integer notificationEvery,
			Date memberEndDate, String loggedInUsername, Date updateOn) {
		logger.info("start updateMemberNotification() " );
		gbsDao.updateMemberNotification(memberID,notificationBefore,notificationEvery,memberEndDate,loggedInUsername,updateOn);
		
	}

	public List<Notifications> getNotificationByMemberID(Integer memberID) {
		logger.info("start getNotificationByMemberID() ", memberID);
		return gbsDao.getNotificationByMemberID(memberID);
	}

	public void deleteSubscriberByMemberId(Integer memberID) {
		logger.info("start deleteSubscriberByMemberId() " );
		gbsDao.deleteSubscriberByMemberId(memberID);
		
	}

	public void deleteNotificationByMemberId(Integer memberID) {
		logger.info("start deleteNotificationByMemberId() ",memberID );
		gbsDao.deleteNotificationByMemberId(memberID);
		
	}

	public Attachment getAttachmentbyBoardId(Integer boardId) {
		logger.info("start getattachmentbyBoardId() ",boardId );
		return gbsDao.getAttachmentbyBoardId(boardId);
	}

	public void updateBoardStatus(Integer boardID, String updatedBy,
			Date updateOn) {
		logger.info("start updateBoardStatus() ",boardID );
		gbsDao.updateBoardStatus(boardID,updatedBy,updateOn);
		
	}

	public void addBoardWithoutDecree(Integer boardId) {
		logger.info("start addBoardWithoutDecree() ",boardId );
		gbsDao.addBoardWithoutDecree(boardId);
		
	}

}
