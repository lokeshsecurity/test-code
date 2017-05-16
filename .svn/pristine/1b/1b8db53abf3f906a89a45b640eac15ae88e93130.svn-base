angular.module('common-lookup-directives', [])

.directive('cioLookup', function($compile , Utils){

		  var directiveDefinitionObject = 
		   {
			
			restrict : 'E',
			replace : true,
		    require: 'ngModel',
		    scope: {
			          title         :'@lookupName' ,
			          placeholder   :'@placeholder',
			          lookupList    :'=lookupList',
			          selectedItem 	:'=ngModel' ,
			          reset 		:'=' ,
			          defaultOption :'=defaultOption',
			          isDisabled    :'=disabled',
			          style			:'@style',
			          changeCallBackFn	: '&changeCallbackFn',
			          clear				:'=?',
			       },

		    template : 
		    	        ' <div>' +
		                 '<div> <ui-select theme="select2"  ng-disabled="isDisabled" class=" lookup" ng-model="selected.item" style="{{style}}" ng-change="change();">' +
		                 '<ui-select-match  allow-clear="{{clear}}" placeholder="{{placeholder}}">  {{ selectedItem.name}}  <i class="clear glyphicon glyphicon-remove"></i>  </ui-select-match>'+
		                 '<ui-select-choices   repeat="lookup in (lookupList | filter: $select.search) track by $index " >' +
		                 '<div  ng-bind-html="lookup.name | highlight: $select.search"></div>	'+
		                 '</ui-select-choices>' +
		                 '</ui-select> </div>' +
		                ' </div> ' ,

		    link : function(scope, element, attrs ,ngModel){
		    	scope.selected = {};
		        scope.selected.item = {};	
		    	if(scope.selectedItem != null && scope.selectedItem!= undefined){
		    		scope.selected.item = scope.selectedItem;
		    	}
	        	scope.$watch('selected.item', function () {
	           		scope.selectedItem = scope.selected.item;
	           		Logger.info('selected.item : scope.selectedItem : ' + JSON.stringify(scope.selectedItem));
	           		scope.clear = (!Utils.isEmpty(scope.selectedItem) && scope.selectedItem.id > 0 ) ? true : false;
	           		Logger.info('selected.item : scope.clear : ' + scope.clear);
	           		if(scope.selectedItem!= null && scope.selectedItem.id < 0){
	           			scope.selected.item = undefined;
	           		}
	           		
	           		if(scope.clear == false && scope.selected.item == undefined){
		           		if(attrs.changeCallbackFn != undefined && attrs.changeCallbackFn.indexOf('showDecreeFields(type);') != -1){
		           			scope.changeCallBackFn({type : scope.selected.item});
		           		}
	           		}
	           		
	        	}); 
	        	scope.$watch('reset', function () {
	        		// to re-initialize the ng-model (refresh)
	        		Logger.info('scope.reset : ' + JSON.stringify(scope.reset));
	           		scope.selected.item = scope.reset;
	           		scope.clear = false;
	           		Logger.info('reset : scope.clear : ' + scope.clear);
	        	}); 
	        	
	        	scope.$watch('isDisabled', function () {
	        		// used in edit member to remove ng-disabled
	        		Logger.info('scope.isDisabled : ' + scope.isDisabled);
	        		if(!scope.isDisabled){
	        			var div=element[0].childNodes[0].childNodes[1];
	        			if(div.className.indexOf('select2-container-disabled') !=-1){
	        				angular.element(div).removeClass('select2-container-disabled');
	        			}
	        		}
	        	});
	        	
			  },
			  
			 controller:function($scope , $attrs) {
				 if($scope.defaultOption){
					 var index = Utils.findInArray($scope.lookupList , 'id' , $scope.defaultOption.id);
					 $scope.selected.item = $scope.lookupList[index];
					 $scope.selectedItem = $scope.lookupList[index];
				 }
				 
				 $scope.change = function(){
					 if($attrs.changeCallbackFn != undefined){
						 if($attrs.changeCallbackFn.indexOf('showNotificationRepeat(notification);') != -1){
							 if($scope.changeCallBackFn != undefined){
								 console.log('changeCallBackFn' + $scope.changeCallBackFn);
								 $scope.changeCallBackFn({notification : $scope.selected.item});
							 }
							 
						 }else if($attrs.changeCallbackFn.indexOf('showBoardsRelated(creationType);') != -1){
							 $scope.changeCallBackFn({creationType : $scope.selected.item});
						 }else if($attrs.changeCallbackFn.indexOf('getNotificationMemberRepeat(notification);') != -1){
							console.log('notification : ' + JSON.stringify($scope.selected.item));
							 $scope.changeCallBackFn({notification : $scope.selected.item});
						 }else{
							 if($scope.changeCallBackFn != undefined){
								 $scope.changeCallBackFn({type : $scope.selected.item});
							 }
						 }
					 }
				 };
				 
				
			 }	
		};
		                              
		return directiveDefinitionObject;
	})


//.directive('resetUiSelect', function(){
//	 return {
//	        restrict: 'A',
//	        require: ['^ngModel','uiSelect'],
//	        link: function (scope, element, attrs, ctrls) {
//	            scope.$watch(attrs.ngModel,function (newval,oldval,scope) {
//	            	console.log('newval : ' + newval);
//	                if (newval != undefined && newval.length < 1){
//	                	console.log('scope.selected.item : '+ scope.selected.item);
//	                    scope.selected.item = undefined;
//	                }
//	            });
//	        }
//	    };
//})


//.directive('resetsearchmodel', [resetSearchModel]);
//function resetSearchModel () {
//    return {
//        restrict: 'A',
//        require: ['^ngModel','uiSelect'],
//        link: function (scope, element, attrs, ctrls) {
//        	Logger.info('attrs ngModel: ' + attrs.ngModel);
//            scope.$watch(attrs.ngModel,function (newval,oldval,scope) {
//            	console.log('newval : ' + newval);
//            	console.log('oldval : ' + oldval);
//            	console.log('scope.selected.item : ' + JSON.stringify(scope.$select.selected));
//                if (newval == undefined){
//                    scope.$select.selected = undefined;
//                }
//            });
//        }
//    };
//}