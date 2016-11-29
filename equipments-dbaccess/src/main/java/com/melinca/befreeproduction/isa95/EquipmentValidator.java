package com.melinca.befreeproduction.isa95;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class EquipmentValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Equipment.class.equals(arg0);
	}

	@Override
	public void validate(Object obj, Errors er) {
		ValidationUtils.rejectIfEmptyOrWhitespace(er, "ID", "empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(er, "HierarchyScope", "empty");
		Equipment e = (Equipment) obj;
		if (null == er.getFieldError("HierarchyScope")
				&& !HierarchyScopeEnum.Enterprise.equals(e.getHierarchyScope())) {
			ValidationUtils.rejectIfEmptyOrWhitespace(er, "Parent", "empty");
		}
		
		
	}

}
