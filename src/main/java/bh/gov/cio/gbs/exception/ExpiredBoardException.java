package bh.gov.cio.gbs.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class ExpiredBoardException.
 */
public class ExpiredBoardException extends ApplicationException{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 279560672927472148L;
	
	/** The code. */
	private int code;
	
	/** The error. */
	private String error;

	
	/**
	 * Instantiates a new board exception.
	 */
	public ExpiredBoardException() {
		super();
	}

	/**
	 * Instantiates a new board exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public ExpiredBoardException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new board exception.
	 *
	 * @param message the message
	 */
	public ExpiredBoardException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new board exception.
	 *
	 * @param cause the cause
	 */
	public ExpiredBoardException(Throwable cause) {
		super(cause);
	}
	
	public ExpiredBoardException(int code, String error) {
		super(error);
		this.setCode(code);
		this.setError(error);
	}
	
	public ExpiredBoardException(String message, Throwable cause , int code) {
		super(message, cause);
		this.setCode(code);
	}
	
	
	public ExpiredBoardException(int code) {
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
