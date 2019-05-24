package cn.donespeak.demos.validator.base.validators;

import javax.validation.GroupSequence;

@GroupSequence({FirstGroup.class, PhoneGroup.class})
public interface FirstPhoneGroup {

}
