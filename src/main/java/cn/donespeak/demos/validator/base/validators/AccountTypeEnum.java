package cn.donespeak.demos.validator.base.validators;

public enum AccountTypeEnum implements BaseCodeEnum {
	PHONE(1),
	EMAIL(2)
	;
	private final int code;
	
	AccountTypeEnum(int code) {
		this.code = code;
	}
	
	@Override
	public int getCode() {
		return code;
	}
	
	public static AccountTypeEnum valueOf(int code) {
		return EnumAssistant.getEnumByCode(AccountTypeEnum.class, code);
	}
}