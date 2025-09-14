package com.examly.springapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    private Long userId;
    private String studentName;
    private int score;
    private int totalQuestions;
    private LocalDateTime completedAt;

    @OneToMany(mappedBy = "quizAttempt", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers;

    @PrePersist
    protected void onComplete() {
        this.completedAt = LocalDateTime.now();
    }
}
