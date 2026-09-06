package com.example.orchestation.DTO;

import com.example.orchestation.Entity.Status;
import com.example.orchestation.Validation.EnumValue;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskUpdateRequestDto {
    
    @EnumValue(enumClass = Status.class, ignoreCase = true)
    private String status;

    @NotNull(message = "Employee ID cannot be null")
    private Long employeeId;

    @NotNull(message = "Task ID cannot be null")
    private Long taskId;

}
