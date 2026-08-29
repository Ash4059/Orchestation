package com.example.orchestation.Mapper;

import com.example.orchestation.DTO.WorkSpaceInfoDto;
import com.example.orchestation.DTO.WorkSpaceRequestDto;
import com.example.orchestation.Entity.Workspace;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkspaceMapper {

    WorkSpaceInfoDto toDto(Workspace workspace);
    Workspace toEntity(WorkSpaceRequestDto dto);

}
