package com.example.app.common.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ConditionalValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ConditionalValidations.class)
public @interface ConditionalValidation {
	String message() default "Conditional validation failed";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String conditionalProperty();

	String[] values();

	String[] requiredProperties();
}
