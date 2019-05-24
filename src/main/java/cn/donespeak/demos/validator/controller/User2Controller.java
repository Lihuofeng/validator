package cn.donespeak.demos.validator.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.donespeak.demos.validator.base.validators.AgeGroup;
import cn.donespeak.demos.validator.base.validators.EmailAndPhoneGroup;
import cn.donespeak.demos.validator.pojo.User;
import cn.donespeak.demos.validator.pojo.UserFilterOption;

/**
 * @author guanrongYang
 * @date 2019/03/13 22:34:29
 */
@RestController
@RequestMapping(value="/users2", consumes="application/json")
@Validated
public class User2Controller {

	@PostMapping("/valid")
	public Object listUsersValid(@RequestBody @Valid UserFilterOption option) {
		return option;
	}

	@PostMapping("/validated")
	public Object listUsersValidated(@Validated UserFilterOption option) {
		return option;
	}

	@GetMapping("/page")
	public Object pageUsers(@RequestParam @Min(1)int pageIndex, @RequestParam @Max(100)int pageSize) {
		Map<String, Object> page = new HashMap<String, Object>();
		page.put("pageIndex", pageIndex);
		page.put("pageSize", pageSize);
		return page;
	}
	
	@PostMapping("")
	public Object addUser(@Validated User user) {
		return user;
	}
	
	@PutMapping("")
	public Object userEmailAndPhonle(@Validated({EmailAndPhoneGroup.class}) User user) {
		return user;
	}
	
	@PatchMapping("")
	public Object updateAge(@Validated(AgeGroup.class) User user) {
		return user;
	}
}
