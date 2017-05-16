angular.module('common.directives.file.model', [])
       .directive('cioFileModel',['$parse' , function($parse) {
    	   
    	   var directiveDefinitionObject = 
    		{
    			   restrict: 'A',
    		       link: function(scope, element, attrs) {
    		    	   element.bind('change', function(){
    		        	$parse(attrs.cioFileModel).assign(scope, element[0].files);
    		            scope.$apply();
    		           });
    		    	   
    		        }
    		   
    		};
    	   return directiveDefinitionObject;
     }]);
	
	
