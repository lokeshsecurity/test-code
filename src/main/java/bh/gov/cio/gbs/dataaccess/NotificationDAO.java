package bh.gov.cio.gbs.dataaccess;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import bh.gov.cio.gbs.exception.ApplicationException;
import bh.gov.cio.gbs.exception.DatabaseException;
import bh.gov.cio.gbs.model.BoardNotification;
import bh.gov.cio.gbs.model.MemberNotification;
import bh.gov.cio.gbs.model.NotificationHistory;
import bh.gov.cio.gbs.model.NotificationPeriod;
import bh.gov.cio.gbs.model.NotificationRepeat;
import bh.gov.cio.gbs.model.Notifications;
import bh.gov.cio.gbs.model.Subscriber;
import bh.gov.cio.gbs.util.DataBaseUtil;
import bh.gov.cio.gbs.util.DateUtil;

public class NotificationDAO extends BaseDAO implements QueryStore {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory
			.getLogger(NotificationDAO.class);

	/**
	 * Test transaction.
	 * 
	 * @param input
	 *            the input
	 * @throws DataAccessException
	 *             the data access exception
	 * @throws ApplicationException
	 *             the application exception
	 */

	public List<Notifications> getRunnableNotifications()
			throws DatabaseException {
		logger.info("getRunnableNotifications()");
		Date startOfDay = DateUtil.getStartOfDay(new Date());
		Date endOfDay = DateUtil.getEndOfDay(new Date());
		Date today = new Date();
		
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				new Timestamp(startOfDay.getTime()),
				new Timestamp(endOfDay.getTime()),
				new Timestamp(today.getTime())),
				new String[] {"TODAY",
				"NOTIFICATION_DATE_START", "NOTIFICATION_DATE_END" });
		
		return getList(QueryStore.Queries.GET_RUNNABLE_NOTIFICATIONS_QUERY, Notifications.class , params);
	}

	public void recordNotificationEvent(NotificationHistory notificationHistory) {
		
		logger.info("recordNotificationEvent({})" , notificationHistory.toString());

		Map<String, Object> params = fillParams(
				DataBaseUtil.paramList(
						notificationHistory.getNotificationId(),
						notificationHistory.getStatus().name(),
						new Timestamp(notificationHistory.getRunDate()
								.getTime()),
						notificationHistory.getDescription(),
						notificationHistory.getJobRunnableType().name(),
						notificationHistory.getCreatedBy() != null ? notificationHistory
								.getCreatedBy() : null,
						notificationHistory.getCreatedOn() != null ? new Timestamp(
								notificationHistory.getCreatedOn().getTime())
								: null,
						notificationHistory.getUpdatedBy() != null ? notificationHistory
								.getUpdatedBy() : null, notificationHistory
								.getUpdatedOn() != null ? new Timestamp(
								notificationHistory.getUpdatedOn().getTime())
								: null), new String[] { "NOTIFICATION_ID",
						"STATUS", "RUN_DATE", "DESCRIPTION",
						"JOB_RUNNABLE_TYPE", "CREATED_BY", "CREATED_ON",
						"UPDATED_BY", "UPDATED_ON" });
		executeQuery(QueryStore.Queries.RECORD_NOTIFICATION_HISTORY_QUERY,
				params);

	}

	public List<Subscriber> getSubscriberEmailByNID(Long notificationId) {
		logger.info("getSubscriberEmailByNID({})" , notificationId);

		Map<String, Object> params = fillParams(
				DataBaseUtil.paramList(notificationId),
				new String[] { "NOTIFICATION_ID" });
		return getList(QueryStore.Queries.GET_SUBSCRIBER_EMAIL_QUERY,
				Subscriber.class, params);

	}
	public List<NotificationPeriod> getNotificationPeriods() {
		logger.info("getNotificationPeriods()");
		return getList(QueryStore.Queries.GET_NOTIFICATION_PERIOD_QUERY, NotificationPeriod.class);
	}

	public List<NotificationRepeat> getNotificationRepeats() {
		logger.info("getNotificationRepeat()");
		return getList(QueryStore.Queries.GET_NOTIFICATION_REPEAT_QUERY, NotificationRepeat.class);
	}

	public void updateNotificationDate(Date updatedNotificationDate,Date updatedLastRunDate, Integer notificationID) {
		logger.info("UPDATE_NOTIFICATION_DATE_QUERY()");
		
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(new Timestamp(updatedNotificationDate.getTime()),new Timestamp(updatedLastRunDate.getTime()),notificationID), new String[] {"UPDATED_NOTIFICATION_DATE","UPDATED_LAST_RUN_DATE" ,"NOTIFICATION_ID"});		
		executeQuery(QueryStore.Queries.UPDATE_NOTIFICATION_DATES_QUERY,params);
	}

	public List<BoardNotification> getBoardNotifications() {
		
		logger.info("getBoardNotifications()");
		Date startOfDay = DateUtil.getStartOfDay(new Date());
		Date endOfDay = DateUtil.getEndOfDay(new Date());
		Date today = new Date();
		
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				new Timestamp(startOfDay.getTime()),
				new Timestamp(endOfDay.getTime()),
				new Timestamp(today.getTime())),
				new String[] {"TODAY",
				"NOTIFICATION_DATE_START", "NOTIFICATION_DATE_END" });
		return getList(QueryStore.Queries.GET_BOARD_NOTIFICATIONS_QUERY,
				BoardNotification.class, params);
	}

	public List<MemberNotification> getMemberNotifications() {

		logger.info("getMemberNotifications()");
		Date startOfDay = DateUtil.getStartOfDay(new Date());
		Date endOfDay = DateUtil.getEndOfDay(new Date());
		Date today = new Date();
		
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				new Timestamp(startOfDay.getTime()),
				new Timestamp(endOfDay.getTime()),
				new Timestamp(today.getTime())),
				new String[] {"TODAY",
				"NOTIFICATION_DATE_START", "NOTIFICATION_DATE_END" });
		return getList(QueryStore.Queries.GET_MEMBER_NOTIFICATIONS_QUERY,
				MemberNotification.class, params);
	}
}
