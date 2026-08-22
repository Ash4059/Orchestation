package com.example.orchestation.Entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(updatable = false, nullable = false)
    private Long Id;

    @JsonAlias("title")
    private String Title;

    @JsonAlias("description")
    private String Description;

    @JsonAlias("status")
    @Enumerated(EnumType.STRING)
    private Status Status;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Employee AssignedTo;

    private LocalDateTime creationTime;

    @OneToMany(mappedBy = "task")
    private List<TaskUpdate> updates;

    @PrePersist
    protected void onCreate() {
        this.creationTime = LocalDateTime.now();
    }
}
