package com.example.orchestation.Repository;

import org.springframework.stereotype.Repository;

import com.example.orchestation.Entity.Task;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class TaskRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Task task) {
        entityManager.persist(task);
    }

    public Task findById(Long id) {
        return entityManager.find(Task.class, id);
    }

    public void update(Task task) {
        entityManager.merge(task);
    }

    public void delete(Task task) {
        entityManager.remove(task);
    }

}
