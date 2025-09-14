package com.examly.springapp.service;

import com.examly.springapp.dto.AnswerDTO;
import com.examly.springapp.dto.QuizAttemptDTO;
import com.examly.springapp.exception.ResourceNotFoundException;
import com.examly.springapp.model.*;
import com.examly.springapp.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizAttemptService {

    private final QuizAttemptRepository quizAttemptRepository;
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;

    public QuizAttemptService(QuizAttemptRepository quizAttemptRepository,
                              QuizRepository quizRepository,
                              QuestionRepository questionRepository,
                              OptionRepository optionRepository) {
        this.quizAttemptRepository = quizAttemptRepository;
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.optionRepository = optionRepository;
    }

    public QuizAttemptDTO submitAttempt(QuizAttemptDTO dto) {
        Quiz quiz = quizRepository.findById(dto.getQuizId())
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found"));

        int score = 0;
        int totalQuestions = dto.getAnswers().size();

        QuizAttempt attempt = QuizAttempt.builder()
                .quiz(quiz)
                .studentName(dto.getStudentName())
                .score(0)
                .totalQuestions(totalQuestions)
                .completedAt(LocalDateTime.now())
                .answers(new ArrayList<>())
                .build();

        QuizAttempt savedAttempt = quizAttemptRepository.save(attempt);

        for (AnswerDTO answerDTO : dto.getAnswers()) {
            Question question = questionRepository.findById(answerDTO.getQuestionId())
                    .orElseThrow(() -> new ResourceNotFoundException("Question not found"));

            Option selected = optionRepository.findById(answerDTO.getSelectedOptionId())
                    .orElseThrow(() -> new ResourceNotFoundException("Option not found"));

            if (selected.getIsCorrect()) score++;

            Answer answer = Answer.builder()
                    .quizAttempt(savedAttempt)
                    .question(question)
                    .selectedOption(selected)
                    .build();

            savedAttempt.getAnswers().add(answer);
        }

        savedAttempt.setScore(score);
        QuizAttempt updatedAttempt = quizAttemptRepository.save(savedAttempt);

        return mapToDTO(updatedAttempt);
    }

    public List<QuizAttemptDTO> getAttemptsByQuiz(Long quizId) {
        return quizAttemptRepository.findByQuizId(quizId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private QuizAttemptDTO mapToDTO(QuizAttempt attempt) {
        QuizAttemptDTO dto = new QuizAttemptDTO();
        dto.setId(attempt.getId());
        dto.setQuizId(attempt.getQuiz().getId());
        dto.setStudentName(attempt.getStudentName());
        dto.setScore(attempt.getScore());
        dto.setTotalQuestions(attempt.getTotalQuestions());
        dto.setCompletedAt(attempt.getCompletedAt());
        return dto;
    }
}
