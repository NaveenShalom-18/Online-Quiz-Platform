// QuizAttemptDTO.java
package com.examly.springapp.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class QuizAttemptDTO {
    private Long id;
    private Long quizId;
    private Long userId;
    private String studentName;
    private String quizTitle;
    private int score;
    private int totalQuestions;
    private LocalDateTime completedAt;
    private List<AnswerDTO> answers;
}
