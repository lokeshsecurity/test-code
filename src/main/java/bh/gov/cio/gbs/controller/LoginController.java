package bh.gov.cio.gbs.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import bh.gov.cio.gbs.model.SystemRole;
import bh.gov.cio.gbs.service.BoardManager;
import bh.gov.cio.gbs.util.CommonUtil;


@Controller 
@RequestMapping("/login")
public class LoginController {
	
private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	private static final String USER_INFO_COOKIE="userInfo";
	private static final String SERVER_TIME_COOKIE="serverTime";
	private static final String SESSION_EXPIRY_COOKIE="sessionExpiry";
	public static final String COMMA = ",";
	
	
	@Autowired
	private BoardManager boardManager;
	
	@RequestMapping(value="/getLoggedUser", method = RequestMethod.GET)  
	 public void login(HttpServletRequest request , Principal principal , HttpServletResponse response) {  
	   
		String email = principal.getName();
		
		// for handling session time out and log it in database
		HttpSession session= request.getSession();
		String sessionUserAttribute = email + COMMA + session.getId();
		session.setAttribute(session.getId(), sessionUserAttribute);
		
		boardManager.createLoginEvent(email , session.getId());
		logger.info("LoginController.login(): User Email  {} ", email);
		
		 if(email != null) {
		 	try {
		 		String cookieData = email;
		 		logger.info("cookieData : " + cookieData);
		 		Cookie cookie = new Cookie(USER_INFO_COOKIE, cookieData);
		 		cookie.setPath("/");
				cookie.setMaxAge(60*60*24); //24 Hour
				response.addCookie(cookie);				
				response.sendRedirect("/gbs/index.html"); //?ownerCprNumber=" + dbUser.getId()
			} catch (IOException exception) {
				logger.error("LoginController.login() Error while logging : {} ", exception);
			}
		 } else {
			 try {
				response.sendRedirect("/gbs/login.html");
			} catch (IOException exception) {
				logger.error("LoginController.login() Error while logging : {} ", exception);
			}
		 }
	   
	 }  
	
	
	
	 @RequestMapping(value="/logoutAction", method = RequestMethod.GET)  
	 public void logout(HttpServletRequest request, HttpServletResponse response) {		 
		logger.info("Logging out ............................................");	
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		logger.info("Logout - user : " + auth.getName());
		if(auth.isAuthenticated()){
			HttpSession session = request.getSession(false);
			boardManager.createLogoutEvent(auth.getName() , session!= null ?session.getId() : null);
			session.setAttribute("logout", true);
		}
		
       if (auth != null){    
          new SecurityContextLogoutHandler().logout(request, response, auth);
       }
       SecurityContextHolder.getContext().setAuthentication(null);
       
     
		
		
//		if (session != null) {
//			session.invalidate();
//			logger.info("Invalidating the session ............................................");
//		}

		Cookie loginCookie = null;
		Cookie serverTimeCookie = null;
		Cookie sessionExpiryCookie = null;
		
		loginCookie=CommonUtil.getCookieByName(request ,USER_INFO_COOKIE);
		serverTimeCookie=CommonUtil.getCookieByName(request ,SERVER_TIME_COOKIE);
		sessionExpiryCookie=CommonUtil.getCookieByName(request ,SESSION_EXPIRY_COOKIE);
		
		if (serverTimeCookie != null) {
			serverTimeCookie.setValue(null);
			serverTimeCookie.setPath("/");
			serverTimeCookie.setMaxAge(0);
			response.addCookie(serverTimeCookie);
			logger.info("Deleting serverTime cookie  ............................................");
		}
		
		if (sessionExpiryCookie != null) {
			sessionExpiryCookie.setValue(null);
			sessionExpiryCookie.setPath("/");
			sessionExpiryCookie.setMaxAge(0);
			response.addCookie(sessionExpiryCookie);
			logger.info("Deleting sessionExpiry cookie ............................................");
		}
		
		if (loginCookie != null) {
			loginCookie.setValue(null);
			loginCookie.setPath("/");
			loginCookie.setMaxAge(0);
			response.addCookie(loginCookie);
			logger.info("Deleting login cookie ............................................");
		}
		
		try {
			response.sendRedirect("/gbs/login.html");
		} catch (IOException exception) {
			logger.error("LoginController.logout() Error while logout : {} ", exception);
		}
	 }
	 
	 
	 	@RequestMapping(value="/userRoles",method = RequestMethod.GET, produces = "application/json")
		@ResponseStatus(value = HttpStatus.OK)
		public @ResponseBody List<SystemRole> getUserRoles(HttpServletRequest request , Principal principal){
	 		logger.info("principal.getName()  : " + principal.getName());
	 		List<SystemRole> roles = new ArrayList<SystemRole>(); 
//	 		AccessControlManager accessControlManager = (AccessControlManager) SpringApplicationContext.getBean(SpringApplicationContext.ACCESS_CONTROL);
//	 		List<Role> roles=accessControlManager.getUserRoles(principal.getName());
//	 		logger.info("Roles : " + roles.size());
//	 		return roles;
	 		
	 		Collection<? extends GrantedAuthority> authorites=SecurityContextHolder.getContext().getAuthentication().getAuthorities();
	 		for (GrantedAuthority grantedAuthority : authorites) {
	 			SystemRole role = new SystemRole();
	 			role.setName(grantedAuthority.getAuthority());
	 			roles.add(role);
			}
	 		
	 		return roles;
	 	}
	 
	 

}
