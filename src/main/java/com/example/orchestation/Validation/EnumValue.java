package com.example.orchestation.Validation;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = EnumValueValidator.class)
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
public @interface EnumValue {

    String message() default "must be any of enum {enumClass}";
    
    // Validation groups can be used to group constraints together. 
    // This is useful for conditional validation scenarios.
    Class<?>[] groups() default {}; 

    // Payload can be used by clients of the Jakarta Bean Validation 
    // API to assign custom payload objects to a constraint.
    Class<? extends Payload>[] payload() default {};

    // Specifies the enum class that this annotation will validate against.
    Class<? extends Enum<?>> enumClass();

    boolean ignoreCase() default false;
}
