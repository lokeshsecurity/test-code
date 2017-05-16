angular.module("gbs.search.controllers" , [])
.controller('searchBoardController',['$rootScope' , '$scope' , '$http' ,'$state', 'Utils', 'Toast', 'BASE_CONSTS', '$timeout' ,'ValidateService' , 'DecreeService' , 'BoardService' , 'MemberService', 'blockUI' , 'BASE_RESOURCES','ExportService', 'modalService' ,
                                        function($rootScope , $scope , $http , $state , Utils , Toast , BASE_CONSTS , $timeout , ValidateService , DecreeService , BoardService , MemberService , blockUI , BASE_RESOURCES , ExportService , modalService) {
	
	
	/**
	 * Define variables & objects Here
	 */
	
	 $scope.open = function($event,opened) {
		 
//		 Logger.info('searchBoardController : opened : ' + opened);
//		 Logger.info('searchBoardController :  $scope[opened] : ' +  $scope[opened]);
		    $event.preventDefault();
		    $event.stopPropagation();
		    $scope[opened] = true;
//		    setTimeout(function() {
//		    	 $scope[opened] = false; // did the trick -- solve the date picker open once and this is because the scope of date picker need to reinitialize again
//	        }, 10);  
	};
	
	
	$scope.resetOrganization = null;
	$scope.resetGovernmentBoardType = null;
	$scope.resetDestinationOrganization = null;
	$scope.resetSearchSourceOrganization = null;
	$scope.resetSelectDecree = null;
	$scope.resetStatusType = null;
	
	
	$scope.search = {governmentBoardType :'' , boardName:'' , sourceOrganization:'' , decreeNumber:'' , decreeYear :'' , decreeType:'' , startDateFrom:'' , startDateTo:'' , endDateFrom:'' , endDateTo:'' , destinationOrganization:'', status:''};
	
	$scope.result = [];
	
	$scope.filteredCollection=null;
	
	
	/********************************************************************************************************************************************************************************/
	
	$scope.exportExcel = function () {
		
	  var exportDataList = [];
	  angular.forEach($scope.filteredCollection, function(value, index) {
		  var exportJsonData = {decree : Utils.concatDecreeLow(value.decreeNumber , value.decreeYear , value.decreeTypeName) , decreeIssueDate:value.decreeIssueDateMelady , 
				  boardName:value.boardName , sourceOrganization : value.sourceOragnizationName , boardStartDate:value.boardStartDate , 
				  boardEndDate :value.boardExpiryDate , destinationOrganization:value.destinationOrganizationName};
		  exportDataList.push(exportJsonData);
		  
	  });
	  
	  var headerMap = new Object();
	  headerMap['decree'] = BASE_CONSTS.DECREE_LABLE;
	  headerMap['decreeIssueDate'] = BASE_CONSTS.ISSUE_DATE_MELADY_LABLE;
	  headerMap['boardName'] = BASE_CONSTS.BOARD_NAME_LABEL;
	  headerMap['sourceOrganization'] = BASE_CONSTS.BOARD_SOURCE_OARGNIZATION_LABEL;
	  headerMap['boardStartDate'] = BASE_CONSTS.START_DATE_DECREE_LABLE;
	  headerMap['boardEndDate'] = BASE_CONSTS.END_DATE_DECREE_LABLE;
	  headerMap['destinationOrganization'] = BASE_CONSTS.BOARD_DESTINATION_ORGANIZATION_LABEL;
	  
	  var REPORT_NAME = BASE_CONSTS.SEARCH_BOARD_TITLE + " : " + Utils.getLocaleDate();
	  
	  ExportService.exportExcel(REPORT_NAME , exportDataList , true , headerMap);
	  
	};
	
	
	
	$scope.clearResult = function () {
		$scope.result = null;
		$scope.search = {governmentBoardType :'' , boardName:'' , sourceOrganization:'' , decreeNumber:'' , decreeYear :'' , decreeType:'' , startDateFrom:'' , startDateTo:'' , endDateFrom:'' , endDateTo:'' , destinationOrganization:'', status:''};
		$scope.resetOrganization = {id:'-1' , name:""};
		$scope.resetGovernmentBoardType = {id:'-2' , name:""};
		$scope.resetDestinationOrganization = {id:'-3' , name:""};
		$scope.resetSearchSourceOrganization = {id:'-4' , name:""};
		$scope.resetSelectDecree = {id:'-5' , name:""};
		$scope.resetStatusType = {id:'-6' , name:""};
	
	};
	
	
	 /********************************************************************************************* Search ACTION **************************************************************************************/
	
	
	
	$scope.searchBoard = function(){
		blockUI.start();
		var valid = $scope.validateSearchData();
		
		if(valid){
			var map =$scope.formatSearchData();
			Logger.info('map : ' + JSON.stringify(map));
			 $http.post(BASE_RESOURCES.SEARCH_BOARD_URL , JSON.stringify(map) , {headers: {'Content-Type': 'application/json'} })
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
		 if(ValidateService.isEndDateBeforeStartDate($scope.search.startDateFrom , $scope.search.startDateTo)){
			 Toast.error(BASE_CONSTS.SEARCH_START_DATE_TO_BEFORE_START_DATE_FROM, '', BASE_CONSTS.LONG);
			 valid = false;
			 blockUI.stop();
			 return false;
		 }
		 
		 if(ValidateService.isEndDateBeforeStartDate($scope.search.endDateFrom , $scope.search.endDateTo)){
			 Toast.error(BASE_CONSTS.SEARCH_END_DATE_TO_BEFORE_END_DATE_FROM, '', BASE_CONSTS.LONG);
			 valid = false;
			 blockUI.stop();
			 return false;
		 }
		 
		 if(BoardService.isAllSearchFieldsEmpty($scope.search)){
			 Toast.error(BASE_CONSTS.ALL_SEARCH_FIELDS_EMPTY, '', BASE_CONSTS.LONG);
			 valid = false;
			 blockUI.stop();
			 return false;
		 }
		 
		 return valid;
	};
	
	$scope.formatSearchData = function(){
		 var map = new Object();
		 
		 map['governmentBoard'] = !Utils.isEmpty($scope.search.governmentBoardType) ?  $scope.search.governmentBoardType.id : null;
		 map['boardName'] = !Utils.isEmpty($scope.search.boardName) ? $scope.search.boardName : null;
		 map['sourceOrganization'] = !Utils.isEmpty($scope.search.sourceOrganization) ? $scope.search.sourceOrganization.id : null;
		 map['decreeNumber'] = !Utils.isEmpty($scope.search.decreeNumber) ? $scope.search.decreeNumber : null;
		 map['decreeYear'] = !Utils.isEmpty($scope.search.decreeYear) ? $scope.search.decreeYear : null;
		 map['decreeType'] = !Utils.isEmpty($scope.search.decreeType) ? $scope.search.decreeType.id : null;
		 map['startDateFrom'] = !Utils.isEmpty($scope.search.startDateFrom) ? $scope.search.startDateFrom : null;
		 map['startDateTo'] = !Utils.isEmpty($scope.search.startDateTo) ? $scope.search.startDateTo : null;
		 map['endDateFrom'] = !Utils.isEmpty($scope.search.endDateFrom) ? $scope.search.endDateFrom : null;
		 map['endDateTo'] = !Utils.isEmpty($scope.search.endDateTo) ? $scope.search.endDateTo : null;
		 map['destinationOrganization'] = !Utils.isEmpty($scope.search.destinationOrganization) ? $scope.search.destinationOrganization.id : null;
		 map['status'] = !Utils.isEmpty($scope.search.status) ? $scope.search.status.id : null;
		 
		 return map;
	};
	
	
	$scope.showYearValidation = function(){
		 var hasError=angular.element("#decreeYear").hasClass('errorLable');
		 console.log('hasError : ' + hasError);
		 if(hasError){
			 $scope.yearValidationError = true;
		 }else{
			 $scope.yearValidationError = false;
		 }
	 };
	 
	 /*******************************************************************************************************************************************************************************************************/
	 
	 
		$scope.viewLog = function(boardId , boardName){
			Logger.info('boardId : '  + boardId);
			Logger.info('boardName : '  + boardName);
			BoardService.getBoardLogDetails(boardId).then(function(result){
				Logger.info('result : '  + JSON.stringify(result));
				$scope.logs = result;
				
				var modalDefaults = {
			        backdrop: true,
			        keyboard: true,
			        modalFade: true,
			        templateUrl: './app/search/board/templates/board-log-details-modal.html',
			        scope : $scope,
			        size : 'lg'
				};
				  
				var modalOptions = {
				        closeButtonText: BASE_CONSTS.CANCEL_BUTTON,
				        headerText: BASE_CONSTS.VIEW_BOARD_LOG + boardName,
				        bodyText: ''
				    };
				    modalService.showModal(modalDefaults, modalOptions).then(function (result) {
				    	
				    	
				    });
			});
		};
		
		
		/********************************************************************************************************************************************************************************************************/
		
		$scope.getDecreeTypeName = function(typeId){
			
			if(!Utils.isEmpty(typeId)){
				var type = $rootScope.lookupMap.DECREE_TYPES[Utils.findInArray($rootScope.lookupMap.DECREE_TYPES , 'id' , typeId)];
				return type.name;
			}else {
				return "";
			}
		};
	
}]);