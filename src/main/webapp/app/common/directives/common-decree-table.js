angular.module('common.directive.decreeTable', [])

.directive('decreeTable', function()
    {
	  var directiveDefinitionObject = 
	   {
		restrict : 'E',
		replace : true,
	    templateUrl: "./app/common/directives/templates/decree-table-tpl.html" ,
	    scope: {
	    	       list 			: '=list',
	    	       showRemoveColumn : '=showRemoveColumn',
	    	       editCallBackCtrlFn	: '&editCallbackFn',
	    	       clearCallBackCtrlFn	: '&clearCallbackFn',
		       },
		            
	    link : function(scope, element, attrs , ngModel){	    	       	
		  },
		  
		 controller:function($rootScope , $scope , $http , DownloadService , DecreeService , modalService , BASE_CONSTS , SweetAlert) {	
			$scope.remove = function(decree){
			
				var modalOptions = {
				        closeButtonText: BASE_CONSTS.NO_BUTTON,
				        actionButtonText: BASE_CONSTS.YES_BUTTON,
				        headerText: BASE_CONSTS.CONFIRM_DIALOG_HEADER,
				        bodyText: BASE_CONSTS.CONFIRM_DECREE_DIALOG_BODY_MSG
				    };
				    modalService.showModal({}, modalOptions).then(function (result) {
				    	// in case of yes
						 var index = DecreeService.findInDecree(decree , $scope.list);
						 $scope.list.splice(index,1);
						 $scope.clearCallBackCtrlFn();
				    });
			 };
			 
			 $scope.edit = function(decree){
				 var index = DecreeService.findInDecree(decree , $scope.list);
				 $scope.editCallBackCtrlFn({index:index});
			 };
			 
			$scope.downloadFile = function(base64Data , contentType , filename){
				DownloadService.downloadBase64File(base64Data , contentType , filename);
			};
			
			$scope.showNotes = function(decree){
				SweetAlert.custom(BASE_CONSTS.NOTES_LABEL , decree.notes , "info" , false , "#6aad88", BASE_CONSTS.CANCEL_BUTTON);
			};
	      }
		  
	 };
	                              
	return directiveDefinitionObject;
});








