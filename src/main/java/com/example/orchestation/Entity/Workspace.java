package com.example.orchestation.Entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Workspace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(updatable = false, nullable = false)
    private Long id;
    
    @JsonAlias("name")
    @Column(unique = true, nullable = false)
    private String name;

    @JsonAlias("description")
    private String description;

    @OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Team> teams = new ArrayList<>();

    @OneToMany(mappedBy = "workspace", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Employee> employees = new ArrayList<>();

    public void addTeam(Team team) {
        teams.add(team);
        team.setWorkspace(this);
    }

    public void removeTeam(Team team) {
        teams.remove(team);
        team.setWorkspace(null);
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        employee.setWorkspace(this);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        employee.setWorkspace(null);
    }
}
