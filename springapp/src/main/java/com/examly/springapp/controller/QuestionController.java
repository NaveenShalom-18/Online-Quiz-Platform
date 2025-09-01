package com.examly.springapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.Question;
import com.examly.springapp.service.QuestionService;

@RestController
@RequestMapping("/api/quizzes")
public class QuestionController {
    QuestionService questionservice;

    @PostMapping("/{id}/questions")
    public Question addQuestion(@PathVariable Long id,@RequestBody Question question) {
        return questionservice.addQuestion(id, question);
    }

    @GetMapping("/{id}/questions")
    public List<Question> getQuestion(@PathVariable Long id) {
        return questionservice.getQuestion(id);
    }
}
