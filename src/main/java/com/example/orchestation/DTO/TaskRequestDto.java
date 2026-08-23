package com.example.orchestation.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDto {
    
    private String Title;
    private String Description;
    private String Status;
    private Long EmployeeId;

}
