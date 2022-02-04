package com.meydan.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.meydan.validator.TelNotExistValidator;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Constraint(validatedBy = TelNotExistValidator.class)
public @interface TelNotExist {

	
	
	String message() default "Girdiginiz numara kayitli degil";
	
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
