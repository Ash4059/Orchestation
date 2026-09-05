package com.example.orchestation.Mapper;

import com.example.orchestation.DTO.TaskInfoDto;
import com.example.orchestation.DTO.TaskRequestDto;
import com.example.orchestation.DTO.TaskResponseDto;
import com.example.orchestation.DTO.TaskUpdateInfoDto;
import com.example.orchestation.DTO.TaskUpdateResponseDto;
import com.example.orchestation.Entity.Employee;
import com.example.orchestation.Entity.Status;
import com.example.orchestation.Entity.Task;
import com.example.orchestation.Entity.TaskUpdate;
import com.example.orchestation.Repository.EmployeeRepository;
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
public abstract class TaskMapper {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Mapping(target = "employeeName", source = "assignedTo.name")
    public abstract TaskInfoDto toDto(Task task);

    @Mapping(target = "assignedTo", source = "employeeId")
    public abstract Task toEntity(TaskRequestDto dto);

    @Mapping(target = "assignedTo", source = "employeeId")
    public abstract void updateEntityFromDto(TaskRequestDto dto, @MappingTarget Task task);

    public TaskResponseDto toResponseDto(Task task, String message){
        return new TaskResponseDto(toDto(task), message);
    }

    protected Employee mapEmployeeIdToEmployee(Long employeeId) {
        if (employeeId == null) {
            return null;
        }
        return employeeRepository.findEmployeeById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with ID: " + employeeId));
    }

    protected Status mapStatus(String status) {
        if (status == null) {
            return null;
        }
        return Status.valueOf(status.toUpperCase());
    }

    protected TaskUpdateResponseDto mapTaskUpdateToResponse(TaskUpdate taskUpdate) {
        if (taskUpdate == null) {
            return null;
        }
        TaskUpdateInfoDto info = new TaskUpdateInfoDto(
                taskUpdate.getId(),
                taskUpdate.getTask() == null ? null : taskUpdate.getTask().getId(),
                taskUpdate.getStatus() == null ? null : taskUpdate.getStatus().name(),
                taskUpdate.getEmployee() == null ? null : taskUpdate.getEmployee().getName(),
                taskUpdate.getUpdateTime());
        return new TaskUpdateResponseDto(info, null);
    }
}
