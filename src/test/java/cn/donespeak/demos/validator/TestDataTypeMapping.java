package cn.donespeak.demos.validator;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;

import cn.donespeak.demos.validator.base.validators.DataTypeMapping;
import cn.donespeak.demos.validator.base.validators.ValidatorErrorType;
import cn.donespeak.demos.validator.config.communication.HttpStatusResponse;
import cn.donespeak.demos.validator.pojo.UserFilterOption;

/**
 * @author guanrongYang
 * @date 2019/03/14 17:58:29
 */
public class TestDataTypeMapping {

	public static void main(String[] args) {
		System.out.println(DataTypeMapping.map(args.getClass()));
		System.out.println(DataTypeMapping.map(Boolean.class));
		System.out.println(DataTypeMapping.map(Integer.class));
		System.out.println(DataTypeMapping.map(Long.class));
		System.out.println(DataTypeMapping.map(Short.class));
		System.out.println(DataTypeMapping.map(Byte.class));
		System.out.println(DataTypeMapping.map(Float.class));
		System.out.println(DataTypeMapping.map(Double.class));
		System.out.println(DataTypeMapping.map(String.class));
		System.out.println(DataTypeMapping.map(Character.class));
		System.out.println(DataTypeMapping.map(Object.class));
		System.out.println(DataTypeMapping.map(HttpStatus.class));
		System.out.println(DataTypeMapping.map(HttpStatusResponse.class));
		System.out.println(DataTypeMapping.map(UserFilterOption.class));
		System.out.println(DataTypeMapping.map(ValidatorErrorType.class));
		System.out.println(DataTypeMapping.map(List.class));
		System.out.println(DataTypeMapping.map(Set.class));
		System.out.println(DataTypeMapping.map(Map.class));
		System.out.println(DataTypeMapping.map(Collection.class));
		System.out.println(DataTypeMapping.map(HashSet.class));
	}
}
