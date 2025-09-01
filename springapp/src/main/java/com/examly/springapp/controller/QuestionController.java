package com.examly.springapp.controller;

import com.examly.springapp.dto.QuestionDTO;
import com.examly.springapp.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes/{quizId}/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity<QuestionDTO> addQuestion(
            @PathVariable Long quizId,
            @RequestBody QuestionDTO dto) {
        QuestionDTO created = questionService.addQuestion(quizId, dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<QuestionDTO>> getQuestions(@PathVariable Long quizId) {
        return ResponseEntity.ok(questionService.getQuestionsByQuiz(quizId));
    }
}
