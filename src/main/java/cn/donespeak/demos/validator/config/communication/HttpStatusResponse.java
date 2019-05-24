package cn.donespeak.demos.validator.config.communication;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author guanrongYang
 * @date 2019/03/12 18:26:32
 */
public class HttpStatusResponse {
	private HttpStatus httpStatus;
	
	public HttpStatusResponse() {		
	}
	
	public HttpStatusResponse(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	@JsonIgnore
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
}
