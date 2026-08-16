package com.example.orchestation.Entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

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

    private String Title;

    private String Description;

    private Status Status;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Employee AssignedTo;

    private LocalDateTime creationTime;

    @OneToMany(mappedBy = "task")
    private List<Updates> updates;

    @PrePersist
    protected void onCreate() {
        this.creationTime = LocalDateTime.now();
    }
}
