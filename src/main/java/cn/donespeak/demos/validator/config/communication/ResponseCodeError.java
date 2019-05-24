package cn.donespeak.demos.validator.config.communication;

import org.springframework.http.HttpStatus;

/**
 * @author guanrongYang
 * @date 2019/03/12 18:11:32
 */
public class ResponseCodeError extends HttpStatusResponse {
	private String code;
	private String message;
	
	public ResponseCodeError() {
	}
	
	public ResponseCodeError(HttpStatus httpStatus, String code, String message) {
		super(httpStatus);
		this.code = code;
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
