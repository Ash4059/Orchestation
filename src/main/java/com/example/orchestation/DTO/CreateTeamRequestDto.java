package com.example.orchestation.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTeamRequestDto {
    
    @NotNull
    @Size(min = 2, max = 50)
    private String Name;

    @NotNull
    @Size(min = 2, max = 200)
    private String Description;

    private Long WorkspaceId;

}
