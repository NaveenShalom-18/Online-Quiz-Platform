package com.examly.springapp.dto;

import lombok.Data;

@Data
public class OptionDTO {
    private Long id;
    private String optionText;
    private Boolean isCorrect;
}
