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
public class TeamResponseDto {
    
    private String Name;
    private String Description;
    private List<String> EmployeeNames;
    private String WorkSpaceName;

}
