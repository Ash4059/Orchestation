package com.example.orchestation.Services;

import com.example.orchestation.DTO.*;
import com.example.orchestation.Mapper.TaskUpdateMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import com.example.orchestation.Entity.TaskUpdate;
import com.example.orchestation.Repository.TaskUpdatesRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TaskUpdatesServices {

    private final TaskUpdatesRepository taskUpdatesRepository;
    private final TaskUpdateMapper taskUpdateMapper;

    private TaskUpdate getTaskUpdateById(Long Id){
        return taskUpdatesRepository.findTaskUpdateById(Id)
                .orElseThrow(() -> new EntityNotFoundException("TaskUpdate not found with Id : " + Id));
    }

    public TaskUpdateInfoDto findTaskUpdateById(Long Id){
        TaskUpdate taskUpdate = getTaskUpdateById(Id);
        return taskUpdateMapper.toDto(taskUpdate);
    }

    @Transactional
    public TaskUpdateResponseDto saveTaskUpdate(TaskUpdateRequestDto taskUpdateRequestDto) {
        TaskUpdate taskUpdate = taskUpdateMapper.toEntity(taskUpdateRequestDto);
        taskUpdatesRepository.saveTaskUpdate(taskUpdate);

        String message = String.format("Task with ID: %s, created successfully!!!", taskUpdate.getId());
        return taskUpdateMapper.toResponseDto(taskUpdate, message);
    }

    @Transactional
    public TaskUpdateResponseDto taskUpdateUpdate(Long id, TaskUpdateRequestDto dto) {
        TaskUpdate existingTaskUpdate = getTaskUpdateById(id);
        taskUpdateMapper.updateEntityFromDto(dto, existingTaskUpdate);

        //Commit triggers automatic SQL update for changed columns
        String message = String.format("TaskUpdate with ID %d updated successfully", id);
        return taskUpdateMapper.toResponseDto(existingTaskUpdate, message);
    }

    @Transactional
    public TaskUpdateResponseDto deleteTaskUpdate(Long id) {
        TaskUpdate taskUpdate = getTaskUpdateById(id);
        taskUpdatesRepository.deleteTaskUpdate(taskUpdate);

        //Commit triggers automatic SQL update for changed columns
        String message = String.format("TaskUpdate with ID %d deleted successfully", id);
        return taskUpdateMapper.toResponseDto(taskUpdate, message);
    }

}
