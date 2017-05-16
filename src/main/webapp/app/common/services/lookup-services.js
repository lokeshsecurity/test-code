/**
 * 
 * GBS Common Services 
 */
angular.module('lookup-services',['ngResource'])


.service('LookupResources', function($resource , BASE_RESOURCES , $http , $q) {
	return {
//		getDestOrganizations : function(){
//			var deferred = $q.defer();
//			$http.get(BASE_RESOURCES.DESTINATION_ORGANIZATIONS_URL)
//			.success(function(data, status, headers, config) {
//		        var response = data;
//		        deferred.resolve(response);
//		     }).error(function(data, status, headers, config) {
//		    	 deferred.reject(data);
//		     });
//		 	return deferred.promise;
//		},
//		
//		getSourceOrganizations : function(){
//			var deferred = $q.defer();
//			$http.get(BASE_RESOURCES.SOURCE_ORGANIZATIONS_URL)
//			.success(function(data, status, headers, config) {
//		        var response = data;
//		        deferred.resolve(response);
//		     }).error(function(data, status, headers, config) {
//		    	 deferred.reject(data);
//		     });
//		 	return deferred.promise;
//		},
//		
//		getDecreeTypes : function(){
//			var deferred = $q.defer();
//			$http.get(BASE_RESOURCES.DECREE_TYPES_URL)
//			.success(function(data, status, headers, config) {
//		        var response = data;
//		        deferred.resolve(response);
//		     }).error(function(data, status, headers, config) {
//		    	 deferred.reject(data);
//		     });
//		 	return deferred.promise;
//		},
//		
//		getRoles : function(){
//			var deferred = $q.defer();
//			$http.get(BASE_RESOURCES.ROLES_URL)
//			.success(function(data, status, headers, config) {
//		        var response = data;
//		        deferred.resolve(response);
//		     }).error(function(data, status, headers, config) {
//		    	 deferred.reject(data);
//		     });
//		 	return deferred.promise;
//		},
//		
//		getAttachmentTypes : function(){
//			var deferred = $q.defer();
//			$http.get(BASE_RESOURCES.ATTACHMENT_TYPES_URL)
//			.success(function(data, status, headers, config) {
//		        var response = data;
//		        deferred.resolve(response);
//		     }).error(function(data, status, headers, config) {
//		    	 deferred.reject(data);
//		     });
//		 	return deferred.promise;
//		},
		
		getLookup : function(resourceUrl){
			var deferred = $q.defer();
			$http.get(resourceUrl)
			.success(function(data, status, headers, config) {
		        var response = data;
		        deferred.resolve(response);
		     }).error(function(data, status, headers, config) {
		    	 deferred.reject(data);
		     });
		 	return deferred.promise;
		},
		
	};
})

