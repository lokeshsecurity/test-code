package bh.gov.cio.gbs.exception;




// TODO: Auto-generated Javadoc
/**
 * The Class BusinessException.
 */
public class BusinessException extends ApplicationException{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2041346876137373220L;
	
	/** The code. */
	private int code;
	
	/** The error. */
	private String error;
	
	/**
	 * Instantiates a new business exception.
	 */
	public BusinessException() {
		super();
	}

	/**
	 * Instantiates a new business exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new business exception.
	 *
	 * @param message the message
	 */
	public BusinessException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new business exception.
	 *
	 * @param cause the cause
	 */
	public BusinessException(Throwable cause) {
		super(cause);
	}
	
	
	/**
	 * Instantiates a new database exception.
	 *
	 * @param code the code
	 * @param error the error
	 */
	public BusinessException(int code, String error) {
		super(error);
		this.setCode(code);
		this.setError(error);
	}
	
	public BusinessException(int code) {
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
