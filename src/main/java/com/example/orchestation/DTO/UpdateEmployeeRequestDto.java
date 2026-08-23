package com.example.orchestation.DTO;

import java.time.LocalDateTime;

import com.example.orchestation.Entity.Role;
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
    
    @Size(min = 2, max = 50)
    private String Name;

    @PasswordValidator
    private String Password;

    @EnumValue(enumClass = Role.class, ignoreCase = true)
    private String Role;

    private Long TeamId;
    private LocalDateTime DateOfBirth;

}
