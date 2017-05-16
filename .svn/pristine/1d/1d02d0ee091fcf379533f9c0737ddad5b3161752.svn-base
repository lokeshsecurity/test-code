package bh.gov.cio.gbs.service.impl;


import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bh.gov.cio.gbs.exception.BoardException;
import bh.gov.cio.gbs.exception.BusinessException;
import bh.gov.cio.gbs.exception.DatabaseException;
import bh.gov.cio.gbs.exception.ExpiredBoardException;
import bh.gov.cio.gbs.exception.GBSFileUploadExcption;
import bh.gov.cio.gbs.model.ActionOperation;
import bh.gov.cio.gbs.model.ActionStatus;
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
import bh.gov.cio.gbs.model.GBSStatus;
import bh.gov.cio.gbs.model.GovernmentBoardType;
import bh.gov.cio.gbs.model.JobRunnableType;
import bh.gov.cio.gbs.model.LoginStatus;
import bh.gov.cio.gbs.model.Member;
import bh.gov.cio.gbs.model.MemberHistory;
import bh.gov.cio.gbs.model.MemberNotification;
import bh.gov.cio.gbs.model.MemberSearchResult;
import bh.gov.cio.gbs.model.NotificationHistory;
import bh.gov.cio.gbs.model.NotificationPeriod;
import bh.gov.cio.gbs.model.NotificationRepeat;
import bh.gov.cio.gbs.model.NotificationStatus;
import bh.gov.cio.gbs.model.NotificationType;
import bh.gov.cio.gbs.model.Notifications;
import bh.gov.cio.gbs.model.Organization;
import bh.gov.cio.gbs.model.Role;
import bh.gov.cio.gbs.model.Status;
import bh.gov.cio.gbs.model.Subscriber;
import bh.gov.cio.gbs.model.UserLoginHistory;
import bh.gov.cio.gbs.service.BoardManager;
import bh.gov.cio.gbs.service.SupplementaryModelManager;
import bh.gov.cio.gbs.systemevent.ActionEvent;
import bh.gov.cio.gbs.systemevent.BoardEvent;
import bh.gov.cio.gbs.systemevent.LoginEvent;
import bh.gov.cio.gbs.systemevent.MemberEvent;
import bh.gov.cio.gbs.systemevent.NotificationEvent;
import bh.gov.cio.gbs.util.APP_Constant;
import bh.gov.cio.gbs.util.CPRUtil;
import bh.gov.cio.gbs.util.CommonUtil;
import bh.gov.cio.gbs.util.DataBaseUtil;
import bh.gov.cio.gbs.util.DateUtil;
import bh.gov.cio.gbs.util.FileUtil;
import bh.gov.cio.gbs.util.JSONUtil;
import bh.gov.cio.gbs.util.MailUtil;

@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = {
		BoardException.class, BusinessException.class })
public class BoardManagerImpl implements BoardManager, ApplicationContextAware {

	public static final Logger logger = LoggerFactory
			.getLogger(BoardManagerImpl.class);

	/** The external model. */
	private SupplementaryModelManager externalModel = null;

	/** The workflow. */

	private JavaMailSender mailSender = null;

	private VelocityEngine velocityEngine = null;

	private ApplicationContext applicationContext;

	public BoardManagerImpl() {
	}

	/**
	 * The Constructor.
	 * 
	 * @param externalModel
	 *            the external model
	 */
	public BoardManagerImpl(SupplementaryModelManager externalModel,
			JavaMailSender mailSender, VelocityEngine velocityEngine) {
		super();
		this.externalModel = externalModel;
		this.mailSender = mailSender;
		this.velocityEngine = velocityEngine;
	}

	public List<Organization> getOrganizations() {
		return externalModel.getOrganizations();
	}

	public List<Organization> getSourceOrgnizations() {
		return externalModel.getSourceOrgnizations();

	}

	public List<Organization> getDestinationOrganizations() {
		return externalModel.getDestinationOrganizations();

	}

	public List<Member> getMembers() {
		return externalModel.getMembers();
	}

	public List<DecreeType> getDecreeTypes() {
		return externalModel.getDecreeTypes();
	}

	public List<BoardMembers> getBoardMembers() {
		return externalModel.getBoardMembers();
	}

