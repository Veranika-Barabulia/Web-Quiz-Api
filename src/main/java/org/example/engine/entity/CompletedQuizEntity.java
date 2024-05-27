package org.example.engine.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "completed_quiz")
public class CompletedQuizEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "quiz_id")
    private int quizId;

    @Column(name = "user_name")
    private String username;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;
}
