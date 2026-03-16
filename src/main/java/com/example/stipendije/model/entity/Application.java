package com.example.stipendije.model.entity;

import com.example.stipendije.model.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
@Getter
@Setter
@NoArgsConstructor
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(nullable = false)
    private String academicYear;

    @Column(nullable = false)
    private double totalPoints;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Application(Student student, String academicYear) {
        this.student = student;
        this.academicYear = academicYear;
        this.status = ApplicationStatus.PENDING;
        this.createdAt = LocalDateTime.now();
        this.totalPoints = 0;
    }
}
