package com.melinca.befreeproduction.isa95;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class CheckHierarchyScopeValidator implements ConstraintValidator<CheckHierarchyScope, Equipment> {

	@Override
	public void initialize(CheckHierarchyScope arg0) {

	}

	@Override
	public boolean isValid(Equipment eq, ConstraintValidatorContext arg1) {
		return (null != eq && null != eq.getHierarchyScope())
				? (HierarchyScopeEnum.Enterprise.equals(eq.getHierarchyScope()) && null == eq.getParent())
						|| (null != eq.getParent() && !HierarchyScopeEnum.Enterprise.equals(eq.getHierarchyScope()))
				: false;
	}

}
