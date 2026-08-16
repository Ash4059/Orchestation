package com.example.orchestation.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

public class Updates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID Id;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    private LocalDateTime updateTime;

    @PrePersist
    private void onCreate(){
        this.updateTime = LocalDateTime.now();
    }
}
