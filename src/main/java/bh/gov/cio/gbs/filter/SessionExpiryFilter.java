package bh.gov.cio.gbs.filter;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bh.gov.cio.gbs.util.CommonUtil;

public class SessionExpiryFilter implements Filter {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory
			.getLogger(SessionExpiryFilter.class);

	private static final String ANONYMUS_USER = "Anonymous user";
	private static final String SERVER_TIME_COOKIE = "serverTime";
	private static final String SESSION_EXPIRY_COOKIE = "sessionExpiry";

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// for browser session timeout
		logger.info("Start session Expiry filter ");
		String currentUser = null;
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession currentSession = httpRequest.getSession(false);
		logger.info("currentSession  id : " + (currentSession !=null ? currentSession.getId() : null));
		logger.info("Session Filter - get Remote User  : " + (httpRequest.getRemoteUser()!=null ? httpRequest.getRemoteUser() : null));
		logger.info("Session Filter - IP : " + httpRequest.getRemoteHost());
		if (httpRequest.getRemoteUser() != null && currentSession != null) {
			currentUser = httpRequest.getRemoteUser();
		} else {
			currentUser = ANONYMUS_USER;
		}
		if (currentSession != null) {

			long currTime = System.currentTimeMillis();
			long expiryTime = currTime
					+ Integer
							.valueOf(
									(httpRequest.getSession()
											.getMaxInactiveInterval() * 1000))
							.longValue();

			Cookie sessionCookie = new Cookie(SERVER_TIME_COOKIE, "" + currTime);
			sessionCookie.setPath("/");
			httpResponse.addCookie(sessionCookie);
			if (currentUser.equals(ANONYMUS_USER)) {
				logger.info("Expiry time  for current user : " + currentUser
						+ " : "
						+ CommonUtil.getDateTimeFromDate(new Date(currTime)));
				sessionCookie = new Cookie(SESSION_EXPIRY_COOKIE, "" + currTime);
			} else {
				sessionCookie = new Cookie(SESSION_EXPIRY_COOKIE, ""
						+ expiryTime);
				logger.info("Expiry time  for current user : " + currentUser
						+ " : "
						+ CommonUtil.getDateTimeFromDate(new Date(expiryTime)));
			}
			sessionCookie.setPath("/");
			httpResponse.addCookie(sessionCookie);
		} else {
			logger.info("Session timeout");
//			logger.info("Session timeout : " + currentUser);
//			logger.info("Session timeout  -  Set session cookie , server cookie with current date");
//			long currTime = System.currentTimeMillis();
//			Cookie sessionCookie = new Cookie(SERVER_TIME_COOKIE, "" + currTime);
//			sessionCookie.setPath("/");
//			httpResponse.addCookie(sessionCookie);
//			sessionCookie = new Cookie(SESSION_EXPIRY_COOKIE, "" + currTime);
//			sessionCookie.setPath("/");
//			httpResponse.addCookie(sessionCookie);
			
		}
		logger.info("End session Expiry filter ");
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
