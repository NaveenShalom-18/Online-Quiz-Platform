package com.examly.springapp.controller;

import com.examly.springapp.dto.QuizAttemptDTO;
import com.examly.springapp.service.QuizAttemptService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class QuizAttemptController {

    private final QuizAttemptService quizAttemptService;

    public QuizAttemptController(QuizAttemptService quizAttemptService) {
        this.quizAttemptService = quizAttemptService;
    }

    @PostMapping("/quiz-attempts")
    public ResponseEntity<QuizAttemptDTO> submitAttempt(@Valid @RequestBody QuizAttemptDTO attemptDTO) {
        QuizAttemptDTO savedAttempt = quizAttemptService.submitAttempt(attemptDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAttempt);
    }

    @GetMapping("/quizzes/{quizId}/attempts")
    public ResponseEntity<List<QuizAttemptDTO>> getAttempts(@PathVariable Long quizId) {
        return ResponseEntity.ok(quizAttemptService.getAttemptsByQuiz(quizId));
    }
}
