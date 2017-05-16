angular.module('common-directives',[])


.directive('gbsDate', ['dateFilter',function(dateFilter , $parse){
	  return{
	    restrict:'EAC',
	    require:'?ngModel',
	    link:function(scope,element,attrs,ngModel,ctrl){
	      ngModel.$parsers.push(function(viewValue){
	        return dateFilter(viewValue,'yyyy-MM-dd');
	      });
	    }
	 };
}])

.directive('contenteditable', function() {
	return {
		require : 'ngModel',
		link : function(scope, elm, attrs, ctrl) {
			// view -> model
			elm.bind('blur', function() {
				scope.$apply(function() {
					ctrl.$setViewValue(elm.html());
				});
			});

			// model -> view
			ctrl.render = function(value) {
				elm.html(value);
			};

			elm.bind('keydown', function(event) {
				console.log("keydown " + event.which);
				var esc = event.which == 27, el = event.target;

				if (esc) {
					console.log("esc");
					ctrl.$setViewValue(elm.html());
					el.blur();
					event.preventDefault();
				}

			});

		}
	};
})



.directive('validNumber', function() {
  return {
    require: '?ngModel',
    link: function(scope, element, attrs, ngModelCtrl) {
      if(!ngModelCtrl) {
        return; 
      }

      ngModelCtrl.$parsers.push(function(val) {
        if (angular.isUndefined(val)) {
            var val = '';
        }
        var clean = val.replace( /[^0-9]+/g, '');
//        console.log('clean : ' + clean);
        if (val !== clean) {
          ngModelCtrl.$setViewValue(clean);
          ngModelCtrl.$render();
        }
        return clean;
      });

      element.bind('keypress', function(event) {
        if(event.keyCode === 32) {
          event.preventDefault();
        }
      });
    }
  };
})


.directive('validCharacter', function() {
  return {
    require: '?ngModel',
    link: function(scope, element, attrs, ngModelCtrl) {
      if(!ngModelCtrl) {
        return; 
      }

      ngModelCtrl.$parsers.push(function(val) {
        if (angular.isUndefined(val)) {
            var val = '';
        }
        console.log('val : ' + val);
        var clean = val.replace(/\d/gi, '');
//        var clean = val.replace(/^[a-zA-Z]*$/, '');
        console.log('clean : ' + clean);
        if (val !== clean) {
          ngModelCtrl.$setViewValue(clean);
          ngModelCtrl.$render();
        }
        return clean;
      });

      // allow space GBS-135
//      element.bind('keypress', function(event) {
//        if(event.keyCode === 32) {
//          event.preventDefault();
//        }
//      });
    }
  };
})


.directive('validYear', function(Utils) {
  return {
    require: '?ngModel',
    link: function(scope, element, attrs, ngModelCtrl) {
      if(!ngModelCtrl) {
        return; 
      }

      ngModelCtrl.$parsers.push(function(val) {
        if (angular.isUndefined(val)) {
            var val = '';
        }
        var clean = val.replace(/[^0-9]+/g, '');
        var current_year = new Date().getFullYear();
        if (val !== clean) {
        	ngModelCtrl.$setViewValue(clean);
            ngModelCtrl.$render();
        }else{
        	if((!Utils.isEmpty(clean)) && (clean.length != 4 || clean > current_year)){
            	element.addClass('errorLable');
            }else if((!Utils.isEmpty(clean)) && clean.length == 4 && clean <= current_year){
            	element.removeClass('errorLable');
            }else if(Utils.isEmpty(clean)){
            	element.removeClass('errorLable');
            }
        }
        return clean;
      });

      element.bind('keypress', function(event) {
        if(event.keyCode === 32) {
          event.preventDefault();
        }
      });
    }
  };
})


.directive('yearValid', function(Utils) {
  return {
    require: '?ngModel',
    link: function(scope, element, attrs, ngModelCtrl) {
      if(!ngModelCtrl) {
        return; 
      }

      ngModelCtrl.$parsers.push(function(val) {
        if (angular.isUndefined(val)) {
            var val = '';
        }
        var clean = val.replace(/[^0-9]+/g, '');
        var current_year = new Date().getFullYear();
        if (val !== clean) {
        	ngModelCtrl.$setViewValue(clean);
            ngModelCtrl.$render();
        }
        return clean;
      });

      element.bind('keypress', function(event) {
        if(event.keyCode === 32) {
          event.preventDefault();
        }
      });
    }
  };
})


