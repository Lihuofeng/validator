package cn.donespeak.demos.validator.base.validators;

/**
 * @author guanrongYang
 * @date 2019/03/14 11:20:08
 */
public enum ValidatorErrorType {
	MISSING_FIELD,
	TYPE_MISMATCH,
	INVALID
	;
	
	private final String value;
	
	ValidatorErrorType() {
		this.value = name().toLowerCase();
	}
	
	public String value() {
		return value;
	}
}
