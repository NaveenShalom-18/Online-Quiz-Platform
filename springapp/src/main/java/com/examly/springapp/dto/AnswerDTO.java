package com.examly.springapp.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AnswerDTO {
    private Long questionId;
    private Long selectedOptionId;

    public AnswerDTO() {
    }

    public AnswerDTO(Long questionId, Long selectedOptionId) {
        this.questionId = questionId;
        this.selectedOptionId = selectedOptionId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getSelectedOptionId() {
        return selectedOptionId;
    }

    public void setSelectedOptionId(Long selectedOptionId) {
        this.selectedOptionId = selectedOptionId;
    }
}
