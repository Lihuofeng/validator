package cn.donespeak.demos.validator.base;

/**
 * The code with be use as the response result code, 
 * and message will NOT be use as the response result message.
 * @author guanrong.yang
 * @date 2019/02/11
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -824087270188153536L;
		
	protected int msgCode;
	
	public ServiceException(int msgCode) {
		super();
		this.msgCode = msgCode;
	}
	
	public ServiceException(int msgCode, String message) {
		super(message);
		this.msgCode = msgCode;
	}
	
	public ServiceException(int msgCode, Throwable cause) {
		super(cause);
		this.msgCode = msgCode;
	}

	public ServiceException(int msgCode, String message, Throwable cause) {
		super(message, cause);
		this.msgCode = msgCode;
	}
	
	public int getMsgCode() {
		return msgCode;
	}
}
