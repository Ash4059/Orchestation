package com.example.orchestation.Controller;

import com.example.orchestation.DTO.TaskInfoDto;
import com.example.orchestation.DTO.TaskRequestDto;
import com.example.orchestation.DTO.TaskResponseDto;
import com.example.orchestation.DTO.TaskUpdateRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.orchestation.Entity.Task;
import com.example.orchestation.Services.TaskServices;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskServices taskServices;

    public TaskController(TaskServices taskServices) {
        this.taskServices = taskServices;
    }

    @PostMapping
    public ResponseEntity<?> saveTask(@RequestBody TaskRequestDto taskRequestDto) {
        TaskResponseDto taskResponseDto = taskServices.saveTask(taskRequestDto);
        return ResponseEntity.ok(taskResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findTaskById(@PathVariable Long id) {
        TaskInfoDto taskInfoDto = taskServices.findTaskById(id);
        return ResponseEntity.ok(taskInfoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody TaskRequestDto taskRequestDto) {
        TaskResponseDto taskResponseDto = taskServices.updateTask(id, taskRequestDto);
        return ResponseEntity.ok(taskResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        TaskResponseDto taskResponseDto = taskServices.deleteTask(id);
        return ResponseEntity.ok(taskResponseDto);
    }

}
