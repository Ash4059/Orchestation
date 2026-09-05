package com.example.orchestation.Mapper;

import com.example.orchestation.DTO.TaskUpdateInfoDto;
import com.example.orchestation.DTO.TaskUpdateRequestDto;
import com.example.orchestation.DTO.TaskUpdateResponseDto;
import com.example.orchestation.Entity.Employee;
import com.example.orchestation.Entity.Status;
import com.example.orchestation.Entity.TaskUpdate;
import com.example.orchestation.Entity.Task;
import com.example.orchestation.Repository.EmployeeRepository;
import com.example.orchestation.Repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class TaskUpdateMapper {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Mapping(target = "taskId", source = "task.id")
    @Mapping(target = "employeeName", source = "employee.name")
    public abstract TaskUpdateInfoDto toDto(TaskUpdate taskUpdate);

    @Mapping(target = "employee", source = "employeeId")
    @Mapping(target = "task", source = "taskId")
    public abstract TaskUpdate toEntity(TaskUpdateRequestDto dto);

    @Mapping(target = "employee", source = "employeeId")
    @Mapping(target = "task", source = "taskId")
    public abstract void updateEntityFromDto(TaskUpdateRequestDto dto, @MappingTarget TaskUpdate taskUpdate);

    public TaskUpdateResponseDto toResponseDto(TaskUpdate taskUpdate, String message) {
        return new TaskUpdateResponseDto(toDto(taskUpdate), message);
    }

    protected Employee mapEmployeeIdToEmployee(Long employeeId) {
        if (employeeId == null) {
            return null;
        }
        return employeeRepository.findEmployeeById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with ID: " + employeeId));
    }

    protected Task mapTaskIdToTask(Long taskId) {
        if (taskId == null) {
            return null;
        }
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with ID: " + taskId));
    }

    protected Status mapStatus(String status) {
        if (status == null) {
            return null;
        }
        return Status.valueOf(status.toUpperCase());
    }
}
