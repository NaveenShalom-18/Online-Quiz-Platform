package com.examly.springapp.service;

import com.examly.springapp.dto.QuizDTO;
import com.examly.springapp.dto.QuestionDTO;
import com.examly.springapp.exception.ResourceNotFoundException;
import com.examly.springapp.model.Quiz;
import com.examly.springapp.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {

    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public QuizDTO createQuiz(QuizDTO dto) {
        Quiz quiz = Quiz.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .timeLimit(dto.getTimeLimit())
                .build();

        Quiz saved = quizRepository.save(quiz);
        return mapToDTO(saved);
    }

    public List<QuizDTO> getAllQuizzes() {
        return quizRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public QuizDTO getQuizById(Long id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found"));
        return mapToDTO(quiz);
    }

    public QuizDTO updateQuiz(Long id, QuizDTO dto) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found"));

        quiz.setTitle(dto.getTitle());
        quiz.setDescription(dto.getDescription());
        quiz.setTimeLimit(dto.getTimeLimit());

        Quiz updated = quizRepository.save(quiz);
        return mapToDTO(updated);
    }

    public void deleteQuiz(Long id) {
        if (!quizRepository.existsById(id)) {
            throw new ResourceNotFoundException("Quiz not found");
        }
        quizRepository.deleteById(id);
    }

    private QuizDTO mapToDTO(Quiz quiz) {
        QuizDTO dto = new QuizDTO();
        dto.setId(quiz.getId());
        dto.setTitle(quiz.getTitle());
        dto.setDescription(quiz.getDescription());
        dto.setTimeLimit(quiz.getTimeLimit());
        dto.setCreatedAt(quiz.getCreatedAt());
        dto.setUpdatedAt(quiz.getUpdatedAt());
 if (quiz.getQuestions() != null) {
            dto.setQuestions(
                quiz.getQuestions().stream().map(q -> {
                    QuestionDTO qdto = new QuestionDTO();
                    qdto.setId(q.getId());
                    qdto.setQuestionText(q.getQuestionText());
                    qdto.setQuestionType(q.getQuestionType());
                    return qdto;
                }).collect(Collectors.toList())
            );
        }

        return dto;
    }
}