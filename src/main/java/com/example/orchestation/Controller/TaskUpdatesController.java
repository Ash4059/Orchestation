package com.example.orchestation.Controller;

import com.example.orchestation.DTO.TaskUpdateInfoDto;
import com.example.orchestation.DTO.TaskUpdateRequestDto;
import com.example.orchestation.DTO.TaskUpdateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.orchestation.Services.TaskUpdatesServices;

@RequiredArgsConstructor
@RestController
@RequestMapping("/task/updates")
public class TaskUpdatesController {

    private final TaskUpdatesServices taskUpdatesService;

    @GetMapping("/{taskId}")
    public ResponseEntity<?> getTaskUpdates(@PathVariable Long taskId) {
        TaskUpdateInfoDto taskUpdate = taskUpdatesService.findTaskUpdateById(taskId);
        return ResponseEntity.ok(taskUpdate);
    }

    @PostMapping
    public ResponseEntity<?> saveTaskUpdate(@RequestBody TaskUpdateRequestDto taskUpdateRequestDto) {
        TaskUpdateResponseDto taskUpdateResponseDto = taskUpdatesService.saveTaskUpdate(taskUpdateRequestDto);
        return ResponseEntity.ok(taskUpdateResponseDto);
    }

    @PutMapping("/{taskUpdateId}")
    public ResponseEntity<?> updateTaskUpdate(
            @PathVariable Long taskUpdateId,
            @RequestBody TaskUpdateRequestDto taskUpdateRequestDto) {
        TaskUpdateResponseDto taskUpdateResponseDto =
                taskUpdatesService.taskUpdateUpdate(taskUpdateId, taskUpdateRequestDto);
        return ResponseEntity.ok(taskUpdateResponseDto);
    }

    @DeleteMapping("/{taskUpdateId}")
    public ResponseEntity<?> deleteTaskUpdate(@PathVariable Long taskUpdateId) {
        TaskUpdateResponseDto taskUpdateResponseDto = taskUpdatesService.deleteTaskUpdate(taskUpdateId);
        return ResponseEntity.ok(taskUpdateResponseDto);
    }

}
