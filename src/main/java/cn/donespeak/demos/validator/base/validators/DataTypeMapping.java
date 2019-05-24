package cn.donespeak.demos.validator.base.validators;

import java.util.HashMap;
import java.util.Map;

/**
 * @author guanrongYang
 * @date 2019/03/14 17:42:50
 */
public class DataTypeMapping {
	private static final String NUMBER = "number";
	private static final String ARRAY = "array";
	private static final String OBJECT = "object";
	private static final String BOOLEAN = "boolean";
	private static final String INTEGER = "integer";
	private static final String STRING = "string";
	
	private static Map<Class<?>, String> mapping = new HashMap<Class<?>, String>();
	
	static {
		mapping.put(Boolean.class, BOOLEAN);
		mapping.put(Integer.class, INTEGER);
		mapping.put(Long.class, INTEGER);
		mapping.put(Short.class, INTEGER);
		mapping.put(Byte.class, INTEGER);
		mapping.put(Float.class, NUMBER);
		mapping.put(Double.class, NUMBER);
		mapping.put(String.class, STRING);
		mapping.put(Character.class, STRING);
		mapping.put(Object.class, OBJECT);
	}
	
	public static String map(Class<?> klass) {
		System.out.println("===============\n");
		System.out.println("klass: " + klass);
		System.out.println("getTypeName: " + klass.getTypeName());
		System.out.println("getName: " + klass.getName());
		System.out.println("getSimpleName: " + klass.getSimpleName());
		System.out.println("isMemberClass: " + klass.isMemberClass());
		System.out.println("isAnnotation: " + klass.isAnnotation());
		System.out.println("isEnum: " + klass.isEnum());
		System.out.println("isInstance: " + klass.isInstance(Object.class));
		
		String result = mapping.get(klass);
		if(result != null) {
			return result;
		}
		if(klass.isArray()) {
			return ARRAY;
		}
		return OBJECT;
	}
}
