package com.example.orchestation.Repository;

import org.springframework.stereotype.Repository;

import com.example.orchestation.Entity.TaskUpdate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class TaskUpdatesRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveTaskUpdate(TaskUpdate taskUpdate) {
        entityManager.persist(taskUpdate);
    }

    public TaskUpdate findTaskUpdateById(Long id) {
        return entityManager.find(TaskUpdate.class, id);
    }

    public void updateTaskUpdate(TaskUpdate taskUpdate) {
        entityManager.merge(taskUpdate);
    }

    public void deleteTaskUpdate(TaskUpdate taskUpdate) {
        entityManager.remove(taskUpdate);
    }

}
