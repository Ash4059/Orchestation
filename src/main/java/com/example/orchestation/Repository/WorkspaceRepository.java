package com.example.orchestation.Repository;

import org.springframework.stereotype.Repository;

import com.example.orchestation.Entity.Workspace;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Optional;

@Repository
public class WorkspaceRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveWorkspace(Workspace workspace) {
        entityManager.persist(workspace);
    }

    public Optional<Workspace> findWorkspaceById(Long id) {
        return Optional.ofNullable(entityManager.find(Workspace.class, id));
    }
    
    public void deleteWorkspace(Workspace workspace) {
        entityManager.remove(workspace);
    }

}
