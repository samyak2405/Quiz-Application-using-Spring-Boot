package com.samyak.quizapp.DAO;

import com.samyak.quizapp.model.question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDAO extends JpaRepository <question,Integer>{

    @Query(value = "SELECT * FROM question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numberOfQue", nativeQuery = true)
    List<question> findRandomQuestionsByCategory(String category, int numberOfQue);

    List<question> findByCategory(String category);

    List<question> findBydifficultylevel(String difficultlevel);
}
