/**
 * 
 */
package bh.gov.cio.gbs.util;

/**
 * @author CSDVZZE
 * @since  Jun 12, 2014
 * 
 */
public class TimeDuration
	{
		private int noOfDays;
		private int noOfHours;
		private int noOfMinutes;
		
		
		
		
		
		
		/**
		 * @return the noOfDays
		 */
		public int getNoOfDays()
			{
				return this.noOfDays;
			}
		/**
		 * @param noOfDays the noOfDays to set
		 */
		public void setNoOfDays(int noOfDays)
			{
				this.noOfDays = noOfDays;
			}
		/**
		 * @return the noOfHours
		 */
		public int getNoOfHours()
			{
				return this.noOfHours;
			}
		/**
		 * @param noOfHours the noOfHours to set
		 */
		public void setNoOfHours(int noOfHours)
			{
				this.noOfHours = noOfHours;
			}
		/**
		 * @return the noOfMinutes
		 */
		public int getNoOfMinutes()
			{
				return this.noOfMinutes;
			}
		/**
		 * @param noOfMinutes the noOfMinutes to set
		 */
		public void setNoOfMinutes(int noOfMinutes)
			{
				this.noOfMinutes = noOfMinutes;
			}
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
			{
				return "TimeDuration [noOfDays=" + noOfDays + ", noOfHours=" + noOfHours + ", noOfMinutes=" + noOfMinutes + "]";
			}
		
		
		
		
		
		

	}
