angular.module("gbs.search.memeber.controllers" , [])
.controller('searchMemberController',['$rootScope' , '$scope' , '$http' ,'$state', 'Utils', 'Toast', 'BASE_CONSTS', '$timeout' ,'ValidateService' , 'DecreeService' , 'BoardService' , 'MemberService', 'blockUI' , 'BASE_RESOURCES','ExportService','modalService',
                                        function($rootScope , $scope , $http , $state , Utils , Toast , BASE_CONSTS , $timeout , ValidateService , DecreeService , BoardService , MemberService , blockUI , BASE_RESOURCES , ExportService , modalService) {
	
	
	/**
	 * Define variables & objects Here
	 */
	
	 $scope.open = function($event,opened) {
		    $event.preventDefault();
		    $event.stopPropagation();
		    $scope[opened] = true;
	};
	
	
	$scope.searchMember = {cprNumber :'' , memberName:'' , occupation:'' , organizaionOnBehalf:'' , role:'' , organization :'' , startDateFrom:'' , startDateTo:'' , endDateFrom:'' , endDateTo:'' , status:''};
	
	$scope.result = [];
	
	$scope.filteredCollection=null;
	
	
	/********************************************************************************************************************************************************************************/
	
	$scope.exportExcel = function () {
		
		  var exportDataList = [];
		  angular.forEach($scope.filteredCollection, function(value, index) {
			  var exportJsonData = {cprNumber:value.cprNumber , boardName:value.boardName , memberName :value.memberName  , occupation:value.memberOccupation , organizaionOnBehalf : value.organizationOnBehalfName , role : value.roleName ,
					  organization:value.organizationName , memberStartDate : value.memberStartDate , memberEndDate:value.memberEndDate , memberStatus : value.statusName};
			  exportDataList.push(exportJsonData);
			  
		  });
		  
		  var headerMap = new Object();
		  headerMap['cprNumber'] = BASE_CONSTS.CPR_NUMBER_LABLE;
		  headerMap['boardName'] = BASE_CONSTS.BOARD_NAME_LABEL;
		  headerMap['memberName'] = BASE_CONSTS.MEMBER_NAME_LABLE;
		  headerMap['occupation'] = BASE_CONSTS.MEMBER_OCCUPATION_LABLE;
		  headerMap['organizaionOnBehalf'] = BASE_CONSTS.MEMEBR_ONBEHALF_OF_LABLE;
		  headerMap['role'] = BASE_CONSTS.MEMBER_ROLE_LABEL;
		  headerMap['organization'] = BASE_CONSTS.MEMBER_ORGANIZATION_LABEL;
		  headerMap['memberStartDate'] = BASE_CONSTS.MEMBER_START_DATE_LABEL;
		  headerMap['memberEndDate'] = BASE_CONSTS.MEMBER_END_DATE_LABEL;
		  headerMap['memberStatus'] = BASE_CONSTS.MEMBER_STATUS_LABEL;
		  
		  var REPORT_NAME = BASE_CONSTS.SEARCH_MEMBER_TITLE + " : " + Utils.getLocaleDate();
		  
		  ExportService.exportExcel(REPORT_NAME , exportDataList , true , headerMap);
		  
		};
	
	$scope.clearResult = function () {
		$scope.result = null;
		$scope.searchMember = {cprNumber :'' , memberName:'' , occupation:'' , organizaionOnBehalf:'' , role:'' , organization :'' , startDateFrom:'' , startDateTo:'' , endDateFrom:'' , endDateTo:'' , status:''};
		$scope.resetMemberOrganization = {id:'-1' , name:""};
		$scope.resetMemberOrganizationOnBehalf = {id:'-2' , name:""};
		$scope.resetMemberRole = {id:'-3' , name:""};
		$scope.resetStatusType = {id:'-4' , name:""};
	};
	
	/********************************************************************************************* Search ACTION **************************************************************************************/
	
	
	$scope.search = function(){
		blockUI.start();
		var valid = $scope.validateSearchData();
		
		if(valid){
			var map =$scope.formatSearchData();
			Logger.info('map : ' + JSON.stringify(map));
			 $http.post(BASE_RESOURCES.SEARCH_MEMBER_URL , JSON.stringify(map) , {headers: {'Content-Type': 'application/json'} })
			 .success(function(data, status, headers, config) {
			     
				 if(data!=null && data.length >0){
					 $scope.result = data;
				 }else{
					 // no data found
					 Toast.success(BASE_CONSTS.SEARCH_BOARD_NO_RESULT_FOUND, '', BASE_CONSTS.SHORT);
					 $scope.result = data;
				 }
				 
//			        console.log('Success' + JSON.stringify(data));
			        blockUI.stop();
			 })
			 .error(function(data, status, headers, config) {
				 console.log('error: ' + data);
			        blockUI.stop();
			        
			 });
		}
		
	};
	
	
	$scope.validateSearchData = function(){
			
		var valid = true;
		 if(ValidateService.isEndDateBeforeStartDate($scope.searchMember.startDateFrom , $scope.searchMember.startDateTo)){
			 Toast.error(BASE_CONSTS.SEARCH_START_DATE_TO_BEFORE_START_DATE_FROM, '', BASE_CONSTS.LONG);
			 valid = false;
			 blockUI.stop();
			 return false;
		 }
		 
		 if(ValidateService.isEndDateBeforeStartDate($scope.searchMember.endDateFrom , $scope.searchMember.endDateTo)){
			 Toast.error(BASE_CONSTS.SEARCH_END_DATE_TO_BEFORE_END_DATE_FROM, '', BASE_CONSTS.LONG);
			 valid = false;
			 blockUI.stop();
			 return false;
		 }
		 
		 if(MemberService.isAllSearchFieldsEmpty($scope.searchMember)){
			 Toast.error(BASE_CONSTS.ALL_SEARCH_FIELDS_EMPTY, '', BASE_CONSTS.LONG);
			 valid = false;
			 blockUI.stop();
			 return false;
		 }
		 
		 if(!Utils.isEmpty($scope.searchMember.cprNumber)){
			if(!ValidateService.isValidCprNumberLength($scope.searchMember.cprNumber)){
				valid = false;
			}
			
			if(!valid){
				Toast.error(BASE_CONSTS.INAVLID_CPR_NUMBER, '', BASE_CONSTS.LONG);
				valid = false;
				blockUI.stop();
				return false;
			}else{
				MemberService.isCprNumberValid($scope.searchMember.cprNumber).then(function(res){
					 if(res == BASE_CONSTS.BOOLEAN_FALSE){
						 Toast.error(BASE_CONSTS.INAVLID_CPR_NUMBER, '', BASE_CONSTS.LONG);
						 valid = false;
						 blockUI.stop();
						 return false;
					 }
				 });
			}
		}
		 
		 return valid;
	};
	
	$scope.formatSearchData = function(){
		 var map = new Object();
		 
		 map['cprNumber'] = !Utils.isEmpty($scope.searchMember.cprNumber) ?  $scope.searchMember.cprNumber : null;
		 map['name'] = !Utils.isEmpty($scope.searchMember.memberName) ? $scope.searchMember.memberName : null;
		 map['occupation'] = !Utils.isEmpty($scope.searchMember.occupation) ? $scope.searchMember.occupation : null;
		 map['organizaionOnBehalf'] = !Utils.isEmpty($scope.searchMember.organizaionOnBehalf) ? $scope.searchMember.organizaionOnBehalf.id : null;
		 map['role'] = !Utils.isEmpty($scope.searchMember.role) ? $scope.searchMember.role.id : null;
		 map['organization'] = !Utils.isEmpty($scope.searchMember.organization) ? $scope.searchMember.organization.id : null;
		 map['startDateFrom'] = !Utils.isEmpty($scope.searchMember.startDateFrom) ? $scope.searchMember.startDateFrom : null;
		 map['startDateTo'] = !Utils.isEmpty($scope.searchMember.startDateTo) ? $scope.searchMember.startDateTo : null;
		 map['endDateFrom'] = !Utils.isEmpty($scope.searchMember.endDateFrom) ? $scope.searchMember.endDateFrom : null;
		 map['endDateTo'] = !Utils.isEmpty($scope.searchMember.endDateTo) ? $scope.searchMember.endDateTo : null;
		 map['status'] = !Utils.isEmpty($scope.searchMember.status) ? $scope.searchMember.status.id : null;
		 
		 return map;
	};
	
	/***********************************************************************************************************************************************************************************************/
	
	$scope.viewLog = function(memberId , memberName){
		Logger.info('memberId : '  + memberId);
		Logger.info('memberName : '  + memberName);
		MemberService.getMemberLogDetails(memberId).then(function(result){
//			Logger.info('result : '  + JSON.stringify(result));
			
			$scope.logs = result;
			
			var modalDefaults = {
		        backdrop: true,
		        keyboard: true,
		        modalFade: true,
		        templateUrl: './app/search/member/templates/member-log-details-modal.html',
		        scope : $scope,
		        size : 'lg'
			};
			  
			var modalOptions = {
			        closeButtonText: BASE_CONSTS.CANCEL_BUTTON,
			        headerText: BASE_CONSTS.VIEW_MEMBER_LOG + memberName,
			        bodyText: ''
			    };
			    modalService.showModal(modalDefaults, modalOptions).then(function (result) {
			    	
			    	
			    });
		});
	};
	
	/******************************************************************************************************************************************************************************************************/
	
	$scope.getDecreeTypeName = function(typeId){
		
		if(!Utils.isEmpty(typeId)){
			var type = $rootScope.lookupMap.DECREE_TYPES[Utils.findInArray($rootScope.lookupMap.DECREE_TYPES , 'id' , typeId)];
			return type.name;
		}else {
			return "";
		}
	};
	
	
	/********************************************************************************************************************************************************************************************************/
	
	$scope.getMemberOrganizationName = function(organizationId){
		
		if(!Utils.isEmpty(organizationId)){
			var obj = $rootScope.lookupMap.DESTINATION_ORGANIZATIONS[Utils.findInArray($rootScope.lookupMap.DESTINATION_ORGANIZATIONS , 'id' , organizationId)];
			return obj.name;
		}else {
			return "";
		}
		
	};
	
	/********************************************************************************************************************************************************************************************************/
	$scope.getMemberRoleName = function(roleId){
		
		if(!Utils.isEmpty(roleId)){
			var obj = $rootScope.lookupMap.ROLES[Utils.findInArray($rootScope.lookupMap.ROLES , 'id' , roleId)];
			return obj.name;
		}else {
			return "";
		}
	};
	
	
	/********************************************************************************************************************************************************************************************************/
	
	$scope.getMemberStatusName = function(statusId){
		
		if(!Utils.isEmpty(statusId)){
			var obj = $rootScope.lookupMap.MEMBER_STATUS_LIST[Utils.findInArray($rootScope.lookupMap.MEMBER_STATUS_LIST , 'id' , statusId)];
			return obj.name;
		}else {
			return "";
		}
	};
}]);