package com.examly.springapp.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class QuizDTO {
    private Long id;
    private String title;
    private String description;
    private int timeLimit;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<QuestionDTO> questions;
}
