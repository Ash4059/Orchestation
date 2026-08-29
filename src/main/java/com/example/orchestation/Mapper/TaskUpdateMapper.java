package com.example.orchestation.Mapper;

import com.example.orchestation.DTO.TaskUpdateInfoDto;
import com.example.orchestation.DTO.TaskUpdateRequestDto;
import com.example.orchestation.Entity.TaskUpdate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskUpdateMapper {

    TaskUpdateInfoDto toDto(TaskUpdate taskUpdate);

    TaskUpdate toEntity(TaskUpdateRequestDto dto);

}
