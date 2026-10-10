package com.example.orchestation.Validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class AdultValidationTest {

    @Nested
    class MathematicalLogicTests{

        private AdultValidatorImpl adultValidator;

        @BeforeEach
        void setUp(){
            // Direct instantiation for blazing fast logic execution
            adultValidator = new AdultValidatorImpl();
        }

        @Test
        void whenDateOfBirthIsNull_thenInvalid(){
            assertFalse(adultValidator.isValid(null,null));
        }

        @Test
        void WhenExactly18YearsOld_thenValid(){
            LocalDate exactly18 = LocalDate.now().minusYears(18);
            assertTrue(adultValidator.isValid(exactly18, null));
        }

        @Test
        void When17YearsAnd364DaysOld_thenInvalid(){
            LocalDate almost18 = LocalDate.now().minusYears(18).plusDays(1);
            assertFalse(adultValidator.isValid(almost18,null));
        }

        @Test
        void When18YearsAnd1DaysOld_thenValid(){
            LocalDate justOver18 = LocalDate.now().minusYears(18).minusDays(1);
            assertTrue(adultValidator.isValid(justOver18,null));
        }

    }

    @Nested
    class IntegrationWiringTests{

        private static Validator validator;

        @BeforeAll
        public static void setUp(){
            // Bootstrapping the framework just once for this nested class
            try(ValidatorFactory factory = Validation.buildDefaultValidatorFactory()){
                validator = factory.getValidator();
            }
        }

        // Dummy DTO representing the payload where your custom annotation is used
        static class EmployeeRegistrationRequest {

            @AdultValidator
            LocalDate dateOfBirth;

            public EmployeeRegistrationRequest(LocalDate dateOfBirth){
                this.dateOfBirth = dateOfBirth;
            }
        }

        @Test
        void whenValidAge_thenValidationSucceeds(){
            EmployeeRegistrationRequest request = new EmployeeRegistrationRequest(LocalDate.now().minusYears(25));
            Set<ConstraintViolation<EmployeeRegistrationRequest>> voilations = validator.validate(request);
            assertTrue(voilations.isEmpty(), "Expected no validation errors for a 25-year-old");
        }

        @Test
        void whenUnderage_thenValidationFailsWithCorrectMessage(){
            EmployeeRegistrationRequest request = new EmployeeRegistrationRequest(LocalDate.now().minusYears(15));
            Set<ConstraintViolation<EmployeeRegistrationRequest>> voilations = validator.validate(request);
            assertEquals(1, voilations.size());

            ConstraintViolation<EmployeeRegistrationRequest> violation = voilations.iterator().next();
            assertEquals("Employee must be at least 18 years old", violation.getMessage());
            assertEquals("dateOfBirth", violation.getPropertyPath().toString());
        }

    }

}