.directive('decreeLowConcat', function(Utils , BASE_CONSTS) {
	
	 return{
		 	restrict : 'E',
			replace : true,
			 scope: {
	    	       decreeNumber : '=decreeNumber',
	    	       decreeYear 	: '=decreeYear',
	    	       decreeType	: '=decreeType',
		       },
		            
	    link : function(scope, element, attrs , ngModel){
				 scope.$watch('decreeNumber', function(value) {
					 if(!Utils.isEmpty(scope.decreeType) && !Utils.isEmpty(scope.decreeNumber) && !Utils.isEmpty(scope.decreeYear)){
				    		var text = scope.decreeType + BASE_CONSTS.NUMBER +' ( '+scope.decreeNumber+' ) '+ BASE_CONSTS.YEAR + scope.decreeYear;
					    	 element.html(text);
				    	}else{
				    		element.html('');
				    	}
				});

				scope.$watch('decreeYear', function(value) {
					if(!Utils.isEmpty(scope.decreeType) && !Utils.isEmpty(scope.decreeNumber) && !Utils.isEmpty(scope.decreeYear)){
			    		var text = scope.decreeType + BASE_CONSTS.NUMBER +' ( '+scope.decreeNumber+' ) '+ BASE_CONSTS.YEAR + scope.decreeYear;
				    	 element.html(text);
			    	}else{
			    		element.html('');
			    	}
				});

				scope.$watch('decreeType', function(value) {
					if(!Utils.isEmpty(scope.decreeType) && !Utils.isEmpty(scope.decreeNumber) && !Utils.isEmpty(scope.decreeYear)){
			    		var text = scope.decreeType + BASE_CONSTS.NUMBER +' ( '+scope.decreeNumber+' ) '+ BASE_CONSTS.YEAR + scope.decreeYear;
				    	 element.html(text);
			    	}else{
			    		element.html('');
			    	}
				});
		  },
	 	};
})


.directive('ngConfirmClick', [
        function(BASE_CONSTS){
            return {
                link: function (scope, element, attr) {
                    var msg = attr.ngConfirmClick || BASE_CONSTS.ARE_YOU_SURE;
                    var clickAction = attr.confirmedClick;
                    element.bind('click',function (event) {
                        if (window.confirm(msg)) {
                        	Logger.info('hhhhhhhhh');
                            scope.$eval(clickAction);
                        }
                    });
                }
            };
}])


.directive('compileHtml', function ($compile) {
  return function (scope, element, attrs) {
    scope.$watch(
      function(scope) {
        return scope.$eval(attrs.compileHtml);
      },
      function(value) {
        element.html(value);
        $compile(element.contents())(scope);
      }
    );
  };
})


 .directive('stFilteredCollection', function() {
      return {
        restrict: 'A',
        require: '^stTable',
        scope: {
          stFilteredCollection: '='
        },
        controller: 'stTableController',
        link: function(scope, element, attr, ctrl) {

          scope.$watch(function() {
            return ctrl.getFilteredCollection();
          }, function(newValue, oldValue) {
            scope.stFilteredCollection = ctrl.getFilteredCollection();
          });
        }
      };
})


 .directive('stIndexGet', function(Utils) {
      return {
        restrict: 'A',
        require: '^stTable',
        scope: {
        	stCorrectIndex: '=',
        },
        controller: 'stTableController',
        link: function(scope, element, attr, ctrl) {
        	 var list = scope.$eval(attr.stList);
        	 var row = scope.$eval(attr.stRow);
//        	 Logger.info('row : ' + JSON.stringify(row));
//        	 Logger.info('list : ' + JSON.stringify(list));
        	 var index = Utils.findInArray(list , "createdOn" ,row.createdOn);
        	 Logger.info(' index  : ' +  index);
        	 var pagination = ctrl.tableState().pagination;
        	 scope.currentPage = Math.floor(pagination.start / pagination.number) + 1;
//        	 Logger.info(' scope.currentPage  : ' +  scope.currentPage);
//        	 scope.stCorrectIndex = (index) + (scope.currentPage - 1 ) * pagination.numberOfPages;
        	 scope.stCorrectIndex = (index) ;
        	 Logger.info(' scope.stCorrectIndex  : ' +  scope.stCorrectIndex);
        	 return scope.stCorrectIndex;
        }
      };
})

.directive("inputDisabled", function(){
  return function(scope, element, attrs){
    scope.$watch(attrs.inputDisabled, function(val){
      if(val)
    	  element.attr("disabled", "disabled");
      else
    	  element.removeAttr("disabled");
    });
  }
})

.directive('scrollTo', function () {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            element.on('click', function () {
                var target = $(attrs.scrollTo);
                console.log('target : ' + target);
                if (target.length > 0) {
                    $('html, body').animate({
                        scrollTop: target.offset().top
                    });
                }
            });
        }
    }
});    