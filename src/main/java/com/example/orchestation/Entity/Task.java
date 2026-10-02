package com.example.orchestation.Entity;

import java.time.LocalDateTime;
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
@Setter
@Getter
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(updatable = false, nullable = false)
    private Long id;

    @JsonAlias("title")
    private String title;

    @JsonAlias("description")
    private String description;

    @JsonAlias("status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee assignedTo;

    private LocalDateTime creationTime;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskUpdate> updates = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.creationTime = LocalDateTime.now();
    }

    public void addUpdate(TaskUpdate update) {
        updates.add(update);
        update.setTask(this);
    }

    public void removeUpdate(TaskUpdate update) {
        updates.remove(update);
        update.setTask(null);
    }
}
