package com.examly.springapp.controller;

import com.examly.springapp.dto.QuestionDTO;
import com.examly.springapp.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes/{quizId}/questions")
@CrossOrigin(originPatterns = "*", allowCredentials = "false")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity<?> addQuestion(
            @PathVariable Long quizId,
            @RequestBody QuestionDTO dto) {
        try {
            System.out.println("Adding question to quiz: " + quizId);
            QuestionDTO created = questionService.addQuestion(quizId, dto);
            System.out.println("Question added successfully: " + created.getId());
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            System.out.println("Error adding question: " + e.getMessage());
            return ResponseEntity.badRequest().body("Failed to add question: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<QuestionDTO>> getQuestions(@PathVariable Long quizId) {
        return ResponseEntity.ok(questionService.getQuestionsByQuiz(quizId));
    }
}
