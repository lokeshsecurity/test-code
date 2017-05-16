	

var calendarModule = angular.module("calendarModule", []);
	

	function calendarController($scope, $http) {
		var visitMap = new Object();
		
		getResearcherVisits($scope, $http);	

		$scope.addVisitToMap = function (event){
			if(event.id == $scope.task.request.socialResearcherVisit.socialResearcherVisitId){
				$scope.task.request.socialResearcherVisit.visitDate = event.start;
			}
			visitMap[event.id] = event.start;
			$scope.allVisitsMap = visitMap;
		}
		
	}
	

	calendarModule.controller("calendarController", calendarController);


	// DO NOT REMOVE : GLOBAL FUNCTIONS!

	//$(document).ready(function() {
	function iniCalendar(data,$scope, $http) {
		var currentDate = new Date("2016-03-6");
		pageSetUp();
	
		"use strict";
	
		var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();
		var test = new Object();
		var hdr = {
			left : 'title',
			center : 'month,agendaWeek,agendaDay',
			right : 'prev,today,next'
		};
	
		var initDrag = function(e) {
			// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
			// it doesn't need to have a start or end
			var eventObject = {
				title : $.trim(e.children().text()), // use the element's text as the event title
				description : $.trim(e.children('span').attr('data-description')),
				icon : $.trim(e.children('span').attr('data-icon')),
				className : $.trim(e.children('span').attr('class')),
				id		: $.trim(e.children('span').attr('data-id'))
				
			// use the element's children as the event class
			};
			// store the Event Object in the DOM element so we can get to it later
			e.data('eventObject', eventObject);
	
			// make the event draggable using jQuery UI
			e.draggable({
				zIndex : 999,
				revert : true, // will cause the event to go back to its
				revertDuration : 0
			//  original position after the drag
			});
		};
	
		/* initialize the external events
		 -----------------------------------------------------------------*/
	
		$('#external-events > li').each(function() {
			initDrag($(this));
		});
	
		/* initialize the calendar
		 -----------------------------------------------------------------*/
	
		$('#calendar')
				.fullCalendar(
						{
							
							header : hdr,
							buttonText : {
								prev : '<i class="fa fa-chevron-left"></i>',
								next : '<i class="fa fa-chevron-right"></i>'
							},
	
							defaultView: 'month',
						    
							editable : true,
							droppable : true, // this allows things to be dropped onto the calendar !!!
							drop : function(date, allDay) { // this function is called when something is dropped
	
								// retrieve the dropped element's stored Event Object
								var originalEventObject = $(
										this).data(
										'eventObject');
	
								// we need to copy it, so that multiple events don't have a reference to the same object
								var copiedEventObject = $
										.extend({},
												originalEventObject);
	
								// assign it the date that was reported
								copiedEventObject.start = date;
								copiedEventObject.allDay = allDay;
									// alert('logging dropped object start date:'+ copiedEventObject.start);
								// render the event on the calendar
								// the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
								$('#calendar')
										.fullCalendar(
												'renderEvent',
												copiedEventObject,
												true);
								
								// always "remove after drop" 
								$(this).remove();
	
							},

							/* This constrains it to today or later */
				            eventConstraint: {
				                start:  moment().format('YYYY-MM-DD'),
				                end: '2100-01-01' // hard coded for testing 
				            },
							events : $
									.map(
											data,
											function(item, i) {
												var event = new Object();
												event.id = item.id;
												event.start = new Date(item.startDate);
												event.end = new Date(item.endDate);
												event.title = item.description;
												if(event.title == $scope.task.request.requestId){
													event.className = 'bg-color-darken';													
												}else{
													event.className = 'bg-color-blue';
												}
												return event;
											}),
							eventRender : function(event,element, icon) {
								
								if (!event.description == "") {
									element
											.find('.fc-event-title')
											.append("<br/><span class='ultra-light'>"+ event.description+ "</span>");
								}
								if (!event.icon == "") {
									element.find('.fc-event-title')
										   .append("<i class='air air-top-right fa " + event.icon + " '></i>");
								}

								$scope.addVisitToMap(event);

							},
	
							windowResize : function(event, ui) {
								$('#calendar').fullCalendar(
										'render');
							}
						});
 		/* hide default buttons */
		$('.fc-header-right, .fc-header-center').hide();

	} //end of iniCalendar
	
	
	$('#calendar-buttons #btn-prev').click(function() {
		$('.fc-button-prev').click();
		return false;
	});

	$('#calendar-buttons #btn-next').click(function() {
		$('.fc-button-next').click();
		return false;
	});

	$('#calendar-buttons #btn-today').click(function() {
		$('.fc-button-today').click();
		return false;
	});

	$('#mt').click(function() {
		$('#calendar').fullCalendar('changeView', 'month');
	});

	$('#ag').click(
			function() {
				$('#calendar').fullCalendar('changeView',
						'agendaWeek');
			});

	$('#td').click(
			function() {
				$('#calendar').fullCalendar('changeView',
						'agendaDay');
			});

					
					
					
	function getResearcherVisits($scope, $http){
		$.ajax({
			url : './social-researcher/visits/'+$scope.task.userName ,
			type : "GET",
			contentType : 'application/json; charset=utf-8',
			success : function(data) {
				iniCalendar(data,$scope, $http);
			},
			error : function() {
				console.log('calendar getResearcherVisits error');
			}
		});
	}