	public List<BoardDecrees> getBoardDecrees() {
		return externalModel.getBoardDecrees();
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	public void createLoginEvent(String email, String sessionId) {
		logger.info("start createLoginEvent() ", email);
		UserLoginHistory userLoginHistory = new UserLoginHistory();
		userLoginHistory.setUsername(email);
		userLoginHistory.setLoginDate(new Date());
		userLoginHistory.setLoginStatus(LoginStatus.ACTIVE);
		userLoginHistory.setSessionId(sessionId);
		userLoginHistory.setAction(ActionEvent.LOGIN.getName());
		applicationContext.publishEvent(new LoginEvent(userLoginHistory));
		logger.info("end createLoginEvent() ", email);
	}

	public void createLogoutEvent(String name, String sessionId) {
		logger.info("start createLogoutEvent : ", name);
		UserLoginHistory userLoginHistory = new UserLoginHistory();
		userLoginHistory.setUsername(name);
		userLoginHistory.setLogoutDate(new Date());
		userLoginHistory.setLoginStatus(LoginStatus.NOT_ACTIVE);
		userLoginHistory.setSessionId(sessionId);
		userLoginHistory.setAction(ActionEvent.LOGOUT.getName());
		applicationContext.publishEvent(new LoginEvent(userLoginHistory));
		logger.info("end createLogoutEvent() ", name);
	}

	public List<Role> getRoles() {
		logger.info("start getRoles() ");
		return externalModel.getRoles();
	}

	public List<AttachmentType> getAttachmentTypes() {
		logger.info("start getAttachmentTypes() ");
		return externalModel.getAttachmentTypes();
	}

	public List<Notifications> getRunnableNotifications() {
		logger.info("start getRunnableNotifications() ");
		return externalModel.getRunnableNotifications();
	}

	public List<Subscriber> getSubscriberEmailByNID(Long notificationId) {
		logger.info("start getSubscriberEmailByNID() ", notificationId);
		return externalModel.getSubscriberEmailByNID(notificationId);

	}

	public void createNotificationEvent(Integer NID, Date runDate,
			List<String> emailList, JobRunnableType JobType,
			NotificationStatus NotificationStatus, String eventDescription) {
		logger.info("start createNotificationEvent({}) ", DataBaseUtil
				.paramList(NID, runDate, emailList, JobType,
						NotificationStatus, eventDescription));
		NotificationHistory notificationHistory = new NotificationHistory();
		notificationHistory.setNotificationId(NID);
		notificationHistory.setRunDate(runDate == null ? null : new Timestamp(
				runDate.getTime()));
		notificationHistory.setStatus(NotificationStatus);
		notificationHistory.setDescription(eventDescription);
		notificationHistory.setJobRunnableType(JobType);
		applicationContext.publishEvent(new NotificationEvent(
				notificationHistory));
		logger.info("end createNotificationEvent({}) ", DataBaseUtil.paramList(
				NID, runDate, emailList, JobType, NotificationStatus,
				eventDescription));
	}

	public void createBoardEvent(BoardJSON boardJason, int boardId,
			int boardStatusId, String status, List<Decree> decrees,
			List<Member> members, Date createdon) {
		logger.info("start createBoardEvent({}) ", DataBaseUtil.paramList(
				boardJason, boardId, boardStatusId, status, decrees, members,
				createdon));
		BoardHistrory boardHistrory = new BoardHistrory();

		boardHistrory.setBoardId(boardId);
		boardHistrory.setBoardName(boardJason.getName());
		boardHistrory.setBoardTypeId(boardJason.getBoardTypeId());
		boardHistrory.setDecrees(decrees);
		boardHistrory.setLoggedInUser(getLoggedInUsername());
		boardHistrory.setSourceOrganizationId(boardJason.getSourceOrganizationId());
		boardHistrory.setDestinationOrganizationIds(CommonUtil.removeNullValues(boardJason.getDestinationOrganizationIds()));
		boardHistrory.setCreatedOn(createdon);
		boardHistrory.setBoardStartDate(boardJason.getStartDate());
		boardHistrory.setBoardExpiryDate(boardJason.getEndDate());
		boardHistrory.setParentBoardId(boardJason.getParentBoardId());
		boardHistrory.setStatus(status);
		boardHistrory.setMembers(members);
		boardHistrory.setBoardStatusId(boardStatusId);
		boardHistrory.setActionOperation(ActionOperation.ADD);
		boardHistrory.setNotificationBefore(boardJason.getNotificationBefore());
		boardHistrory.setNotificationRepeat(boardJason.getNotificationRepaet());
		boardHistrory.setNotificationDate(boardJason.getNotificationBefore() == null ? null : getActualNotificationDate(boardJason.getEndDate(), boardJason.getNotificationBefore()));

		applicationContext.publishEvent(new BoardEvent(boardHistrory));
		logger.info("end createBoardEvent() ");
	}

	public void createMemberEvent(List<Member> members, List<Decree> decrees,
			String status, BoardJSON boardJSON, Date createdon) {
		logger.info("start createMemberEvent({}) ", DataBaseUtil.paramList(
				members, decrees, status, boardJSON, createdon));
		for (Member member : members) {
			logger.info("start createMemberEvent() ", member.getMemberId(),
					member.getMemberName());
			MemberHistory memberHistrory = new MemberHistory();

			memberHistrory.setMemberId(member.getMemberId());
			memberHistrory.setMemberName(member.getMemberName());
			memberHistrory.setCprNumber(member.getCprNumber());
			memberHistrory.setMemberOccuption(member.getMemberOccupation());
			memberHistrory.setOrganizationId(member.getOranizationId());
			memberHistrory.setRoleId(member.getRoleId());
			memberHistrory.setOrganizationOnBehalfId(member
					.getOrganizationOnBehalfId());
			memberHistrory.setLoggedInUser(getLoggedInUsername());
			memberHistrory.setCreatedOn(createdon);
			memberHistrory.setMemberStartDate(member.getMemberStartDate());
			memberHistrory.setMemberEndDate(member.getMemberEndDate());
			memberHistrory.setBoardId(member.getBoardId());
			memberHistrory.setBoardName(boardJSON.getName());
			memberHistrory.setBoardTypeId(boardJSON.getBoardTypeId());
			memberHistrory.setDecrees(decrees);
			memberHistrory.setBoardExpiryDate(boardJSON.getEndDate());
			memberHistrory.setBoardStartDate(boardJSON.getStartDate());
			memberHistrory.setParentBoardId(boardJSON.getParentBoardId());
			memberHistrory.setStatus(status);
			memberHistrory.setActionOperation(ActionOperation.ADD);
			memberHistrory.setMemberStatusId(member.getStatusId());
			memberHistrory.setNotificationBefore(member.getNotificationBefore());
			memberHistrory.setNotificationRepeat(member.getNotificationEvery());
			memberHistrory.setNotificationDate(member.getNotificationBefore() ==  null ? null : getActualNotificationDate(member.getMemberEndDate(), member.getNotificationBefore()));
			
			applicationContext.publishEvent(new MemberEvent(memberHistrory));
			logger.info("end createMemberEvent() ", member.getMemberId(),
					member.getMemberName());
		}
	}
	
	public void createUpdateBoardEvent(BoardJSON boardJason,Integer boardID,String status,List<Decree> decrees,
			List<Member> members, String loggedInUsername, Date createdOn, Integer boardStatusID) {
		logger.info("start createBoardEvent({}) ", DataBaseUtil.paramList(boardID,  loggedInUsername,  createdOn));		
		BoardHistrory updateBoardHistrory = new BoardHistrory();

		updateBoardHistrory.setBoardId(boardID);
		updateBoardHistrory.setBoardName(boardJason.getName());
		updateBoardHistrory.setBoardTypeId(boardJason.getBoardTypeId());
		updateBoardHistrory.setSourceOrganizationId(boardJason.getSourceOrganizationId());
		updateBoardHistrory.setDestinationOrganizationIds(CommonUtil.removeNullValues(boardJason.getDestinationOrganizationIds()));
		updateBoardHistrory.setBoardStartDate(boardJason.getStartDate());
		updateBoardHistrory.setBoardExpiryDate(boardJason.getEndDate());
		updateBoardHistrory.setParentBoardId(boardJason.getParentBoardId());
		updateBoardHistrory.setNotificationBefore(boardJason.getNotificationBefore());
		updateBoardHistrory.setNotificationRepeat(boardJason.getNotificationRepaet());
		updateBoardHistrory.setBoardStatusId(boardStatusID);
		updateBoardHistrory.setStatus(status);
		updateBoardHistrory.setDecrees(decrees);
		updateBoardHistrory.setLoggedInUser(loggedInUsername);
		updateBoardHistrory.setCreatedOn(createdOn);
		updateBoardHistrory.setMembers(members);
		updateBoardHistrory.setActionOperation(ActionOperation.UPDATE);
		updateBoardHistrory.setNotificationDate(boardJason.getNotificationBefore() == null ? null : getActualNotificationDate(boardJason.getEndDate(), boardJason.getNotificationBefore()));

		applicationContext.publishEvent(new BoardEvent(updateBoardHistrory));
		logger.info("end createUpdateBoardEvent() ");
	}
	
	public void createUpdateExpiredBoardEvent(Board expiredBoard, String loggedInUsername, Date createdOn, String actionStatus, Integer boardExpiredStatusID) {
		logger.info("start createUpdateExpiredBoardEvent({}) ", DataBaseUtil.paramList(expiredBoard.getBoardId(),  loggedInUsername,  createdOn));		
		BoardHistrory updateExpiredBoardHistrory = new BoardHistrory();
		
		List<Decree> decreeList = externalModel.getboardDecrees(expiredBoard.getBoardId().intValue());
		List<Member> memberList = externalModel.getBoardMembers(expiredBoard.getBoardId().intValue());
		
		updateExpiredBoardHistrory.setBoardId(expiredBoard.getBoardId().intValue());
		updateExpiredBoardHistrory.setBoardName(expiredBoard.getBoardName());
		updateExpiredBoardHistrory.setBoardTypeId(expiredBoard.getBoardTypeId());
		updateExpiredBoardHistrory.setSourceOrganizationId(expiredBoard.getSourceOrganizationId().intValue());
		updateExpiredBoardHistrory.setDestinationOrganizationIds(CommonUtil.removeNullValues(expiredBoard.getDestinationOrganizationIds()));
		updateExpiredBoardHistrory.setBoardStartDate(expiredBoard.getBoardStartDate());
		updateExpiredBoardHistrory.setBoardExpiryDate(expiredBoard.getBoardExpiryDate());
		updateExpiredBoardHistrory.setParentBoardId(expiredBoard.getParentBoardId());
		updateExpiredBoardHistrory.setNotificationBefore(expiredBoard.getNotificationBefore());
		updateExpiredBoardHistrory.setNotificationRepeat(expiredBoard.getNotificationEvery());
		updateExpiredBoardHistrory.setBoardStatusId(boardExpiredStatusID);
		updateExpiredBoardHistrory.setDecrees(decreeList);
		updateExpiredBoardHistrory.setMembers(memberList);
		updateExpiredBoardHistrory.setStatus(actionStatus);
		updateExpiredBoardHistrory.setLoggedInUser(loggedInUsername);
		updateExpiredBoardHistrory.setCreatedOn(createdOn);
		updateExpiredBoardHistrory.setActionOperation(ActionOperation.EXPIRE);

		applicationContext.publishEvent(new BoardEvent(updateExpiredBoardHistrory));
		logger.info("end createUpdateExpiredBoardEvent() ");
	}
	public void createUpdateMemberEvent(List<Member> members,
			String status, BoardJSON boardJSON, Date createdon) {
		logger.info("start createMemberEvent({}) ", DataBaseUtil.paramList(
				members, status, boardJSON, createdon));
		for (Member member : members) {
			logger.info("start createMemberEvent() ", member.getMemberId(),
					member.getMemberName());
			MemberHistory memberHistrory = new MemberHistory();

			memberHistrory.setMemberId(member.getMemberId());
			memberHistrory.setMemberName(member.getMemberName());
			memberHistrory.setCprNumber(member.getCprNumber());
			memberHistrory.setMemberOccuption(member.getMemberOccupation());
			memberHistrory.setOrganizationId(member.getOranizationId());
			memberHistrory.setRoleId(member.getRoleId());
			memberHistrory.setOrganizationOnBehalfId(member.getOrganizationOnBehalfId());
			memberHistrory.setDecrees(member.getMemberDecreesList());
			memberHistrory.setLoggedInUser(getLoggedInUsername());
			memberHistrory.setCreatedOn(createdon);
			memberHistrory.setMemberStartDate(member.getMemberStartDate());
			memberHistrory.setMemberEndDate(member.getMemberEndDate());
			memberHistrory.setBoardId(member.getBoardId());
			memberHistrory.setBoardName(boardJSON.getName());
			memberHistrory.setBoardTypeId(boardJSON.getBoardTypeId());
			memberHistrory.setBoardExpiryDate(boardJSON.getEndDate());
			memberHistrory.setBoardStartDate(boardJSON.getStartDate());
			memberHistrory.setParentBoardId(boardJSON.getParentBoardId());
			memberHistrory.setStatus(status);
			memberHistrory.setActionOperation(member.getActionOperation());
			memberHistrory.setMemberStatusId(member.getStatusId());
			memberHistrory.setNotificationBefore(member.getNotificationBefore());
			memberHistrory.setNotificationRepeat(member.getNotificationEvery());
			memberHistrory.setNotificationDate(member.getNotificationBefore() ==  null ? null : getActualNotificationDate(member.getMemberEndDate(), member.getNotificationBefore()));

			applicationContext.publishEvent(new MemberEvent(memberHistrory));
			logger.info("end createUpdateMemberEvent() ", member.getMemberId(),
					member.getMemberName());
		}
	}
	
//	private List<Decree> getMemberDecreesbyId(List<Decree> decrees,
//			Integer memberId) {
//		List<Integer> memberDecreesIds = externalModel.getMembersDecrees(memberId);
//		List<Decree> oldMemberDecrees = null;
//		for(Decree decree : decrees){
//			if (memberDecreesIds.contains(decree.getDecreeId()))
//			{
//				oldMemberDecrees.add(decree);
//			}
//		}
//		return oldMemberDecrees;
//	}

	public List<BoardType> getBoardTypes() {
		logger.info("start getBoardTypes() ");
		return externalModel.getBoardTypes();
	}

	public List<NotificationPeriod> getNotificationPeriods() {
		logger.info("start getNotificationPeriods() ");
		return externalModel.getNotificationPeriods();
	}

	public void updateNotificationDate(Date updatedNotificationDate,
			Date updatedLastRunDate, Integer notificationID) {
		logger.info("start updateNotificationDate() ", updatedNotificationDate,
				updatedLastRunDate, notificationID);
		externalModel.updateNotificationDate(updatedNotificationDate,
				updatedLastRunDate, notificationID);

	}

	public List<NotificationRepeat> getNotificationRepeats() {
		logger.info("start getNotificationRepeats() ");
		return externalModel.getNotificationRepeats();
	}

	public List<Member> searchMembers(String text) {
		logger.info("start searchMembers() ", text);
		return externalModel.searchMembers(text);
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {
			BoardException.class, DatabaseException.class })
	public void addBoard(BoardJSON boardJSON, List<?> decreeList,
			List<?> memberList) throws BoardException {

		logger.info("start addBoard({}) ",
				DataBaseUtil.paramList(boardJSON, decreeList, memberList));

		Integer statusID = null;
		Integer boardId = null;
		Integer notificationId = null;
		Date createdon = new Date();

		List<Decree> decrees = new ArrayList<Decree>();
		List<Member> members = new ArrayList<Member>();

		try {
			if (memberList.size() == 0) {
				statusID = externalModel.getBoardNewStatus();
			} else {
				statusID = externalModel.getBoardValidStatus();
			}

			boolean isBoardNameExists = isBoardNameExists(boardJSON.getName(),
					boardJSON.getBoardTypeId());
			if (isBoardNameExists == true) {
				throw new BoardException(
						GBSStatus.GBS_BOARD_NAME_ALREADY_EXISTS.getStatusCode());
			}

			if (boardJSON.getDestinationOrganizationIds() == null) {
				throw new BoardException(
						GBSStatus.GBS_NO_DESTINATION_ORGANIZATION_ENTERED
								.getStatusCode());
			}

			boardId = externalModel.addBoard(boardJSON, statusID,
					getLoggedInUsername(), createdon);

			if (boardId != null) {
				
				externalModel.addDestinationOrganization(boardId,
						CommonUtil.removeNullValues(boardJSON.getDestinationOrganizationIds()));

				Integer boardHaveExpiryDate = externalModel
						.isBoardHaveExpiryDate(boardJSON.getBoardTypeId());
				if (boardHaveExpiryDate.equals(Integer.valueOf(1))) {
					if (boardJSON.getNotificationBefore() != null) {
						notificationId = externalModel.addNotification(boardId,
								boardJSON, getLoggedInUsername(), createdon,NotificationType.BOARD.name());
						externalModel.addSubscriber(notificationId,
								getLoggedInUsername(), createdon);
					}

				}

				List<Long> decreesIdList = new ArrayList<Long>();

				for (Object decree : decreeList) {
					Map<String, ?> decreeMap = (Map<String, ?>) decree;
					Integer decreeNumber = CommonUtil.getInt((String) decreeMap
							.get("number"));
					Integer decreeYear = CommonUtil.getInt((String) decreeMap
							.get("year"));
					Integer decreeTypeId = (Integer) decreeMap.get("typeId");

					boolean isDecreeExists = isDecreeExists(decreeTypeId,
							decreeNumber, decreeYear);
					if (isDecreeExists == true) {
						throw new BoardException(
								GBSStatus.GBS_DECREE_NUMBER_AND_YEAR_ALREADY_EXISTS
										.getStatusCode());
					}

					Integer attachmentTypeId = (Integer) decreeMap.get("attachmentTypeId");
					Date issueDateMelady =((String) decreeMap.get("issueDateMelady") != null ? DateUtil.getDate((String) decreeMap.get("issueDateMelady")) : null);
					String issueDateHijry = (String) decreeMap.get("issueDateHijry");
					String notes = (String) decreeMap.get("notes");
					String josn = JSONUtil.converObjectToJSON(decreeMap
							.get("attachment"));
					AttachmentJSON attachment = JSONUtil.converJSONToObject(
							josn, AttachmentJSON.class);
					logger.info("attachment : " + attachment.toString());

					int attachType = externalModel.getAttachmentTypeDecree(); 
					
					Integer decreeId = null;
					List<Integer> decreeIdList = new ArrayList<Integer>();
					Decree decreeObj = new Decree();
					if (attachType == attachmentTypeId) {
						decreeId = externalModel.addDecree(decreeNumber,
								decreeYear, decreeTypeId, issueDateMelady,
								issueDateHijry, notes, getLoggedInUsername(),
								createdon);
						if(decreeId!=null)
							decreesIdList.add(decreeId.longValue());
						
						decreeObj.setDecreeId(decreeId!=null ? decreeId.longValue() : null);
						decreeObj.setDecreeNumber(decreeNumber.longValue());
						decreeObj.setDecreeYear(decreeYear.longValue());
						decreeObj.setDecreeTypeId(decreeTypeId.longValue());
						decreeObj.setDecreeDescription(notes);
						decreeObj.setDecreeDescriptionNormalized( DataBaseUtil.normalize(notes));
						decreeIdList.add(decreeId);
					}
					
					String fileNameWithTimeStamp = FileUtil.upload(
							attachment.getName(), attachment.getBase64());

					int attachemntId= externalModel
							.addAttachment(
									attachment,
									boardId != null ? boardId : Integer
											.valueOf(0),
									decreeId != null ? decreeId : Integer
											.valueOf(0), attachmentTypeId,
									fileNameWithTimeStamp,
									getLoggedInUsername(), createdon);
					
					if (attachType == attachmentTypeId) {
						decreeObj.setAttachmentId((long) attachemntId);
						decrees.add(decreeObj);	
					}
					
					Integer[] decreesIdArr = CommonUtil
							.convertToIntegerArray(decreeIdList);
					if (boardId != null)
						externalModel.addBoardDecree(boardId, decreesIdArr);
				}
				//In case no decree added for a committee
				if (decreesIdList.isEmpty()){
					if (boardId != null)
						externalModel.addBoardWithoutDecree(boardId);
				}

				List<Integer> membersIdList = new ArrayList<Integer>();
				for (Object member : memberList) {
					Map<String, ?> memberMap = (Map<String, ?>) member;
					Integer cprNumber = CommonUtil.getInt((String) memberMap
							.get("cprNumber"));
					if (cprNumber != null) {
						boolean isValidCPRNumber = isValidCPRNumber(cprNumber);
						if (isValidCPRNumber == false) {
							throw new BoardException(
									GBSStatus.GBS_NOT_VALID_MEMBER_CPR_NUMBER
											.getStatusCode());
						}

					}

					String name = (String) memberMap.get("name");

					String nameNormalized = DataBaseUtil.normalize(name);
					String occupation = (String) memberMap.get("occupation");
					String occupationNormlized = DataBaseUtil
							.normalize(occupation);
					Integer organizationId = (Integer) memberMap
							.get("organizationId");
					Integer roleId = (Integer) memberMap.get("roleId");
					Integer organizaionOnBehalfId = (Integer) memberMap
							.get("organizaionOnBehalfId");
					Date startDate = DateUtil.getDate((String) memberMap
							.get("startDate"));
					Date endDate = DateUtil.getDate((String) memberMap
							.get("endDate"));
					
					Integer memberNotificationBefore	= memberMap.get("notificationBefore") != null ? Integer.parseInt(memberMap.get("notificationBefore").toString()) : null;
					Integer memberNotificationEvery 	= memberMap.get("notificationRepeat") != null ? Integer.parseInt(memberMap.get("notificationRepeat").toString()) : null;
					
					Integer memberStatusID = null;
					if(cprNumber == null){
						memberStatusID = externalModel.getMemberIncompleteStatus();
					}
					else{
						memberStatusID = externalModel.getMemberValidStatus();
					}
				
					// decree list
					int memberId = externalModel.addMember(boardId, cprNumber,
							roleId, name, nameNormalized, occupation,
							occupationNormlized, organizationId,
							organizaionOnBehalfId, startDate, endDate,
							memberStatusID, getLoggedInUsername(), createdon,memberNotificationBefore,memberNotificationEvery);
					
					//add member notification if any
					if (memberNotificationBefore != null) {
						notificationId = externalModel.addMemberNotification(boardId,memberId,
								memberNotificationBefore,memberNotificationEvery!=null ? memberNotificationEvery : 0,endDate, getLoggedInUsername(), createdon,NotificationType.MEMBER.name());
						externalModel.addSubscriber(notificationId,
								getLoggedInUsername(), createdon);
					}

					membersIdList.add(memberId);

					Member memberObj = new Member();
					memberObj.setMemberId(memberId);
					memberObj.setBoardId(boardId);
					memberObj.setCprNumber(cprNumber);
					memberObj.setStatusId(memberStatusID);
					memberObj.setRoleId(roleId);
					memberObj.setMemberName(name);
					memberObj.setMemberNameNormalized(nameNormalized);
					memberObj.setMemberOccupation(occupation);
					memberObj
							.setMemberOccupationNormalized(occupationNormlized);
					memberObj.setOranizationId(organizationId);
					memberObj.setOrganizationOnBehalfId(organizaionOnBehalfId);
					memberObj.setMemberStartDate(startDate);
					memberObj.setMemberEndDate(endDate);
					memberObj.setNotificationBefore(memberNotificationBefore);
					memberObj.setNotificationEvery(memberNotificationEvery);
					memberObj.setCreatedOn(createdon);
//					memberObj.setCreatedBy(getLoggedInUsername());
					members.add(memberObj);
				}

				Integer[] membersIdArr = CommonUtil
						.convertToIntegerArray(membersIdList);

				for (Long decreeId : decreesIdList) {
					externalModel.addMemberDecree(membersIdArr, decreeId);
				}

				createBoardEvent(boardJSON, boardId, statusID,
						ActionStatus.SUCCESS.name(), decrees, members,
						createdon);
				
				if(members!=null && members.size() > 0)
					createMemberEvent(members, decrees,
						ActionStatus.SUCCESS.name(), boardJSON, createdon);

				logger.info("end addBoard() ", boardJSON, decreeList,
						memberList);

			}
		} catch (GBSFileUploadExcption e) {
			e.printStackTrace();
			createBoardEvent(boardJSON, boardId!=null ? boardId : Integer.valueOf(0), statusID!=null ? statusID : Integer.valueOf(0),
					ActionStatus.FAIL.name(), decrees, members, createdon);
			createMemberEvent(members, decrees, ActionStatus.FAIL.name(),
					boardJSON, createdon);
			logger.error("FileUpload Error : " + e.getMessage(), e);
			throw new BoardException(e.getMessage(), e,
					GBSStatus.GBS_FILE_UPLOAD_FAILED.getStatusCode());
		} catch (DatabaseException e) {
			e.printStackTrace();
			createBoardEvent(boardJSON, boardId!=null ? boardId : Integer.valueOf(0), statusID!=null ? statusID : Integer.valueOf(0),
					ActionStatus.FAIL.name(), decrees, members, createdon);
			createMemberEvent(members, decrees, ActionStatus.FAIL.name(),
					boardJSON, createdon);
			logger.error("DataBase Error : " + e.getMessage(), e);
			throw new BoardException(e.getMessage(), e,
					GBSStatus.GBS_DATABASE_EXCEPTION.getStatusCode());

		} catch (Throwable e) {
			e.printStackTrace();
			createBoardEvent(boardJSON, boardId!=null ? boardId : Integer.valueOf(0) , statusID!=null ? statusID : Integer.valueOf(0),
					ActionStatus.FAIL.name(), decrees, members, createdon);
			createMemberEvent(members, decrees, ActionStatus.FAIL.name(),
					boardJSON, createdon);
			logger.error("General Error : " + e.getMessage(), e);
			throw new BoardException(e.getMessage(), e,
					GBSStatus.GBS_GENERAL_EXCEPTION.getStatusCode());
		}

	}

	public Boolean isValidCPRNumber(Integer cprNumber) {
		logger.info("start isValidCPRNumber() ", cprNumber);
		return CPRUtil.isCPRValid(cprNumber);
	}

	public Boolean isDecreeExists(Integer decreeTypeId, Integer decreeNumber,
			Integer decreeYear) {
		logger.info("start isDecreeExists() ", decreeTypeId, decreeNumber,
				decreeYear);
		return externalModel.isDecreeExists(decreeTypeId, decreeNumber,
				decreeYear);
	}

	public Boolean isBoardNameExists(String boardName, Integer boardTypeId) {
		logger.info("start isBoardNameExists({} , {}) ", boardName, boardTypeId);
		return externalModel.isBoardNameExists(boardName, boardTypeId);
	}

	public List<BoardNotification> getBoardNotifications() {
		logger.info("start getBoardNotifications() ");
		return externalModel.getBoardNotifications();
	}

	public List<GovernmentBoardType> getGovernmentBoardTypes() {
		logger.info("start getGovernmentBoardTypes() ");
		return externalModel.getGovernmentBoardTypes();
	}

	public List<BoardDecrees> searchBoard(Integer governmentBoardId,
			String boardName, Integer sourceOrganizationId,
			Integer decreeNumber, Integer decreeYear, Integer decreeTypeId,
			Date startDateFrom, Date startDateTo, Date endDateFrom,
			Date endDateTo, Integer destinationOrganizationPrimaryId,
			Integer statusId) {

		logger.info("start searchBoard({}) ", DataBaseUtil.paramList(
				governmentBoardId, boardName, sourceOrganizationId,
				decreeNumber, decreeYear, decreeTypeId, startDateFrom,
				startDateTo, endDateFrom, endDateTo,
				destinationOrganizationPrimaryId, statusId));

		return externalModel.searchBoard(governmentBoardId, boardName,
				sourceOrganizationId, decreeNumber, decreeYear, decreeTypeId,
				startDateFrom, startDateTo, endDateFrom, endDateTo,
				destinationOrganizationPrimaryId, statusId);
	}

	public List<Status> getBoardStatusList() {
		logger.info("start getBoardStatusList() ");
		return externalModel.getBoardStatusList();
	}

	public List<Board> searchBoard(String text) {
		logger.info("start searchBoard() ");
		return externalModel.searchBoard(text);
	}

	public String getLoggedInUsername() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

	public String getUserBySessionId(String sessionId) {
		return externalModel.getUserBySessionId(sessionId);
	}

	public void recordSessionTimeoutEvent(String username, String sessionId) {
		logger.info("start recordSessionTimeoutEvent : ", username);
		UserLoginHistory userLoginHistory = new UserLoginHistory();
		userLoginHistory.setUsername(username);
		userLoginHistory.setLogoutDate(new Date());
		userLoginHistory.setLoginStatus(LoginStatus.NOT_ACTIVE);
		userLoginHistory.setSessionId(sessionId);
		userLoginHistory.setAction(ActionEvent.SESSION_TIMEOUT.getName());
		applicationContext.publishEvent(new LoginEvent(userLoginHistory));
		logger.info("end recordSessionTimeoutEvent() ", username);

	}

	public List<Status> getMemberStatusList() {
		logger.info("start getMemberStatusList() ");
		return externalModel.getMemberStatusList();
	}

	public List<MemberSearchResult> searchMember(Integer cprNumber,
			String memberName, String occupation, Integer organizationId,
			Integer organizaionOnBehalfId, Integer roleId, Date startDateFrom,
			Date startDateTo, Date endDateFrom, Date endDateTo, Integer statusId) {
		
		logger.info("start searchMember({}) ", DataBaseUtil.paramList(
				cprNumber, memberName, occupation,
				organizationId, organizaionOnBehalfId, roleId, startDateFrom,
				startDateTo, endDateFrom, endDateTo,statusId));
		
		return externalModel.searchMember(cprNumber, memberName, occupation,
				organizationId, organizaionOnBehalfId, roleId, startDateFrom,
				startDateTo, endDateFrom, endDateTo,statusId);
	}

	public BoardNotification getNotificationByBoardId(Integer boardId) {
		logger.info("start getNotificationByBoardId() ", boardId);
		return externalModel.getNotificationByBoardId(boardId);
		
	}

	public List<BoardDecrees> getDecreesByBoardId(Integer boardId) {
		logger.info("start getDecreesByBoardId() ", boardId);
		List<BoardDecrees> boardDecrees = externalModel.getDecreesByBoardId(boardId);
		
		for (BoardDecrees bd: boardDecrees){
			if(bd.getDecreeId()==null || bd.getDecreeId()== 0){
			 // get from attachement table and set bd with attachement returned values	
				Attachment attachment=  externalModel.getAttachmentbyBoardId(boardId);
				bd.setAttachmentId(attachment.getId());
				bd.setAttachmentName(attachment.getName());
				bd.setAttachmentNameNormalized(attachment.getNameNormalized());
				bd.setAttachmentMIME(attachment.getMime());
				bd.setAttachmentSize(attachment.getSize());
				bd.setAttachmentCreatedOn(attachment.getCreatedOn());
				bd.setAttachmentTypeId(attachment.getTypeId());
				bd.setBoardId(attachment.getBoardId());
			}
			String fileName = bd.getAttachmentName();
			try {
				String base64 = FileUtil.getBase64Attachment(fileName);
				bd.setBase64(base64);
			} catch (IOException e) {
				e.printStackTrace();
				logger.info("Failed to get attachment base64");
			}
		}
		return boardDecrees;
	}

	public Board getBoard(Integer boardId) {
		logger.info("start getBoard() ", boardId);
		return externalModel.getBoard(boardId);
	}

	public List<Member> getBoardMembers(Integer boardId) {
		logger.info("start getBoardMembers() ", boardId);
		return externalModel.getBoardMembers(boardId);
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {
			BoardException.class, DatabaseException.class })
	public void updateBoard(BoardJSON boardJSON, List<?> decreeList,
			List<?> memberList) throws BoardException {
		Date updateOn = new Date();

		Integer boardID = boardJSON.getBoardId();
		Integer notificationBefore = boardJSON.getNotificationBefore();
		Integer notificationEvery = boardJSON.getNotificationRepaet();
		Date boardEndDate = boardJSON.getEndDate();
		Integer boardTypeID = boardJSON.getBoardTypeId();
		Integer boardStatusID = null;
		boolean boardStatusChanged=false;
		ActionOperation actionOperation = null;

		List<Decree> decrees = new ArrayList<Decree>();
		List<Member> members = new ArrayList<Member>();
		List<Decree> boardDecrees= new ArrayList<Decree>();
		
		if (memberList.size()>0){
			// in case of adding members not found before
			boardStatusID =  externalModel.getBoardValidStatus();
			boardStatusChanged = true;
		}else{
			// in case of not adding members 
			boardStatusID = externalModel.getBoardStatus(boardID);
			boardStatusChanged = false;
		}

		try {
			Integer boardHaveExpiryDate = externalModel
					.isBoardHaveExpiryDate(boardTypeID);
			if (boardHaveExpiryDate.equals(Integer.valueOf(1))) {
				Board oldBoardData = externalModel.getBoard(boardID);
				if (notificationBefore != null && oldBoardData.getNotificationBefore()!=null && !notificationBefore.equals(oldBoardData.getNotificationBefore())) {
					// update board notification
						externalModel.updateBoard(boardID, notificationBefore,
								notificationEvery!=null ? notificationEvery : 0, getLoggedInUsername(),
								updateOn,boardStatusID);
						externalModel.updateNotification(boardID,
								notificationBefore, notificationEvery!=null ? notificationEvery : 0,
								boardEndDate, getLoggedInUsername(), updateOn);

						// get board notification ID and subscribers list
						List<Notifications> notificationsByBoardID = externalModel
								.getNotificationByBoardID(boardID);

						for (Notifications Notification : notificationsByBoardID) {
							if (!getLoggedInUsername().equals(
									Notification.getEmail())) {
								// check if subscriber exists in same
								// notification
								externalModel.updateSubscriber(
										Notification.getNotificationId(),
										getLoggedInUsername(), updateOn);
							}
						}
					
				} else if (notificationBefore == null && oldBoardData.getNotificationBefore()!=null){
					// delete
						externalModel.updateBoard(boardID, notificationBefore,
								notificationEvery, getLoggedInUsername(),
								updateOn,boardStatusID);
						//delete subscriber
						externalModel.deleteSubscriber(boardID);
						//delete notification
						externalModel.deleteNotificationByBoardId(boardID);
				} else if (notificationBefore != null && oldBoardData.getNotificationBefore() == null) {
					// add notification
					externalModel.updateBoard(boardID, notificationBefore,
							notificationEvery!=null ? notificationEvery : 0, getLoggedInUsername(),
							updateOn,boardStatusID);
					Integer notificationId = externalModel.addNotification(boardID,
							boardJSON, getLoggedInUsername(), updateOn,NotificationType.BOARD.name());
					externalModel.addSubscriber(notificationId,getLoggedInUsername(), updateOn);
				}
			}

			List<Long> newDecreesIdList = new ArrayList<Long>();
			List<Long> oldDecreesIdList = new ArrayList<Long>();

			for (Object decree : decreeList) {
				Map<String, ?> decreeMap = (Map<String, ?>) decree;

				if ((Boolean) decreeMap.get("newOne") == true) {
					Integer decreeNumber = CommonUtil.getInt((String) decreeMap
							.get("number"));
					Integer decreeYear = CommonUtil.getInt((String) decreeMap
							.get("year"));
					Integer decreeTypeId = (Integer) decreeMap.get("typeId");

					boolean isDecreeExists = isDecreeExists(decreeTypeId,
							decreeNumber, decreeYear);
					if (isDecreeExists == true) {
						throw new BoardException(
								GBSStatus.GBS_DECREE_NUMBER_AND_YEAR_ALREADY_EXISTS
										.getStatusCode());
					}

					Integer attachmentTypeId = (Integer) decreeMap
							.get("attachmentTypeId");
					Date issueDateMelady = DateUtil.getDate((String) decreeMap
							.get("issueDateMelady"));
					String issueDateHijry = (String) decreeMap
							.get("issueDateHijry");
					String notes = (String) decreeMap.get("notes");
					String josn = JSONUtil.converObjectToJSON(decreeMap
							.get("attachment"));
					AttachmentJSON attachment = JSONUtil.converJSONToObject(
							josn, AttachmentJSON.class);
					logger.info("attachment : " + attachment.toString());

					Integer[] attachType = externalModel
							.getAttachmentTypesForDecree();

					Integer decreeId = null;
					List<Integer> decreesIdList = new ArrayList<Integer>();
					for (int attachTypeID : attachType) {
						if (attachTypeID == attachmentTypeId) {
							decreeId = externalModel.addDecree(decreeNumber,
									decreeYear, decreeTypeId, issueDateMelady,
									issueDateHijry, notes,
									getLoggedInUsername(), updateOn);
							if(decreeId!=null)
								newDecreesIdList.add(decreeId.longValue());
							Decree decreeObj = new Decree();
							decreeObj.setDecreeId(decreeId!=null ? decreeId.longValue():null);
							decreeObj.setDecreeNumber(decreeNumber.longValue());
							decreeObj.setDecreeYear(decreeYear.longValue());
							decreeObj.setDecreeTypeId(decreeTypeId.longValue());
							decreeObj.setDecreeDescription(notes);
							decreeObj.setDecreeDescriptionNormalized( DataBaseUtil.normalize(notes));
							
							String fileNameWithTimeStamp = FileUtil.upload(
									attachment.getName(), attachment.getBase64());

							int attachmentId= externalModel.addAttachment(attachment,
									boardID != null ? boardID : Integer.valueOf(0),
									decreeId != null ? decreeId : Integer.valueOf(0),
									attachmentTypeId, fileNameWithTimeStamp,
									getLoggedInUsername(), updateOn);
							
							decreeObj.setAttachmentId((long) attachmentId);
							decrees.add(decreeObj);
							decreesIdList.add(decreeId);

						}
					}
					
					Integer[] decreesIdArr = CommonUtil.convertToIntegerArray(decreesIdList);
					
					if (boardID != null)
						externalModel.addBoardDecree(boardID, decreesIdArr);

				}
				else{
					//if old ones
					if(decreeMap.get("decreeId")!= null){
						Integer decreeId =(Integer) decreeMap.get("decreeId");
						oldDecreesIdList.add(decreeId.longValue());
					}

				}
			}

			List<Integer> membersIdList = new ArrayList<Integer>();
			for (Object member : memberList) {
				Map<String, ?> memberMap = (Map<String, ?>) member;
				Integer cprNumber = CommonUtil.getInt((String) memberMap
						.get("cprNumber"));
				if (cprNumber != null) {
					boolean isValidCPRNumber = isValidCPRNumber(cprNumber);
					if (isValidCPRNumber == false) {
						throw new BoardException(
								GBSStatus.GBS_NOT_VALID_MEMBER_CPR_NUMBER
										.getStatusCode());
					}

				}
				
				String name = (String) memberMap.get("name");
				String nameNormalized = DataBaseUtil.normalize(name);
				String occupation = (String) memberMap.get("occupation");
				String occupationNormlized = DataBaseUtil.normalize(occupation);
				Integer organizationId = (Integer) memberMap
						.get("organizationId");
				Integer roleId = (Integer) memberMap.get("roleId");
				Integer organizaionOnBehalfId = (Integer) memberMap
						.get("organizaionOnBehalfId");
				Date startDate = memberMap.get("startDate") != null ? DateUtil.getDate((String) memberMap.get("startDate")) : null;
				Date memberEndDate = memberMap.get("endDate") != null ? DateUtil.getDate((String) memberMap.get("endDate")) : null;
				Integer memberNotificationBefore	= memberMap.get("notificationBefore") != null ? Integer.parseInt(memberMap.get("notificationBefore").toString()) : null;
				Integer memberNotificationAfter 	= memberMap.get("notificationRepeat") != null ? Integer.parseInt(memberMap.get("notificationRepeat").toString()) : null;
				Integer memberStatusID = null;
				Integer memberId = (Integer) memberMap.get("memberId");
				
				

				
				if (memberId != null) {
					
					Member oldMember = externalModel.getMemberById(memberId);
					oldMember.setMemberStartDate(oldMember.getMemberStartDate() != null ? DateUtil.getDate(oldMember.getMemberStartDate().toString()) : null);
					oldMember.setMemberEndDate(oldMember.getMemberEndDate() != null ? DateUtil.getDate(oldMember.getMemberEndDate().toString()) : null);
					
					if ((Boolean) memberMap.get("renew") == true) {
						// renew member expiration date
						// if date changed and in future
						if (!oldMember.getMemberEndDate().equals(memberEndDate)
								&& oldMember.getMemberEndDate().before(
										memberEndDate)) {
							//change the status to valid after renewing
							memberStatusID = externalModel.getMemberValidStatus();
							externalModel.renewMember(boardID, memberId,
									cprNumber, roleId, name, nameNormalized,
									occupation, occupationNormlized,
									organizationId, organizaionOnBehalfId,
									startDate, memberEndDate, memberStatusID,
									getLoggedInUsername(), updateOn ,memberNotificationBefore,memberNotificationAfter);
							membersIdList.add(memberId);
							actionOperation = ActionOperation.RENEW;
							
							checkMemberNotificationChanged(memberNotificationBefore,memberNotificationAfter,oldMember,memberId,boardID, memberEndDate, updateOn);

						} else {
							throw new BoardException(
									GBSStatus.GBS_MEMBER_EXPIRY_DATE_EXCEPTION
											.getStatusCode());
						}

					} else {
						// update member data
						//check if member data changed
						Member newMember = new Member();
						newMember.setCprNumber(cprNumber);
						newMember.setRoleId(roleId);
						newMember.setMemberName(name);
						newMember.setMemberOccupation(occupation);
						newMember.setOranizationId(organizationId);
						newMember.setOrganizationOnBehalfId(organizaionOnBehalfId);
						newMember.setMemberStartDate(startDate);
						newMember.setMemberEndDate(memberEndDate);
						newMember.setNotificationBefore(memberNotificationBefore);
						newMember.setNotificationEvery(memberNotificationAfter);
						if (newMember.getCprNumber() != null){
							memberStatusID = externalModel.getMemberValidStatus();
						}
						else{
							memberStatusID = externalModel.getMemberStatus(memberId);

						}
						Boolean isChanged= false;						
						if(!newMember.equals(oldMember)){
							isChanged= true;
							
						}						
						if(isChanged)
						{
							//get the status from the DB
							externalModel.updateMember(boardID, memberId,
								cprNumber, roleId, name, nameNormalized,
								occupation, occupationNormlized,
								organizationId, organizaionOnBehalfId,
								startDate, memberEndDate,
								getLoggedInUsername(), updateOn,memberNotificationBefore,memberNotificationAfter, memberStatusID);
						actionOperation = ActionOperation.UPDATE;
						
						checkMemberNotificationChanged(memberNotificationBefore,memberNotificationAfter,oldMember,memberId,boardID, memberEndDate, updateOn);
						}
						else{
							actionOperation = ActionOperation.NOT_UPDATED;
						}
					}

				} else {
					// insert new member
					// validate that expiry date is in the future, bigger than
					// the start date and not more the board expiry date
					
					if(cprNumber == null){
						memberStatusID = externalModel.getMemberIncompleteStatus();
					}
					else{
						memberStatusID = externalModel.getMemberValidStatus();
					}
						memberId = externalModel.addMember(boardID, cprNumber,
								roleId, name, nameNormalized, occupation,
								occupationNormlized, organizationId,
								organizaionOnBehalfId, startDate, memberEndDate,
								memberStatusID, getLoggedInUsername(), updateOn,memberNotificationBefore,memberNotificationAfter);
						//update board status "valid"
						//if old board status "new" change to "valid"
						if (boardStatusChanged){
							externalModel.updateBoardStatus(boardID, getLoggedInUsername(),updateOn);
						}
						membersIdList.add(memberId);
						actionOperation = ActionOperation.ADD;
						
						//add member notification if any
						if (memberNotificationBefore != null) {
							Integer notificationId = externalModel.addMemberNotification(boardID,memberId,
									memberNotificationBefore,memberNotificationAfter!=null ? memberNotificationAfter : 0,memberEndDate, getLoggedInUsername(), updateOn,NotificationType.MEMBER.name());
							externalModel.addSubscriber(notificationId,getLoggedInUsername(), updateOn);
						}
				}

				Integer[] membersIdArr = CommonUtil
						.convertToIntegerArray(membersIdList);

				for (Long decreeId : newDecreesIdList) {
					externalModel.addMemberDecree(membersIdArr, decreeId);
				}

				Member memberObj = new Member();
				memberObj.setMemberId(memberId);
				memberObj.setBoardId(boardID);
				memberObj.setCprNumber(cprNumber);
				memberObj.setStatusId(memberStatusID);
				memberObj.setRoleId(roleId);
				memberObj.setMemberName(name);
				memberObj.setMemberNameNormalized(nameNormalized);
				memberObj.setMemberOccupation(occupation);
				memberObj.setMemberOccupationNormalized(occupationNormlized);
				memberObj.setOranizationId(organizationId);
				memberObj.setOrganizationOnBehalfId(organizaionOnBehalfId);
				memberObj.setMemberStartDate(startDate);
				memberObj.setMemberEndDate(memberEndDate);
				memberObj.setActionOperation(actionOperation);
				memberObj.setMemberDecreesList(getMemberDecrees(actionOperation,memberId,oldDecreesIdList,newDecreesIdList));
				memberObj.setNotificationBefore(memberNotificationBefore);
				memberObj.setNotificationEvery(memberNotificationAfter);
				members.add(memberObj);
			}
			
			boardDecrees= externalModel.getboardDecrees(boardID); // why here i go to data base to get decrees 

			createUpdateBoardEvent(boardJSON, boardID,
					ActionStatus.SUCCESS.name(), boardDecrees, members, getLoggedInUsername(),
					updateOn,boardStatusID);
			if (members != null && members.size() > 0)
				createUpdateMemberEvent(members,
						ActionStatus.SUCCESS.name(), boardJSON, updateOn);

			logger.info("end updateBoard() ");

		} catch (GBSFileUploadExcption e) {
			e.printStackTrace();
			createUpdateBoardEvent(boardJSON, boardID,
					ActionStatus.FAIL.name(), boardDecrees, members, getLoggedInUsername(),
					updateOn,boardStatusID);
			createUpdateMemberEvent(members, ActionStatus.FAIL.name(),
					boardJSON, updateOn);
			logger.error("FileUpload Error : " + e.getMessage(), e);
			throw new BoardException(e.getMessage(), e,
					GBSStatus.GBS_FILE_UPLOAD_FAILED.getStatusCode());
		} catch (DatabaseException e) {
			e.printStackTrace();
			createUpdateBoardEvent(boardJSON, boardID,
					ActionStatus.FAIL.name(), boardDecrees, members, getLoggedInUsername(),
					updateOn,boardStatusID);
			createUpdateMemberEvent(members, ActionStatus.FAIL.name(),
					boardJSON, updateOn);
			logger.error("DataBase Error : " + e.getMessage(), e);
			throw new BoardException(e.getMessage(), e,
					GBSStatus.GBS_DATABASE_EXCEPTION.getStatusCode());

		} catch (Throwable e) {
			e.printStackTrace();
			createUpdateBoardEvent(boardJSON, boardID,
					ActionStatus.FAIL.name(), boardDecrees, members, getLoggedInUsername(),
					updateOn,boardStatusID);
			createUpdateMemberEvent(members, ActionStatus.FAIL.name(),
					boardJSON, updateOn);
			logger.error("General Error : " + e.getMessage(), e);
			throw new BoardException(e.getMessage(), e,
					GBSStatus.GBS_GENERAL_EXCEPTION.getStatusCode());
		}
	}

	private void checkMemberNotificationChanged(Integer notificationBefore,Integer notificationEvery, Member oldMemberData, Integer memberID,Integer boardID, Date memberEndDate, Date updateOn) {
		if (notificationBefore != null && oldMemberData.getNotificationBefore()!= null &&  !notificationBefore.equals(oldMemberData.getNotificationBefore())) {
			// update
				externalModel.updateMemberNotification(memberID,notificationBefore,notificationEvery !=null ? notificationEvery : 0,memberEndDate,getLoggedInUsername(),updateOn);
				// get board notification ID and subscribers list
				List<Notifications> notificationsByMemberID = externalModel.getNotificationByMemberID(memberID);
				for (Notifications Notification : notificationsByMemberID) {
					if (!getLoggedInUsername().equals(Notification.getEmail())) {
						// check if subscriber exists in same notification
						externalModel.updateSubscriber(
								Notification.getNotificationId(),
								getLoggedInUsername(), updateOn);
					}
				}
		
		}else if (notificationBefore == null && oldMemberData.getNotificationBefore()!=null){
			// delete
				//delete subscriber
				externalModel.deleteSubscriberByMemberId(memberID);
				//delete notification
				externalModel.deleteNotificationByMemberId(memberID);

		}else if(notificationBefore != null && oldMemberData.getNotificationBefore() == null){
			// add
			Integer notificationId = externalModel.addMemberNotification(boardID,memberID,
					notificationBefore,notificationEvery !=null ? notificationEvery : 0,memberEndDate, getLoggedInUsername(), updateOn,NotificationType.MEMBER.name());
			externalModel.addSubscriber(notificationId,getLoggedInUsername(), updateOn);
		}
	}

	private List<Decree> getMemberDecrees(ActionOperation actionOperation,Integer memberId, List<Long> oldDecreesIdList,List<Long> newDecreesIdList) {
		List<Decree> memberDecrees= new ArrayList<Decree>();
		
			if(actionOperation.equals(ActionOperation.NOT_UPDATED)||actionOperation.equals(ActionOperation.UPDATE)){
				//insert the old decrees only
				memberDecrees= externalModel.getmemberDecrees(memberId,oldDecreesIdList);
			}
			else if (actionOperation.equals(ActionOperation.ADD))
			{
				//insert new decree only
				memberDecrees= externalModel.getmemberDecrees(memberId,newDecreesIdList);
			}
		
			else if (actionOperation.equals(ActionOperation.RENEW)){
				//insert new+old(search by member id) decrees
				List<Long> oldAndNewDecreesId = new ArrayList<Long>();
				oldAndNewDecreesId.addAll(oldDecreesIdList);
				oldAndNewDecreesId.addAll(newDecreesIdList);
				memberDecrees= externalModel.getmemberDecrees(memberId,oldAndNewDecreesId);
			}
			return memberDecrees;
	}

	public List<AttachmentType> getAttachmentTypesForMembers() {
		logger.info("start getAttachmentTypesForMembers() ");
		return externalModel.getAttachmentTypesForMembers();
	}

	public void updateExpiredBoardsStatus(Long boardId, String jobLoggedinUser, Date updatedOn) {
		logger.info("start updateExpiredBoardsStatus() ");
		externalModel.updateExpiredBoardsStatus(boardId,jobLoggedinUser,updatedOn);
	}

	public List<Decree> getboardDecrees(Integer boardID) {
		return externalModel.getboardDecrees(boardID);
	}

	public List<Board> getExpiredBoards() {
		return externalModel.getExpiredBoards();

	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {
			ExpiredBoardException.class, DatabaseException.class})
	public void updateExpiredBoardsStatusAndLogBoardHistory(Board expiredBoard,
			String jobLoggedinUser, Date createdOn, String actionStatus) {
		updateExpiredBoardsStatus(expiredBoard.getBoardId(),APP_Constant.JOB_LOGGEDIN_USER,createdOn);
		int boardExpiredStatusID= externalModel.getBoardExpiredStatus();
		createUpdateExpiredBoardEvent(expiredBoard,APP_Constant.JOB_LOGGEDIN_USER, createdOn,actionStatus,boardExpiredStatusID);
		
	}

	public List<MemberHistory> getMemberHistoryLog(Integer memberId) {
		logger.info("start getMemberHistoryLog() " , memberId);
		return externalModel.getMemberHistoryLog(memberId);
	}

	
	public void sendBoardNotificationMail(BoardNotification boardNotification , List<String> emailsList) throws BusinessException{
		
		logger.info("start sendBoardNotificationMail() " , boardNotification , emailsList);
		
		Properties properties = MailUtil.loadPropsFile(APP_Constant.MAIL_PROPERTIES_FILE_NAME);
		String from = null;
		String subject = null;
		try {
			if (properties != null) {
				from = properties.getProperty(APP_Constant.MAIL_FROM);
				subject = properties.getProperty(APP_Constant.MAIL_SUBJECT);
			}

			Date sendDate = new Date();
			
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("boardName", boardNotification.getBoardName());
			model.put("boardExpiryDate", boardNotification.getBoardExpiryDate());
			
			String to = MailUtil.getSemiCommaSeperatedEmails(emailsList);
			
			if (!StringUtils.isEmpty(from) && !StringUtils.isEmpty(to)) {
				MailUtil.sendMailUsingVelocityTemplate(from, to ,
						subject, mailSender, velocityEngine, model,
						APP_Constant.BOARD_NOTIFICATION_MAIL_TEMPLATE, sendDate);
			}else{
				throw new BusinessException(
						"sendBoardNotificationMail() : Failed to send Mail , Error fromEmail is null or to is null or empty");
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("sendBoardNotificationMail() : Failed to send mail due to business exception , error : " , e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("sendBoardNotificationMail() : Failed to send mail due to exception , error : " , e.getMessage());
		}
		
	}
	
	
	public void sendMemberNotificationMail(MemberNotification memberNotification , List<String> emailsList) throws BusinessException {
		
		logger.info("start sendMemberNotificationMail() " , emailsList);
		
		Properties properties = MailUtil.loadPropsFile(APP_Constant.MAIL_PROPERTIES_FILE_NAME);
		String from = null;
		String subject = null;
		try {
			if (properties != null) {
				from = properties.getProperty(APP_Constant.MAIL_FROM);
				subject = properties.getProperty(APP_Constant.MAIL_MEMBER_SUBJECT);
			}

			Date sendDate = new Date();
			
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("memberName", memberNotification.getMemberName());
			model.put("memberExpiryDate", memberNotification.getMemberEndDate());
			
			String to = MailUtil.getSemiCommaSeperatedEmails(emailsList);
			
			if (!StringUtils.isEmpty(from) && !StringUtils.isEmpty(to)) {
				MailUtil.sendMailUsingVelocityTemplate(from, to ,
						subject, mailSender, velocityEngine, model,
						APP_Constant.MEMBER_NOTIFICATION_MAIL_TEMPLATE, sendDate);
			}else{
				throw new BusinessException(
						"sendMemberNotificationMail () : Failed to send Mail , Error fromEmail is null or emailsList is null or empty");
			}
			
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("sendMemberNotificationMail() :Failed to send mail due to business exception , error : " , e.getMessage());
		} catch (Exception e) {
			logger.error("sendMemberNotificationMail() : Failed to send mail due to exception , error : " , e.getMessage());
		}

		
	}
	
	
	public Date getActualNotificationDate(Date endDate , Integer notificationBefore){
		Integer actualDays = DateUtil.getActualDaysFromWorkingDays(notificationBefore);
		Date actualNotificationDate = DateUtil.getFirstActualDateOfDeliveryDate(endDate, actualDays);
		return actualNotificationDate;
	}

	public List<BoardHistrory> getBoardHistoryLog(Integer boardId) {
		logger.info("start getBoardHistoryLog() " , boardId);
		return externalModel.getBoardHistoryLog(boardId);
	}

	public List<MemberNotification> getMemberNotifications() {
		logger.info("start getMemberNotifications() ");
		return externalModel.getMemberNotifications();
	}

	public List<Member> getExpiredMembers() {
		return externalModel.getExpiredMembers();
	}

	public void updateExpiredMembersStatusAndLogMemberHistory(
			Member expiredMember, String jobLoggedinUser, Date createdOn,
			String actionStatus) {
		updateExpiredMembersStatus(expiredMember.getMemberId(),APP_Constant.JOB_LOGGEDIN_USER,createdOn);
		int memberExpiredStatusID= externalModel.getMemberExpiredStatus();
		createUpdateExpiredMemberEvent(expiredMember,APP_Constant.JOB_LOGGEDIN_USER, createdOn,actionStatus,memberExpiredStatusID);
		
	}

	public void createUpdateExpiredMemberEvent(Member expiredMember,
			String loggedInUsername, Date createdOn, String actionStatus,
			Integer expiredMemberStatus) {
		logger.info("start createUpdateExpiredMemberEvent({}) ", DataBaseUtil.paramList(expiredMember.getMemberId(),  loggedInUsername,  createdOn));		
		MemberHistory updateExpiredMemberHistrory = new MemberHistory();

		List<Decree> decreeList = externalModel.getmemberDecrees(expiredMember.getMemberId().intValue());
		Board boardDetails= externalModel.getBoard(expiredMember.getBoardId());
		
		updateExpiredMemberHistrory.setMemberId(expiredMember.getMemberId());
		updateExpiredMemberHistrory.setMemberName(expiredMember.getMemberName());
		updateExpiredMemberHistrory.setCprNumber(expiredMember.getCprNumber());
		updateExpiredMemberHistrory.setMemberOccuption(expiredMember.getMemberOccupation());
		updateExpiredMemberHistrory.setOrganizationId(expiredMember.getOranizationId());
		updateExpiredMemberHistrory.setRoleId(expiredMember.getRoleId());
		updateExpiredMemberHistrory.setOrganizationOnBehalfId(expiredMember.getOrganizationOnBehalfId());
		updateExpiredMemberHistrory.setDecrees(decreeList);
		updateExpiredMemberHistrory.setLoggedInUser(loggedInUsername);
		updateExpiredMemberHistrory.setCreatedOn(createdOn);
		updateExpiredMemberHistrory.setMemberStartDate(expiredMember.getMemberStartDate());
		updateExpiredMemberHistrory.setMemberEndDate(expiredMember.getMemberEndDate());
		updateExpiredMemberHistrory.setBoardId(expiredMember.getBoardId());
		updateExpiredMemberHistrory.setBoardName(boardDetails.getBoardName());
		updateExpiredMemberHistrory.setBoardTypeId(boardDetails.getBoardTypeId());
		updateExpiredMemberHistrory.setBoardExpiryDate(boardDetails.getBoardExpiryDate());
		updateExpiredMemberHistrory.setBoardStartDate(boardDetails.getBoardStartDate());
		updateExpiredMemberHistrory.setParentBoardId(boardDetails.getParentBoardId());
		updateExpiredMemberHistrory.setStatus(actionStatus);
		updateExpiredMemberHistrory.setActionOperation(ActionOperation.EXPIRE);
		updateExpiredMemberHistrory.setMemberStatusId(expiredMemberStatus);
		updateExpiredMemberHistrory.setNotificationBefore(expiredMember.getNotificationBefore());
		updateExpiredMemberHistrory.setNotificationRepeat(expiredMember.getNotificationEvery());
		updateExpiredMemberHistrory.setNotificationDate(expiredMember.getNotificationBefore() ==  null ? null : getActualNotificationDate(expiredMember.getMemberEndDate(), expiredMember.getNotificationBefore()));
		
		
		applicationContext.publishEvent(new MemberEvent(updateExpiredMemberHistrory));
		logger.info("end createUpdateExpiredMemberEvent() ");
		
	}

	private void updateExpiredMembersStatus(Integer memberId,
			String jobLoggedinUser, Date updatedOn) {
		logger.info("start updateExpiredMembersStatus() ");
		externalModel.updateExpiredMembersStatus(memberId,jobLoggedinUser,updatedOn);
		
	}
}
