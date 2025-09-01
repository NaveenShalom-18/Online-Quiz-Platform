package com.examly.springapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
    private Long id;
    private String questionText;
    private String questionType;
    private Long quizId;
    private List<OptionDTO> options;

    public void addOption(OptionDTO option) {
        this.options.add(option);
    }
}
