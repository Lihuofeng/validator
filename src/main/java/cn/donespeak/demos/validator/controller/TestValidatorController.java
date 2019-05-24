package cn.donespeak.demos.validator.controller;


import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.donespeak.demos.validator.base.validators.GroupA;
import cn.donespeak.demos.validator.base.validators.GroupAB;
import cn.donespeak.demos.validator.base.validators.GroupB;
import cn.donespeak.demos.validator.pojo.UserEntity;
import cn.donespeak.demos.validator.pojo.UserModel;


@RestController
@RequestMapping(value="/tests",consumes="application/json")
//@Validated
public class  TestValidatorController {
	
	//测试这个方法是把类上面的@Validated注解打开
	@GetMapping("/test1")
	public @ResponseBody Object validation1(@Valid UserModel user ){
		return user;
	}
	
	@PostMapping("/test2")
	public  Object validation2( @RequestBody @Valid UserModel user ){
		return user;
	}
	@PostMapping("/test3")
	public  Object validation3(@RequestBody @Validated({GroupAB.class}) UserEntity user, BindingResult bindingResult){
		for(Object obj: bindingResult.getAllErrors()) {
			System.out.println("### " + obj.toString());
		}
		return user;
	}
	@PostMapping("/test4")
	public  Object validation4(@RequestBody @Validated({GroupAB.class}) UserEntity user){
		return user;
	}
	@PostMapping("/test5")
	public  Object validation5(@RequestBody @Validated({GroupA.class, GroupB.class}) UserEntity user){
		return user;
	}
}
