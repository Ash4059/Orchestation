package com.example.orchestation.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String Id;

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
