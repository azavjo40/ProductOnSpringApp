package com.example.app.order.validations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ConditionalValidations {
	ConditionalValidation[] value();
}
