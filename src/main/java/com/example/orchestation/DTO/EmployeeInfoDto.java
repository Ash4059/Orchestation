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
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeInfoDto {
    
    private Long id;
    private String name;
    private String email;
    private String role;
    private String teamName;
    private LocalDateTime dateOfBirth;

}
