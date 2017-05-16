package bh.gov.cio.gbs.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class FileUploadExcption.
 */
public class GBSFileUploadExcption extends RuntimeException {

	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -168692093706535242L;
	
	/** The code. */
	private int code;
	
	/** The error. */
	private String error;

	/**
	 * Instantiates a new file upload excption.
	 */
	public GBSFileUploadExcption() {
		super();
	}

	/**
	 * Instantiates a new file upload excption.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public GBSFileUploadExcption(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new file upload excption.
	 *
	 * @param message the message
	 */
	public GBSFileUploadExcption(String message) {
		super(message);
	}

	/**
	 * Instantiates a new file upload excption.
	 *
	 * @param cause the cause
	 */
	public GBSFileUploadExcption(Throwable cause) {
		super(cause);
	}
	
	/**
	 * Instantiates a new database exception.
	 *
	 * @param code the code
	 * @param error the error
	 */
	public GBSFileUploadExcption(int code, String error) {
		super(error);
		this.setCode(code);
		this.setError(error);
	}
	
	public GBSFileUploadExcption(int code) {
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
