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
    
    private Long id;
    private Long taskId;
    private String status;
    private String employeeName;
    private LocalDateTime updateTime;

}
