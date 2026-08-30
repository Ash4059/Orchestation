package com.example.orchestation.DTO;

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
    
    private TeamInfoDto teamInfoDto;
    private String message;

}
