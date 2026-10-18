package com.example.orchestation.Validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordValueValidatorTest {

    private PasswordStrengthValidator passwordStrengthValidator;

    @BeforeEach
    void setUp(){
        this.passwordStrengthValidator = new PasswordStrengthValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "abc12!",       // Exact minimums: 3 letters, 2 digits, 1 special, length 6
            "Pass123!",     // Standard strong password
            "A1!B2c3$",     // Mixed case, scattered digits and specials
            "abcd1@",       // 4 letters, 1 digit, 1 special
            "123!abc",      // Starts with numbers/specials
            "ab1!cde"       // Letters split up
    })
    void testValidPasswords(String password) {
        assertTrue(this.passwordStrengthValidator.isValid(password, null),
                "Password should be valid: " + password);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "abc1!",        // Fails: Length is 5 (Needs 6+)
            "ab123!",       // Fails: Only 2 letters (Needs 3+)
            "abcdef!",      // Fails: No digits (Needs 1+)
            "abc1234",      // Fails: No special character (Needs 1+)
            "abc12!#",      // Fails: '#' is not in the allowed special character list
            "abc 12!",      // Fails: Contains a space
            "abc_12!",      // Fails: Underscore '_' is not allowed
            "abc-12!",      // Fails: Hyphen '-' is not allowed
            "123456!",      // Fails: 0 letters
            "ABCDE@"        // Fails: No digits
    })
    void testInvalidPasswords(String password){
        assertFalse(this.passwordStrengthValidator.isValid(password, null),
                "Password should be invalid " + password);
    }

}
