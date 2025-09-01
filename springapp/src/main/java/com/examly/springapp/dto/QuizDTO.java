package com.examly.springapp.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class QuizDTO {
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @Min(value = 1, message = "Time limit must be at least 1 minute")
    @Max(value = 180, message = "Time limit cannot exceed 180 minutes")
    private int timeLimit;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<QuestionDTO> questions;
}
