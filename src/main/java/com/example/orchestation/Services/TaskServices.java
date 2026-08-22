package com.example.orchestation.Services;

import org.springframework.stereotype.Service;

import com.example.orchestation.Entity.Task;
import com.example.orchestation.Repository.TaskRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskServices {

    private final TaskRepository taskRepository;

    public TaskServices(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @Transactional
    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    public Task findTaskById(Long id) {
        return taskRepository.findById(id);
    }

    @Transactional
    public void updateTask(Long id, Task task) {
        Task existingTask = findTaskById(id);
        if (existingTask != null) {
            taskRepository.update(task);
        } else {
            throw new IllegalArgumentException("Task with ID " + id + " not found.");
        }
    }

    @Transactional
    public void deleteTask(Long id) {
        Task task = findTaskById(id);
        if (task != null) {
            taskRepository.delete(task);
        } else {
            throw new IllegalArgumentException("Task with ID " + id + " not found.");
        }
    }

}
