package com.examly.springapp.service;

import com.examly.springapp.dto.QuestionDTO;
import com.examly.springapp.dto.OptionDTO;
import com.examly.springapp.dto.QuizDTO;
import com.examly.springapp.exception.ResourceNotFoundException;
import com.examly.springapp.model.Question;
import com.examly.springapp.model.Quiz;
import com.examly.springapp.repository.QuizRepository;
import com.examly.springapp.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    public QuizService(QuizRepository quizRepository, QuestionRepository questionRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
    }

    public QuizDTO createQuiz(QuizDTO dto) {
        Quiz quiz = new Quiz();
        quiz.setTitle(dto.getTitle());
        quiz.setDescription(dto.getDescription());
        quiz.setTimeLimit(dto.getTimeLimit());
        Quiz saved = quizRepository.save(quiz);
        return mapToDTOWithQuestions(saved);
    }

    @Transactional(readOnly = true)
    public List<QuizDTO> getAllQuizzes() {
        return quizRepository.findAll().stream()
                .map(this::mapToDTOWithQuestions)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public QuizDTO getQuizById(Long id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found"));
        return mapToDTOWithQuestions(quiz);
    }

    private QuizDTO mapToDTOWithQuestions(Quiz quiz) {
        QuizDTO dto = new QuizDTO();
        dto.setId(quiz.getId());
        dto.setTitle(quiz.getTitle());
        dto.setDescription(quiz.getDescription());
        dto.setTimeLimit(quiz.getTimeLimit());
        dto.setCreatedAt(quiz.getCreatedAt());
        dto.setUpdatedAt(quiz.getUpdatedAt());
        
        // Use the questions from the quiz entity (EAGER loaded)
        if (quiz.getQuestions() != null && !quiz.getQuestions().isEmpty()) {
            List<QuestionDTO> questionDTOs = quiz.getQuestions().stream().map(q -> {
                QuestionDTO qdto = new QuestionDTO();
                qdto.setId(q.getId());
                qdto.setQuestionText(q.getQuestionText());
                qdto.setQuestionType(q.getQuestionType());
                
                // Map options (EAGER loaded)
                if (q.getOptions() != null && !q.getOptions().isEmpty()) {
                    List<OptionDTO> optionDTOs = q.getOptions().stream().map(o -> {
                        OptionDTO odto = new OptionDTO();
                        odto.setId(o.getId());
                        odto.setOptionText(o.getOptionText());
                        odto.setIsCorrect(o.getIsCorrect());
                        return odto;
                    }).collect(Collectors.toList());
                    qdto.setOptions(optionDTOs);
                }
                return qdto;
            }).collect(Collectors.toList());
            dto.setQuestions(questionDTOs);
        }
        
        return dto;
    }
}
