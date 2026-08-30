package com.example.orchestation.DTO;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTeamRequestDto {
    
    @Size(min = 2, max = 50)
    private String name;

    @Size(min = 2, max = 200)
    private String description;

}
