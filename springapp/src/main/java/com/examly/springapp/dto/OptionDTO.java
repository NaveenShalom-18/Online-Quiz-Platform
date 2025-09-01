package com.examly.springapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionDTO {
    private Long id;
    private String optionText;
    private boolean isCorrect;

    // Explicit setter for isCorrect
    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    // Optional explicit getter if needed
    public boolean getIsCorrect() {
        return this.isCorrect;
    }
}
