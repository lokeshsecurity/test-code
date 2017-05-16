angular.module('common.directive.memberTable', [])

.directive('memberTable', function()
    {
	  var directiveDefinitionObject = 
	   {
		restrict : 'E',
		replace : true,
	    templateUrl: "./app/common/directives/templates/member-table-tpl.html" ,
	    scope: {
	    	       list 			: '=list',
	    	       showRemoveColumn : '=showRemoveColumn',
	    	       editCallBackCtrlFn	: '&editCallbackFn',
	    	       clearCallBackCtrlFn	: '&clearCallbackFn',
	    	       renewCallBackCtrlFn  : '&renewCallbackFn',
	    	       notificationCallBackCtrlFn : '&notificationCallbackFn',
		       },
		            
	    link : function(scope, element, attrs , ngModel){	    	       	
		  },
		  
		 controller:function($rootScope , $scope , $http , Utils , modalService , BASE_CONSTS , ValidateService, SweetAlert) {	
			$scope.remove = function(member){
				
				var modalOptions = {
				        closeButtonText: BASE_CONSTS.NO_BUTTON,
				        actionButtonText: BASE_CONSTS.YES_BUTTON,
				        headerText: BASE_CONSTS.CONFIRM_MEMBER_DIALOG_HEADER,
				        bodyText: BASE_CONSTS.CONFIRM_MEMBER_DIALOG_BODY_MSG
				    };
				    modalService.showModal({}, modalOptions).then(function (result) {
				    	// in case of yes
				    	 console.log('member  Remove   '+ JSON.stringify(member));
//						 var index = Utils.findInArray($scope.list , 'cprNumber' , member.cprNumber);  fix issue of cpr number is empty
				    	 var index = Utils.findInArrayByObject($scope.list , member);
						 Logger.info('index : ' + index );
						 $scope.list.splice(index,1);
						 $scope.clearCallBackCtrlFn();
				    });
				
				
			 };
			 
			 $scope.edit = function(member){
//				 var index = Utils.findInArray($scope.list , 'cprNumber' , member.cprNumber); fix issue of cpr number is empty
				 var index = Utils.findInArrayByObject($scope.list , member);
				 $scope.editCallBackCtrlFn({index:index});
			 };
			 
			 $scope.renew = function(member){
//				 var index = Utils.findInArray($scope.list , 'cprNumber' , member.cprNumber);  fix issue of cpr number is empty
				 var index = Utils.findInArrayByObject($scope.list , member);
				 $scope.renewCallBackCtrlFn({index:index});
			 };
			 
			 $scope.checkRenewal = function(member){
				 if(!Utils.isEmpty(member.memberId) && !Utils.isEmpty(member.endDate) && ValidateService.isDateBeforeToday(member.endDate)){
					 return true;
				 } else {
					 return false;
				 }
			 };
			 
			 $scope.getNotification = function(member){
				 var index = Utils.findInArrayByObject($scope.list , member);
				 console.log('index : getNotification : ' + index);
				 $scope.notificationCallBackCtrlFn({index:index});
			};
	      }
	 };
	                              
	return directiveDefinitionObject;
});
