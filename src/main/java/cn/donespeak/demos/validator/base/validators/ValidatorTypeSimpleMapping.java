package cn.donespeak.demos.validator.base.validators;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.*;

/**
 * @author guanrongYang
 * @date 2019/03/14 11:20:33
 */
public class ValidatorTypeSimpleMapping {
	
	public enum ValidatorErrorSimpleType {
		MISSING_FIELD,
		INVALID
		;
		public String getValue() {
			return name().toLowerCase();
		}
	}
	
	private static Map<String, String> mapping = new HashMap<String, String>();
	
	static {
		map(Min.class, ValidatorErrorType.INVALID);
	}

	private static void map(Class<? extends Annotation> klass, ValidatorErrorType type) {
		mapping.put(name(klass), type.value());
	}
	
	private static String name(Class<? extends Annotation> klass) {
		return klass == null? "null": klass.getSimpleName();
	}

	public static String toType(Class<? extends Annotation> klass) {
		return name(klass);
	}

	public static String toType(String validatorCode) {
		String type = mapping.get(validatorCode);
		return type == null? "invalid": type;
	}

	public static void main(String[] args) {
		System.out.println(NotNull.class.getSimpleName());
		
	}
}
