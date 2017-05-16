package bh.gov.cio.gbs.controller.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.context.support.WebApplicationContextUtils;

import bh.gov.cio.gbs.logging.SessionTimeoutLogging;

public class SessionCounterListener extends HttpSessionEventPublisher {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory
			.getLogger(SessionCounterListener.class);
	

	
	private static int totalActiveSessions;
	
	public static int getTotalActiveSession(){
		return totalActiveSessions;
	}

	@Override
	public void sessionCreated(HttpSessionEvent sessionEvent) {
		totalActiveSessions++;
		logger.info("sessionCreated () : users count : " + getTotalActiveSession());
		
//		synchronized (this) {
//			setUserSessionId(sessionEvent);
//		}
		super.sessionCreated(sessionEvent);
	}

	private void setUserSessionId(HttpSessionEvent sessionEvent) {
		
		HttpSession session = sessionEvent.getSession();
		
		ApplicationContext ctx =
                WebApplicationContextUtils.
                      getWebApplicationContext(session.getServletContext());

		SessionTimeoutLogging sessionTimeoutLogging =
                      (SessionTimeoutLogging) ctx.getBean("SessionTimeoutLoggerService");
		
		sessionTimeoutLogging.setUserSessionId(session);
		
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
		HttpSession session = sessionEvent.getSession();
		String userSessionId = (String)session.getAttribute(session.getId());
		Boolean logout = (Boolean)session.getAttribute("logout");
		
//		logger.info("userSessionId () " + userSessionId);
		
		if(totalActiveSessions > 0){
			totalActiveSessions--;
		}
		logger.info("sessionDestroyed () : users count : " + getTotalActiveSession());
		
		if(null == logout || !logout)
			handleSession(session , userSessionId);
		
		 super.sessionDestroyed(sessionEvent);
		 
	}

	private void handleSession(HttpSession session  , String userSessionId ) {
		
		ApplicationContext ctx =
                WebApplicationContextUtils.
                      getWebApplicationContext(session.getServletContext());

		SessionTimeoutLogging sessionTimeoutLogging =
                      (SessionTimeoutLogging) ctx.getBean("SessionTimeoutLoggerService");
//		logger.info(" session id : " , session.getId());
		sessionTimeoutLogging.handleSessionTimeout(userSessionId);

	}
	
	
	
	


}
