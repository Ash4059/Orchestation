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
public class TaskUpdateInfoDto {
    
    private Long Id;
    private Long TaskId;
    private String Status;
    private String EmployeeName;
    private LocalDateTime UpdateTime;

}
