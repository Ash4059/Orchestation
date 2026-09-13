package com.example.orchestation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.orchestation.Entity.TaskUpdate;

@Repository
public interface TaskUpdatesRepository extends JpaRepository<TaskUpdate, Long> {

}
