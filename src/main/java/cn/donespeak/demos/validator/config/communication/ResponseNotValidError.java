package cn.donespeak.demos.validator.config.communication;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

/**
 * @author guanrongYang
 * @date 2019/03/12 18:13:03
 */
public class ResponseNotValidError extends HttpStatusResponse {
	
	public static class FieldNotValidError {
		private String type;
		private String field;
		private String message;
		
		public FieldNotValidError() {			
		}
		
		public FieldNotValidError(String code, String field, String message) {
			this.type = code;
			this.field = field;
			this.message = message;
		}
		
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getField() {
			return field;
		}
		public void setField(String field) {
			this.field = field;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
	}
	
	private String code;
	private String message;
	private List<FieldNotValidError> errors;
	
	private static final HttpStatus HTTP_STATUS_UNPROCESSABLE_ENTITY = HttpStatus.UNPROCESSABLE_ENTITY;
	private static final String DEFAULT_CODE = "";
	private static final String DEFAULT_MESSAGE = "Validation Failed";
		
	public ResponseNotValidError() {
		super(HTTP_STATUS_UNPROCESSABLE_ENTITY);
	}
	
	public ResponseNotValidError(String code, String message, List<FieldNotValidError> errors) {
		this();
		this.code = code;
		this.message = message;
		this.errors = errors;
	}
	
	public ResponseNotValidError(String message, List<FieldNotValidError> errors) {
		this();
		this.code = DEFAULT_CODE;
		this.message = message;
		this.errors = errors;
	}

	public ResponseNotValidError(List<FieldNotValidError> errors) {
		this(DEFAULT_MESSAGE, errors);
	}
	
	public ResponseNotValidError(FieldNotValidError error) {
		this(Arrays.asList(error));
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
	public List<FieldNotValidError> getErrors() {
		return errors;
	}
	public void setErrors(List<FieldNotValidError> errors) {
		this.errors = errors;
	}
}
