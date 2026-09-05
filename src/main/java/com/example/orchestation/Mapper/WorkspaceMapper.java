package com.example.orchestation.Mapper;

import com.example.orchestation.DTO.WorkSpaceInfoDto;
import com.example.orchestation.DTO.WorkSpaceRequestDto;
import com.example.orchestation.DTO.WorkSpaceResponseDto;
import com.example.orchestation.Entity.Workspace;
import com.example.orchestation.Entity.Team;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface WorkspaceMapper {

    @Mapping(target = "teamNames", source = "teams")
    WorkSpaceInfoDto toDto(Workspace workspace);

    Workspace toEntity(WorkSpaceRequestDto dto);

    void updateEntityFromDto(WorkSpaceRequestDto dto, @MappingTarget Workspace workspace);

    default WorkSpaceResponseDto toResponseDto(Workspace workspace, String message) {
        return new WorkSpaceResponseDto(toDto(workspace), message);
    }

    default List<String> mapTeamsToNames(List<Team> teams) {
        if (teams == null) {
            return null;
        }
        return teams.stream()
                .map(Team::getName)
                .collect(Collectors.toList());
    }
}
