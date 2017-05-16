package bh.gov.cio.gbs.notification;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.security.core.context.SecurityContextHolder;

import bh.gov.cio.gbs.exception.DatabaseException;
import bh.gov.cio.gbs.exception.ExpiredMemberException;
import bh.gov.cio.gbs.exception.NotificationException;
import bh.gov.cio.gbs.model.ActionStatus;
import bh.gov.cio.gbs.model.JobRunnableType;
import bh.gov.cio.gbs.model.Member;
import bh.gov.cio.gbs.model.MemberNotification;
import bh.gov.cio.gbs.model.NotificationStatus;
import bh.gov.cio.gbs.service.BoardManager;
import bh.gov.cio.gbs.util.APP_Constant;
import bh.gov.cio.gbs.util.DateUtil;

public class MemberNotificationJob extends QuartzJobBean{
	
	public static final Logger logger = LoggerFactory
			.getLogger(MemberNotificationJob.class);

		
	/** The Board Manager */
	private BoardManager externalBoardService;
	
	public void setExternalBoardService(BoardManager externalBoardService) {
		this.externalBoardService = externalBoardService;
	}

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		
		Integer notificationID = null;
		Date notificationDate = null;
		List<String> emailsList = null;
		Date createdOn = new Date();
		Member member = null;

		try {
			 List <MemberNotification> runnableNotifications= externalBoardService.getMemberNotifications();
			 Map<MemberNotification, List<String>> subscribersEmailMap = new HashMap<MemberNotification, List<String>>();
			 
			for (MemberNotification Notification : runnableNotifications) {
				
				if (!subscribersEmailMap.containsKey(Notification)) {
					List <String> emailList = new ArrayList<String>();
					emailList.add(Notification.getEmail());

					subscribersEmailMap.put(Notification, emailList);
				} else {
					subscribersEmailMap.get(Notification).add(Notification.getEmail());
				}
				
			}

			for (MemberNotification notification : subscribersEmailMap.keySet()) {
			    // send mail
			    String eventDescription = "The notification with ID: "+notification.getNotificationId()+" run successfully on "+new Date()+"  for the following mails list: "+subscribersEmailMap.get(notification);
			    
			    notificationID = notification.getNotificationId();
			    notificationDate = notification.getNotificationDate();
			    emailsList = subscribersEmailMap.get(notification);
			    
			    //Publish notification event
			    externalBoardService.createNotificationEvent(notificationID,notificationDate,emailsList,JobRunnableType.NOTIFICATION_MEMBER_AUTOMATIC, NotificationStatus.SUCCESS,eventDescription );
				
			    //update notifications' dates
			    Date updatedNotificationDate= calculateNextNotificationDate(notification.getNotificationDate(), notification.getNotificationRepaetEvery());
				externalBoardService.updateNotificationDate(updatedNotificationDate,notification.getNotificationDate(),notification.getNotificationId());
				
				// send mail 
				externalBoardService.sendMemberNotificationMail(notification , emailsList);
				
			}
			
			//Create job to update the board status to expired
			List<Member> expiredMembers= externalBoardService.getExpiredMembers();
			
			for (Member expiredMember : expiredMembers){
				member = expiredMember;				
				externalBoardService.updateExpiredMembersStatusAndLogMemberHistory(expiredMember , APP_Constant.JOB_LOGGEDIN_USER, createdOn,ActionStatus.SUCCESS.name());
			}
			
		} catch (Throwable e) {
			if(e instanceof NotificationException){
				// mail exception
			    String eventDescription = "Could not create notification event successfully because of an "+ e.getMessage();
			    
			    //Publish notification event
			    externalBoardService.createNotificationEvent(notificationID,notificationDate,emailsList,JobRunnableType.NOTIFICATION_MEMBER_AUTOMATIC, NotificationStatus.FAIL,eventDescription);
			    
				logger.error("Notification Event Exception: " + e.getMessage(), e);
				
			}
			else if(e instanceof ExpiredMemberException){			    
			    //Publish expired board event
				externalBoardService.createUpdateExpiredMemberEvent(member,getLoggedInUsername(), createdOn,ActionStatus.FAIL.name(),null);
				logger.error("Expired Member Exception: " + e.getMessage(), e);
				
			}else if(e instanceof DatabaseException){			    
				logger.error("Data base Failed Excption: " + e.getMessage(), e);
				
			}
		}
		
		
	}
	
	public static Date calculateNextNotificationDate(Date NotificationDate, Integer repeatEvery) {
	    Integer workingRepeatDays=DateUtil.getActualDaysFromWorkingDays(repeatEvery);
		Integer actualDays= DateUtil.getActualDaysFromWorkingDays(workingRepeatDays);
		return DateUtil.getResponseActualDateOfDeliveryDate(NotificationDate, actualDays);			
	}
	
	
	
	public String getLoggedInUsername() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
}
