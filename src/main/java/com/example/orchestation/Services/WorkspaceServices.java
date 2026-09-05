package com.example.orchestation.Services;

import com.example.orchestation.DTO.WorkSpaceInfoDto;
import com.example.orchestation.DTO.WorkSpaceRequestDto;
import com.example.orchestation.DTO.WorkSpaceResponseDto;
import com.example.orchestation.Entity.Workspace;
import com.example.orchestation.Mapper.WorkspaceMapper;
import com.example.orchestation.Repository.WorkspaceRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WorkspaceServices {

    private final WorkspaceRepository workspaceRepository;
    private final WorkspaceMapper workspaceMapper;

    private Workspace getWorkSpaceById(Long Id) {
        return workspaceRepository.findWorkspaceById(Id)
                .orElseThrow(() -> new EntityNotFoundException("Workspace not found with Id: " + Id));
    }

    @Transactional
    public WorkSpaceResponseDto saveWorkspace(WorkSpaceRequestDto workSpaceRequestDto) {
        Workspace workspace = workspaceMapper.toEntity(workSpaceRequestDto);
        workspaceRepository.saveWorkspace(workspace);
        String message = String.format("WorkSpace with ID: %s, created successfully!!!", workspace.getId());
        return workspaceMapper.toResponseDto(workspace, message);
    }

    public WorkSpaceInfoDto findWorkspaceById(Long id) {
        Workspace workspace = getWorkSpaceById(id);
        return workspaceMapper.toDto(workspace);
    }

    @Transactional
    public WorkSpaceResponseDto updateWorkspace(Long id, WorkSpaceRequestDto workSpaceRequestDto) {
        Workspace workspace = getWorkSpaceById(id);
        workspaceMapper.updateEntityFromDto(workSpaceRequestDto, workspace);

        //Commit triggers automatic SQL update for changed columns
        String message = String.format("Workspace with ID %d updated successfully", id);
        return workspaceMapper.toResponseDto(workspace, message);
    }

    @Transactional
    public WorkSpaceResponseDto deleteWorkspace(Long id) {
        Workspace workspace = getWorkSpaceById(id);
        workspaceRepository.deleteWorkspace(workspace);

        //Commit triggers automatic SQL update for changed columns
        String message = String.format("Workspace with ID %d deleted successfully", id);
        return workspaceMapper.toResponseDto(workspace, message);
    }

}
