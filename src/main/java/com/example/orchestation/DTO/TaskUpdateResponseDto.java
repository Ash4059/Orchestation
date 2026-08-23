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
public class TaskUpdateResponseDto {
    
    private Long TaskId;
    private String Status;
    private String EmployeeName;
    private LocalDateTime UpdateTime;

}
