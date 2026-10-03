package com.example.orchestation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.orchestation.Entity.Workspace;

public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
}
