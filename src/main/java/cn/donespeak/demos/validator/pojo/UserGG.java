package cn.donespeak.demos.validator.pojo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import cn.donespeak.demos.validator.base.validators.EmailGroup;
import cn.donespeak.demos.validator.base.validators.PhoneGroup;


public class UserGG {
//	@Pattern(regexp="[1,2]" ,groups= {First.class})
	private String accountType;
	
	@NotNull(groups= {PhoneGroup.class,EmailGroup.class})
	@Length(min=5, max=20 ,groups= {PhoneGroup.class,EmailGroup.class})
	private String name;
	
	@Length(min=8, max=16,groups= {PhoneGroup.class,EmailGroup.class})
	private String password;
	
	@Min(value=1,groups= {PhoneGroup.class,EmailGroup.class})
	@Max(value=150,groups= {PhoneGroup.class,EmailGroup.class})
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

}
