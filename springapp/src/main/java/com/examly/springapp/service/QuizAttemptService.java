package com.examly.springapp.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.dto.AnswerDTO;
import com.examly.springapp.dto.QuizAttemptDTO;
import com.examly.springapp.model.Option;
import com.examly.springapp.model.Quiz;
import com.examly.springapp.model.QuizAttempt;
import com.examly.springapp.repository.OptionRepository;
import com.examly.springapp.repository.QuestionRepository;
import com.examly.springapp.repository.QuizAttemptRepository;
import com.examly.springapp.repository.QuizRepository;

@Service
public class QuizAttemptService {

    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private OptionRepository optionRepository;
    @Autowired
    private QuizAttemptRepository quizAttemptRepository;

    public QuizAttemptDTO submitAttempt(QuizAttemptDTO attemptDTO) {
        Quiz quiz = quizRepository.findById(attemptDTO.getQuizId())
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        int score = 0;
        int totalQuestions = attemptDTO.getAnswers().size();

        for (AnswerDTO answer : attemptDTO.getAnswers()) {
            Option selectedOption = optionRepository.findById(answer.getSelectedOptionId())
                    .orElseThrow(() -> new RuntimeException("Option not found"));
            if (selectedOption.isCorrect()) {
                score++;
            }
        }

        QuizAttempt attempt = QuizAttempt.builder()
                .quiz(quiz)
                .studentName(attemptDTO.getStudentName())
                .score(score)
                .totalQuestions(totalQuestions)
                .completedAt(LocalDateTime.now())
                .build();

        attempt = quizAttemptRepository.save(attempt);

        return QuizAttemptDTO.builder()
                .id(attempt.getId())
                .quizId(quiz.getId())
                .studentName(attempt.getStudentName())
                .score(score)
                .totalQuestions(totalQuestions)
                .completedAt(attempt.getCompletedAt())
                .build();
    }

    public List<QuizAttempt> getAttemptsByQuiz(Long quizId) {
        return quizAttemptRepository.findByQuizId(quizId);
    }

}
