package com.example.orchestation.DTO;

import com.example.orchestation.Entity.Status;
import com.example.orchestation.Validation.EnumValue;

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
public class TaskRequestDto {
    
    @NotNull (message = "title cannot be null")
    @Size (min = 2, max = 100, message = "Title must be between 2 and 100 characters")
    private String title;

    @NotNull (message = "description cannot be null")
    @Size (min = 2, max = 500, message = "Description must be between 2 and 500 characters")
    private String description;

    @EnumValue(enumClass = Status.class, ignoreCase = true)
    private String status;

    @NotNull (message = "Employee ID cannot be null")
    private Long employeeId;

}
