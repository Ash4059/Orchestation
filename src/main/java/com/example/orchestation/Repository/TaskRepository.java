package com.example.orchestation.Repository;

import org.springframework.stereotype.Repository;

import com.example.orchestation.Entity.Task;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Optional;

@Repository
public class TaskRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Task task) {
        entityManager.persist(task);
    }

    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Task.class, id));
    }

    public void delete(Task task) {
        entityManager.remove(task);
    }

}
