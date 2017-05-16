package bh.gov.cio.gbs.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bh.gov.cio.gbs.model.Notifications;
import bh.gov.cio.gbs.service.BoardManager;

@Controller 
@RequestMapping("/notification")
@RolesAllowed(value={"ROLE_ADMIN" , "ROLE_USER"})
public class NotificationController {
	
	private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);
	
	@Autowired
	private BoardManager boardManager;
	
	
	@RequestMapping(value="/runnableNotifications" , method=RequestMethod.GET , produces="application/json")
	public @ResponseBody List<Notifications> getRunnableNotifications(){
		logger.info("getRunnableNotifications : start()");
		return boardManager.getRunnableNotifications();
	}
	
}
