package com.examly.springapp.model;

import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionText;
    private String questionType;

    @ManyToOne
    @JoinColumn(name="quiz_id")
    private List<Quiz> options;
    private Quiz quiz;
    
    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
    public Quiz getQuiz() {
        return this.quiz;
    }
}
