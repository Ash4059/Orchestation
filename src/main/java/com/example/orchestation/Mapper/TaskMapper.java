package com.example.orchestation.Mapper;

import com.example.orchestation.DTO.TaskInfoDto;
import com.example.orchestation.DTO.TaskRequestDto;
import com.example.orchestation.Entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskInfoDto toDto(Task task);

    Task toEntity(TaskRequestDto dto);

}
