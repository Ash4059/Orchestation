package com.example.orchestation.Repository;

import org.springframework.stereotype.Repository;

import com.example.orchestation.Entity.TaskUpdate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Optional;

@Repository
public class TaskUpdatesRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveTaskUpdate(TaskUpdate taskUpdate) {
        entityManager.persist(taskUpdate);
    }

    public Optional<TaskUpdate> findTaskUpdateById(Long id) {
        return Optional.ofNullable(entityManager.find(TaskUpdate.class, id));
    }

    public void deleteTaskUpdate(TaskUpdate taskUpdate) {
        entityManager.remove(taskUpdate);
    }

}
