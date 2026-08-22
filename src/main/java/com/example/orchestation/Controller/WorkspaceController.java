package com.example.orchestation.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.orchestation.Entity.Workspace;
import com.example.orchestation.Services.WorkspaceServices;

@RestController
@RequestMapping("/workspace")
public class WorkspaceController {

    private final WorkspaceServices workspaceServices;

    public WorkspaceController(WorkspaceServices workspaceServices) {
        this.workspaceServices = workspaceServices;
    }

    @PostMapping
    public ResponseEntity<?> saveWorkspace(@RequestBody Workspace workspace) {
        workspaceServices.saveWorkspace(workspace);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findWorkspaceById(@PathVariable Long id) {
        Workspace workspace = workspaceServices.findWorkspaceById(id);
        return ResponseEntity.ok(workspace);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateWorkspace(@PathVariable Long id, @RequestBody Workspace workspace) {
        workspaceServices.updateWorkspace(id, workspace);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWorkspace(@PathVariable Long id) {
        workspaceServices.deleteWorkspace(id);
        return ResponseEntity.ok().build();
    }

}
