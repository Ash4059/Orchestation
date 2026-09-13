package com.example.orchestation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.orchestation.Entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
