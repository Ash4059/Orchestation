package com.example.orchestation.Mapper;

import com.example.orchestation.DTO.*;
import com.example.orchestation.Entity.Team;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface TeamMapper {

    TeamInfoDto toDto(Team team);

    Team toEntity(CreateTeamRequestDto dto);

    Team toEntity(UpdateTeamRequestDto dto);

    void updateEntityFromDto(UpdateTeamRequestDto dto, @MappingTarget Team team);

    default TeamResponseDto toResponseDto(Team team, String message) {
        return new TeamResponseDto(toDto(team), message);
    }

}
