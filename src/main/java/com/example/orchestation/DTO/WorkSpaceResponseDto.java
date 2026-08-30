package com.example.orchestation.DTO;

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
    
    private WorkSpaceInfoDto workSpaceInfoDto;
    private String message;

}
