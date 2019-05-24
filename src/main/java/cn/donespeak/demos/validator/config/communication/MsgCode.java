package cn.donespeak.demos.validator.config.communication;

public class MsgCode {
	private String code;
	private String Msg;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return Msg;
	}
	public void setMsg(String msg) {
		Msg = msg;
	}
	@Override
	public String toString() {
		return "MsgCode [code=" + code + ", Msg=" + Msg + "]";
	}
	
}
