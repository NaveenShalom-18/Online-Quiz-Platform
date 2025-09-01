package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.dto.QuizAttemptDTO;
import com.examly.springapp.model.QuizAttempt;
import com.examly.springapp.service.QuizAttemptService;

@RestController
@RequestMapping("/api")
public class QuizAttemptController {

    @Autowired
    private QuizAttemptService attemptService;

   @PostMapping("/quiz-attempts")
    public ResponseEntity<QuizAttemptDTO> submitQuizAttempt(@RequestBody QuizAttemptDTO attemptDTO) {
        QuizAttemptDTO savedAttempt = attemptService.submitAttempt(attemptDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAttempt);
    }

    @GetMapping("/quizzes/{quizId}/attempts")
    public List<QuizAttempt> getAttemptsByQuiz(@PathVariable Long quizId) {
        return attemptService.getAttemptsByQuiz(quizId);
    }
}
