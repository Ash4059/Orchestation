package com.example.orchestation.Validation;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EnumValidatorTest {

    private EnumValueValidator enumValueValidator;

    @Mock
    private EnumValue enumValueAnnotation;

    @Mock
    private ConstraintValidatorContext context;

    @Mock
    private ConstraintValidatorContext.ConstraintViolationBuilder violationBuilder;

    // A dummy enum specifically for testing
    private enum TestRole{
        ADMIN, USER, GUEST
    }

    @BeforeEach
    void setUp(){
        this.enumValueValidator = new EnumValueValidator();

        // Mock the annotation configuration to return our test role enum
        doReturn(TestRole.class).when(enumValueAnnotation).enumClass();
    }

    @Test
    void testCaseSensitive_ValidValues(){
        doReturn(false).when(enumValueAnnotation).ignoreCase();
        enumValueValidator.initialize(enumValueAnnotation);

        assertTrue(enumValueValidator.isValid("ADMIN", context));
        verify(context, never()).disableDefaultConstraintViolation();
    }

    @Test
    void testCaseSensitive_InvalidValues(){
        doReturn(false).when(enumValueAnnotation).ignoreCase();
        enumValueValidator.initialize(enumValueAnnotation);
        when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(violationBuilder);

        // Lowercase should fail when case-sensitive
        assertFalse(enumValueValidator.isValid("admin", context));

        verify(context, times(1)).disableDefaultConstraintViolation();
    }

    @Test
    void testCaseInsensitive_ValidValues(){
        // Setup mock for case-insensitive
        doReturn(true).when(enumValueAnnotation).ignoreCase();
        enumValueValidator.initialize(enumValueAnnotation);

        // Mixed case and lowercase should pass
        assertTrue(enumValueValidator.isValid("admin", context));
        assertTrue(enumValueValidator.isValid("aDmIn", context));
        assertTrue(enumValueValidator.isValid("USER", context));
        assertTrue(enumValueValidator.isValid("guest", context));

        verify(context, never()).disableDefaultConstraintViolation();

    }

    @Test
    void testCaseInsensitive_InvalidValues(){
        doReturn(true).when(enumValueAnnotation).ignoreCase();
        enumValueValidator.initialize(enumValueAnnotation);
        when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(violationBuilder);

        // Completely wrong strings should still fail
        assertFalse(enumValueValidator.isValid("SUPER_ADMIN", context));

        verify(context, times(1)).disableDefaultConstraintViolation();
    }
}
