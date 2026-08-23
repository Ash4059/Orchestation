package com.example.orchestation.DTO;

import com.example.orchestation.Entity.Status;
import com.example.orchestation.Validation.EnumValue;

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

    @EnumValue(enumClass = Status.class, ignoreCase = true)
    private String Status;

    private Long EmployeeId;

}
