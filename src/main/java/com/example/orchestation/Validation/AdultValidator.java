package com.example.orchestation.Validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented 
@Target ({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = { AdultValidatorImpl.class })
@Retention (RetentionPolicy.RUNTIME)
public @interface AdultValidator {
    
    String message() default "Employee must be at least 18 years old";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
