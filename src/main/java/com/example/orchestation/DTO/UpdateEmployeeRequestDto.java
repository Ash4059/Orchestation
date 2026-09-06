package com.example.orchestation.DTO;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.orchestation.Entity.Role;
import com.example.orchestation.Validation.AdultValidator;
import com.example.orchestation.Validation.EnumValue;
import com.example.orchestation.Validation.PasswordValidator;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmployeeRequestDto {
    
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @PasswordValidator
    private String password;

    @EnumValue(enumClass = Role.class, ignoreCase = true)
    private String role;

    private Long teamId;

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso=DateTimeFormat.ISO.DATE)
    @AdultValidator 
    private LocalDateTime dateOfBirth;

}
