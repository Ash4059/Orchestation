package com.example.orchestation.Services;

import com.example.orchestation.DTO.TaskInfoDto;
import com.example.orchestation.DTO.TaskRequestDto;
import com.example.orchestation.DTO.TaskResponseDto;
import com.example.orchestation.Entity.Employee;
import com.example.orchestation.Mapper.TaskMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.orchestation.Entity.Task;
import com.example.orchestation.Repository.TaskRepository;

import jakarta.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class TaskServices {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    private Task getTaskById(Long Id){
        return taskRepository.findById(Id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with Id : " + Id));
    }

    @Transactional
    public TaskResponseDto saveTask(TaskRequestDto taskRequestDto) {
        Task saveTask = taskMapper.toEntity(taskRequestDto);
        taskRepository.save(saveTask);
        String message = String.format("Task with ID: %s, created successfully!!!", saveTask.getId());
        return taskMapper.toResponseDto(saveTask, message);
    }

    public TaskInfoDto findTaskById(Long id) {
        Task task = getTaskById(id);
        return taskMapper.toDto(task);
    }

    @Transactional
    public TaskResponseDto updateTask(Long id, TaskRequestDto taskRequestDto) {
        Task existingTask = getTaskById(id);

        taskMapper.updateEntityFromDto(taskRequestDto, existingTask);

        //Commit triggers automatic SQL update for changed columns
        String message = String.format("Task with ID %d updated successfully", id);
        return taskMapper.toResponseDto(existingTask, message);
    }

    @Transactional
    public TaskResponseDto deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);

        //Commit triggers automatic SQL update for changed columns
        String message = String.format("Task with ID %d deleted successfully", id);
        return taskMapper.toResponseDto(task,message);
    }

}
