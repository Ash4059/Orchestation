package com.example.orchestation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.orchestation.Entity.Workspace;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Optional;

public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
}
