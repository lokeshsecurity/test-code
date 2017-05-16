angular.module("gbs.notification.controllers" , [])

.filter('firstElementFilter' , function(){
    return function(input) {
       
        return input[0];
      };
    
})

.controller('notificationController',['$rootScope' , '$scope' , '$http' ,'$state', 'Utils', 'Toast', 'BASE_CONSTS', '$timeout' ,'blockUI' , 'BASE_RESOURCES','$interval',
                                        function($rootScope , $scope , $http , $state , Utils , Toast , BASE_CONSTS , $timeout , blockUI , BASE_RESOURCES , $interval) {

	var ctrl = this;
	ctrl.getDate = function() {
		return Utils.getLocaleDate();
	};

	$scope.refreshCallback = function(contentScope, done) {
		// use contentScope to get access with activityContent directive's Control Scope
//		console.log(contentScope);

		// for example getting your very long data ...........
		setTimeout(function() {
			$scope.getNotifications();
			done();
		}, 500);
		
		$scope.footerContent = ctrl.getDate();
	};
	
	
	$scope.src = './app/notification/templates/notifications.html';
	$scope.footerContent = ctrl.getDate();
	
	
	$scope.getNotifications = function(){
//	blockUI.start();
	$http.get(BASE_RESOURCES.NOTIFICATIONS_URL)
		.success(function(data, status, headers, config) {
		    $scope.notifications = data;
		    Logger.info('Success GET (getNotifications) : ' + JSON.stringify(data));
		    $scope.total = data.length;
//		    blockUI.stop();
		})
		.error(function(data, status, headers, config) {
		 console.log('error: ' + data);
//		        blockUI.stop();
		});
	};
	    		
	    	
//	 $interval(function(){ $scope.getNotifications(); }, BASE_CONSTS.NOTIFICATION_TIMER_REPEATER); not need now
	 $scope.getNotifications();
	
}]);

