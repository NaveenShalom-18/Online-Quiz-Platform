package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.examly.springapp.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    
}
