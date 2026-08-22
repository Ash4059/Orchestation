package com.example.orchestation.Services;

import org.springframework.stereotype.Service;

import com.example.orchestation.Entity.Workspace;
import com.example.orchestation.Repository.WorkspaceRepository;

import jakarta.transaction.Transactional;

@Service
public class WorkspaceServices {

    private final WorkspaceRepository workspaceRepository;

    public WorkspaceServices(WorkspaceRepository workspaceRepository){
        this.workspaceRepository = workspaceRepository;
    }

    @Transactional
    public void saveWorkspace(Workspace workspace) {
        workspaceRepository.saveWorkspace(workspace);
    }

    public Workspace findWorkspaceById(Long id) {
        return workspaceRepository.findWorkspaceById(id);
    }

    @Transactional
    public void updateWorkspace(Long id, Workspace workspace) {
        Workspace existingWorkspace = findWorkspaceById(id);
        if (existingWorkspace != null) {
            existingWorkspace.setName(workspace.getName());
            existingWorkspace.setDescription(workspace.getDescription());
        } else {
            throw new IllegalArgumentException("Workspace with ID " + id + " not found.");
        }
    }

    @Transactional
    public void deleteWorkspace(Long id) {
        Workspace workspace = findWorkspaceById(id);
        if (workspace != null) {
            workspaceRepository.deleteWorkspace(workspace);
        } else {
            throw new IllegalArgumentException("Workspace with ID " + id + " not found.");
        }
    }

}
