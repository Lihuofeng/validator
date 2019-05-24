package cn.donespeak.demos.validator.pojo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author guanrongYang
 * @date 2019/03/13 19:49:57
 */
public class UserFilterOption {
	private String email;	
	private String phone;	
	private Integer minAge;
	private Integer maxAge;
	
	@Min(1)
	private int pageIndex;
	
	@Max(100)
	private int pageSize;
	
	private UserFilterOption fitler;
	
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
	public Integer getMinAge() {
		return minAge;
	}
	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}
	public Integer getMaxAge() {
		return maxAge;
	}
	public void setMaxAge(Integer maxAge) {
		this.maxAge = maxAge;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public UserFilterOption getFitler() {
		return fitler;
	}
	public void setFitler(UserFilterOption fitler) {
		this.fitler = fitler;
	}
	
}
