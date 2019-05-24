package cn.donespeak.demos.validator.base.validators;

import javax.validation.GroupSequence;

@GroupSequence({FirstGroup.class, EmailGroup.class})
public interface FirstEmailGroup {

}
