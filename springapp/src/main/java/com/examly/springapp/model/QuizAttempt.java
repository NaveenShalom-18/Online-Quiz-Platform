package com.examly.springapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizAttempt {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Quiz quiz;
    
    private String studentName;
    private int score;
    private int totalQuestions;
    private LocalDateTime completedAt;
    
    @PrePersist
    protected void onComplete() {
        this.completedAt = LocalDateTime.now();
    }
    @Builder.Default
    @OneToMany(mappedBy = "quizAttempt", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers = new ArrayList<>();
}
