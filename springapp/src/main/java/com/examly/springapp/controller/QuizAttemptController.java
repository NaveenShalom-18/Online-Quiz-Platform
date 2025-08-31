package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.QuizAttempt;
import com.examly.springapp.service.QuizAttemptService;

@RestController
@RequestMapping("/api")
public class QuizAttemptController {

    @Autowired
    private QuizAttemptService attemptService;

    @PostMapping("/quiz-attempts")
    public QuizAttempt submitAttempt(@RequestBody QuizAttempt attempt) {
        return attemptService.submitAttempt(attempt.getQuiz().getId(), attempt);
    }

    @GetMapping("/quizzes/{quizId}/attempts")
    public List<QuizAttempt> getAttemptsByQuiz(@PathVariable Long quizId) {
        return attemptService.getAttemptsByQuiz(quizId);
    }
}
