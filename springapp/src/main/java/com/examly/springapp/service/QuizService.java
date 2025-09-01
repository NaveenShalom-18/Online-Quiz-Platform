package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Quiz;
import com.examly.springapp.exception.ResourceNotFoundException;
import com.examly.springapp.repository.QuizRepository;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Optional<Quiz> getQuizById(Long id) {
        if(quizRepository.existsById(id)) {
            return quizRepository.findById(id);
        }
        return null;
    }
    
    public Quiz updateQuiz(Long id, Quiz quiz) {
        if(quizRepository.existsById(id)) {
            return quizRepository.save(quiz);
        }
        return null;
    }
    
    public void deleteQuiz(Long id) {
    if (quizRepository.existsById(id)) {
        quizRepository.deleteById(id);
    } else {
        throw new ResourceNotFoundException("Quiz not found with id " + id);
    }
}



    
}
