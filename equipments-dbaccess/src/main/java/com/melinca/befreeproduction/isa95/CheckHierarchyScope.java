package com.melinca.befreeproduction.isa95;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckHierarchyScopeValidator.class)
public @interface CheckHierarchyScope {

	String message() default "equipment.checkParent.message";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
