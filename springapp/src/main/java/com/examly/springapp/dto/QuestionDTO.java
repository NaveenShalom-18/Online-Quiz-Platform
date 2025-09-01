package com.examly.springapp.dto;

import lombok.Data;
import java.util.List;

@Data
public class QuestionDTO {
    private Long id;
    private String questionText;
    private String questionType;
    private List<OptionDTO> options;
}
