package cn.donespeak.demos.validator.base;

/**
 * The code with be use as the response result code, 
 * and message will be use as the response result message.
 * @author guanrong.yang
 * @date 2019/02/11
 */
public class ServiceMessageException extends ServiceException {

	private static final long serialVersionUID = -7422710276176498682L;

	private String message; 
	
	public ServiceMessageException(int code, String message) {
		super(code);
		this.message = message;
	}
	
	public ServiceMessageException(int code, String message, Throwable cause) {
		super(code, cause);
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
