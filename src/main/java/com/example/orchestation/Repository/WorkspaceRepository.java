package com.example.orchestation.Repository;

import org.springframework.stereotype.Repository;

import com.example.orchestation.Entity.Workspace;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class WorkspaceRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveWorkspace(Workspace workspace) {
        entityManager.persist(workspace);
    }

    public Workspace findWorkspaceById(Long id) {
        return entityManager.find(Workspace.class, id);
    }

    public void updateWorkspace(Workspace workspace) {
        entityManager.merge(workspace);
    }

    public void deleteWorkspace(Workspace workspace) {
        entityManager.remove(workspace);
    }

}
