package com.example.orchestation.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDto {
    
    private String Name;
    private String Email;
    private String Role;
    private String TeamName;
    private LocalDateTime DateOfBirth;

}
