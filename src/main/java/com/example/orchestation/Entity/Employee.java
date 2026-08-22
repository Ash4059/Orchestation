package com.example.orchestation.Entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @JsonAlias("name")
    private String Name;

    @JsonAlias("email")
    private String Email;

    @JsonAlias("password")
    private String Password;

    @JsonAlias("role")
    @Enumerated(EnumType.STRING)
    private Role Role;

    @JsonAlias("date_of_birth")
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
