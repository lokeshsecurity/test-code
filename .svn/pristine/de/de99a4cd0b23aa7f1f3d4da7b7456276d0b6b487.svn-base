var userRoleMap = new Object();
var smartApp = angular.module('gbs-app', [ 'common-services' , 'common-directives' , 'common-filters' , 'ui.router', 'ui.bootstrap','ngAnimate',
		'app.controllers', 'app.demoControllers','gbs.controllers' ,'gbs.main.controllers' , 'gbs.search.controllers' , 'gbs.search.memeber.controllers' ,'gbs.notification.controllers' , 'gbs.update.controllers' ,'ngRoute', 'app.main',
		'app.navigation', 'app.localize', 'app.activity', 'app.smartui',
		'ncy-angular-breadcrumb', 'blockUI', 'oitozero.ngSweetAlert',
		'ui.bootstrap.module', 'ngSanitize', 'ui.select', 'smart-table',
		'ui.grid' , 'ngCookies' , 'common-lookup-directives' , 'lookup-services' , 'common.directives.file.model' ,'common.directive.attachment' , 'naif.base64' , 'common.directive.decreeTable' , 'common.directive.memberTable']);

smartApp.config(function($stateProvider, $urlRouterProvider , BASE_CONSTS) {
	
	$stateProvider
					
			.state("home", {
						url : "/",
						templateUrl : "./app/menu.html",
						ncyBreadcrumb : {
							label : 'الرئيسيه'
						}
			})
			
			
			.state("search", {
						url : "/search",
						templateUrl : "./app/search/board/templates/board-search-tpl.html",
						controller  : 'searchBoardController',
						ncyBreadcrumb : {
							label : BASE_CONSTS.SEARCH_BOARD_TITLE
						}
			})
			
			.state("membersearch", {
						url : "/search/member",
						templateUrl : "./app/search/member/templates/member-search-tpl.html",
						controller  : 'searchMemberController',
						ncyBreadcrumb : {
							label : BASE_CONSTS.SEARCH_MEMBER_TITLE
						}
			})
			
			.state("board", {
						url : "/board",
						templateUrl : "./app/main/templates/board_managment_tpl.html",
						controller : "BoardManagmentController",
						ncyBreadcrumb : {
							label : BASE_CONSTS.CREATE_BOARD_TITLE
						}
			})
			
			.state("updateBoard", {
						url : "/board/update/:boardId",
						templateUrl : "./app/update/templates/update_board_managment_tpl.html",
						controller : "UpdateBoardManagmentController",
						ncyBreadcrumb : {
							label : BASE_CONSTS.UPDATE_BOARD_TITLE
						},
						resolve: {
							boardData: function(BoardService , $stateParams){
				            	Logger.info('boardResponse - BoardId : ' + $stateParams.boardId);
				            	var id = $stateParams.boardId;
				                return BoardService.getBoardById(id);
				            },
				            members : function(MemberService , $stateParams){
				            	Logger.info('memberList - BoardId : ' + $stateParams.boardId);
				            	var id = $stateParams.boardId;
				            	return MemberService.getMembersByBoardId(id);
				            },
				            decrees : function(DecreeService , $stateParams){
				            	Logger.info('decreeList - BoardId : ' + $stateParams.boardId);
				            	var id = $stateParams.boardId;
				            	return DecreeService.getDecreesByBoardId(id);
				            }
				            
				        }
			})
			

			$urlRouterProvider.otherwise("/search");
		});

smartApp.controller('SmartAppController',function($rootScope, $scope, $state, $http , AuthService , LookupService){
	  // security 
	$scope.getUserRoles = function(){
  		var userRolesUrl = 'api/login/userRoles';
  		 $http.get(userRolesUrl)
		    .success(function(data, status, headers, config) {
//		    	 console.log('getUserRoles == success : '+ JSON.stringify(data));
		    	$.each(data, function(idx, obj) {
					userRoleMap[obj.name] = true; 
				});
//		    	console.log('getUserRoles == userRoleMap : '+ JSON.stringify(userRoleMap));
		    	$rootScope.userRoles = userRoleMap;
		  }).error(function(data, status, headers, config) {
		    	 console.log('error : '+ JSON.stringify(data));
		    	
		  });
  	};
  	
  	// for init all lookups used in APP
  	LookupService.initLookups();
	
	$scope.isUserInRole = function(rolename){
		if(AuthService.isLoggedIn()){
			if(userRoleMap[rolename]==true){
	  			return true;
	  		}else{
	  			return false;
	  		}
		}else{
			return false;
		}
	};
	
	$scope.getUserRoles();
});




smartApp.run(['$rootScope','$state','$http','settings','$location', '$cookieStore', 'AuthService','Utils' ,'$templateCache' , function($rootScope, $state, $http, settings , $location , $cookieStore , AuthService , Utils , $templateCache) {
	
		Logger.setLevel(Logger.OFF);
		Logger.useDefaults({
			    formatter: function (messages, context) {
			    	var date = Utils.getLocaleDate();
//			    	var date = new Date().toUTCString();
			        messages.unshift(date);
			    }
			});
		 settings.currentLang = settings.languages[0]; // en
		 AuthService.init();
		 if(AuthService.isLoggedIn()){
			 Logger.info('loggedInUser : ' + $rootScope.loggedInUser);
//			 $location.path('/search'); // resolve issue GBS-119
		 }else{
			    console.log('not loggedUser');
			    $(location).attr('href', '/gbs/login.html');
		 }
		 
		 // for smart table pagination
		  $templateCache.put('template/smart-table/pagination.html','<div class="pagination" ng-if="pages.length >= 2">'
								+ '<ul class="pagination">'
								+ '<li ng-if="currentPage > 1">'
								+ '<a ng-click="selectPage(1)">&lt;&lt;</a>'
								+ '</li>'
								+ '<li ng-if="currentPage > 1">'
								+ '<a ng-click="selectPage(currentPage-1)">&lt;</a>'
								+ '</li>'
								+ '<li ng-repeat="page in pages" ng-class="{active: page==currentPage}"><a ng-click="selectPage(page)">{{page}}</a>'
								+ '</li>'
								+ '<li ng-if="currentPage < numPages"><a ng-click="selectPage(currentPage+1)">></a>'
								+ '</li>'
								+ '<li ng-if="currentPage < numPages">'
								+ '<a ng-click="selectPage(numPages)">>></a>'
								+ '</li>' + '</ul>' + '</div>');
		
}]);
