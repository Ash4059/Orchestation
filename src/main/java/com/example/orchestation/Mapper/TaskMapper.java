package com.example.orchestation.Mapper;

import com.example.orchestation.DTO.TaskInfoDto;
import com.example.orchestation.DTO.TaskRequestDto;
import com.example.orchestation.DTO.TaskResponseDto;
import com.example.orchestation.Entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface TaskMapper {

    TaskInfoDto toDto(Task task);

    Task toEntity(TaskRequestDto dto);

    void updateEntityFromDto(TaskRequestDto dto, @MappingTarget Task task);

    default TaskResponseDto toResponseDto(Task task, String message){
        return new TaskResponseDto(toDto(task), message);
    }
}
