package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Quiz;
import com.examly.springapp.model.QuizAttempt;
import com.examly.springapp.repository.QuizAttemptRepository;
import com.examly.springapp.repository.QuizRepository;

@Service
public class QuizAttemptService {

    @Autowired
    private QuizAttemptRepository attemptRepository;

    @Autowired
    private QuizRepository quizRepository;
    
    public QuizAttempt submitAttempt(Long quizId, QuizAttempt attempt) {
        Quiz quiz = quizRepository.findById(quizId).orElse(null);
        if (quiz == null) return null;

        attempt.setQuiz(quiz);
        return attemptRepository.save(attempt);
    }
    public List<QuizAttempt> getAttemptsByQuiz(Long quizId) {
        return attemptRepository.findByQuizId(quizId);
    }
}
