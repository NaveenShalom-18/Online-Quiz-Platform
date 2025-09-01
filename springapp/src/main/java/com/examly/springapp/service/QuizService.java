package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Quiz;
import com.examly.springapp.exception.ResourceNotFoundException;
import com.examly.springapp.repository.QuizRepository;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    // Create quiz
    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    // Get all quizzes
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    // Get quiz by ID
    public Quiz getQuizById(Long id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found"));
    }

    // Update quiz
    public Quiz updateQuiz(Long id, Quiz quizDetails) {
        Quiz existingQuiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found"));

        existingQuiz.setTitle(quizDetails.getTitle());
        existingQuiz.setTimeLimit(quizDetails.getTimeLimit());

        return quizRepository.save(existingQuiz);
    }

    // Delete quiz
    public void deleteQuiz(Long id) {
        Quiz existingQuiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found"));
        quizRepository.delete(existingQuiz);
    }
}
