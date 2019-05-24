package cn.donespeak.demos.validator.pojo;

import javax.validation.constraints.NotNull;

import cn.donespeak.demos.validator.base.validators.EmailGroup;

public class UserEmail extends UserGG{
	@NotNull(groups={EmailGroup.class})
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}
