package com.example.orchestation.DTO;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.orchestation.Entity.Role;
import com.example.orchestation.Validation.AdultValidator;
import com.example.orchestation.Validation.EnumValue;
import com.example.orchestation.Validation.PasswordValidator;
import com.example.orchestation.Validation.ValidEmail;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeRequestDto {
    
    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotNull (message = "Email cannot be null")
    @ValidEmail
    private String email;

    @NotNull (message = "Password cannot be null")
    @PasswordValidator
    private String password;

    @EnumValue(enumClass = Role.class, ignoreCase = true)
    private String role;

    @NotNull(message = "Team ID cannot be null")
    private Long teamId;
    
    @NotNull (message = "Date of Birth cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso=DateTimeFormat.ISO.DATE)
    @AdultValidator
    private LocalDate dateOfBirth;

}
