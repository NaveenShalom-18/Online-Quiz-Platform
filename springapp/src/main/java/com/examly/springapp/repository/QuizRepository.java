package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examly.springapp.dto.QuizDTO;
import com.examly.springapp.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    QuizDTO save(QuizDTO quizDTO);
    
}
