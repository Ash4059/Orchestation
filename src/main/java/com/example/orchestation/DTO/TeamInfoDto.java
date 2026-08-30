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
public class TeamInfoDto {
    
    private Long id;
    private String name;
    private String description;
    private List<String> employeeNames;
    private String workSpaceName;

}
