package cn.donespeak.demos.validator.base.validators;

public class EnumAssistant {
	public static <E extends Enum<?> & BaseCodeEnum> E getEnumByCode(Class<E> enumClass, Integer code){
		if(code == null){
			return null;
		}
		E[] enumConstants = enumClass.getEnumConstants();
		for(E e: enumConstants){
			if(e.getCode() == code){
				return e;
			}
		}
		return null;
	}
}