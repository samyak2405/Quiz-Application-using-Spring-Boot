package com.samyak.quizapp.DAO;

import com.samyak.quizapp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDAO extends JpaRepository<Quiz,Integer> {
}
