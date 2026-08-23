package com.example.orchestation.DTO;

import java.util.List;

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
public class TaskResponseDto {
    
    private String Title;
    private String Description;
    private String Status;
    private String EmployeeName;
    private List<TaskUpdateResponseDto> updates;

}
