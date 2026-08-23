package com.example.orchestation.Validation;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValueValidator implements ConstraintValidator<EnumValue, String> {
    
    private Set<String> acceptedValues; // Set of accepted enum values for validation
    private boolean ignoreCase; // Flag to determine if case should be ignored during validation

    @Override
    public void initialize(EnumValue annotation) {
        
        // Extract enum class from annotation
        Class<? extends Enum<?>> enumClass = annotation.enumClass();
        this.ignoreCase = annotation.ignoreCase();

        // Collect all enum names into a set for fast lookup
        this.acceptedValues = Arrays.stream(enumClass.getEnumConstants())
                .map(e -> this.ignoreCase ? e.name().toLowerCase() : e.name())
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // If the value is null or empty, let service decide itself
        if (value == null || value.trim().isEmpty()) return true;
        String v = this.ignoreCase ? value.toLowerCase() : value;
        return acceptedValues.contains(v);
    }
}
