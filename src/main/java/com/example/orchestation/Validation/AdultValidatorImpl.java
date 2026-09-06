package com.example.orchestation.Validation;

import java.time.LocalDate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AdultValidatorImpl implements ConstraintValidator<AdultValidator, LocalDate> {

    @Override
    public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext context) {
        if (dateOfBirth == null) {
            return false; // or true, depending on whether you want to allow null values
        }
        LocalDate today = LocalDate.now();
        LocalDate adultDate = today.minusYears(18);
        return !dateOfBirth.isAfter(adultDate);
    }
    
}
