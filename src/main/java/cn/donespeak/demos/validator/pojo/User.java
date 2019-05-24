package cn.donespeak.demos.validator.pojo;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * @author guanrongYang
 * @date 2019/03/13 18:28:39
 */
public class User {
	private List<String> accountType;
	
	@NotNull
	@Length(min=5, max=20 )
	private String name;
	
	@NotNull
	private String email;
	
	@NotBlank
	private String phone;
	
	@Length(min=8, max=16)
	private String password;
	
	@Min(value=1)
	@Max(value=150)
	private int age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public List<String> getAccountType() {
		return accountType;
	}
	public void setAccountType(List<String> accountType) {
		this.accountType = accountType;
	}
	
	
}
