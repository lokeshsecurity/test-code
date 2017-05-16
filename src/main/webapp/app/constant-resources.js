smartApp.constant('BASE_RESOURCES', (function() {
	  // Define your Resources URL Here
	  var GET_DESTINIATION_ORGANIZATIONS_URL = 'api/lookup/organizations/destination';
	  var GET_SOURCE_ORGANIZATIONS_URL = 'api/lookup/orgnizations/source';
	  var GET_DECREE_TYPES_URL = 'api/lookup/decreeTypes';
	  var GET_ROLES_URL = 'api/lookup/roles';
	  var IS_CPR_NUMBER_VALID = 'api/member/isvalidcpr/{cpr}';
	  var GET_ATTACHMENT_TYPES_URL = 'api/lookup/attachmentTypes';
	  var GET_BOARD_TYPES_URL = 'api/lookup/boardTypes';
	  var GET_NOTIFICATION_PERIODS_URL = 'api/lookup/notification/periods';
	  var GET_NOTIFICATION_REPEATS_URL = 'api/lookup/notification/repeats';
	  var GET_SERACH_MEMBER_URL = 'api/member/search/{text}';
	  var GET_IS_DECREE_EXISTS_URL = 'api/board/isDecreeExists';
	  var GET_ADD_BOARD_URL = 'api/board/addBoard';
	  var GET_NOTIFICATIONS_URL = 'api/board/notifications';
	  var GET_GOVERNMENT_TYPES_URL = 'api/lookup/governmentBoradTypes';
	  var GET_SEARCH_BOARD_URL = 'api/board/search';
	  var GET_BOARD_STATUS_URL = 'api/lookup/board/status';
	  var GET_SERACH_BOARD_URL = 'api/board/search/{text}';
	  var IS_BOARD_NAME_EXISTS_URL = 'api/board/isBoardNameExists';
	  var GET_MEMBER_STATUS_URL = 'api/lookup/member/status';
	  var GET_SEARCH_MEMBER_URL = 'api/member/search';
	  var GET_BOARD_BY_BOARD_ID_URL = 'api/board/{boardId}';
	  var GET_MEMBERS_BY_BOARD_ID_URL = 'api/board/members/{boardId}';
	  var GET_DECREES_BY_BOARD_ID_URL = 'api/board/decrees/{boardId}';
	  var GET_MEMBER_ATTACHMENT_TYPES_URL = 'api/lookup/attachmentTypesForMember';
	  var GET_UPDATE_BOARD_URL = 'api/board/updateBoard';
	  var GET_MEMBER_LOG_DETAILS_BY_MEMBER_ID_URL = 'api/member/history/{memberId}';
	  var GET_BOARD_LOG_DETAILS_BY_BOARD_ID_URL = 'api/board/history/{boardId}';
	  var IS_NOTIFICATION_DATE_VALID_URL = 'api/board/isNotificationDateValid';
	  
	  return {
	    DESTINATION_ORGANIZATIONS_URL: GET_DESTINIATION_ORGANIZATIONS_URL,
	    SOURCE_ORGANIZATIONS_URL: GET_SOURCE_ORGANIZATIONS_URL,
	    DECREE_TYPES_URL : GET_DECREE_TYPES_URL,
	    ROLES_URL : GET_ROLES_URL,
	    IS_CPR_NUMBER_VALID : IS_CPR_NUMBER_VALID,
	    ATTACHMENT_TYPES_URL : GET_ATTACHMENT_TYPES_URL,
	    MEMBER_ATTACHMENT_TYPES_URL :GET_MEMBER_ATTACHMENT_TYPES_URL,
	    BOARD_TYPES_URL : GET_BOARD_TYPES_URL,
	    NOTIFICATION_PERIODS_URL : GET_NOTIFICATION_PERIODS_URL,
	    NOTIFICATION_REPEATS_URL : GET_NOTIFICATION_REPEATS_URL,
	    SERACH_MEMBER_URL 	: GET_SERACH_MEMBER_URL,
	    IS_DECREE_EXISTS_URL : GET_IS_DECREE_EXISTS_URL,
	    ADD_BOARD_URL : GET_ADD_BOARD_URL,
	    NOTIFICATIONS_URL : GET_NOTIFICATIONS_URL,
	    GOVERNMENT_TYPES_URL : GET_GOVERNMENT_TYPES_URL,
	    SEARCH_BOARD_URL : GET_SEARCH_BOARD_URL,
	    BOARD_STATUS_URL : GET_BOARD_STATUS_URL,
	    SERACH_BOARD_URL : GET_SERACH_BOARD_URL,
	    IS_BOARD_NAME_EXISTS_URL : IS_BOARD_NAME_EXISTS_URL,
	    MEMBER_STATUS_URL : GET_MEMBER_STATUS_URL,
	    SEARCH_MEMBER_URL : GET_SEARCH_MEMBER_URL,
	    GET_BOARD_BY_BOARD_ID : GET_BOARD_BY_BOARD_ID_URL,
	    GET_MEMBERS_BY_BOARD_ID : GET_MEMBERS_BY_BOARD_ID_URL,
	    GET_DECREES_BY_BOARD_ID : GET_DECREES_BY_BOARD_ID_URL,
	    UPDATE_BOARD_URL : GET_UPDATE_BOARD_URL,
	    GET_MEMBER_LOG_DETAILS_BY_MEMBER_ID : GET_MEMBER_LOG_DETAILS_BY_MEMBER_ID_URL,
	    GET_BOARD_LOG_DETAILS_BY_BOARD_ID : GET_BOARD_LOG_DETAILS_BY_BOARD_ID_URL,
	    IS_NOTIFICATION_DATE_VALID_URL:IS_NOTIFICATION_DATE_VALID_URL,
	  };
})());