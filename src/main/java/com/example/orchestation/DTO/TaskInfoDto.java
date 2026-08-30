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
public class TaskInfoDto {
    
    private Long id;
    private String title;
    private String description;
    private String status;
    private String employeeName;
    private List<TaskUpdateResponseDto> updates;


}
