package com.example.orchestation.Entity;

import java.time.LocalDate;

import com.example.orchestation.Validation.PasswordValidator;
import com.example.orchestation.Validation.ValidEmail;
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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    @JsonAlias("name")
    private String Name;

    @ValidEmail
    @JsonAlias("email")
    private String Email;

    @PasswordValidator
    @JsonAlias("password")
    private String Password;

    @NotNull(message = "Role cannot be null")
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
