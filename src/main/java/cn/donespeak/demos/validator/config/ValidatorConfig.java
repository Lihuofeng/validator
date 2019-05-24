package cn.donespeak.demos.validator.config;

import java.nio.charset.StandardCharsets;

import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@Configuration
@EnableTransactionManagement
public class ValidatorConfig {

	@Bean
	public MessageSource messageSource(){
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());		
		messageSource.setBasenames("classpath:validateMessage");
		return messageSource;
	}

	@Bean
	public LocalValidatorFactoryBean localValidatorFactoryBean(){
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.setValidationMessageSource(messageSource());
		return validator;
	}

	/**
     * JSR和Hibernate validator的校验只能对Object的属性进行校验
     * 不能对单个的参数进行校验
     * spring 在此基础上进行了扩展
     * 添加了MethodValidationPostProcessor拦截器
     * 可以实现对方法参数的校验
     *
     */
	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor(){
		MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
		processor.setValidator(localValidatorFactoryBean());
		return processor;
	}
	
//	@Bean
//    public static Validator validator() {
//        return Validation
//                .byProvider(HibernateValidator.class)
//                .configure()
//                //快速返回模式，有一个验证失败立即返回错误信息
//                .failFast(false)
//                .buildValidatorFactory()
//                .getValidator();
//    }
}
