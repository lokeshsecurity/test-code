angular.module('common-filters',[])


.filter('dateFilter', function($filter) {
    return function(value , formatter) {
      return $filter('date')(value , formatter);
    };
})


/**
 * used to filter application constant in HTML
 */
.filter('constant', function(BASE_CONSTS , Utils) {
	return function (text) {
		var foundKey = null;
		for (key in BASE_CONSTS) {
			if(key == text){
				foundKey = key;
				break;
			}
		}
		if(!Utils.isEmpty(foundKey))
			return BASE_CONSTS[foundKey];
		return null;
    }; 
})