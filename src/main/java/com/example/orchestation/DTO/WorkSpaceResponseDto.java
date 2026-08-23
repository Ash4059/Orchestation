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
@AllArgsConstructor
@NoArgsConstructor
public class WorkSpaceResponseDto {
    
    private String Name;
    private String Description;
    private List<String> TeamNames;

}
