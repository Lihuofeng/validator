//package cn.donespeak.demos.validator.config.communication;
//
//import java.util.List;
//
//public class ResponseErrors {
//	private boolean success;
//	private Object data;
//	private List<MsgCode> errors;
//	
//	public ResponseErrors() {
//		
//	}
//	
//	public ResponseErrors(boolean success, List<MsgCode> errors) {
//		this.success = success;
//		this.errors = errors;
//	}
//	
//	public ResponseErrors(boolean success, Object data, List<MsgCode> errors) {
//		this.success = success;
//		this.data = data;
//		this.errors = errors;
//	}
//	
//	public static ResponseErrors success(boolean success, Object data) {
//		return new ResponseErrors(success, data, null);
//	}
//	
//	public static ResponseErrors error(boolean success, List<MsgCode> errors) {
//		return new ResponseErrors(success, null, errors);
//	}
//
//	public boolean isSuccess() {
//		return success;
//	}
//
//	public void setSuccess(boolean success) {
//		this.success = success;
//	}
//
//	public Object getData() {
//		return data;
//	}
//
//	public void setData(Object data) {
//		this.data = data;
//	}
//
//	public List<MsgCode> getErrors() {
//		return errors;
//	}
//
//	public void setErrors(List<MsgCode> errors) {
//		this.errors = errors;
//	}
//
//	@Override
//	public String toString() {
//		return "ResponseJson [success=" + success + ", data=" + data + ", errors=" + errors + "]";
//	}
//
//
//}
