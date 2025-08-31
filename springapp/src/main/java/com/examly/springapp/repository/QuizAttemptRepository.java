package com.examly.springapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.examly.springapp.model.QuizAttempt;

public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {
    List<QuizAttempt> findByQuizId(Long quizId);
}
