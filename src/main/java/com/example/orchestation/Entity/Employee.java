package com.example.orchestation.Entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(updatable = false, nullable = false)
    private Long Id;

    private String Name;

    private String Email;

    private String Password;

    private Role Role;

    private LocalDate DateOfBirth;

    private LocalDate DateOfJoining;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @PrePersist
    private void onCreate() {
        this.DateOfJoining = LocalDate.now();
    }
}
