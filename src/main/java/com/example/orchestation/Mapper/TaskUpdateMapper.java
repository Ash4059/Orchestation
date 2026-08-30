package com.example.orchestation.Mapper;

import com.example.orchestation.DTO.TaskUpdateInfoDto;
import com.example.orchestation.DTO.TaskUpdateRequestDto;
import com.example.orchestation.DTO.TaskUpdateResponseDto;
import com.example.orchestation.Entity.TaskUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface TaskUpdateMapper {

    TaskUpdateInfoDto toDto(TaskUpdate taskUpdate);

    TaskUpdate toEntity(TaskUpdateRequestDto dto);

    void updateEntityFromDto(TaskUpdateRequestDto dto, @MappingTarget TaskUpdate taskUpdate);

    default TaskUpdateResponseDto toResponseDto(TaskUpdate taskUpdate, String message) {
        return new TaskUpdateResponseDto(toDto(taskUpdate), message);
    }

}
