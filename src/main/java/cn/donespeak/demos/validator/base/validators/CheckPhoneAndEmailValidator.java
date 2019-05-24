package cn.donespeak.demos.validator.base.validators;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckPhoneAndEmailValidator implements ConstraintValidator<CheckPhoneAndEmail, Integer>{
	
	private AccountTypeEnum accountTypeEnum;
	
	@Override
	public void initialize(CheckPhoneAndEmail constraintAnnotation) {
		this.accountTypeEnum = constraintAnnotation.value();
	}
	
	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		return AccountTypeEnum.valueOf(value) != null;
	}

}
