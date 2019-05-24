package cn.donespeak.demos.validator.base;

public class SystemException extends RuntimeException {

	private static final long serialVersionUID = -3645360976347771367L;

	public SystemException() {
		super();
	}
	
	public SystemException(String message) {
		super(message);
	}
	
	public SystemException(Throwable cause) {
		super(cause);
	}
	
	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}
}
