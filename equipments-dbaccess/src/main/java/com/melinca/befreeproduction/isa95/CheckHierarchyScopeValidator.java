package com.melinca.befreeproduction.isa95;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class CheckHierarchyScopeValidator implements ConstraintValidator<CheckHierarchyScope, Equipment> {

	@Autowired
	private MongoTemplate mTemplate;

	@Override
	public void initialize(CheckHierarchyScope arg0) {

	}

	@Override
	public boolean isValid(Equipment eq, ConstraintValidatorContext arg1) {
		if (null != eq && null != eq.getHierarchyScope()) {
			if (HierarchyScopeEnum.Enterprise.equals(eq.getHierarchyScope()) && null == eq.getParent())
				return true;
			Equipment parent = mTemplate.findById(eq.getParent(), Equipment.class);
			if (null != parent && HierarchyScopeRules.childrenHierarchies.get(parent.getHierarchyScope())
					.contains(eq.getHierarchyScope()))
				return true;
		}
		return false;
	}

}
