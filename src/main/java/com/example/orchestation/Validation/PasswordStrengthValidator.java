package com.example.orchestation.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PasswordStrengthValidator implements ConstraintValidator<PasswordValidator, String> {

    // Regex breakdown: 3+ letter, 1+ digit, 1+ special char, 6+ min length
    private static final String PASSWORD_PATTERN =
            "^(?=(?:.*[A-Za-z]){3,})(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$";

    private static final Pattern PATTERN = Pattern.compile(PASSWORD_PATTERN);

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context){
        // Allow nulls here if you want @NotBlank to handle null/empty separately
        if (password == null) {
            return false;
        }
        return PATTERN.matcher(password).matches();
    }

}
