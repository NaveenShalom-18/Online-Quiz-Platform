package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Question;
import com.examly.springapp.model.Quiz;
import com.examly.springapp.repository.QuestionRepository;
import com.examly.springapp.repository.QuizRepository;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizRepository quizRepository;

    public Question addQuestion(long quizId, Question question) {
        Quiz quiz = quizRepository.findById(quizId).get();
        question.setQuiz(quiz);
        return questionRepository.save(question);
    }

    public List<Question> getQuestion(long quizId) {
        return questionRepository.findByQuizId(quizId);
    }
}
