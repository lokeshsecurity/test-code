package bh.gov.cio.gbs.dataaccess;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bh.gov.cio.gbs.exception.DatabaseException;
import bh.gov.cio.gbs.model.AttachmentType;
import bh.gov.cio.gbs.model.BoardType;
import bh.gov.cio.gbs.model.DecreeType;
import bh.gov.cio.gbs.model.GovernmentBoardType;
import bh.gov.cio.gbs.model.Organization;
import bh.gov.cio.gbs.model.Role;
import bh.gov.cio.gbs.model.Status;
import bh.gov.cio.gbs.util.DataBaseUtil;

public class LookupDAO extends BaseDAO implements QueryStore{
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(LookupDAO.class);
	
	
	public List<Organization> getOrganzations() throws DatabaseException{
		logger.info("getOrganzations()");
		return getList(QueryStore.Queries.GET_ORGANIZATIONS_QUERY, Organization.class);
	}
	
	
	public List<Organization> searchOrganization(String text) throws DatabaseException{
		logger.info("searchOrganization({})" , text);
		String queryParam = DataBaseUtil.queryParamLike(DataBaseUtil.normalize(text));
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				queryParam), new String[] {"ORAGNIZATION_NAME_NORMALIZED"});
		return getList(QueryStore.Queries.SEARCH_ORGANIZATION_QUERY,
				Organization.class, params);
	}
	
	public List<Organization> getSourceOrgnizations() throws DatabaseException{
		logger.info("SourceOrgnizations()");
		return getList(QueryStore.Queries.GET_SOURCE_ORGANIZATIONS_QUERY, Organization.class);
	}
	
	public List<Organization> getDestinationOrganizations() throws DatabaseException{
		logger.info("destinationOrganizations()");
		return getList(QueryStore.Queries.GET_DESTINATION_ORGANIZATIONS_QUERY, Organization.class);
	}
	
	public List<Role> getRoles() throws DatabaseException{
		logger.info("getRoles()");
		return getList(QueryStore.Queries.GET_ROLES_QUERY, Role.class);
	}
	
	
	public List<Role> searchRoles(String text) throws DatabaseException{
		logger.info("searchRoles({})" , text);
		String queryParam = DataBaseUtil.queryParamLike(DataBaseUtil.normalize(text));
		Map<String, Object> params = fillParams(DataBaseUtil.paramList(
				queryParam), new String[] {"ROLE_NORMALIZED"});
		return getList(QueryStore.Queries.SEARCH_ROLE_QUERY,
				Role.class, params);
	}
	
	
	public List<DecreeType> getDecreeTypes() throws DatabaseException{
		logger.info("getDecreeTypes()");
		return getList(QueryStore.Queries.GET_DECREE_TYPES_QUERY, DecreeType.class);
	}


	public List<AttachmentType> getAttachmentTypes() {
		logger.info("getAttachmentTypes()");
		return getList(QueryStore.Queries.GET_ATTACHMENT_TYPES_QUERY, AttachmentType.class);
	}


	public List<BoardType> getBoardTypes() {
		logger.info("getBoardTypes()");
		return getList(QueryStore.Queries.GET_BOARD_TYPES_QUERY, BoardType.class);
	}


	public List<GovernmentBoardType> getGovernmentBoardTypes() {
		logger.info("getGovernmentBoardTypes()");
		return getList(QueryStore.Queries.GET_GOVERNMENT_TYPES_QUERY, GovernmentBoardType.class);
	}


	public List<Status> getBoardStatusList() {
		logger.info("getBoardStatusList()");
		return getList(QueryStore.Queries.GET_BOARD_STATUS_LIST_QUERY, Status.class);
	}


	public List<Status> getMemberStatusList() {
		logger.info("getMemberStatusList()");
		return getList(QueryStore.Queries.GET_MEMBER_STATUS_LIST_QUERY, Status.class);
	}


	public List<AttachmentType> getAttachmentTypesForMembers() {
		logger.info("getAttachmentTypesForMembers()");
		return getList(QueryStore.Queries.GET_ATTACHMENT_TYPE_FOR_MEMBER_QUERY, AttachmentType.class);
	}

}
