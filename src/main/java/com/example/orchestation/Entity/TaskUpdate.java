package com.example.orchestation.Entity;

import java.time.LocalDateTime;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class TaskUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(updatable = false, nullable = false)
    private Long Id;

    @NotNull(message = "Update content cannot be null")
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @NotNull(message = "Task cannot be null")
    @JsonAlias("status")
    @Enumerated(EnumType.STRING)
    private Status Status;

    @NotNull(message = "Task cannot be null")
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    private LocalDateTime updateTime;

    @PrePersist
    private void onCreate(){
        this.updateTime = LocalDateTime.now();
    }
}
