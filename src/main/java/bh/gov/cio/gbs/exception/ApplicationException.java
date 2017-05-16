/**
 * ******************************************************************
 * Copyrighted Material, Confidential, unauthorized review or 
 * reproduction is prohibited, ad so can and will result in 
 * legal action against violators 
 * ******************************************************************
 * ***  Copyright Holders *******************************************
 * ******************************************************************
 * Government of Kingdom Of Bahrain 						(KOB)	
 * Central Informatics Organization 						(CIO) 
 * Governerate Data Network 								(GDN) 
 * National Data Services Project							(NDS) 
 * The General Directorate of Information Technology 		(GDIT) 
 * Application Transformation Project 						(ATP)
 * Civil Registration Service 								(CRS)
 * ________________________________________________________________________________
 * Change Log 
 * ________________________________________________________________________________
 * VER		USER		Timestamp				Activity
 * ________________________________________________________________________________
 * 
 */
package bh.gov.cio.gbs.exception;

/**
 * The Class ApplicationException.
 */
public class ApplicationException extends Exception {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The code. */
	private int code;
	
	/** The error. */
	private String error;


	/**
	 * Instantiates a new application exception.
	 */
	public ApplicationException() {
		super();
	}


	/**
	 * Instantiates a new application exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}


	/**
	 * Instantiates a new application exception.
	 *
	 * @param message the message
	 */
	public ApplicationException(String message) {
		super(message);
	}


	/**
	 * Instantiates a new application exception.
	 *
	 * @param cause the cause
	 */
	public ApplicationException(Throwable cause) {
		super(cause);
	}
	
	
	public ApplicationException(int code, String error) {
		super(error);
		this.setCode(code);
		this.setError(error);
	}
	
	
	public ApplicationException(int code) {
		super();
		this.setCode(code);
	}


	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}


	public String getError() {
		return error;
	}


	public void setError(String error) {
		this.error = error;
	}

	
}
