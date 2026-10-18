package com.example.orchestation.Validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailValidatorTest {

    private EmailValidator emailValidator;

    @BeforeEach
    void setUp(){
        this.emailValidator = new EmailValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "test@example.com",           // Standard valid email
        "user.name@example.com",      // Valid dot in local part
        "user+tag@example.com",       // Valid '+' in local part
        "user_name@example.com",      // Valid '_' in local part
        "user-name@example.com",      // Valid '-' in local part
        "user&name*@example.com",     // Valid '&' and '*' in local part (explicitly allowed by your regex)
        "123user@example.com",        // Starts with digits
        "user@sub.example.com",       // Multiple domain segments
        "user@my-domain.com",         // Hyphens allowed in domain
        "user@example.co",            // TLD length of 2 (minimum allowed)
        "user@example.network"        // TLD length of 7 (maximum allowed)
    })
    void testValidEmails(String email){
        assertTrue(this.emailValidator.isValid(email, null),
                "Email is valid : " + email);
    }

    @ParameterizedTest()
    @ValueSource(strings = {
            "user!name@example.com",      // Fails: '!' is not in your allowed list [a-zA-Z0-9_+&*-]
            ".user@example.com",          // Fails: Cannot start with a dot
            "user.@example.com",          // Fails: Cannot end the local part with a dot
            "user..name@example.com",     // Fails: Consecutive dots are not allowed by (?:\\.[...])*
            "user@my_domain.com",         // Fails: Underscores '_' are not allowed in the domain part, only hyphens '-'
            "user@example.c",             // Fails: TLD length is 1 (needs to be 2-7)
            "user@example.internat",      // Fails: TLD length is 8 (needs to be 2-7)
            "user@example.com1",          // Fails: Digits are not allowed in the TLD
            "user@example",               // Fails: Missing TLD
            "@example.com",               // Fails: Missing local part
            "userexample.com",            // Fails: Missing '@' symbol
            "user @example.com"           // Fails: Spaces are not allowed
    })
    void testInvalidEmails(String email){
        assertFalse(this.emailValidator.isValid(email, null),
                "Email should be invalid: " + email);
    }

}
