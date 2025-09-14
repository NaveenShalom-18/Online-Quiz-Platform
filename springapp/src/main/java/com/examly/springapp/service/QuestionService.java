package com.examly.springapp.service;

import com.examly.springapp.dto.QuestionDTO;
import com.examly.springapp.dto.OptionDTO;
import com.examly.springapp.exception.ResourceNotFoundException;
import com.examly.springapp.model.Question;
import com.examly.springapp.model.Option;
import com.examly.springapp.model.Quiz;
import com.examly.springapp.repository.QuestionRepository;
import com.examly.springapp.repository.OptionRepository;
import com.examly.springapp.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;
    private final OptionRepository optionRepository;

    public QuestionService(QuestionRepository questionRepository,
                           QuizRepository quizRepository,
                           OptionRepository optionRepository) {
        this.questionRepository = questionRepository;
        this.quizRepository = quizRepository;
        this.optionRepository = optionRepository;
    }

    public QuestionDTO addQuestion(Long quizId, QuestionDTO dto) {
        System.out.println("Adding question to quiz: " + quizId);
        System.out.println("Question text: " + dto.getQuestionText());
        System.out.println("Options count: " + (dto.getOptions() != null ? dto.getOptions().size() : 0));
        
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found"));

        if (dto.getOptions() != null && !dto.getOptions().isEmpty()) {
            long correctCount = dto.getOptions().stream().filter(OptionDTO::getIsCorrect).count();
            if (correctCount != 1) {
                throw new IllegalArgumentException("Each question must have exactly one correct option");
            }
        }

        Question question = new Question();
        question.setQuiz(quiz);
        question.setQuestionText(dto.getQuestionText());
        question.setQuestionType(dto.getQuestionType());

        Question savedQuestion = questionRepository.save(question);

        List<Option> savedOptions = new ArrayList<>();
        if (dto.getOptions() != null && !dto.getOptions().isEmpty()) {
            savedOptions = dto.getOptions().stream()
                    .map(opt -> {
                        System.out.println("Saving option: " + opt.getOptionText() + ", correct: " + opt.getIsCorrect());
                        Option option = new Option();
                        option.setQuestion(savedQuestion);
                        option.setOptionText(opt.getOptionText());
                        option.setIsCorrect(opt.getIsCorrect());
                        return optionRepository.save(option);
                    })
                    .collect(Collectors.toList());
        }

        savedQuestion.setOptions(savedOptions);

        return mapToDTO(savedQuestion);
    }

    public List<QuestionDTO> getQuestionsByQuiz(Long quizId) {
        return questionRepository.findByQuizId(quizId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private QuestionDTO mapToDTO(Question q) {
        QuestionDTO dto = new QuestionDTO();
        dto.setId(q.getId());
        dto.setQuestionText(q.getQuestionText());
        dto.setQuestionType(q.getQuestionType());

        if (q.getOptions() != null) {
            dto.setOptions(q.getOptions().stream().map(o -> {
                OptionDTO odto = new OptionDTO();
                odto.setId(o.getId());
                odto.setOptionText(o.getOptionText());
                odto.setIsCorrect(o.getIsCorrect());
                return odto;
            }).collect(Collectors.toList()));
        }

        return dto;
    }
}
