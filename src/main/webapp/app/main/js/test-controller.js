angular.module("gbs.controllers" , [])
.controller('TestController',['$rootScope' , '$scope' , '$http' ,'$state', function($rootScope,$scope,$http,$state) {
	
	
//	$scope.todayDate = new Date();
	
	
	
	$scope.getSelectedDate = function(){
		console.log('date selected : ' + $scope.todayDate);
	}
	
	  $scope.open = function($event) {
		    $event.preventDefault();
		    $event.stopPropagation();

		    if(! $($event.target.nextElementSibling).is(':disabled')){
		    	$scope.opened = true;
		    }else{
		    	toastr.error("عذرا , لا يمكنك تعديل هذه القيمه");
		    }
		    	
	 };
	
}])