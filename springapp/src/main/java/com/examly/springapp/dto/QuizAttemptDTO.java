package com.examly.springapp.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class QuizAttemptDTO {
    private Long id;
    private Long quizId;
    private String studentName;
    private List<AnswerDTO> answers;
    private Integer score;
    private Integer totalQuestions;
    private LocalDateTime completedAt;
}
