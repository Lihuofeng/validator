package cn.donespeak.demos.validator.pojo;

import javax.validation.constraints.NotNull;

import cn.donespeak.demos.validator.base.validators.PhoneGroup;

public class UserPhone {
	@NotNull(groups={PhoneGroup.class})
	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
