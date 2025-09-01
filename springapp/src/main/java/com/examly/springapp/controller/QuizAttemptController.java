package com.examly.springapp.controller;

import com.examly.springapp.dto.QuizAttemptDTO;
import com.examly.springapp.service.QuizAttemptService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuizAttemptController {

    private final QuizAttemptService quizAttemptService;

    public QuizAttemptController(QuizAttemptService quizAttemptService) {
        this.quizAttemptService = quizAttemptService;
    }

    @PostMapping("/quiz-attempts")
    public ResponseEntity<QuizAttemptDTO> submitAttempt(@RequestBody QuizAttemptDTO dto) {
        QuizAttemptDTO created = quizAttemptService.submitAttempt(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/quizzes/{quizId}/attempts")
    public ResponseEntity<List<QuizAttemptDTO>> getAttempts(@PathVariable Long quizId) {
        return ResponseEntity.ok(quizAttemptService.getAttemptsByQuiz(quizId));
    }
}
