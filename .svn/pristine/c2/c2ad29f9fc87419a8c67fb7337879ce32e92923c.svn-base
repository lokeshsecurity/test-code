angular.module('ui.bootstrap.module', ['ui.bootstrap']);
angular.module('ui.bootstrap.module').controller('DatepickerDemoCtrl', function ($scope) {
  $scope.today = function() {
    $scope.dt = new Date();
  };
  $scope.today();

  $scope.clear = function () {
    $scope.dt = null;
  };

  // Disable certain dates selection based on the interval
  //default enabled date is future date
  $scope.disabled = function(date, mode, interval) {
	  if(interval === 'past'){
		  return ( date > new Date());
	  }
	  
    return ( date < new Date());
  };

  $scope.toggleMin = function() {
    $scope.minDate = $scope.minDate ? null : new Date();
  };
  $scope.toggleMin();

  $scope.open = function($event) {
    $event.preventDefault();
    $event.stopPropagation();

    if(! $($event.target.nextElementSibling).is(':disabled')){
    	$scope.opened = true;
    }else{
    	toastr.error("عذرا , لا يمكنك تعديل هذه القيمه");
    }
    	
  };



  $scope.dateOptions = {
    formatYear: 'yy',
    startingDay: 1
  };

  $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
  $scope.format = $scope.formats[4];
});