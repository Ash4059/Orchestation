package com.example.orchestation.Entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
public class Workspace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(updatable = false, nullable = false)
    private Long Id;

    private String Name;

    private String Description;

    @OneToMany(mappedBy = "workspace")
    private List<Team> Teams;
}
