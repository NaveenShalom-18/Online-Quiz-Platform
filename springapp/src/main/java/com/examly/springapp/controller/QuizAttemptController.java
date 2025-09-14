package com.examly.springapp.controller;

import com.examly.springapp.dto.QuizAttemptDTO;
import com.examly.springapp.service.QuizAttemptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attempts")
@CrossOrigin(originPatterns = "*", allowCredentials = "false")
public class QuizAttemptController {

    private final QuizAttemptService quizAttemptService;

    public QuizAttemptController(QuizAttemptService quizAttemptService) {
        this.quizAttemptService = quizAttemptService;
    }

    @PostMapping
    public ResponseEntity<QuizAttemptDTO> submitQuizAttempt(@RequestBody QuizAttemptDTO attemptDTO) {
        QuizAttemptDTO saved = quizAttemptService.submitAttempt(attemptDTO);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<QuizAttemptDTO>> getAttemptsByQuiz(@PathVariable Long quizId) {
        return ResponseEntity.ok(quizAttemptService.getAttemptsByQuiz(quizId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<QuizAttemptDTO>> getAttemptsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(quizAttemptService.getAttemptsByUser(userId));
    }
}
