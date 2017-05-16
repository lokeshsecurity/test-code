package bh.gov.cio.gbs.service;

import java.util.Date;
import java.util.List;

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

public interface SupplementaryModelManager {

	List<Organization> getOrganizations();
	List<Organization> getSourceOrgnizations();
	List<Organization> getDestinationOrganizations();
	List<Member> getMembers();
	List<DecreeType> getDecreeTypes();
	List<BoardMembers> getBoardMembers();
	List<BoardDecrees> getBoardDecrees();
	List <Notifications> getRunnableNotifications();

	void recordLoginEvent(UserLoginHistory userLoginHistory);
	List<Role> getRoles();
	List<AttachmentType> getAttachmentTypes();
	void recordNotificationEvent(NotificationHistory notificationHistory);
	List<Subscriber> getSubscriberEmailByNID(Long notificationId);
	List<BoardType> getBoardTypes();
	List<NotificationPeriod> getNotificationPeriods();
	void updateNotificationDate(Date updatedNotificationDate,Date updatedLastRunDate,Integer notificationID) throws DatabaseException;
	List<NotificationRepeat> getNotificationRepeats();
	List<Member> searchMembers(String text);
	int addBoard(BoardJSON boardJSON, int statusID, String loggerUser, Date date);
	Boolean isDecreeExists(Integer decreeTypeId, Integer decreeNumber,
			Integer decreeYear);
	int addDecree(int decreeNumber, int decreeYear, int decreeTypeId, Date issueDateMelady, String issueDateHigry, String loggerUser, String notes, Date date) throws DatabaseException;	
	void addBoardDecree(int boardId, Integer[] decreeId) throws DatabaseException;
	void addMemberDecree(Integer[] memberIds, Long decreeId) throws DatabaseException;
	void addDestinationOrganization(int boardId,
			Integer[] destinationOrganizationIds) throws DatabaseException;
	int isBoardHaveExpiryDate(Integer boardTypeId);
	Integer addNotification(int boardId, BoardJSON boardJSON, String loggerUser, Date date, String type) throws DatabaseException;
	int addMember(int boardId, Integer cprNumber, Integer roleId, String name,
			String nameNormalized, String occupation,
			String occupationNormlized, Integer organizationId,
			Integer organizaionOnBehalfId, Date startDate, Date endDate, int memberStatusID, String loggerUser, Date date, Integer notificationBefore, Integer notificationAfter) throws DatabaseException;
	int addAttachment(AttachmentJSON attachment, int boardId, int decreeId, Integer attachmentTypeId, String fileNameWithTimeStamp, String loggerUser, Date date) throws DatabaseException;
	Boolean isBoardNameExists(String boardName, Integer boardTypeId);
	List<BoardNotification> getBoardNotifications();
	List<GovernmentBoardType> getGovernmentBoardTypes();
	List<BoardDecrees> searchBoard(Integer governmentBoardId, String boardName,
			Integer sourceOrganizationId, Integer decreeNumber,
			Integer decreeYear, Integer decreeTypeId, Date startDateFrom,
			Date startDateTo, Date endDateFrom, Date endDateTo,
			Integer destinationOrganizationPrimaryId, Integer statusId);
	List<Status> getBoardStatusList();
	List<Board> searchBoard(String text);
	void recordBoardEvent(BoardHistrory boardHistrory);
	void recordMemberEvent(MemberHistory memberHistory);
	
	int getBoardValidStatus();
	int getBoardNewStatus();
	int getMemberValidStatus();
	String getUserBySessionId(String sessionId);
	int getAttachmentTypeDecree();
	void addSubscriber(Integer notificationId, String loggedInUsername,
			Date createdon);
	List<Status> getMemberStatusList();
	List<MemberSearchResult> searchMember(Integer cprNumber, String memberName,
			String occupation, Integer organizationId,
			Integer organizaionOnBehalfId, Integer roleId, Date startDateFrom,
			Date startDateTo, Date endDateFrom, Date endDateTo, Integer statusId);
	Board getBoard(Integer boardId);
	List<Member> getBoardMembers(Integer boardId);
	
	BoardNotification getNotificationByBoardId(Integer boardId);
	List<BoardDecrees> getDecreesByBoardId(Integer boardId);
	void updateBoard(Integer boardID, Integer notificationBefore,
			Integer notificationEvery, String loggedInUsername, Date updateOn, Integer boardStatusID);
	void updateNotification(Integer boardID, Integer notificationBefore,
			Integer notificationEvery, Date endDate, String loggedInUsername, Date updateOn);
	void updateSubscriber(Long notificationId, String loggedInUsername,
			Date updateOn);
	void renewMember(Integer boardID, Integer memberID, Integer cprNumber,
			Integer roleId, String name, String nameNormalized,
			String occupation, String occupationNormlized,
			Integer organizationId, Integer organizaionOnBehalfId,
			Date startDate, Date memberEndDate, int memberStatusID,
			String loggedInUsername, Date updateOn, Integer memberNotificationBefore, Integer memberNotificationAfter);
	
	void updateMember(Integer boardID, Integer memberID, Integer cprNumber,
			Integer roleId, String name, String nameNormalized,
			String occupation, String occupationNormlized,
			Integer organizationId, Integer organizaionOnBehalfId,
			Date startDate, Date memberEndDate,
			String loggedInUsername, Date updateOn, Integer memberNotificationBefore, Integer memberNotificationAfter, Integer memberStatusID);
	List<AttachmentType> getAttachmentTypesForMembers();
	List<Notifications> getNotificationByBoardID(Integer boardID);
	boolean isSubscriberExists(Integer notificationId, String loggedInUsername);
	Integer[] getAttachmentTypesForDecree();
	Member getMemberById(Integer memberID);
	int getMemberIncompleteStatus();
	String getBoardTypeName(Integer boardTypeId);
	List<Decree> getmemberDecrees(Integer memberId, List<Long> decreesIdList);
	List<Decree> getboardDecrees(Integer boardID);
	List<BoardHistrory> getBoardHistoryLog(Integer boardId);
	void updateExpiredBoardsStatus(Long boardId, String jobLoggedinUser, Date updatedOn);
	Integer getMemberStatus(Integer memberId);
	Integer getBoardStatus(Integer boardID);
	List<Board> getExpiredBoards();
	int getBoardExpiredStatus();
	Integer addMemberNotification(Integer boardId, int memberId,
			Integer memberNotificationBefore, Integer memberNotificationEvery,
			Date endDate, String loggedInUsername, Date createdon,
			String type);
	List<MemberHistory> getMemberHistoryLog(Integer memberId);
	List<MemberNotification> getMemberNotifications();
	List<Member> getExpiredMembers();
	int getMemberExpiredStatus();
	void updateExpiredMembersStatus(Integer memberId, String jobLoggedinUser,
			Date updatedOn);
	List<Decree> getmemberDecrees(int intValue);
	void deleteSubscriber(Integer boardID);
	void deleteNotificationByBoardId(Integer boardID);
	void updateMemberNotification(Integer memberID,
			Integer notificationBefore, Integer notificationEvery,
			Date memberEndDate, String loggedInUsername, Date updateOn);
	List<Notifications> getNotificationByMemberID(Integer memberID);
	void deleteSubscriberByMemberId(Integer memberID);
	void deleteNotificationByMemberId(Integer memberID);
	Attachment getAttachmentbyBoardId(Integer boardId);
	void updateBoardStatus(Integer boardID, String updatedBy,Date updateOn);
	void addBoardWithoutDecree(Integer boardId);
}
