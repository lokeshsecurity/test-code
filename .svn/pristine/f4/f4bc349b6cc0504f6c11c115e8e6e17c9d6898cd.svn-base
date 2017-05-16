/**
 *  @author csdvedd
 */
package bh.gov.cio.gbs.model;

import org.apache.http.HttpStatus;

/**
 * @author csdvedd
 * 
 */
public enum GBSStatus implements HttpStatus {

	TEST_GBS_STATUS(1001, "TEST_GBS_STATUS"),
	GBS_FAILD_TO_ADD_BOARD(1002,"Failed to add Board"),
	GBS_BOARD_NAME_ALREADY_EXISTS(1003,"Board name already exist"),
	GBS_FILE_UPLOAD_FAILED(1004 , "Failed to upload attachment"),
	GBS_DATABASE_EXCEPTION(1005,"Database exception done"),
	GBS_NOT_VALID_MEMBER_CPR_NUMBER(1006,"The member's CPR number entered not in the valid format"),
	GBS_DECREE_NUMBER_AND_YEAR_ALREADY_EXISTS(1007,"The combination of decree number and year already exists"),
	GBS_GENERAL_EXCEPTION(1008 , "General Error"), 
	GBS_NO_DESTINATION_ORGANIZATION_ENTERED(1009,"No destination orgnization entered"),
	GBS_MEMBER_EXPIRY_DATE_EXCEPTION(1010,"The renewed member expiry date should be greater than the old member expiry date"),
	GBS_NOTIFICATION_ISSUE_DATE_EXCEPTION(1011,"Cannot notify before issue date"),
	;

	private Integer statusCode;
	private String statusMessage;

	GBSStatus(Integer statusCode, String statusMessage) {
		this.setStatusCode(statusCode);
		this.setStatusMessage(statusMessage);
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

}
