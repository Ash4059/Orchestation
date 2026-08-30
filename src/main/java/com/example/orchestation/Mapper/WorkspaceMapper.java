package com.example.orchestation.Mapper;

import com.example.orchestation.DTO.WorkSpaceInfoDto;
import com.example.orchestation.DTO.WorkSpaceRequestDto;
import com.example.orchestation.DTO.WorkSpaceResponseDto;
import com.example.orchestation.Entity.Workspace;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface WorkspaceMapper {

    WorkSpaceInfoDto toDto(Workspace workspace);

    Workspace toEntity(WorkSpaceRequestDto dto);

    void updateEntityFromDto(WorkSpaceRequestDto dto, @MappingTarget Workspace workspace);

    default WorkSpaceResponseDto toResponseDto(Workspace workspace, String message) {
        return new WorkSpaceResponseDto(toDto(workspace), message);
    }
}
