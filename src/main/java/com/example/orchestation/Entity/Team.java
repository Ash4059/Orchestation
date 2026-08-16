package com.example.orchestation.Entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(updatable = false, nullable = false)
    private Long Id;

    private String Name;

    private String Description;

    @OneToMany(mappedBy = "team")
    private List<Employee> Employees;

    @ManyToOne
    @JoinColumn(name = "workspace_id")
    private Workspace workspace;
}
