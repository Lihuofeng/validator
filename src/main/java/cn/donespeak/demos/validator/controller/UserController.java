package cn.donespeak.demos.validator.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.donespeak.demos.validator.base.validators.AgeGroup;
import cn.donespeak.demos.validator.base.validators.EmailAndPhoneGroup;
import cn.donespeak.demos.validator.base.validators.EmailGroup;
import cn.donespeak.demos.validator.base.validators.GroupA;
import cn.donespeak.demos.validator.base.validators.GroupB;
import cn.donespeak.demos.validator.base.validators.PhoneGroup;
import cn.donespeak.demos.validator.pojo.User;
import cn.donespeak.demos.validator.pojo.UserEmail;
import cn.donespeak.demos.validator.pojo.UserEntity;
import cn.donespeak.demos.validator.pojo.UserFilterOption;
import cn.donespeak.demos.validator.pojo.UserPhone;

/**
 * @author guanrongYang
 * @date 2019/03/13 19:47:40
 */
@RestController
@RequestMapping(value="/users", consumes="application/json")
public class UserController {
	
	@PostMapping("/valid")
	public Object listUsersValid(@RequestBody @Valid UserFilterOption option) {
		return option;
	}

	@PostMapping("/validated")
	public Object listUsersValidated(@RequestBody @Validated UserFilterOption option) {
		return option;
	}

	@GetMapping("/page")
	public Object pageUsers(@Min(1)int pageIndex, @Max(100)int pageSize) {
		Map<String, Object> page = new HashMap<String, Object>();
		page.put("pageIndex", pageIndex);
		page.put("pageSize", pageSize);
		
		return page;
	}
	
	@PostMapping("")
	public Object addUser(@RequestBody @Validated User user) {
		return user;
	}
	
	/*@PostMapping("")
	public Object addUser(@RequestBody @Validated({PhoneGroup.class}) User user) {
		return user;
	}*/
	/*@PostMapping("")
	public Object addUser(@RequestBody @Validated({PhoneGroup.class}) UserPhone userphone, @Validated({EmailGroup.class})UserEmail userEmail ) {
		
		return userphone;
	}*/
	
	/*@PostMapping("/test")
	public  Object validation5(@RequestBody @Validated({GroupA.class, GroupB.class}) UserEntity user){
		return user;
	}*/
	
	/*@PutMapping("")
	public Object userEmailAndPhonle(@RequestBody @Validated({EmailAndPhoneGroup.class}) User user) {
		return user;
	}
	
	@PatchMapping("")
	public Object updateAge(@RequestBody @Validated(AgeGroup.class) User user) {
		return user;
	}*/
}
