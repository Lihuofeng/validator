package cn.donespeak.demos.validator.config.communication;

import org.springframework.http.HttpStatus;

/**
 * @author guanrongYang
 * @date 2019/03/12 18:10:16
 */
public class ResponseMessageError extends HttpStatusResponse {
	private String message;
	
	public ResponseMessageError() {
	}
	
	public ResponseMessageError(HttpStatus httpStatus, String message) {
		super(httpStatus);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
