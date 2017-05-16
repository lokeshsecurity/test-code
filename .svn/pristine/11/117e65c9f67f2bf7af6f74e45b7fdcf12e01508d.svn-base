/**
 * 
 */
package bh.gov.cio.gbs.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;



/**
 * @author CSDVZZE
 * @since  Jun 12, 2014
 * 
 */


public class DateUtil
	{
		  private static final String[] DATE_FORMATS = new String[] {"yyyy/MM/dd HH:mm:ss","yyyy/MM/dd","yyyy-MM-dd"};
		  
		  private static final long  DAY_IN_SECONDS = 1000 * 60 * 60 * 24;
		  
		  private static final long  HOUR_IN_SECONDS = 1000 * 60 * 60;
		  
		  private static final long  MINUTES_IN_SECONDS = 1000 * 60;
		  
		  private static final int  WEEK_IN_DAYS = 7;
		  
		  private static final int  DAYS_OFF_COUNT = 2;
		  
		  private static final int START_WORK_HOUR = 7;   //start working hour
		  private static final int END_WORK_HOUR = 14;    // end working hour
		  
		  private static final int WORKING_DAYS_IN_WEEK = 5;    // end working hour
		  

		
		/**
		 * @author CSDVZZE
		 * @since  Jun 12, 2014
		 * main
		 * @param args
		 * void
		 */
		public static void main(String[] args)
			{
				// TODO Auto-generated method stub
				Date DeliveryDate = getDate("2017/12/31");
				System.out.println("DeliveryDate : " + DeliveryDate );
				Integer actualDays=getActualDaysFromWorkingDays(90);
				System.out.println("actualDays : " + actualDays);
				System.out.println(getFirstActualDateOfDeliveryDate(DeliveryDate, actualDays));
//				System.out.println("getResponseActualDateOfDeliveryDate : " + getResponseActualDateOfDeliveryDate(DeliveryDate, actualDays));
				//Date date2 = getDate("2014/8/13 11:47:40");
			  //  System.out.println(getWorkingTimeDuration(date1, date2));
				

			}
		
      
		
		
		public static TimeDuration getWorkingTimeDuration(Date startDate,Date endDate)
			{
				TimeDuration workingTimeDuration = new TimeDuration();
				int noOfDays = getWorkingDays(startDate, endDate);
				
				double workingHoursRange = getWorkingHoursRange(startDate, endDate);
				int noOfHours = (int) workingHoursRange;
				int noOfMinutes = (int) ((workingHoursRange - noOfHours) * 100);
				final int WORKING_HOUR_PER_DAY = END_WORK_HOUR - START_WORK_HOUR;
				if(noOfDays > 1)
					noOfDays = noOfDays + (noOfHours / WORKING_HOUR_PER_DAY);
				else
					noOfDays = (noOfHours / WORKING_HOUR_PER_DAY);
				noOfHours = noOfHours %  WORKING_HOUR_PER_DAY;
				
				workingTimeDuration.setNoOfDays(noOfDays);
				workingTimeDuration.setNoOfHours(noOfHours);
				workingTimeDuration.setNoOfMinutes(noOfMinutes);
				
				return workingTimeDuration;
				
				
			}

	
		
		
		
		
		
		
		private static double getWorkingHoursRange(Date startDate,Date endDate)
			{
				double workingHoursRange = 0;
				
				int noOfMinutes = getDiffMinutes(startDate, endDate);
				int noOfDays = noOfMinutes / (24*60);
				int noOfHours = (noOfMinutes - (noOfDays*24*60)) / (60);
				int remainingMinutes =   noOfMinutes - (noOfDays*24*60) - (noOfHours*60);
				
				if(noOfDays == 0)
					{					
						workingHoursRange =  noOfHours + ((double)remainingMinutes) / 100; 
					}
				else
					{
						Calendar workingHoursCalendar = new GregorianCalendar();
						
						//Start Day Working Hour
						workingHoursCalendar.setTime(startDate);
						workingHoursCalendar.set(Calendar.HOUR_OF_DAY, END_WORK_HOUR);
						workingHoursCalendar.set(Calendar.MINUTE,0);
						workingHoursCalendar.set(Calendar.SECOND,0);
						int startDateWorkingMinutes = getDiffMinutes(startDate,workingHoursCalendar.getTime());

						
						//End Date Working Hour
						workingHoursCalendar.setTime(endDate);
						workingHoursCalendar.set(Calendar.HOUR_OF_DAY, START_WORK_HOUR);
						workingHoursCalendar.set(Calendar.MINUTE,0);
						workingHoursCalendar.set(Calendar.SECOND,0);
						int endDateWorkingMinutes = getDiffMinutes(workingHoursCalendar.getTime(),endDate);
						
						int totalWorkingMinutes = startDateWorkingMinutes + endDateWorkingMinutes;
						noOfHours = totalWorkingMinutes / 60;
						remainingMinutes = totalWorkingMinutes - (noOfHours*60);
						workingHoursRange =  noOfHours + ((double)remainingMinutes) / 100; 
					}
			
				
				return workingHoursRange;
		
			}
		
	
		
		
		public static int getWorkingDays(Date startDate,Date endDate)
			{
				int workingDays = 0;
				
				int diffDays = getDiffDays(startDate, endDate);
			
				
				//Same Day
				if(diffDays == 0)
					{
						return workingDays;
					}
			
				int noOfWeeks =  diffDays / WEEK_IN_DAYS;	
				int extraDays = diffDays %  WEEK_IN_DAYS;
				
				Calendar startDateCalendar = getDateCalendar(startDate);				
				int dayOfWeek = startDateCalendar.get(Calendar.DAY_OF_WEEK);
				int endWorkDay = dayOfWeek + extraDays;
				
				final int FIRST_DAY_OFF_INDEX = 6;
				final int SECOND_DAY_OFF_INDEX = 7;
				int extraDayOff = 0;
				if(endWorkDay >= SECOND_DAY_OFF_INDEX)
					{
						extraDayOff =  2;
					}
				else if(endWorkDay == FIRST_DAY_OFF_INDEX)
					{
						extraDayOff =  1;
					}
				
			
				
				workingDays = (diffDays) - (noOfWeeks * DAYS_OFF_COUNT) - extraDayOff;
				
				
				System.out.println(" Working Days "+workingDays);
				
				
				
				return workingDays;
			}
		
				
		
		
		public static int getDiffHour(Date date1,Date date2)
			{
				long diffTime = getDiffTime(date1, date2);				
                
				int noOfHours = (int) ( (diffTime) / (HOUR_IN_SECONDS) );

				
				return noOfHours;
				
			}
		
		public static int getDiffMinutes(Date date1,Date date2)
			{
				long diffTime = getDiffTime(date1, date2);				
                
				int noOfMinutes = (int) ( (diffTime) / (MINUTES_IN_SECONDS) );
	
				return noOfMinutes;
			}
		
		
		public static int getDiffDays(Date date1, Date date2)
			{
				// TODO Auto-generated method stub
				long diffTime = getDiffTime(date1, date2);				
                  
				int noOfDays = (int) ( (diffTime) / (DAY_IN_SECONDS) );
				
				
				return noOfDays;
			}
		
	
		
		 public static long getDiffTime(Date date1, Date date2)
			 {
				 long date1InTime = date1.getTime();
				 long date2InTime = date2.getTime();
				 
				 long diffTime = date2InTime - date1InTime;
				 
				 
				 return diffTime;	 
			 }
		
		
		public static Calendar getDateCalendar(String dateText)
			{
			
				Date date = getDate(dateText);
				
				Calendar dateCalendar =  getDateCalendar(date);
				
				return dateCalendar;
			}
		
		
		public static Calendar getDateCalendar(Date date)
			{
	            Calendar dateCalendar = new GregorianCalendar();
				
				dateCalendar.setTime(date);
				
				return dateCalendar;	
			}
		
		
		
		public static Date getDate(String dateText)
			{
				// TODO Auto-generated method stub
				Date date = null;
				SimpleDateFormat dateFormat = null;
				for(String datePattern : DATE_FORMATS)
					{
						dateFormat = new SimpleDateFormat(datePattern);
						try
							{
								date = dateFormat.parse(dateText);
								break;
							}
						catch (ParseException e)
							{
								// TODO Auto-generated catch block
								continue;
							}
						
					}

				return date;
			}
		
		public static String getDateTimeFromDate(Date date){
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			return dateFormat.format(date);
		}
		
		
		public static Date getDateFromString(String dateStr){
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				return dateFormat.parse(dateFormat.format(dateStr));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public static Integer getActualDaysFromWorkingDays(Integer workingDays) {
			int numberOfWeeks = workingDays / WORKING_DAYS_IN_WEEK;
			int restOfDays = workingDays % WORKING_DAYS_IN_WEEK;
			int NumberOfDays = (numberOfWeeks * WEEK_IN_DAYS) + restOfDays;
			return Integer.valueOf(NumberOfDays);
		}
		
		public static Date getResponseActualDateOfDeliveryDate(Date DeliveryDate , Integer calendarDays){
			if(DeliveryDate!=null){
				Calendar  calendar= Calendar.getInstance();
				calendar.setTime(DeliveryDate);
				calendar.add(Calendar.DAY_OF_MONTH, calendarDays.intValue());
				return calendar.getTime();
			}else{
				return null;
			}
				
		}
		
		public static Date getFirstActualDateOfDeliveryDate(Date DeliveryDate , Integer calendarDays){
			if(DeliveryDate!=null){
				Calendar  calendar= Calendar.getInstance();
				calendar.setTime(DeliveryDate);
				calendar.add(Calendar.DAY_OF_MONTH,-(calendarDays.intValue()));
				return calendar.getTime();
			}else{
				return null;
			}
				
		}
		
		public static Date getEndOfDay(Date date) {
			if(date != null){
			    Calendar calendar = Calendar.getInstance();
			    calendar.setTime(date);
			    calendar.set(Calendar.HOUR_OF_DAY, 23);
			    calendar.set(Calendar.MINUTE, 59);
			    calendar.set(Calendar.SECOND, 59);
			    calendar.set(Calendar.MILLISECOND, 999);
			    return calendar.getTime();
			}
			else{
				return null;
			}
		}

		public static Date getStartOfDay(Date date) {
			if(date != null){
		    Calendar calendar = Calendar.getInstance();
		    calendar.setTime(date);
		    calendar.set(Calendar.HOUR_OF_DAY, 0);
		    calendar.set(Calendar.MINUTE, 0);
		    calendar.set(Calendar.SECOND, 0);
		    calendar.set(Calendar.MILLISECOND, 0);
		    return calendar.getTime();
			}
		    else{
		    	return null;
		    }
		}
	}
