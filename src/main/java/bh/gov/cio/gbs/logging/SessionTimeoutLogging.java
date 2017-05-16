package bh.gov.cio.gbs.logging;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bh.gov.cio.gbs.service.BoardManager;

public class SessionTimeoutLogging {
	
	private static final Logger logger = LoggerFactory.getLogger(SessionTimeoutLogging.class);
	
	public static final String COMMA = ",";
	
	private BoardManager boardManager;

	public void setBoardManager(BoardManager boardManager) {
		this.boardManager = boardManager;
	}
	
	
	public void handleSessionTimeout(String userSessionId){
		logger.info("userSessionId : ", userSessionId);
		if(!StringUtils.isBlank(userSessionId)){
			String [] userInfo = userSessionId.split(COMMA);
			boardManager.recordSessionTimeoutEvent(userInfo[0] , userInfo[1]);
		}
	}


	public void setUserSessionId(HttpSession session) {
		logger.info("sessionId : ", session.getId());
		if(!StringUtils.isBlank(session.getId())){
			String username =boardManager.getUserBySessionId(session.getId());
			String sessionUserAttribute = username + COMMA + session.getId();
			session.setAttribute(session.getId(), sessionUserAttribute);
		}
		
	}
	
}
