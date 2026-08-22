package com.example.orchestation.Services;

import org.springframework.stereotype.Service;

import com.example.orchestation.Entity.TaskUpdate;
import com.example.orchestation.Repository.TaskUpdatesRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskUpdatesServices {

    private final TaskUpdatesRepository taskUpdatesRepository;

    public TaskUpdatesServices(TaskUpdatesRepository taskUpdatesRepository){
        this.taskUpdatesRepository = taskUpdatesRepository;
    }

    @Transactional
    public void saveTaskUpdate(TaskUpdate taskUpdate) {
        taskUpdatesRepository.saveTaskUpdate(taskUpdate);
    }

    public TaskUpdate findTaskUpdateById(Long id) {
        return taskUpdatesRepository.findTaskUpdateById(id);
    }

    @Transactional
    public void deleteTaskUpdate(Long id) {
        TaskUpdate taskUpdate = findTaskUpdateById(id);
        if (taskUpdate != null) {
            taskUpdatesRepository.deleteTaskUpdate(taskUpdate);
        } else {
            throw new IllegalArgumentException("Task Update with ID " + id + " not found.");
        }
    }

}
