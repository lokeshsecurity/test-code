angular.module("gbs.main.controllers" , [])
.controller('BoardManagmentController',['$rootScope' , '$scope' , '$http' ,'$state', 'Utils', 'Toast', 'BASE_CONSTS', '$timeout' ,'$modal','ValidateService' , 'DecreeService' , 'BoardService' , 'MemberService', 'blockUI' , 'BASE_RESOURCES','modalService',
                                        function($rootScope , $scope , $http , $state , Utils , Toast , BASE_CONSTS , $timeout , $modal, ValidateService , DecreeService , BoardService , MemberService , blockUI , BASE_RESOURCES , modalService) {
	
	
	/**
	 * Define variables & objects Here
	 */
	
	 $scope.elements = []; // solve issue of date picker open once inside the ng-include
	 $scope.open = function($event,opened) {
		 	
//		 Logger.info('BoardManagmentController : opened : ' + opened);
//		 Logger.info('BoardManagmentController :  $scope[opened] : ' +  $scope[opened]);
		    $event.preventDefault();
		    $event.stopPropagation();
//		    $scope[opened] = true;
		    $scope.elements[opened] = !$scope.elements[opened];
//		    setTimeout(function() {
//		    	 $scope[opened] = false; // did the trick -- solve the date picker open once and this is because the scope of date picker need to reinitialize again
//	        }, 10);  
	};
	
	
	$scope.decreeList = [];
	$scope.memberList = [];
	$scope.decree = {type :{} , number:'' , year :'' , attachment:'' , attachmentType :'' , issueDateMelady:'' , issueDateHijry:'' , notes:''};
	$scope.resetSelectDecree = null; // init model of directive
//	$scope.resetSelectAttachmentType = null;
	$scope.resetDestinationOrganization = null;
	$scope.resetSourceOrganization = null;
	$scope.board = {sourceOrganization:'' , destinationOrganization:'' , name:'' , endDate:'' , startDate:'' , boardType:'' , notificationBefore:'' , notificationRepaet : '' , parentId:'' , parentBoard : ''};
	
//	$timeout(function(){
//		$scope.board.notificationBefore = $rootScope.lookupMap.NOTIFICATION_PERIODS[0];
//		$scope.resetNotificationBefore = $rootScope.lookupMap.NOTIFICATION_PERIODS[0];
//	},300);
	
	$scope.addDecreeButton = true;
	$scope.editDecreeButton = false;
	$scope.addMemberButton = true;
	$scope.editMemberButton = false;
	$scope.yearValidationError = false;
	$scope.showDecreeData = false;
	$scope.showNotificationRepeatSelect = false;
	$scope.showMemberNotificationRepeatSelect = false;
	$scope.decree.attachmentType =  $rootScope.lookupMap.ATTACHMENT_TYPES[0]; // for ng-change
	$scope.resetSelectAttachment =  $rootScope.lookupMap.ATTACHMENT_TYPES[0]; // for reset attachment first
	$scope.member = {cprNumber : '' , name:'' , occupation : '' , organization: '' , role : '' , organizaionOnBehalf :'' , startDate : '' , endDate : '' , notificationBefore:'' , notificationRepeat : ''};
	$scope.resetNotificationRepeat = null;
	$scope.disableBoardExpiry = true;
	$scope.members = [];
	$scope.disableAttachmentType = false;
	$scope.resetMemberOrganization = null;
	$scope.resetMemberRole = null;
	$scope.resetMemberOrganizationOnBehalf = null;
	$scope.showSearchBoard = false;
	$scope.x = {parent:''};
	$scope.disableMemberNotification = true;
	
	/********************************************************************************************************************************************************************************/
	
	/**
	 * Define Controller Methods Here
	 */
	
	/***********************************************************************  General Methods ************************************************************************************************************/
	/*** this method to handle more than date picker in the same view */
	
	 
	
	
	/**
	 * not used
	 */
	 $scope.createDistOrganizationTagTransform = function (newTag) {
		 console.log('newTag : ' + newTag);
		    var item = {
		    	id:null,	
		        name: newTag,
		    };
		    return item;
	 };
	 
	 
	/************************************************************************************************* Decree Methods **********************************************************************************/
	
	/**
	 * ADD Decree to table & List
	 */
	$scope.addDecree = function(){
		// validate decree fields is filled
		blockUI.start();
		Logger.info('called angular ');
		var valid = $scope.validateDecreeData(null);
		if(valid){
			if($scope.decree.attachmentType.show == 1){
				// decree
				DecreeService.isDecreeExists($scope.decree).then(function(res){
					 if(res == BASE_CONSTS.BOOLEAN_TRUE){
						 Toast.error(BASE_CONSTS.DECREE_IS_ALREADY_EXISTS, '', BASE_CONSTS.LONG);
					 }else{
						 $scope.decreeList.push($scope.decree);
						 $scope.clearDecreeData();
					 }
				 });
				 
			}else{
				// another attachment not decree
				$scope.decree.issueDateHijry=''; // CLEARED IN CASE OF "NOT DECREE"
				$scope.decreeList.push($scope.decree);
				$scope.clearDecreeData();
			}
		}
		blockUI.stop();
	};
	
	
	
	$scope.validateDecreeData = function(existIndex){
		
		var valid = true;
		var decrees = $scope.decreeList.slice();
		
		if($scope.decree.attachmentType.show == 1){
			
			if(existIndex == null && DecreeService.isOnlyOneDecreeExist(decrees)){
				Toast.error(BASE_CONSTS.PERMIT_ONLY_ONE_DECREE, '', BASE_CONSTS.LONG);
				 valid = false;
				 blockUI.stop();
				 return false;
			}
			
			if(DecreeService.isDecreeExistInList($scope.decree , decrees , existIndex)){
				Toast.error(BASE_CONSTS.DECREE_ALREADY_EXISTS, '', BASE_CONSTS.LONG);
				 valid = false;
				 blockUI.stop();
				 return false;
			}
			
			if(Utils.isEmpty($scope.decree.number)){
				 Toast.error(BASE_CONSTS.DECREE_NUMBER_ERROR_MSG, '', BASE_CONSTS.LONG);
				 valid = false;
				 blockUI.stop();
				 return false;
			}
			
			if(!Utils.isEmpty($scope.decree.year)){
				
				if(!DecreeService.isDecreeValidYear($scope.decree.year)){
					Toast.error(BASE_CONSTS.DECREE_YEAR_ERROR_MSG, '', BASE_CONSTS.LONG);
					 valid = false;
					 blockUI.stop();
					 return false;
				}
				
				if(!DecreeService.isDecreeYearValid($scope.decree.year)){
					Toast.error(BASE_CONSTS.DECREE_YEAR_IS_INVALID, '', BASE_CONSTS.LONG);
					 valid = false;
					 blockUI.stop();
					 return false;
				}
			}
			
			if(Utils.isEmpty($scope.decree.year)){
				 Toast.error(BASE_CONSTS.DECREE_YEAR_REQUIRED, '', BASE_CONSTS.LONG);
				 valid = false;
				 blockUI.stop();
				 return false;
			}
			
			if(Utils.isEmpty($scope.decree.type)){
				 Toast.error(BASE_CONSTS.DECREE_TYPE_REQUIRED, '', BASE_CONSTS.LONG);
				 valid = false;
				 blockUI.stop();
				 return false;
			}
		
			if(Utils.isEmpty($scope.decree.issueDateMelady) || Utils.isEmpty($scope.decree.issueDateHijry)){
				 Toast.error(BASE_CONSTS.DECREE_ISSUE_DATE_REQUIRED, '', BASE_CONSTS.LONG);
				 valid = false;
				 blockUI.stop();
				 return false;
			}
			if(ValidateService.isDateAfterToday($scope.decree.issueDateMelady)){
				 Toast.error(BASE_CONSTS.DECREE_ISSUE_DATE_NOT_IN_FUTURE, '', BASE_CONSTS.LONG);
				 valid = false;
				 blockUI.stop();
				 return false;
			}
			
			
			if(!Utils.isEmpty($scope.board.startDate)){
				if(ValidateService.isBoardStartDateBeforeDecreeMelady($scope.decree.issueDateMelady , $scope.board.startDate)){
					 Toast.error(BASE_CONSTS.BOARD_START_DATE_NOT_OLDER_DECREE_MELADY, '', BASE_CONSTS.LONG);
					 valid = false;
					 blockUI.stop();
					 return false;
				}
			}else{
				Toast.error(BASE_CONSTS.BOARD_START_DATE_REQUIRED, '', BASE_CONSTS.LONG);
				 valid = false;
				 blockUI.stop();
				 return false;
			}
			
			
			
			/**
			 * not need now depend on Feedback come from abdullah habib and the team
			 */
//			if(ValidateService.isHijryDateAfterToday($scope.decree.issueDateHijry)){
//				 Toast.error(BASE_CONSTS.DECREE_ISSUE_DATE_HIJRY_NOT_IN_FUTURE, '', BASE_CONSTS.LONG);
//				 valid = false;
//				 blockUI.stop();
//				 return false;
//			}
		}
		
		if(Utils.isEmpty($scope.decree.attachment.name)){
			 Toast.error(BASE_CONSTS.DECREE_ATTACHMENT_ANOTHER_REQUIRED, '', BASE_CONSTS.LONG);
			 valid = false;
			 blockUI.stop();
			 return false;
		}
		
		if($scope.decree.attachment.size > BASE_CONSTS.FILE_SIZE){
			 Toast.error(BASE_CONSTS.DECREE_ATTACHMENT_SIZE_MORE_THAN_5_MG, '', BASE_CONSTS.LONG);
			 valid = false;
			 blockUI.stop();
			 return false;
		}
		
		
		return valid;
	};
	
	/**
	 * clear the form fields
	 */
	$scope.clearDecreeData = function(){
		$scope.decree = {number:'' , year :'' , attachment:'' , issueDateMelady:'' , issueDateHijry:'' , notes:''};
		// to reset model of directive use 2 line
		$scope.decree.type=undefined;
		$scope.resetSelectDecree = $scope.decree.type; 
		if(document.getElementById('fileName')!= null){
			document.getElementById('fileName').value = '';
		}
		$scope.decree.attachmentType =  $rootScope.lookupMap.ATTACHMENT_TYPES[0];
		$scope.resetSelectAttachment = $scope.decree.attachmentType;
		$scope.showAddDecreeButton();
		$scope.showDecreeFields($scope.decree.attachmentType);
		$scope.disableAttachmentType = false;
	};
	
	
	/**
	 * edit decree form
	 */
	$scope.editDecree = function(index){
		var decreeObj = $scope.decreeList[index];
		$scope.decree.attachmentType = decreeObj.attachmentType;
		$scope.showDecreeFields(decreeObj.attachmentType);
		Logger.info('decreeObj.type : ' + JSON.stringify(decreeObj.type));
		$scope.decree.type=  decreeObj.type; // must reset when edit to not show the remove icon
		$scope.resetSelectDecree = $scope.decree.type; 
		$scope.decree.number=  decreeObj.number;
		$scope.decree.year=  decreeObj.year;
		$scope.decree.attachment = decreeObj.attachment;
		$scope.decree.issueDateMelady = decreeObj.issueDateMelady;
		$scope.decree.issueDateHijry = decreeObj.issueDateHijry;
		$scope.decree.notes = decreeObj.notes;
		$scope.index = index;
		document.getElementById('fileName').value = !Utils.isEmpty(decreeObj.attachment.name) ? decreeObj.attachment.name : '' ;
		$scope.disableAttachmentType = true;
		$scope.showEditDecreeButton();
	};
	
	
	/**
	 * update decree object at specified index and update reference in table
	 */
	
	$scope.updateDecree = function(index){
		var oldDecreeObj = $scope.decreeList[index];
		var valid = $scope.validateDecreeData(index);
		if(valid){
			if($scope.decree.attachmentType.show == 1){
				// decree
				DecreeService.isDecreeExists($scope.decree).then(function(res){
					 if(res == BASE_CONSTS.BOOLEAN_TRUE){
						 Toast.error(BASE_CONSTS.DECREE_IS_ALREADY_EXISTS, '', BASE_CONSTS.LONG);
					 }else{
							angular.extend(oldDecreeObj, $scope.decree); // update reference only in smart table to be render
							$scope.clearDecreeData();
					 }
				 });
				 
			}else{
				// another attachment not decree
				angular.extend(oldDecreeObj, $scope.decree); // update reference only in smart table to be render
				$scope.clearDecreeData();
			}
		}
	
		
	};
	
	
	
	/**
	 * Add Attachment in memory 
	 */
	$scope.addAttachment = function(myfile){
		  if(document.getElementById('fileName').value == null || document.getElementById('fileName').value == ''){
			   Toast.error(BASE_CONSTS.SELECT_FILE_FIRST_MSG , '' , BASE_CONSTS.SHORT);
			   return false;
		   }else{
//			   Logger.info('filesize : ' + myfile.filesize);
			   if(myfile.filesize!= null && myfile.filesize > BASE_CONSTS.FILE_SIZE){
					 Toast.error(BASE_CONSTS.DECREE_ATTACHMENT_SIZE_MORE_THAN_5_MG, '', BASE_CONSTS.LONG);
					 valid = false;
					 blockUI.stop();
					 return false;
				}
			   if(Utils.isEmpty(myfile.filetype) || myfile.filetype.indexOf(BASE_CONSTS.EXE_FILE_TYPE) > -1 || myfile.filename.indexOf(BASE_CONSTS.EXE_FILE_TYPE) > -1 || myfile.filetype.indexOf(BASE_CONSTS.EXE_FILE_TYPE_2) > -1 ){
				   	 Toast.error(BASE_CONSTS.ATTACHMENT_TYPE_EXE_NOT_ALLOWED, '', BASE_CONSTS.LONG);
					 valid = false;
					 blockUI.stop();
					 return false;
			   }
			   
			   $scope.attachment = {name:'' , size:'' , type:'' , base64:'' , createdOn:''};
			   $scope.attachment.name = myfile.filename;
			   $scope.attachment.size = myfile.filesize;
			   $scope.attachment.type = myfile.filetype;
			   $scope.attachment.base64 = myfile.base64;
			   $scope.attachment.createdOn = Utils.getCurrentTimestamp();
			   $scope.decree.attachment = $scope.attachment;
			   document.getElementById('fileName').value = '';
			   Toast.success(BASE_CONSTS.UPLOAD_SUCCESSFULLY_MSG, '', BASE_CONSTS.SHORT);
		   }
		  document.getElementById('fileName').value = '';
	};
	
	
	$scope.showEditDecreeButton = function (){
		$scope.addDecreeButton = false;
		$scope.editDecreeButton = true;
	};
	
	$scope.showAddDecreeButton = function (){
		$scope.addDecreeButton = true;
		$scope.editDecreeButton = false;
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
	 
	 $scope.showDecreeFields = function(type){
		 if(!Utils.isEmpty(type)){
			 if(type.show == 1){
				 $scope.showDecreeData = true;
			 }else{
				 $scope.showDecreeData = false;
			 }
			 $scope.resetSelectAttachment = type;			 
			 $scope.decree = {number:'' , year :'' , attachment:'' , issueDateMelady:'' , issueDateHijry:'' , notes:''};
		 }else{
			 $scope.showDecreeData = false;
		 }
	 };
	 
	 
	 $scope.deleteAttachment = function(){
		 if(document.getElementById('fileName').value == null || document.getElementById('fileName').value == ''){
			   Toast.error(BASE_CONSTS.SELECT_FILE_FIRST_MSG , '' , BASE_CONSTS.SHORT);
			   return false;
		  }else{
			  // delete attachment
			  document.getElementById('fileName').value = '';
			  $scope.decree.attachment = {};
			  Toast.success(BASE_CONSTS.DELETE_ATTACHMENT_SUCCESSFULLY_MSG, '', BASE_CONSTS.SHORT);
		  }
	 };
	 
	 /*********************************************************************************************** Member Methods ************************************************************************************/
	 
	 
	 
	 $scope.showEditMemberButton = function (){
			$scope.addMemberButton = false;
			$scope.editMemberButton = true;
		};
		
		$scope.showAddMemberButton = function (){
			$scope.addMemberButton = true;
			$scope.editMemberButton = false;
		};
	 
	 
	 $scope.addMember = function(){
			// validate Member fields is filled
			 blockUI.start();
			 var valid = $scope.validateMemberData(null);
			 if(valid){
				 if(!Utils.isEmpty($scope.member.cprNumber)){
					 MemberService.isCprNumberValid($scope.member.cprNumber).then(function(res){
						 if(res == BASE_CONSTS.BOOLEAN_FALSE){
							 Toast.error(BASE_CONSTS.INAVLID_CPR_NUMBER, '', BASE_CONSTS.LONG);
						 }else{
							 $scope.memberList.push($scope.member);
							 $scope.clearMemberData();
						 }
					 });
				 }else{
					 // if no cpr entered
					 $scope.memberList.push($scope.member);
					 $scope.clearMemberData();
				 }
				 blockUI.stop();
			 }
		};
	 
		
		$scope.validateMemberData = function(existIndex){
			
			var valid = true;
			var memberList = $scope.memberList.slice();
			Logger.info('memberList : ' + JSON.stringify(memberList));
			 if(!Utils.isEmpty($scope.member.cprNumber)){
				 if(MemberService.isMemberCprNumberExist($scope.member.cprNumber , memberList , existIndex)){
					 var message = BASE_CONSTS.ERROR_ADDING_MORE_THAN_MEMBER_WITH_SAME_CPRNUMBER.replace('{cprNumber}' , $scope.member.cprNumber);
					 Toast.error(message, '', BASE_CONSTS.LONG);
					 valid = false;
					 blockUI.stop();
					 return false;
				 }
				 
			 }
			 if(!Utils.isEmpty($scope.member.name)){
				 if(MemberService.isMemberNameExist($scope.member.name , memberList , existIndex)){
					 var message = BASE_CONSTS.ERROR_ADDING_MORE_THAN_MEMBER_WITH_SAME_NAME.replace('{NAME}' , $scope.member.name);
					 Toast.error(message, '', BASE_CONSTS.LONG);
					 valid = false;
					 blockUI.stop();
					 return false;
				 }
			 }
			 if((Utils.isEmpty($scope.member.name) && !Utils.isEmpty($scope.member.cprNumber)) || (!Utils.isEmpty($scope.member.name) && Utils.isEmpty($scope.member.cprNumber))){
				 if(!MemberService.isMemberNameAndCprNumberEntered($scope.member.name , $scope.member.cprNumber)){
					 Toast.error(BASE_CONSTS.ERROR_ADDING_NAME_WITHOUT_CPRNUMBER, '', BASE_CONSTS.LONG);
					 valid = false;
					 blockUI.stop();
					 return false;
				 }
			 }
			 if((Utils.isEmpty($scope.member.occupation) && !Utils.isEmpty($scope.member.organization))  || (!Utils.isEmpty($scope.member.occupation) && Utils.isEmpty($scope.member.organization))){
				 if(!MemberService.isMemberTitleAndOrganizationEntered($scope.member.occupation , $scope.member.organization)){
					 Toast.error(BASE_CONSTS.ERROR_ADDING_MEMBER_WITH_TITLE_AND_NO_ORGANIZATION_OR_VICEVERSA, '', BASE_CONSTS.LONG);
					 valid = false;
					 blockUI.stop();
					 return false;
				 }
			 }
			 if(!Utils.isEmpty($scope.member.role)){
				 if($scope.member.role.countValid == 1 ){
					 if(MemberService.isMemberRoleExists($scope.member.role , memberList , existIndex)){
						 Toast.error(BASE_CONSTS.MEMBER_ROLE_EXISTS_BEFORE, '', BASE_CONSTS.LONG);
						 valid = false;
						 blockUI.stop();
						 return false;
					 }
				 }
			 }
			 
			 if($scope.board.boardType.haveExpiryDate != 0){
				 
				 // IN CASE OF CPR NUMBER NOT EMPTY MUST VALIDATE JOINING DATA AND EXPIRY 26/10/2016
				 if(!Utils.isEmpty($scope.member.cprNumber)){
					 
					 if(Utils.isEmpty($scope.member.startDate) || Utils.isEmpty($scope.member.endDate)){
						 Toast.error(BASE_CONSTS.MEMBER_START_DATE_AND_END_DATE_REQUIRED, '', BASE_CONSTS.LONG);
						 valid = false;
						 blockUI.stop();
						 return false;
					 }
					 
					 if((Utils.isEmpty($scope.member.startDate) && !Utils.isEmpty($scope.member.endDate)) || (!Utils.isEmpty($scope.member.startDate) && Utils.isEmpty($scope.member.endDate))){
						 Toast.error(BASE_CONSTS.MEMBER_START_DATE_AND_END_DATE_REQUIRED, '', BASE_CONSTS.LONG);
						 valid = false;
						 blockUI.stop();
						 return false;
					 }
					 
					 if(ValidateService.isEndDateBeforeStartDate($scope.member.startDate , $scope.member.endDate)){
						 Toast.error(BASE_CONSTS.MEMBER_START_DATE_BEFORE_END_DATE, '', BASE_CONSTS.LONG);
						 valid = false;
						 blockUI.stop();
						 return false;
					 }
					 
					 if(ValidateService.isMemberEndDateAfterBoardEndDate($scope.member.endDate , $scope.board.endDate)){
						 Toast.error(BASE_CONSTS.MEMBER_END_DATE_AFTER_BOARD_END_DATE, '', BASE_CONSTS.LONG);
						 valid = false;
						 blockUI.stop();
						 return false;
					 }
					 
					 if(!(Utils.isEmpty($scope.member.notificationBefore))){

						 if(!MemberService.isNotificationDateValid($scope.member.notificationBefore.val,$scope.member.endDate)){
							 var notificationDate= MemberService.getNotificationDate($scope.member.notificationBefore.val,$scope.member.endDate)
							 var msg = BASE_CONSTS.MEMBER_NOTIFICATION_ISSUE_DATE_EXCEPTION;
							 msg = msg.replace('{NOTIFICATION_DATE}',notificationDate);
							 Toast.error(msg, '', BASE_CONSTS.LONG);
							 valid = false;
							 blockUI.stop();
							 return false;
						 }
					 }
				 }
			 }else{
					// IN CASE OF CPR NUMBER NOT EMPTY MUST VALIDATE JOINING DATA AND EXPIRY 26/10/2016
				 if(!Utils.isEmpty($scope.member.cprNumber)){
					 
					 if(Utils.isEmpty($scope.member.startDate)){
						 Toast.error(BASE_CONSTS.MEMBER_START_DATE_REQUIRED, '', BASE_CONSTS.LONG);
						 valid = false;
						 blockUI.stop();
						 return false;
					 }
					
					 if(!(Utils.isEmpty($scope.member.notificationBefore)) && Utils.isEmpty($scope.member.endDate)){
						 Toast.error(BASE_CONSTS.MEMBER_NOTIFICATION_END_DATE_REQUIRED, '', BASE_CONSTS.LONG);
						 valid = false;
						 blockUI.stop();
						 return false;
					 }
					 
					 if(!Utils.isEmpty($scope.member.startDate) && !Utils.isEmpty($scope.member.endDate)){
						 if(ValidateService.isEndDateBeforeStartDate($scope.member.startDate , $scope.member.endDate)){
							 Toast.error(BASE_CONSTS.MEMBER_START_DATE_BEFORE_END_DATE, '', BASE_CONSTS.LONG);
							 valid = false;
							 blockUI.stop();
							 return false;
						 }
					 }
					 
					 if(!(Utils.isEmpty($scope.member.notificationBefore))){
						 if(!MemberService.isNotificationDateValid($scope.member.notificationBefore.val,$scope.member.endDate)){
							 var notificationDate= MemberService.getNotificationDate($scope.member.notificationBefore.val,$scope.member.endDate);
							 var msg = BASE_CONSTS.MEMBER_NOTIFICATION_ISSUE_DATE_EXCEPTION;
							 msg = msg.replace('{NOTIFICATION_DATE}',notificationDate);
							 Toast.error(msg, '', BASE_CONSTS.LONG);
							 valid = false;
							 blockUI.stop();
							 return false;
						 }
					 }
				 }
				 
				
			 }
			 
			 
			 if(MemberService.isAllMemberFieldsEmpty($scope.member)){
				 Toast.error(BASE_CONSTS.ALL_FIELDS_EMPTY, '', BASE_CONSTS.LONG);
				 valid = false;
				 blockUI.stop();
				 return false;
			 }
			 
			 
			 if(!Utils.isEmpty($scope.member.cprNumber) && ValidateService.isMemberStartDateBeforeBoardStartDate($scope.member.startDate , $scope.board.startDate)){
				 Toast.error(BASE_CONSTS.MEMBER_START_DATE_BEFORE_BOARD_START_DATE, '', BASE_CONSTS.LONG);
				 valid = false;
				 blockUI.stop();
				 return false;
			 }
			 
			 return valid;
		};
		
		
	$scope.editMember = function(index){
		var memberObj = $scope.memberList[index];
		$scope.member.cprNumber = memberObj.cprNumber;
		$scope.member.name = memberObj.name;
		$scope.member.occupation = memberObj.occupation;
		$scope.member.organization = memberObj.organization;
		$scope.member.role = memberObj.role;
		$scope.member.organizaionOnBehalf = memberObj.organizaionOnBehalf;
		$scope.member.startDate = memberObj.startDate;
		$scope.member.endDate = memberObj.endDate;
		$scope.resetMemberOrganization = $scope.member.organization;
		$scope.resetMemberRole = $scope.member.role;
		$scope.resetMemberOrganizationOnBehalf = $scope.member.organizaionOnBehalf;
		
		
		$scope.member.notificationBefore = memberObj.notificationBefore;
		$scope.resetMemberNotificationBefore = $scope.member.notificationBefore;
		$scope.member.notificationRepeat = memberObj.notificationRepeat;
		$scope.resetMemberNotificationRepeat = $scope.member.notificationRepeat;
		
		if(!Utils.isEmpty($scope.member.cprNumber)){
			$scope.disableMemberNotification =false;
			if(!Utils.isEmpty($scope.member.notificationBefore)){
				 $scope.getNotificationMemberRepeat($scope.member.notificationBefore , $scope.member.notificationRepeat);
			}
		}
		$scope.memberIndex = index;
		$scope.showEditMemberButton();
	};	
	
	$scope.updateMember = function(index){	
		var oldMemberObj = $scope.memberList[index];
		 blockUI.start();
		var valid = $scope.validateMemberData(index);
		Logger.info(' $scope.memberList : ' + JSON.stringify($scope.memberList));
		 if(valid){
			 if(!Utils.isEmpty($scope.member.cprNumber)){
				 MemberService.isCprNumberValid($scope.member.cprNumber).then(function(res){
					 if(res == BASE_CONSTS.BOOLEAN_FALSE){
						 Toast.error(BASE_CONSTS.INAVLID_CPR_NUMBER, '', BASE_CONSTS.LONG);
					 }else{
						 angular.extend(oldMemberObj, $scope.member); // update reference only in smart table to be render
						 $scope.clearMemberData();
					 }
				 });
			 }else{
				 // if no cpr entered
				 angular.extend(oldMemberObj, $scope.member); // update reference only in smart table to be render
				 $scope.clearMemberData();
			 }
			 blockUI.stop();
		 }
	};
		
	$scope.clearMemberData = function(){
		
		$scope.member = {cprNumber : '' , name:'' , occupation : '' , startDate : '' , endDate : '' , organization :undefined , role :undefined , organizaionOnBehalf : undefined , notificationBefore: undefined , notificationRepeat : undefined};
		
		if(Utils.isEmpty($scope.member.organization)){
			$scope.resetMemberOrganization = {id:'-1' , name:""};
		}
		if(Utils.isEmpty($scope.member.role)){
			$scope.resetMemberRole = {id:'-2' , name:""};
		}
		if(Utils.isEmpty($scope.member.organizaionOnBehalf)){
			$scope.resetMemberOrganizationOnBehalf = {id:'-3' , name:""};
		}
		if(Utils.isEmpty($scope.member.notificationBefore)){
			$scope.resetMemberNotificationBefore = {id:'-4' , name:""};
			 $scope.disableMemberNotification = true;
		}
		if(Utils.isEmpty($scope.member.notificationRepeat)){
			$scope.resetMemberNotificationRepeat = {id:'-5' , name:""};
			 $scope.showMemberNotificationRepeatSelect = false;
		}
		$scope.showAddMemberButton();
		
	};
	 
	$scope.isValidCprLength = function(cprNumber){
		
		var valid = true;
		if(!Utils.isEmpty(cprNumber)){
			
			if(!ValidateService.isValidCprNumberLength(cprNumber)){
				valid = false;
			}
			
			if(!valid){
				Toast.error(BASE_CONSTS.INAVLID_CPR_NUMBER, '', BASE_CONSTS.LONG);
				$scope.disableMemberNotification = true;
			}else{
				
				MemberService.isCprNumberValid($scope.member.cprNumber).then(function(res){
					 if(res == BASE_CONSTS.BOOLEAN_FALSE){
						 Toast.error(BASE_CONSTS.INAVLID_CPR_NUMBER, '', BASE_CONSTS.LONG);
						 $scope.disableMemberNotification = true;
					 }else{
						 $scope.disableMemberNotification = false;
					 }
				 });
			}
		}else{
			//
			 $scope.resetMemberNotificationBefore = {id:'-7' , name:""};
			 $scope.disableMemberNotification = true;
			 $scope.member.notificationBefore=null;
			 $scope.showMemberNotificationRepeatSelect = false;
			 $scope.resetMemberNotificationRepeat = {id:'-8' , name:""};
			 $scope.member.notificationRepeat = null;
			 blockUI.stop();
		}
		
		
	}; 
	 
	
	$scope.searchMember = function(search){
		Logger.info('search : '  + search);
		if(!Utils.isEmpty(search)){
			MemberService.searchMember(search).then(function(result){
				Logger.info('result : '  + JSON.stringify(result));
				$scope.members = result;
			});
		}
	};
	  
	
	
	
	 /******************************************************************************* Notification Member Handling ****************************************************************************************************/
	 
	 $scope.getNotificationMemberRepeat = function(notification , memberNotificationSelected){
		 Logger.info('getNotificationMemberRepeat ========== ' + JSON.stringify(notification));
		 blockUI.start();
		 $scope.resetMemberNotificationBefore = notification; // for reset the selected value
		 if(!Utils.isEmpty(notification)){
			 var repeatsLookup = $rootScope.lookupMap.NOTIFICATION_REPEATS;
			 var memberNotificationRepeat = [];
			 for(var i=0; i <repeatsLookup.length ; i ++ ){
				 if(repeatsLookup[i].parentId == notification.id){
					 memberNotificationRepeat.push(repeatsLookup[i]);
				 }
			 }
			 
			 if(memberNotificationRepeat.length > 0){
				 $scope.resetMemberNotificationRepeat = Utils.isEmpty(memberNotificationSelected) ?  memberNotificationRepeat[0] : memberNotificationSelected;
				 $scope.memberNotificationRepeatList = memberNotificationRepeat;
				 $scope.showMemberNotificationRepeatSelect = true;
				 blockUI.stop();
			 }else{
				 $scope.showMemberNotificationRepeatSelect = false;
				 $scope.resetMemberNotificationRepeat = {id:'-7' , name:""};
				 $scope.member.notificationRepeat = null;
				 blockUI.stop();
			 }
		 }else{
			 $scope.showMemberNotificationRepeatSelect = false;
			 $scope.resetMemberNotificationRepeat = {id:'-6' , name:""};
			 $scope.member.notificationRepeat = null;
			 blockUI.stop();
		 }
	 };
	 
	 
	 
	
//	$scope.showNotification = function (index) {
//		console.log('opening pop up' + index);
//		var memberObj = $scope.memberList[index];
//		Logger.info('memberObj : ' + JSON.stringify(memberObj));
//
//		
//		  var modalDefaults = {
//			        backdrop: true,
//			        keyboard: true,
//			        modalFade: true,
//			        templateUrl: './app/common/directives/templates/member-notification-tpl.html',
//			        scope:$scope,
//			    };
//
//		var modalOptions = {
//		        closeButtonText: BASE_CONSTS.CANCEL_BUTTON,
//		        actionButtonText: BASE_CONSTS.SAVE,
//		        headerText: BASE_CONSTS.MEMBER_NOTIFICATION + memberObj.name,
//		    };
//		    modalService.showModal(modalDefaults, modalOptions).then(function (result) {
//		    	// in case of yes
//		    	 console.log('member  save   '+ JSON.stringify(memberObj));
////				 var index = Utils.findInArray($scope.list , 'cprNumber' , member.cprNumber);  fix issue of cpr number is empty
//		    	 var index = Utils.findInArrayByObject($scope.list , memberObj);
//		    	 
//				 Logger.info('index : ' + index );
//				 $scope.list.splice(index,1);
//				 $scope.clearCallBackCtrlFn();
//		    });
//	};
	 /******************************************************************************* Notification Handling ****************************************************************************************************/
	 
	 
	 $scope.showNotificationRepeat = function (notification){
		 Logger.info('showNotificationRepeat ========== ');
		 blockUI.start();
		 $scope.resetNotificationBefore = notification; // for reset the selected value
		 if(!Utils.isEmpty(notification)){
			 var repeatsLookup = $rootScope.lookupMap.NOTIFICATION_REPEATS;
			 var notificationRepeat = [];
			 for(var i=0; i <repeatsLookup.length ; i ++ ){
				 if(repeatsLookup[i].parentId == notification.id){
					 notificationRepeat.push(repeatsLookup[i]);
				 }
			 }
			 
			 if(notificationRepeat.length > 0){
				 $scope.resetNotificationRepeat = notificationRepeat[0];
				 $scope.notificationRepeatList = notificationRepeat;
				 $scope.showNotificationRepeatSelect = true;
				 blockUI.stop();
			 }else{
				 $scope.showNotificationRepeatSelect = false;
				 blockUI.stop();
			 }
		 }else{
			 $scope.showNotificationRepeatSelect = false;
			 blockUI.stop();
		 }
	 };
	 /******************************************************************************* Board Methods ****************************************************************************************************/
	
	$scope.disableOrEnableBoardExpiry = function(boardType){
		
		Logger.info('boardType : ' + JSON.stringify(boardType));
		
		if(boardType!=null && boardType.haveExpiryDate == 0 ){
			$scope.disableBoardExpiry = true;
			$scope.board.endDate = '';
			$scope.board.notificationBefore = '';
			$scope.resetNotificationBefore = '';
			$scope.showNotificationRepeat($scope.board.notificationBefore);
		}else{
			$scope.disableBoardExpiry = false;
		}
	};
	
	
	$scope.showBoardsRelated = function(creationType){
		Logger.info('creationType : ' + JSON.stringify(creationType));
		if(!Utils.isEmpty(creationType) && creationType.id == 2){
			$scope.showSearchBoard = true;
		}else{
			$scope.showSearchBoard = false;
			$scope.getSelectedParentBoard(null);
			$scope.boards = [];
		}
		
	};
	
	
	$scope.searchBoard = function(search){
		Logger.info('search : '  + search);
		if(!Utils.isEmpty(search)){
			BoardService.searchBoard(search).then(function(result){
				Logger.info('result : '  + JSON.stringify(result));
				$scope.boards = result;
			});
		}
	};
	  
	
	$scope.getSelectedParentBoard = function(parentBoard){
		Logger.info('parentBoard : ' + JSON.stringify(parentBoard));
		if(!Utils.isEmpty(parentBoard)){
			angular.forEach($rootScope.lookupMap.BOARD_TYPES, function(value, index) {
				Logger.info('value : ' + JSON.stringify(value));
			    if(value.id == parentBoard.boardTypeId){
			    	$scope.board.boardType = value;
			    	return;
			    }
		  });
			$scope.board.parentId = parentBoard.boardId;
			$scope.board.parentBoard = parentBoard;
			$scope.disableOrEnableBoardExpiry($scope.board.boardType);
		}else{
			$scope.board.boardType = $rootScope.lookupMap.BOARD_TYPES[0];
			$scope.disableOrEnableBoardExpiry($scope.board.boardType);
			$scope.board.parentId = null;
			$scope.board.parentBoard = null;
			$scope.x.parent = [];
		}
	};
	
	
	
	$scope.validateBoardNameExist = function(){
		BoardService.isBoardNameExist($scope.board.name , $scope.board.boardType.id).then(function(res){
			 if(res == BASE_CONSTS.BOOLEAN_TRUE){
				 Toast.error(BASE_CONSTS.BOARD_NAME_ALREADY_EXIST, '', BASE_CONSTS.LONG);
			 }
		 });
	};
	
	 
	 /********************************************************************************************* SAVE ACTION **************************************************************************************/
	 
	 $scope.saveBoard = function(){
		 
		 // need to complete the other methods for decree and member and validate first before send
		 blockUI.start();
		 var valid = $scope.validateBoardData();
		 if(valid){
			 
			 BoardService.isBoardNameExist($scope.board.name , $scope.board.boardType.id).then(function(res){
				 if(res == BASE_CONSTS.BOOLEAN_TRUE){
					 blockUI.stop();
					 Toast.error(BASE_CONSTS.BOARD_NAME_ALREADY_EXIST, '', BASE_CONSTS.LONG);
				 }else{
					 var map = $scope.fillBoardDataMap();
//					 Logger.info('map : ' + JSON.stringify(map));
					 $http.post(BASE_RESOURCES.ADD_BOARD_URL , JSON.stringify(map) , {headers: {'Content-Type': 'application/json'} })
					 .success(function(data, status, headers, config) {
						 Toast.success(BASE_CONSTS.SUCCESSFULLY_BOARD_CREATED, '', BASE_CONSTS.LONG);
					        blockUI.stop();
					        $(location).attr('href', '#/search');
					 })
					 .error(function(data, status, headers, config) {
						 console.log('error: ' + JSON.stringify(status));
						 if(status == '1002' || status == '1008'){
							 Toast.error(BASE_CONSTS.BOARD_CREATION_FAILED, '', BASE_CONSTS.LONG);
						 }else if(status == '1003'){
							 Toast.error(BASE_CONSTS.BOARD_NAME_ALREADY_EXIST, '', BASE_CONSTS.LONG);
						 }else if(status == '1004'){
							 Toast.error(BASE_CONSTS.FILE_UPLOAD_FAILED, '', BASE_CONSTS.LONG);
						 }else if(status == '1005'){
							 Toast.error(BASE_CONSTS.DATABASE_ERROR, '', BASE_CONSTS.LONG);
						 }else if(status == '1006'){
							 Toast.error(BASE_CONSTS.INAVLID_CPR_NUMBER, '', BASE_CONSTS.LONG);
						 }else if(status == '1007'){
							 Toast.error(BASE_CONSTS.DECREE_IS_ALREADY_EXISTS, '', BASE_CONSTS.LONG);
						 }
					        blockUI.stop();
					 });
				 }
			 });
		 }
	 };
	 
	 
	 
	 
	 $scope.fillBoardDataMap = function(){
		 var map = new Object();
		 var board = $scope.formatBoard();
		 var decrees = $scope.formatDecrees();
		 var members = $scope.formatMembers();
		 map['board'] = board;
		 map['decreeList'] = decrees;
		 map['memberList'] = members;
		 return map;
	 };
	 
	 
	 $scope.formatBoard = function(){
		 var board = {name:'' , sourceOrganizationId:'' , destinationOrganizationIds:[] ,endDate:'' , startDate:'' , boardTypeId:'' , notificationBefore:'' , notificationRepaet : '', parentBoardId:''};
		 board.sourceOrganizationId = !Utils.isEmpty($scope.board.sourceOrganization) ? $scope.board.sourceOrganization.id : null ;
		 board.name = $scope.board.name;
		 board.boardTypeId = !Utils.isEmpty($scope.board.boardType) ? $scope.board.boardType.id : null;
		 board.notificationBefore = !Utils.isEmpty($scope.board.notificationBefore) ? $scope.board.notificationBefore.val : null;
		 board.notificationRepaet = !Utils.isEmpty($scope.board.notificationRepaet) ? $scope.board.notificationRepaet.val : null;
		 board.startDate = $scope.board.startDate;
		 board.endDate = $scope.board.endDate;
		 board.parentBoardId = $scope.board.parentId;
		 
		 angular.forEach($scope.board.destinationOrganization, function(value, index) {
			    board.destinationOrganizationIds.push(value.id);
		  });
		 
		 return board;
	 };
	 
	 
	 $scope.formatDecrees = function(){
		 var decrees = [];
		 angular.forEach($scope.decreeList, function(value, index) {
			 var decree = {number:'' , year:'',typeId:'',attachmentTypeId:'',attachment:{name:'' , size:'',type:'',base64:'',createdOn:''} , issueDateMelady:'' , issueDateHijry:'' , notes:''};
			 decree.number = value.number;
			 decree.year = value.year;
			 decree.typeId = !Utils.isEmpty(value.type) ? value.type.id : null;
			 decree.attachmentTypeId = value.attachmentType.id;
			 decree.attachment = value.attachment;
			 decree.issueDateMelady = value.issueDateMelady;
			 decree.issueDateHijry = value.issueDateHijry;
			 decree.notes = value.notes;
			 decrees.push(decree);
		  });
		 return decrees;
	 };
	 
	 
	 $scope.formatMembers = function(){
		 var members = [];
		 angular.forEach($scope.memberList, function(value, index) {
			 var member = {cprNumber:'',name:'',occupation:'',organizationId:'',roleId:'',organizaionOnBehalfId:'',startDate:'',endDate:'', notificationBefore:'' , notificationRepeat : ''};
			 member.cprNumber = value.cprNumber;
			 member.name = value.name;
			 member.occupation = value.occupation;
			 member.organizationId = !Utils.isEmpty(value.organization) ? value.organization.id : null;
			 member.roleId = !Utils.isEmpty(value.role) ? value.role.id : null;
			 member.organizaionOnBehalfId = !Utils.isEmpty(value.organizaionOnBehalf) ? value.organizaionOnBehalf.id : null;
			 member.startDate = value.startDate;
			 member.endDate = value.endDate;
			 member.notificationBefore = !Utils.isEmpty(value.notificationBefore) ? value.notificationBefore.val : null;
			 member.notificationRepeat = !Utils.isEmpty(value.notificationRepeat) ? value.notificationRepeat.val : null;
			 members.push(member);
		  });
		 return members;
	 };
	 
	 
	 
	 $scope.validateBoardData = function(){
		 var valid = true;
		
		//non permenant 
		 if($scope.board.boardType.haveExpiryDate != 0){
			 if((Utils.isEmpty($scope.board.startDate) && !Utils.isEmpty($scope.board.endDate)) || (!Utils.isEmpty($scope.board.startDate) && Utils.isEmpty($scope.board.endDate)) || (Utils.isEmpty($scope.board.startDate) && Utils.isEmpty($scope.board.endDate))){
				 
				 Toast.error(BASE_CONSTS.BOARD_START_DATE_AND_END_DATE_REQUIRED, '', BASE_CONSTS.LONG);
				 valid = false;
				 blockUI.stop();
				 return false;
			 }
			 
			
			 
			 if(ValidateService.isEndDateBeforeStartDate($scope.board.startDate , $scope.board.endDate)){
				 Toast.error(BASE_CONSTS.MEMBER_END_DATE_BEFORE_START_DATE, '', BASE_CONSTS.LONG);
				 valid = false;
				 blockUI.stop();
				 return false;
			 }
			 
		 }
		 
		 
		 if($scope.board.boardType.haveExpiryDate != 1){
			 if((Utils.isEmpty($scope.board.startDate))){
				 Toast.error(BASE_CONSTS.BOARD_START_DATE_REQUIRED, '', BASE_CONSTS.LONG);
				 valid = false;
				 blockUI.stop();
				 return false;
			 }
		 }
		 
		 
		 if(ValidateService.isDateAfterToday($scope.board.startDate)){
			 Toast.error(BASE_CONSTS.BOARD_START_DATE_NOT_IN_FUTURE, '', BASE_CONSTS.LONG);
			 valid = false;
			 blockUI.stop();
			 return false;
		 }
		 
		 if($scope.board.boardType.requiredLegislativeTool != 0){
			 if($scope.decreeList == 0){
				 Toast.error(BASE_CONSTS.BOARD_DECREE_REQUIRED, '', BASE_CONSTS.LONG);
				 valid = false;
				 blockUI.stop();
				 return false;
			 }
		 }else{
			 /*
			  * validate board start date not older than decree issue date melady
			  * */
			 angular.forEach($scope.decreeList, function(value, index) {
				 if(value.attachmentType.show == 1){
					 if(ValidateService.isBoardStartDateBeforeDecreeMelady(value.issueDateMelady , $scope.board.startDate)){
						 Toast.error(BASE_CONSTS.BOARD_START_DATE_NOT_OLDER_DECREE_MELADY, '', BASE_CONSTS.LONG);
						 valid = false;
						 blockUI.stop();
						 return false;
					}
				 }
			 });
		 }
		 
		 
		 if(Utils.isEmpty($scope.board.name)){
			 Toast.error(BASE_CONSTS.BOARD_NAME_REQUIRED, '', BASE_CONSTS.LONG);
			 valid = false;
			 blockUI.stop();
			 return false;
		 }
		 
		 if(Utils.isEmpty($scope.board.sourceOrganization)){
			 Toast.error(BASE_CONSTS.BOARD_SOURCE_ORGANIZATION_REQUIRED, '', BASE_CONSTS.LONG);
			 valid = false;
			 blockUI.stop();
			 return false;
		 }
		 
		 if(Utils.isEmpty($scope.board.destinationOrganization)){
			 Toast.error(BASE_CONSTS.BOARD_DESTINATION_ORGANIZATION_REQUIRED, '', BASE_CONSTS.LONG);
			 valid = false;
			 blockUI.stop();
			 return false;
		 }
		 
		 if(!Utils.isEmpty($scope.board.parentBoard)){
			 if($scope.board.parentBoard.boardTypeId !=  $scope.board.boardType.id){
				 Toast.error(BASE_CONSTS.BOARD_PARENT_IS_NOT_THE_SAME_BOARD_TYPE, '', BASE_CONSTS.LONG);
				 valid = false;
				 blockUI.stop();
				 return false;
			 }
			 
		 }
		 
		 
		 if($scope.board.boardType.haveExpiryDate != 0 && !Utils.isEmpty($scope.board.notificationBefore)){
			 if(!BoardService.isNotificationDateValid($scope.board.notificationBefore.val,$scope.board.endDate)){
				 var notificationDate= BoardService.getNotificationDate($scope.board.notificationBefore.val,$scope.board.endDate);
				 var msg = BASE_CONSTS.BOARD_NOTIFICATION_ISSUE_DATE_EXCEPTION;
				 msg = msg.replace('{NOTIFICATION_DATE}',notificationDate);
				 Toast.error(msg, '', BASE_CONSTS.LONG);
				 valid = false;
				 blockUI.stop();
				 return false;
			 }
		 }	
		 
		 return valid;
	 };
	 
	 /*********************************************************************************************************************************************************************************/
	 $timeout(function(){
			$scope.board.boardType = $rootScope.lookupMap.BOARD_TYPES[0];
		},500);
	 
	 $timeout(function(){
		 $scope.showDecreeFields($scope.decree.attachmentType);
	 },500);
	 
	
	 $timeout(function(){
		 $scope.decree.attachmentType =  $rootScope.lookupMap.ATTACHMENT_TYPES[0]; // for ng-change
		 $scope.resetSelectAttachment =  $rootScope.lookupMap.ATTACHMENT_TYPES[0]; // for reset attachment first
		},500);
	 
	 
//	 $timeout(function(){
//		 $scope.showNotificationRepeat($scope.board.notificationBefore);
//	 },500);
	 
	 
	 
}]);