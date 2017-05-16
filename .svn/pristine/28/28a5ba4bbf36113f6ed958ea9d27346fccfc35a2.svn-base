angular.module('common.directive.attachment', ['common.directives.file.model'])
       .directive('cioAttachment', function()
    {
	  var directiveDefinitionObject = 
	   {
		restrict : 'E',
		replace : true,
	    templateUrl: "./app/common/directives/templates/attachment-tpl.html" ,
	    scope: {
	    	       attachmentTypeList   : '=attachmentTypeList' , 
	    	       attachmentUrl        : '@attachmentUrl' ,
	    	       attachmentList 		: '=attachmentList'	,
	    	       defaultOption        : '=defaultOption'
		       },
		            
	    link : function(scope, element, attrs , ngModel){	    	       	
		  },
		  
		 controller:function($rootScope , $scope , $http) {	
			 
			 $scope.addAttachment = function(){ 
				  console.log(' Add Attachment  '+$scope.attachmentUrl+ '  Attachment Type  '+JSON.stringify($scope.attachmentType));
						  
				  if(document.getElementById('fileName').value == null || document.getElementById('fileName').value == '' ){
					   toastr.error(' يجب تحديد ملف ');
					   return false;
				   }
				 
				   var fd = new FormData();
				   console.log(' fileList  '+JSON.stringify($scope.fileList));
				   angular.forEach($scope.fileList,function(file) {
					   fd.append('file', file );
				   })
				 
				   var attachmentResponse =  $http.post( $scope.attachmentUrl  , fd  , { params : { attachmentTypeId : $scope.attachmentType.attachmentTypeId }  , transformRequest: angular.identity , headers: {'Content-Type': undefined }  } );          
				   attachmentResponse.success(function (data, status, headers, config) {
					     var attachment = { attachmentId : data.attachmentId  , fileName : data.fileName , fileSize : data.fileSize , attachmentType : $scope.attachmentType , createdBy: $rootScope.loggedInUser.userName , createdOn : data.createdOn };
				  		 console.log(' Add File to Target Attachment List  '+JSON.stringify(attachment));
				  		 $scope.attachmentList.push(attachment);
				    });
				   
				  }
			 
			$scope.removeAttachment = function(attachmentIndex,attachmentId){
				 console.log('  Remove Attachment  '+attachmentId+' Attachment Index '+attachmentIndex  );
				 var url =  $scope.attachmentUrl+"/"+attachmentId;
				 var attachmentResponse =  $http.delete( url , {} , {} );          
				   attachmentResponse.success(function (data, status, headers, config) {
				  		 console.log(' File Deleted  '+attachmentId);
				  		 $scope.attachmentList.splice(attachmentIndex,1);
				    });
			 }
			 
					  
	      }
		  
	 };
	                              
	return directiveDefinitionObject;
});








