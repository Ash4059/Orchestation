package com.example.orchestation.Entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private UUID Id;

    private String Title;

    private String Description;

    private Status Status;

    @OneToOne
    @Column(name = "user_id", nullable = false)
    private Employee AssignedTo;

    private LocalDateTime creationTime;

    @OneToMany(mappedBy = "task")
    private Updates updates;

    @PrePersist
    protected void onCreate() {
        this.creationTime = LocalDateTime.now();
    }
}
