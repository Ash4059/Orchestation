package com.example.orchestation.Controller;

import com.example.orchestation.DTO.WorkSpaceInfoDto;
import com.example.orchestation.DTO.WorkSpaceRequestDto;
import com.example.orchestation.DTO.WorkSpaceResponseDto;
import com.example.orchestation.Services.WorkspaceServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/workspace")
public class WorkspaceController {

    private final WorkspaceServices workspaceServices;

    @PostMapping
    public ResponseEntity<?> saveWorkspace(@RequestBody WorkSpaceRequestDto workSpaceRequestDto) {
        WorkSpaceResponseDto workSpaceResponseDto = workspaceServices.saveWorkspace(workSpaceRequestDto);
        return ResponseEntity.ok(workSpaceResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findWorkspaceById(@PathVariable Long id) {
        WorkSpaceInfoDto workSpaceInfoDto = workspaceServices.findWorkspaceById(id);
        return ResponseEntity.ok(workSpaceInfoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateWorkspace(@PathVariable Long id, @RequestBody WorkSpaceRequestDto workSpaceRequestDto) {
        WorkSpaceResponseDto workSpaceResponseDto = workspaceServices.updateWorkspace(id, workSpaceRequestDto);
        return ResponseEntity.ok(workSpaceResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWorkspace(@PathVariable Long id) {
        WorkSpaceResponseDto workSpaceResponseDto = workspaceServices.deleteWorkspace(id);
        return ResponseEntity.ok(workSpaceResponseDto);
    }

}
