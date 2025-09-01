package com.examly.springapp.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class QuizAttemptDTO {
    private Long id;
    private String studentName;
    private int score;
    private int totalQuestions;
    private LocalDateTime completedAt;

    private Long quizId;
    private String quizTitle;

    private List<AnswerDTO> answers;

    public QuizAttemptDTO() {
    }

    public QuizAttemptDTO(Long id, String studentName, int score, int totalQuestions,
                          LocalDateTime completedAt, Long quizId, String quizTitle,
                          List<AnswerDTO> answers) {
        this.id = id;
        this.studentName = studentName;
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.completedAt = completedAt;
        this.quizId = quizId;
        this.quizTitle = quizTitle;
        this.answers = answers;
    }
}
