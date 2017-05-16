package bh.gov.cio.gbs.systemevent;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import bh.gov.cio.gbs.model.BoardHistrory;
import bh.gov.cio.gbs.model.MemberHistory;
import bh.gov.cio.gbs.model.NotificationHistory;
import bh.gov.cio.gbs.model.UserLoginHistory;
import bh.gov.cio.gbs.service.SupplementaryModelManager;

@SuppressWarnings("rawtypes")
public class ApplicationHistoryListener implements ApplicationListener {
	
	private SupplementaryModelManager externalModel;
	public void setExternalModel(SupplementaryModelManager externalModel) {
		this.externalModel = externalModel;
	}

	public void onApplicationEvent(ApplicationEvent event) {
		if(event instanceof LoginEvent){
			// log login Event
			UserLoginHistory userLoginHistory = ((LoginEvent) event).getUserLoginHistory();
			externalModel.recordLoginEvent(userLoginHistory);
		}else if(event instanceof BoardEvent){
			// log board Event
			BoardHistrory boardHistrory = ((BoardEvent) event).getBoardHistrory();
			externalModel.recordBoardEvent(boardHistrory);
		}else if(event instanceof NotificationEvent){
			// log Notification Event
			NotificationHistory notificationHistory = ((NotificationEvent) event).getNotificationHistory();
			externalModel.recordNotificationEvent(notificationHistory);
		}else if(event instanceof MemberEvent){
			// log Notification Event
			MemberHistory memberHistory = ((MemberEvent) event).getMemberHistory();
			externalModel.recordMemberEvent(memberHistory);
		}
		
	}

	


}
