package cn.donespeak.demos.validator.pojo;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;


public class UserModel {

	@Min(value = 1, message="id值必须大于0")
//	@Pattern(regexp="[1,2]", message="用户不正确",groups={GroupA.class, GroupB.class})
	private String id;
	
//	@NotBlank
	@Length(min=6, max=12, message="名字不在范围内")
	private String nickname;
	
	@Min(value=18, message="{validation.param.age}")
	private int age;
	
	@NotBlank
	@Email(message="{validation.common.format.error}")
	private String email;
	
//	@NotBlank
	@Pattern(regexp="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$", message="由6-21字母和数字组成，不能是纯数字或纯英文")
	private String password;
	
//	@NotBlank
	@Length(min=3, max=10, message="{validation.common.not.range}")
	private String username;
	
	@NotBlank
	@Pattern(regexp="^((13[0-9])|(15[^4,\\D])|(18[0,3-9]))\\d{8}$", message="手机号格式不正确")
//	@Phone
	private String phone;

	public UserModel() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