.service('LookupService', function($rootScope , LookupResources , BASE_RESOURCES , BASE_CONSTS) {
	
	$rootScope.lookupMap = { DESTINATION_ORGANIZATIONS:[] , SOURCE_ORGANIZATIONS:[] , DECREE_TYPES:[] , ROLES:[] , ATTACHMENT_TYPES:[] , BOARD_TYPES:[] , NOTIFICATION_PERIODS:[] , NOTIFICATION_REPEATS:[] , 
			GOVERNMENT_TYPES:[] , BOARD_STATUS_LIST:[] , BOARD_CREATION_TYPES :[] , MEMBER_STATUS_LIST : [], MEMBER_ATTACHMENT_TYPES:[]}; 
	
	return{
		
		getDestinationOrganizations : function(){
			Logger.info('getDestinationOrganizations');
			LookupResources.getLookup(BASE_RESOURCES.DESTINATION_ORGANIZATIONS_URL).then(function(result) {
				var lookup = {};
				angular.forEach(result, function(value, key) {
					lookup = {id:value.organizationId , name : value.organizationName};
					$rootScope.lookupMap.DESTINATION_ORGANIZATIONS.push(lookup);
				});
			});
		},
		
		getSourceOrganizations : function(){
			Logger.info('getSourceOrganizations');
			LookupResources.getLookup(BASE_RESOURCES.SOURCE_ORGANIZATIONS_URL).then(function(result) {
				var lookup = {};
				angular.forEach(result, function(value, key) {
					lookup = {id:value.organizationId , name : value.organizationName};
					$rootScope.lookupMap.SOURCE_ORGANIZATIONS.push(lookup);
				});
			});
		},
		
		getDecreeTypes : function(){
			LookupResources.getLookup(BASE_RESOURCES.DECREE_TYPES_URL).then(function(result) {
				var lookup = {};
				angular.forEach(result, function(value, key) {
					lookup = {id:value.decreeTypeId , name : value.decreeTypeName};
					$rootScope.lookupMap.DECREE_TYPES.push(lookup);
				});
			});
		},
		
		getRoles : function(){
			LookupResources.getLookup(BASE_RESOURCES.ROLES_URL).then(function(result){
				var lookup = {};
				angular.forEach(result, function(value, key) {
					lookup = {id:value.roleId , name : value.roleName , countValid : value.validCount};
					$rootScope.lookupMap.ROLES.push(lookup);
				});
			});
		},
		
		getAttachmentTypes : function(){
			LookupResources.getLookup(BASE_RESOURCES.ATTACHMENT_TYPES_URL).then(function(result){
				var lookup = {};
				angular.forEach(result, function(value, key) {
					lookup = {id:value.attachmentTypeId , name : value.attachmentTypeName , show : value.showFields};
					$rootScope.lookupMap.ATTACHMENT_TYPES.push(lookup);
				});
			});
		},
		
		getMemberAttachmentTypes : function(){
			LookupResources.getLookup(BASE_RESOURCES.MEMBER_ATTACHMENT_TYPES_URL).then(function(result){
				var lookup = {};
				angular.forEach(result, function(value, key) {
					lookup = {id:value.attachmentTypeId , name : value.attachmentTypeName , show : value.showFields};
					$rootScope.lookupMap.MEMBER_ATTACHMENT_TYPES.push(lookup);
				});
			});
		},
		
		getBoardTypes : function(){
			LookupResources.getLookup(BASE_RESOURCES.BOARD_TYPES_URL).then(function(result){
				var lookup = {};
				angular.forEach(result, function(value, key) {
					lookup = {id:value.boardTypeId , name : value.boardTypeName , haveExpiryDate : value.haveExpiryDate , requiredLegislativeTool : value.requiredLegislativeTool};
					$rootScope.lookupMap.BOARD_TYPES.push(lookup);
				});
			});
		},
		
		getNotificationPeriods : function(){
			LookupResources.getLookup(BASE_RESOURCES.NOTIFICATION_PERIODS_URL).then(function(result){
				var lookup = {};
				angular.forEach(result, function(value, key) {
					lookup = {id:value.notificationPeriodId , name : value.notificationPeriodName , val : value.notificationPeriodDay};
					$rootScope.lookupMap.NOTIFICATION_PERIODS.push(lookup);
				});
			});
		},
		
		getNotificationRepeats : function(){
			LookupResources.getLookup(BASE_RESOURCES.NOTIFICATION_REPEATS_URL).then(function(result){
				var lookup = {};
				angular.forEach(result, function(value, key) {
					lookup = {parentId:value.parentNotificationPeriodId , childId:value.childNotificationPeriodId , name : value.childNotificationPeriodName , val :value.childNotificationPeriodDay};
					$rootScope.lookupMap.NOTIFICATION_REPEATS.push(lookup);
				});
			});
		},
		
		getGovernmentTypes : function(){
			LookupResources.getLookup(BASE_RESOURCES.GOVERNMENT_TYPES_URL).then(function(result){
				var lookup = {};
				angular.forEach(result, function(value, key) {
					lookup = {id:value.governmentTypeId , name : value.governmentTypeName};
					$rootScope.lookupMap.GOVERNMENT_TYPES.push(lookup);
				});
			});
		},
		
		getBoardStatusList : function(){
			
			LookupResources.getLookup(BASE_RESOURCES.BOARD_STATUS_URL).then(function(result){
				var lookup = {};
				angular.forEach(result, function(value, key) {
					lookup = {id:value.statusId , name : value.statusName};
					$rootScope.lookupMap.BOARD_STATUS_LIST.push(lookup);
				});
			});
		},
		
		getBoardCreationTypes : function(){
			var notRelatedToBoard = {id:'1' , name:BASE_CONSTS.BOARD_NOT_RELATED_TO_OTHER_BOARD};
			var relatedToBoard = {id:'2' , name: BASE_CONSTS.BOARD_RELATED_TO_OTHER_BOARD};
			$rootScope.lookupMap.BOARD_CREATION_TYPES.push(notRelatedToBoard);
			$rootScope.lookupMap.BOARD_CREATION_TYPES.push(relatedToBoard);
//			Logger.info('BOARD_CREATION_TYPES : ' + JSON.stringify($rootScope.lookupMap.BOARD_CREATION_TYPES));
		},
		
		getMemberStatusList : function(){
			LookupResources.getLookup(BASE_RESOURCES.MEMBER_STATUS_URL).then(function(result){
				var lookup = {};
				angular.forEach(result, function(value, key) {
					lookup = {id:value.statusId , name : value.statusName};
					$rootScope.lookupMap.MEMBER_STATUS_LIST.push(lookup);
				});
			});
		},
		
		initLookups : function(){
			Logger.debug('initLookups');
			var self = this;
			self.getDestinationOrganizations();
			self.getSourceOrganizations();
			self.getDecreeTypes();
			self.getRoles();
			self.getAttachmentTypes();
			self.getBoardTypes();
			self.getNotificationPeriods();
			self.getNotificationRepeats();
			self.getGovernmentTypes();
			self.getBoardStatusList();
			self.getBoardCreationTypes();
			self.getMemberStatusList();
			self.getMemberAttachmentTypes();
			
		}
	};
});



