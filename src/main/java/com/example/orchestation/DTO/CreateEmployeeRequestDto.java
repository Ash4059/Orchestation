package com.example.orchestation.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeRequestDto {
    
    private String Name;
    private String Email;
    private String Password;
    private String Role;
    private Long TeamId;
    private LocalDateTime DateOfBirth;

}
