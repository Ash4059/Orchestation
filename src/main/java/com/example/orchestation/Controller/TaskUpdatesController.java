package com.example.orchestation.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.orchestation.Entity.TaskUpdate;
import com.example.orchestation.Services.TaskUpdatesServices;

@RestController
@RequestMapping("/task/updates")
public class TaskUpdatesController {

    private final TaskUpdatesServices taskUpdatesService;

    public TaskUpdatesController(TaskUpdatesServices taskUpdatesService) {
        this.taskUpdatesService = taskUpdatesService;
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<?> getTaskUpdates(@PathVariable Long taskId) {
        TaskUpdate taskUpdate = taskUpdatesService.findTaskUpdateById(taskId);
        return ResponseEntity.ok(taskUpdate);
    }

    @PostMapping
    public ResponseEntity<?> saveTaskUpdate(@RequestBody TaskUpdate taskUpdate) {
        taskUpdatesService.saveTaskUpdate(taskUpdate);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTaskUpdate(@PathVariable Long taskId) {
        taskUpdatesService.deleteTaskUpdate(taskId);
        return ResponseEntity.ok().build();
    }

}
