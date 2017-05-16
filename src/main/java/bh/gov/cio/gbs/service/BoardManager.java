package bh.gov.cio.gbs.service;

import java.util.Date;
import java.util.List;

import bh.gov.cio.gbs.exception.BoardException;
import bh.gov.cio.gbs.exception.BusinessException;
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
import bh.gov.cio.gbs.model.JobRunnableType;
import bh.gov.cio.gbs.model.Member;
import bh.gov.cio.gbs.model.MemberHistory;
import bh.gov.cio.gbs.model.MemberNotification;
import bh.gov.cio.gbs.model.MemberSearchResult;
import bh.gov.cio.gbs.model.NotificationPeriod;
import bh.gov.cio.gbs.model.NotificationRepeat;
import bh.gov.cio.gbs.model.NotificationStatus;
import bh.gov.cio.gbs.model.Notifications;
import bh.gov.cio.gbs.model.Organization;
import bh.gov.cio.gbs.model.Role;
import bh.gov.cio.gbs.model.Status;
import bh.gov.cio.gbs.model.Subscriber;

public interface BoardManager {

	List<Organization> getOrganizations();
	
	List<Organization> getSourceOrgnizations();

	List<Organization> getDestinationOrganizations();

	List<DecreeType> getDecreeTypes();

	void createLoginEvent(String email, String sessionId);

	void createLogoutEvent(String name, String sessionId);
	
	List<Member> getMembers();
	
	List<BoardMembers> getBoardMembers();
	
	List<BoardDecrees> getBoardDecrees();

	List<Role> getRoles();

	List<AttachmentType> getAttachmentTypes();
	
	List <Notifications> getRunnableNotifications();

	List<Subscriber> getSubscriberEmailByNID(Long notificationId);

	void createNotificationEvent(Integer notificationID, Date date, List<String> emailList, JobRunnableType JobType, NotificationStatus NotificationStatus, String eventDescription);

	List<BoardType> getBoardTypes();

	List<NotificationPeriod> getNotificationPeriods();

	void updateNotificationDate(Date updatedNotificationDate,Date updatedLastRunDate,Integer integer);

	List<NotificationRepeat> getNotificationRepeats();

	List<Member> searchMembers(String text);

	public void addBoard(BoardJSON boardJSON , List<?> decreeList,List<?> memberList) throws BoardException;
	
	Boolean isValidCPRNumber(Integer cprNumber);

	Boolean isDecreeExists(Integer decreeTypeId, Integer decreeNumber,
			Integer decreeYear);

	Boolean isBoardNameExists(String boardName, Integer boardTypeId);

	List<BoardNotification> getBoardNotifications();
	
	BoardNotification getNotificationByBoardId(Integer boardId);
	
	List<GovernmentBoardType> getGovernmentBoardTypes();

	List<BoardDecrees> searchBoard(Integer governmentBoardId, String boardName,
			Integer sourceOrganizationId, Integer decreeNumber,
			Integer decreeYear, Integer decreeTypeId, Date startDateFrom,
			Date startDateTo, Date endDateFrom, Date endDateTo,
			Integer destinationOrganizationPrimaryId, Integer statusId);

	List<Status> getBoardStatusList();

	List<Board> searchBoard(String text);
	
	String getLoggedInUsername();

	String getUserBySessionId(String sessionId);

	void recordSessionTimeoutEvent(String username, String sessionId);

	List<Status> getMemberStatusList();

	List<MemberSearchResult> searchMember(Integer cprNumber, String memberName,
			String occupation, Integer organizationId,
			Integer organizaionOnBehalfId, Integer roleId, Date startDateFrom,
			Date startDateTo, Date endDateFrom, Date endDateTo, Integer statusId);

	List<BoardDecrees> getDecreesByBoardId(Integer boardId);

	Board getBoard(Integer boardId);

	List<Member> getBoardMembers(Integer boardId);

	void updateBoard(BoardJSON boardJSON, List<?> decreeList, List<?> memberList) throws BoardException;

	List<AttachmentType> getAttachmentTypesForMembers();

	void updateExpiredBoardsStatus(Long boardId, String jobLoggedinUser, Date updatedOn);

	List<Decree> getboardDecrees(Integer boardID);

	List<Board> getExpiredBoards();

	void createUpdateExpiredBoardEvent(Board expiredBoard,
			String jobLoggedinUser, Date createdOn, String name,Integer expiredBoardStatus);

	void updateExpiredBoardsStatusAndLogBoardHistory(Board expiredBoard,
			String jobLoggedinUser, Date createdOn, String name);

	List<MemberHistory> getMemberHistoryLog(Integer memberId);
	
	List<BoardHistrory> getBoardHistoryLog(Integer boardId);
	
	List<MemberNotification> getMemberNotifications();
	
	void sendBoardNotificationMail(BoardNotification boardNotification , List<String> emailsList) throws BusinessException;

	List<Member> getExpiredMembers();

	void updateExpiredMembersStatusAndLogMemberHistory(Member expiredMember,
			String jobLoggedinUser, Date createdOn, String name);

	void createUpdateExpiredMemberEvent(Member member, String loggedInUsername,Date createdOn, String name,Integer expiredMemberStatus);

	void sendMemberNotificationMail(MemberNotification notification,
			List<String> emailsList) throws BusinessException;
	}
