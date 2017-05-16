package bh.gov.cio.gbs.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bh.gov.cio.gbs.model.AttachmentType;
import bh.gov.cio.gbs.model.BoardDecrees;
import bh.gov.cio.gbs.model.BoardMembers;
import bh.gov.cio.gbs.model.BoardType;
import bh.gov.cio.gbs.model.DecreeType;
import bh.gov.cio.gbs.model.GovernmentBoardType;
import bh.gov.cio.gbs.model.Member;
import bh.gov.cio.gbs.model.NotificationPeriod;
import bh.gov.cio.gbs.model.NotificationRepeat;
import bh.gov.cio.gbs.model.Organization;
import bh.gov.cio.gbs.model.Role;
import bh.gov.cio.gbs.model.Status;
import bh.gov.cio.gbs.service.BoardManager;

@Controller 
@RequestMapping("/lookup")
@RolesAllowed(value={"ROLE_ADMIN" , "ROLE_USER"})
public class LookupController {
	
	private static final Logger logger = LoggerFactory.getLogger(LookupController.class);
	
	@Autowired
	private BoardManager boardManager;
	
	@RequestMapping(value="/organizations" , method=RequestMethod.GET , produces="application/json")
	public @ResponseBody List<Organization> getOrganizations(HttpServletRequest request){
		return boardManager.getOrganizations();
	}
	
	@RequestMapping(value="/decreeTypes" , method=RequestMethod.GET , produces="application/json")
	public @ResponseBody List<DecreeType> getDecreeTypes(HttpServletRequest request){
		return boardManager.getDecreeTypes();
	}
	
	@RequestMapping(value="/members" , method=RequestMethod.GET , produces="application/json")
	public @ResponseBody List<Member> getMembers(HttpServletRequest request){
		return boardManager.getMembers();
	}
	
	@RequestMapping(value="/board/members" , method=RequestMethod.GET , produces="application/json")
	public @ResponseBody List<BoardMembers> getBoardMembers(HttpServletRequest request){
		return boardManager.getBoardMembers();
	}
	
	@RequestMapping(value="/board/decrees" , method=RequestMethod.GET , produces="application/json")
	public @ResponseBody List<BoardDecrees> getBoardDecree(HttpServletRequest request){
		return boardManager.getBoardDecrees();
	}
	
	@RequestMapping(value="/orgnizations/source" , method=RequestMethod.GET , produces="application/json")
	public @ResponseBody List<Organization> getSourceOrgnizations(HttpServletRequest request){
		return boardManager.getSourceOrgnizations();
	}
	@RequestMapping(value="/organizations/destination" , method=RequestMethod.GET , produces="application/json")
	public @ResponseBody List<Organization> getDestinationOrganizations(HttpServletRequest request){
		return boardManager.getDestinationOrganizations();
	}
	
	
	@RequestMapping(value="/roles" , method=RequestMethod.GET , produces="application/json")
	public @ResponseBody List<Role> getRoles(HttpServletRequest request){
		return boardManager.getRoles();
	}
	
	@RequestMapping(value="/attachmentTypes" , method=RequestMethod.GET , produces="application/json")
	public @ResponseBody List<AttachmentType> getAttachmentTypes(HttpServletRequest request){
		return boardManager.getAttachmentTypes();
	}
	
	@RequestMapping(value="/attachmentTypesForMember" , method=RequestMethod.GET , produces="application/json")
	public @ResponseBody List<AttachmentType> getAttachmentTypesForMembers(HttpServletRequest request){
		return boardManager.getAttachmentTypesForMembers();
	}
	
	@RequestMapping(value="/boardTypes" , method=RequestMethod.GET , produces="application/json")
	public @ResponseBody List<BoardType> getBoardTypes(HttpServletRequest request){
		return boardManager.getBoardTypes();
	}
	
	@RequestMapping(value="/notification/periods" , method=RequestMethod.GET , produces="application/json")
	public @ResponseBody List<NotificationPeriod> getNotificationPeriods(HttpServletRequest request){
		return boardManager.getNotificationPeriods();
	}
	
	@RequestMapping(value="/notification/repeats" , method=RequestMethod.GET , produces="application/json")
	public @ResponseBody List<NotificationRepeat> getNotificationRepeat(HttpServletRequest request){
		return boardManager.getNotificationRepeats();
	}
	
	
	@RequestMapping(value="/governmentBoradTypes" , method=RequestMethod.GET , produces="application/json")
	public @ResponseBody List<GovernmentBoardType> getGovernmentBoardTypes(HttpServletRequest request){
		return boardManager.getGovernmentBoardTypes();
	}
	
	
	@RequestMapping(value="/board/status" , method=RequestMethod.GET , produces="application/json")
	public @ResponseBody List<Status> getBoardStatusList(HttpServletRequest request){
		return boardManager.getBoardStatusList();
	}
	
	@RequestMapping(value="/member/status" , method=RequestMethod.GET , produces="application/json")
	public @ResponseBody List<Status> getMemberStatusList(HttpServletRequest request){
		return boardManager.getMemberStatusList();
	}
	
	
	
}
