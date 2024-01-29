package com.example.app.common.validations;

import com.example.app.order.dto.OrderCreateDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;

public class ConditionalValidator implements ConstraintValidator<ConditionalValidation, Object> {

	private String conditionalProperty;
	private String[] values;
	private String[] requiredProperties;
	private String message;

	@Override
	public void initialize(ConditionalValidation constraintAnnotation) {
		this.conditionalProperty = constraintAnnotation.conditionalProperty();
		this.values = constraintAnnotation.values();
		this.requiredProperties = constraintAnnotation.requiredProperties();
		this.message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (!(value instanceof OrderCreateDto)) {
			throw new IllegalArgumentException("Invalid object type");
		}

		try {
			Field conditionalField = value.getClass().getDeclaredField(conditionalProperty);
			conditionalField.setAccessible(true);
			Object conditionalValue = conditionalField.get(value);

			for (String val : values) {
				if (conditionalValue != null && conditionalValue.toString().equals(val)) {
					for (String reqProp : requiredProperties) {
						Field reqField = value.getClass().getDeclaredField(reqProp);
						reqField.setAccessible(true);
						Object reqValue = reqField.get(value);

						if (reqValue == null || (reqValue instanceof String && ((String) reqValue).isEmpty())) {
							context.disableDefaultConstraintViolation();
							context.buildConstraintViolationWithTemplate(message)
									.addPropertyNode(reqProp)
									.addConstraintViolation();
							return false;
						}
					}
				}
			}
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new RuntimeException("Error accessing fields in validation", e);
		}

		return true;
	}
}
