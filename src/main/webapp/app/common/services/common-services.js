/**
 * 
 * GBS Common Services 
 */
angular.module('common-services',['ngCookies'])



/**
 * Util service contains varity of methods
 */
.service('Utils', function($filter , BASE_CONSTS) {
	var EMAIL_REGEXP = /^[_a-z0-9]+(\.[_a-z0-9]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$/;
	return {
		isEmpty : function(value) {
//			 console.log('value : ' + value);
			if (value == 'undefined' || value == null)
				return true;
			if (typeof value === 'string') {
				if (value == null || value == '' || value.length == 0)
					return true;
				else
					return false;
			} else if (typeof str === 'object') {
				if (value == null || value == '' || value.length == 0)
					return true;
				else
					return false;
			}
		},
		
		isEmailValid : function(email){
			return EMAIL_REGEXP.test(email);
		},
		
		getCurrentDate : function(){
			 var currentDate = $filter('date')(new Date(), 'yyyy-MM-dd HH:mm:ss');
			 return currentDate;
		},
		getCurrentTimestamp : function(){
			 var currentDate = new Date().getTime();
			 return currentDate;
		},
		
		findInArray : function(arraytosearch, key, valuetosearch){
			  for (var i = 0; i < arraytosearch.length; i++) {
				if (arraytosearch[i][key] == valuetosearch) {
					return i;
				}
			}
			return null;
		} ,
		
		getSplitString:function(value ,splitter){
			return value.split(splitter);
		},
		
		getLocaleDate : function(){
//			var options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric', hour12: false ,hour:'numeric' , minute:'numeric', second:'numeric' };
//			options.timeZone = 'Asia/Bahrain';
//			options.timeZoneName = 'short';
//			return new Date().toLocaleDateString('en-US', options);
			
			return  moment.tz(new Date(), "Asia/Qatar").format('dddd, YYYY-MM-DD HH:mm:ss');
		} ,
		
		concatDecreeLow : function(decreeNumber , decreeYear , decreeType){
			var text = decreeType + BASE_CONSTS.NUMBER +' ( '+decreeNumber+' ) '+ BASE_CONSTS.YEAR + decreeYear;
			return text;
		},
		
		findInArrayByObject : function(arraytosearch, obj){
			 for (var i = 0; i < arraytosearch.length; i++) {
					if (arraytosearch[i] == obj) {
						return i;
					}
				}
				return null;
		}
		
	};
})


.service('AuthService', function($rootScope, $cookieStore , $http){
	return {
		init : function(){
			var self = this;
			if (self.isLoggedIn()){
	            $rootScope.loggedInUser = self.getCurrentUser();
	        }
		},
		
		isLoggedIn : function(){
			 return $cookieStore.get('userInfo') != null;
		},
		
		getCurrentUser : function(){
			var userInfoCookie = $cookieStore.get('userInfo');
//			console.log('userInfoCookie : ' + userInfoCookie);
			return userInfoCookie;
		},
	};
})

.service('DownloadService', function($rootScope, $cookieStore , $http){
	return {
		downloadBase64File : function(base64Data , contentType , filename){
			contentType = contentType || '';
			var sliceSize = 1024;
			var byteCharacters = atob(base64Data);
			var bytesLength = byteCharacters.length;
			var slicesCount = Math
					.ceil(bytesLength / sliceSize);
			var byteArrays = new Array(slicesCount);

			for (var sliceIndex = 0; sliceIndex < slicesCount; ++sliceIndex) {
				var begin = sliceIndex * sliceSize;
				var end = Math.min(begin + sliceSize,
						bytesLength);

				var bytes = new Array(end - begin);
				for (var offset = begin, i = 0; offset < end; ++i, ++offset) {
					bytes[i] = byteCharacters[offset]
							.charCodeAt(0);
				}
				byteArrays[sliceIndex] = new Uint8Array(bytes);
			}

			var blob = new Blob(byteArrays, {
				type : contentType
			});
//			var downloadLink = angular.element('<a></a>');
//			downloadLink.attr('href', window.URL
//					.createObjectURL(blob));
//			downloadLink.attr('download', filename);
//			downloadLink[0].click();
			
			
			 var anchor = angular.element('<a/>');
			    anchor.css({display: 'none'});
			    angular.element(document.body).append(anchor);
			    anchor.attr({
			        href: window.URL.createObjectURL(blob),
			        target: '_blank',
			        download: filename
			    })[0].click();
			    anchor.remove();
		},
	};
})

.service('Toast', function($rootScope, $cookieStore , $http , BASE_CONSTS){
	var short = 3000;
	var long = 6000;
	return {
		success :function(message , title , duration){
			var timeout = null;
			if(duration == BASE_CONSTS.SHORT){
				timeout = {timeOut: short};
			}else if(duration == BASE_CONSTS.LONG){
				timeout = {timeOut: long};
			}else{
				timeout = {timeOut: short};
			}
			toastr.success(message, title, timeout);
		},
		warning : function(message , title , duration){
			var timeout = null;
			if(duration == BASE_CONSTS.SHORT){
				timeout = {timeOut: short};
			}else if(duration == BASE_CONSTS.LONG){
				timeout = {timeOut: long};
			}else{
				timeout = {timeOut: short};
			}
			toastr.warning(message, title, timeout);
		},
		error : function(message , title , duration){
			var timeout = null;
			if(duration == BASE_CONSTS.SHORT){
				timeout = {timeOut: short};
			}else if(duration == BASE_CONSTS.LONG){
				timeout = {timeOut: long};
			}else{
				timeout = {timeOut: short};
			}
			toastr.error(message, title, timeout);
		}
	};
})

.service('ValidateService', function($rootScope, $cookieStore , $http , BASE_CONSTS , Utils){
	
	return {
		isValidCprNumberLength : function(value){
			if(value!= null && value.toString().length == BASE_CONSTS.CPR_NUMBER_LENGTH)
				return true;
			else
				return false;
		},
		
		isEndDateBeforeStartDate : function(startDate , endDate){
			var start = new Date(startDate);
			var end = new Date(endDate);
			if(end < start)
				return true;
			else
				return false;
		},
		
		isDateBeforeToday : function(date){
			var now  = new Date();
			var currentDate = new Date(date);
			if(currentDate < now){
				return true;
			}
			return false;
		} , 
		
		isDateAfterToday : function(date){
			var now  = new Date();
			var currentDate = new Date(date);
			if(currentDate > now){
				return true;
			}
			return false;
		} , 
		
		isHijryDateAfterToday  :function(hijryDate){
			var splitHijry = Utils.getSplitString(hijryDate , '-');
			var now  = new Date();
			var jd = $.calendars.instance('islamic').newDate(
					parseInt(splitHijry[0].trim(), 10),//year
					parseInt(splitHijry[1].trim(), 10),//month
					parseInt(splitHijry[2].trim(), 10)).toJD(); 
			
			var convertedGregorianDate = $.calendars.instance("gregorian").fromJD(jd); // gregorian
			var selected  = new Date(convertedGregorianDate);
			if(selected > now){
				return true;
			}
			return false;
		},
		
		isMemberEndDateAfterBoardEndDate : function(memberEndDate , BoardEndDate){
			var bEndDate = new Date(BoardEndDate);
			var mEndDate = new Date(memberEndDate);
			if(bEndDate < mEndDate || isNaN(bEndDate.getTime()) || isNaN(mEndDate.getTime()))
				return true;
			else
				return false;
		},
		
		isMemberStartDateBeforeBoardStartDate : function(memberStartDate , BoardStartDate){
			var bStartDate = new Date(BoardStartDate);
			var mStartDate = new Date(memberStartDate);
			if(mStartDate < bStartDate || isNaN(bStartDate.getTime()) || isNaN(mStartDate.getTime()))
				return true;
			else
				return false;
		},
		
		isBoardStartDateBeforeDecreeMelady : function(decreeMeladyDate , BoardStartDate){
			
			var bStartDate = new Date(BoardStartDate);
			var dMeladyDate = new Date(decreeMeladyDate);
			if(bStartDate < dMeladyDate || isNaN(bStartDate.getTime()) || isNaN(dMeladyDate.getTime()))
				return true;
			else
				return false;
		}
	};
})



.service('DecreeService', function($rootScope, $cookieStore , $http , BASE_CONSTS , $q , BASE_RESOURCES , Utils , Toast){
	return {
		isDecreeExistInList : function(decree , list , existIndex){
			var decreeList = list.slice();
			if(!Utils.isEmpty(existIndex)){
				decreeList.splice(existIndex , 1);
			}
			
			var found = false;
			for(var i in decreeList){
				if(decree.number == decreeList[i].number && decree.year == decreeList[i].year && decree.type.id == decreeList[i].type.id){
					found = true;
					break;
				}
			}
			if(found)
				return true;
			else 
				return false;
		},
		
		isDecreeYearValid : function(year){
			var now  = new Date();
			var thisYear = now.getFullYear();
			if(year > thisYear)
				return false;
			else
				return true;
		},
		
		isDecreeExists : function(decree){
			var deferred = $q.defer();
			$http.get(BASE_RESOURCES.IS_DECREE_EXISTS_URL,{params: { decreeTypeId: decree.type.id , decreeNumber : decree.number , decreeYear : decree.year }})
			.success(function(data, status, headers, config) {
		        var response = data;
		        deferred.resolve(response);
		     }).error(function(data, status, headers, config) {
		    	 deferred.reject(data);
		     });
		 	return deferred.promise;
		} , 
		
		findInDecree : function (decree , list){
			for(var i in list){
				if(decree.attachmentType.show == 1){
					if(decree.number == list[i].number && decree.year == list[i].year && decree.type.id == list[i].type.id){
						return i;
					}
				}else{
					if(decree.attachment.name.toLowerCase() == list[i].attachment.name.toLowerCase() 
							&& decree.attachment.type == list[i].attachment.type 
							&& decree.attachment.size == list[i].attachment.size){
						return i;
					}
				}
				
			}
			return null;
		},
		
		isOnlyOneDecreeExist : function (list){
			var decreeList = list.slice();
			var found = false;
			for(var i in decreeList){
				var decree = decreeList[i];
				if(decree.attachmentType.show == 1 && (decree.decreeId == undefined  || decree.decreeId == null)){
					if(decree.newOne == undefined || decree.newOne == true){
						found = true;
						break;	
					}
				}
			}
			if(found)
				return true;
			else 
				return false;
		} , 
		
		getDecreesByBoardId : function(boardId){
			var deferred = $q.defer();
			var resourceURL = BASE_RESOURCES.GET_DECREES_BY_BOARD_ID;
			resourceURL = resourceURL.replace('{boardId}' , encodeURIComponent(boardId));
			Logger.info('resourceURL : ' + resourceURL);
			$http.get(resourceURL)
			.success(function(data, status, headers, config) {
		        var response = data;
		        deferred.resolve(response);
		     }).error(function(data, status, headers, config) {
		    	 Toast.error(BASE_CONSTS.DECREE_NOT_FOUND_ERROR, '', BASE_CONSTS.LONG);
		    	 deferred.reject(data);
		     });
		 	return deferred.promise;
		} , 
		
		getAttachmentType : function(attachmentTypeId){
			
			var attachmentType = null;
			if(!Utils.isEmpty(attachmentTypeId)){
			// check is boardAttachmentType
				if(!Utils.isEmpty(Utils.findInArray($rootScope.lookupMap.ATTACHMENT_TYPES , 'id' , attachmentTypeId))){
					attachmentType = $rootScope.lookupMap.ATTACHMENT_TYPES[Utils.findInArray($rootScope.lookupMap.ATTACHMENT_TYPES , 'id' , attachmentTypeId)];
				}else{
					// Get memberAttachmentType
					attachmentType = $rootScope.lookupMap.MEMBER_ATTACHMENT_TYPES[Utils.findInArray($rootScope.lookupMap.MEMBER_ATTACHMENT_TYPES , 'id' , attachmentTypeId)];
				}
			}
			
			return attachmentType;
		} ,
		
		isDecreeValidYear : function(decreeYear){
			var current_year = new Date().getFullYear();
			var clean = decreeYear.replace(/[^0-9]+/g, '');
			if((!Utils.isEmpty(clean)) && (clean.length != 4 || clean > current_year)){
				return false;
			}
			return true;
		}
	
	}
	
})

.service('BoardService', function($rootScope, $cookieStore , $http , BASE_CONSTS , $q , BASE_RESOURCES , Utils,Toast){
	
	return {
		
		searchBoard : function (search){
			var deferred = $q.defer();
			var resourceURL =BASE_RESOURCES.SERACH_BOARD_URL;
			resourceURL = resourceURL.replace('{text}' , encodeURIComponent(search));
			Logger.info('resourceURL : ' + resourceURL);
			$http.get(resourceURL)
			.success(function(data, status, headers, config) {
		        var response = data;
		        deferred.resolve(response);
		     }).error(function(data, status, headers, config) {
		    	 deferred.reject(data);
		     });
		 	return deferred.promise;
		} ,
		
		
		isBoardNameExist : function(boardName , boardTypeId){
			var deferred = $q.defer();
			$http.get(BASE_RESOURCES.IS_BOARD_NAME_EXISTS_URL,{params: { boardName:encodeURIComponent(boardName) , boardTypeId : boardTypeId  }})
			.success(function(data, status, headers, config) {
		        var response = data;
		        deferred.resolve(response);
		     }).error(function(data, status, headers, config) {
		    	 deferred.reject(data);
		     });
		 	return deferred.promise;
		} ,
		
		isAllSearchFieldsEmpty : function(obj){
			
			if(Utils.isEmpty(obj.governmentBoardType) && Utils.isEmpty(obj.boardName) && Utils.isEmpty(obj.sourceOrganization) &&  Utils.isEmpty(obj.decreeNumber) &&  Utils.isEmpty(obj.decreeYear) 
					&& Utils.isEmpty(obj.decreeType) && Utils.isEmpty(obj.startDateFrom) && Utils.isEmpty(obj.startDateTo) && Utils.isEmpty(obj.endDateFrom) && Utils.isEmpty(obj.endDateTo) 
					&& Utils.isEmpty(obj.destinationOrganization) && Utils.isEmpty(obj.status)){
				return true;
			}else{
				return false;
			}
		} , 
		
		getBoardById : function(boardId){
			var deferred = $q.defer();
			var resourceURL =BASE_RESOURCES.GET_BOARD_BY_BOARD_ID;
			resourceURL = resourceURL.replace('{boardId}' , encodeURIComponent(boardId));
			Logger.info('resourceURL : ' + resourceURL);
			$http.get(resourceURL)
			.success(function(data, status, headers, config) {
		        var response = data;
		        deferred.resolve(response);
		     }).error(function(data, status, headers, config) {
		    	 Toast.error(BASE_CONSTS.GENERAL_ERROR, '', BASE_CONSTS.LONG);
		    	 deferred.reject(data);
		     });
		 	return deferred.promise;
		},
		
		getBoardLogDetails : function(boardId){
			var deferred = $q.defer();
			var resourceURL =BASE_RESOURCES.GET_BOARD_LOG_DETAILS_BY_BOARD_ID;
			resourceURL = resourceURL.replace('{boardId}' , encodeURIComponent(boardId));
			Logger.info('resourceURL : ' + resourceURL);
			$http.get(resourceURL)
			.success(function(data, status, headers, config) {
		        var response = data;
		        deferred.resolve(response);
		     }).error(function(data, status, headers, config) {
		    	 Toast.error(BASE_CONSTS.GENERAL_ERROR, '', BASE_CONSTS.LONG);
		    	 deferred.reject(data);
		     });
		 	return deferred.promise;
		},
		
		isNotificationDateValid : function(notificationBefore , endDate){
			var self = this;
			var workingDays = self.getActualDaysFromWorkingDays(notificationBefore);
			var end = new Date(endDate);
			var today = new Date();
			var notificationDate = new Date();
			notificationDate.setTime(end.getTime() - ((24*60*60*1000)*Number(workingDays)));
			if(notificationDate < today ){
				return false;
			}else
				return true;
		} , 
		
		getNotificationDate : function(notificationBefore , endDate){
			var self = this;
			var workingDays = self.getActualDaysFromWorkingDays(notificationBefore);
			var end = new Date(endDate);
			var today = new Date();
			var notificationDate = new Date();
			notificationDate.setTime(end.getTime() - ((24*60*60*1000)*Number(workingDays)));
			
			return moment.tz(notificationDate, "Asia/Qatar").format('DD-MM-YYYY');
		} , 
		
		getActualDaysFromWorkingDays : function (notificationBefore){
			var workingDaysInWeek = 5;
			var weekInDays = 7;
			var numberOfWeeks = notificationBefore / workingDaysInWeek;
			numberOfWeeks  = Math.floor(numberOfWeeks);
			var restOfDays = notificationBefore % workingDaysInWeek;
			var NumberOfDays = (numberOfWeeks * weekInDays) + restOfDays;
			return NumberOfDays;
		}
	};
})


.service('MemberService', function($rootScope, $cookieStore , $http , BASE_CONSTS , $q , Utils , BASE_RESOURCES,Toast){
	
	return {
		
		isMemberNameExist : function(memberName , list , existIndex){
			var memberList = list.slice();
			if(!Utils.isEmpty(existIndex)){
				memberList.splice(existIndex , 1);
			}
			
			var found = false;
			for(var i in memberList){
				if(memberName.toLowerCase() == memberList[i].name.toLowerCase()){
					found = true;
					break;
				}
			}
			if(found)
				return true;
			else 
				return false;
		} ,
		
		isMemberCprNumberExist : function(cprNumber , list , existIndex){
			
			var memberList = list.slice();
			if(!Utils.isEmpty(existIndex)){
				memberList.splice(existIndex , 1);
			}
			
			var found = false;
			for(var i in memberList){
				if(cprNumber == memberList[i].cprNumber){
					found = true;
					break;
				}
			}
			if(found)
				return true;
			else 
				return false;
		} ,
		
		isCprNumberValid : function(cprNumber){
			var resourceURL =BASE_RESOURCES.IS_CPR_NUMBER_VALID;
			resourceURL = resourceURL.replace('{cpr}' , cprNumber);
//			Logger.info('resourceURL : ' + resourceURL);
			var deferred = $q.defer();
			$http.get(resourceURL)
			.success(function(data, status, headers, config) {
		        var response = data;
		        deferred.resolve(response);
		     }).error(function(data, status, headers, config) {
		    	 deferred.reject(data);
		     });
		 	return deferred.promise;
		} , 
		
		isMemberNameAndCprNumberEntered : function(memberName , cprNumber){
			if(Utils.isEmpty(memberName) && !Utils.isEmpty(cprNumber)){
				return false;
			}else if(!Utils.isEmpty(memberName) && Utils.isEmpty(cprNumber)){
				return false;
			}else {
				// no validation required 
				return true;
			}
		},
		
		isMemberTitleAndOrganizationEntered : function (memberTitle , organization){
			if(Utils.isEmpty(memberTitle) && !Utils.isEmpty(organization)){
				return false;
			}else if(!Utils.isEmpty(memberTitle) && Utils.isEmpty(organization)){
				return false;
			}else {
				// no validation required 
				return true;
			}
		} , 
		
		isMemberRoleExists : function(role , list , existIndex){
			var memberList = list.slice();
			if(!Utils.isEmpty(existIndex)){
				memberList.splice(existIndex , 1);
			}
			
			var found = false;
			for(var i in memberList){
				if(!Utils.isEmpty(memberList[i].role)){
					if(role.id == memberList[i].role.id){
						found = true;
						break;
					}
				}
			}
			if(found)
				return true;
			else 
				return false;
		} , 
		searchMember : function (search){
			var deferred = $q.defer();
			var resourceURL = BASE_RESOURCES.SERACH_MEMBER_URL;
			resourceURL = resourceURL.replace('{text}' , encodeURIComponent(search));
			Logger.info('resourceURL : ' + resourceURL);
			$http.get(resourceURL)
			.success(function(data, status, headers, config) {
		        var response = data;
		        deferred.resolve(response);
		     }).error(function(data, status, headers, config) {
		    	 deferred.reject(data);
		     });
		 	return deferred.promise;
		},
		
		isAllMemberFieldsEmpty : function(obj){
			if(Utils.isEmpty(obj.cprNumber) && Utils.isEmpty(obj.name) && Utils.isEmpty(obj.occupation) &&  Utils.isEmpty(obj.organization) &&  Utils.isEmpty(obj.role) 
					&& Utils.isEmpty(obj.organizaionOnBehalf) && Utils.isEmpty(obj.startDate) && Utils.isEmpty(obj.endDate)){
				return true;
			}else{
				return false;
			}
		} ,
		
		isAllSearchFieldsEmpty : function(obj){
			
			if(Utils.isEmpty(obj.cprNumber) && Utils.isEmpty(obj.memberName) && Utils.isEmpty(obj.organization) &&  Utils.isEmpty(obj.role) &&  Utils.isEmpty(obj.organizaionOnBehalf) 
					&& Utils.isEmpty(obj.startDateFrom) && Utils.isEmpty(obj.startDateTo) && Utils.isEmpty(obj.endDateFrom) && Utils.isEmpty(obj.endDateTo) 
					&& Utils.isEmpty(obj.occupation) && Utils.isEmpty(obj.status)){
				return true;
			}else{
				return false;
			}
		} ,
		
		getMembersByBoardId : function(boardId){
			var deferred = $q.defer();
			var resourceURL = BASE_RESOURCES.GET_MEMBERS_BY_BOARD_ID;
			resourceURL = resourceURL.replace('{boardId}' , encodeURIComponent(boardId));
			Logger.info('resourceURL : ' + resourceURL);
			$http.get(resourceURL)
			.success(function(data, status, headers, config) {
		        var response = data;
		        deferred.resolve(response);
		     }).error(function(data, status, headers, config) {
		    	 Toast.error(BASE_CONSTS.GENERAL_ERROR, '', BASE_CONSTS.LONG);
		    	 deferred.reject(data);
		     });
		 	return deferred.promise;
		},
		getMemberLogDetails : function(memberId){
			var deferred = $q.defer();
			var resourceURL =BASE_RESOURCES.GET_MEMBER_LOG_DETAILS_BY_MEMBER_ID;
			resourceURL = resourceURL.replace('{memberId}' , encodeURIComponent(memberId));
			Logger.info('resourceURL : ' + resourceURL);
			$http.get(resourceURL)
			.success(function(data, status, headers, config) {
		        var response = data;
		        deferred.resolve(response);
		     }).error(function(data, status, headers, config) {
		    	 Toast.error(BASE_CONSTS.GENERAL_ERROR, '', BASE_CONSTS.LONG);
		    	 deferred.reject(data);
		     });
		 	return deferred.promise;
		},
		
		isNotificationDateValid : function(notificationBefore , endDate){
			var self = this;
			var workingDays = self.getActualDaysFromWorkingDays(notificationBefore);
			var end = new Date(endDate);
			var today = new Date();
			var notificationDate = new Date();
			notificationDate.setTime(end.getTime() - ((24*60*60*1000)*Number(workingDays)));
			if(notificationDate < today ){
				return false;
			}else
				return true;
		} , 
		
		getNotificationDate : function(notificationBefore , endDate){
			var self = this;
			var workingDays = self.getActualDaysFromWorkingDays(notificationBefore);
			var end = new Date(endDate);
			var today = new Date();
			var notificationDate = new Date();
			notificationDate.setTime(end.getTime() - ((24*60*60*1000)*Number(workingDays)));
			return moment.tz(notificationDate, "Asia/Qatar").format('DD-MM-YYYY');
		} , 
		
		getActualDaysFromWorkingDays : function (notificationBefore){
			var workingDaysInWeek = 5;
			var weekInDays = 7;
			var numberOfWeeks = notificationBefore / workingDaysInWeek;
			numberOfWeeks  = Math.floor(numberOfWeeks);
			var restOfDays = notificationBefore % workingDaysInWeek;
			var NumberOfDays = (numberOfWeeks * weekInDays) + restOfDays;
			return NumberOfDays;
		}
		
	};
})


.service('modalService', ['$modal', function ($modal) {

    var modalDefaults = {
        backdrop: true,
        keyboard: true,
        modalFade: true,
        templateUrl: './app/common/services/templates/confirm-modal.html'
    };

    var modalOptions = {
        closeButtonText: 'Close',
        actionButtonText: 'OK',
        headerText: 'Proceed?',
        bodyText: 'Perform this action?'
    };

    this.showModal = function (customModalDefaults, customModalOptions) {
        if (!customModalDefaults) customModalDefaults = {};
        customModalDefaults.backdrop = 'static';
        return this.show(customModalDefaults, customModalOptions);
    };

    this.show = function (customModalDefaults, customModalOptions) {
        //Create temp objects to work with since we're in a singleton service
        var tempModalDefaults = {};
        var tempModalOptions = {};

        //Map angular-ui modal custom defaults to modal defaults defined in service
        angular.extend(tempModalDefaults, modalDefaults, customModalDefaults);

        //Map modal.html $scope custom properties to defaults defined in service
        angular.extend(tempModalOptions, modalOptions, customModalOptions);

        if (!tempModalDefaults.controller) {
            tempModalDefaults.controller = function ($scope, $modalInstance) {
                $scope.modalOptions = tempModalOptions;
                $scope.modalOptions.ok = function (result) {
                    $modalInstance.close(result);
                };
                $scope.modalOptions.close = function (result) {
                    $modalInstance.dismiss('cancel');
                };
            }
        }

        return $modal.open(tempModalDefaults).result;
    };

}])


.service('ExportService', function($rootScope, $cookieStore , $http){
	return {
		exportExcel : function(ReportTitle , JSONData , ShowLabel , headerMap){
			var arrData = typeof JSONData != 'object' ? JSON.parse(JSONData) : JSONData;
//			Logger.info('arrData : ' + JSON.stringify(arrData));
		    
		    var CSV = '';    
		    //Set Report title in first row or line
		    
		    CSV += ReportTitle + '\r\n\n';

		    //This condition will generate the Label/Header
		    if (ShowLabel) {
		        var row = "";
		        
		        //This loop will extract the label from 1st index of on array
		        for (var index in arrData[0]) {
		        	
//		        	console.log('index : ' + index);
		            
		            //Now convert each value to string and comma-seprated
		            row += headerMap[index] + ',';
		        }

		        row = row.slice(0, -1);
		        
		        //append Label row with line break
		        CSV += row + '\r\n';
		    }
		    
//		    Logger.info('Haeder : ' +CSV);
		    
		    //1st loop is to extract each row
		    for (var i = 0; i < arrData.length; i++) {
		        var row = "";
		        
		        //2nd loop will extract each column and convert it in string comma-seprated
		        for (var index in arrData[i]) {
		            row += '"' + arrData[i][index] + '",';
		        }

		        row.slice(0, row.length - 1);
		        
		        //add a line break after each row
		        CSV += row + '\r\n';
		    }

		    if (CSV == '') {        
		        Logger.info('Invalid data');
		        return;
		    }  
		    
		    
//		    Logger.info('Data : ' +CSV);
		    
		    //Generate a file name
		    var fileName = "";
		    //this will remove the blank-spaces from the title and replace it with an underscore
		    fileName += ReportTitle.replace(/ /g,"_");   
		    
		    //Initialize file format you want csv or xls
		    var uri = 'data:text/csv;charset=utf-8,%EF%BB%BF' + encodeURIComponent(CSV);
		    
		    // Now the little tricky part.
		    // you can use either>> window.open(uri);
		    // but this will not work in some browsers
		    // or you will not get the correct file extension    
		    
		    //this trick will generate a temp <a /> tag
		    
		    var anchor = angular.element('<a/>');
		    anchor.css({display: 'none'});
		    angular.element(document.body).append(anchor);
		    anchor.attr({
		        href: uri,
		        target: '_blank',
		        download: fileName + ".csv"
		    })[0].click();
		    anchor.remove();
		    
//		    var downloadLink = angular.element('<a></a>');
//		    downloadLink.attr('href', uri);
//			downloadLink.attr('download', fileName + ".csv");
//			downloadLink[0].click();
//			downloadLink.remove();
		},
	};
})

	   