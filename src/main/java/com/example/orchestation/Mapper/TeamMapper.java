package com.example.orchestation.Mapper;

import com.example.orchestation.DTO.CreateTeamRequestDto;
import com.example.orchestation.DTO.TeamInfoDto;
import com.example.orchestation.DTO.UpdateTeamRequestDto;
import com.example.orchestation.Entity.Team;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    TeamInfoDto toDto(Team team);
    Team toEntity(CreateTeamRequestDto dto);
    Team toEntity(UpdateTeamRequestDto dto);

}
