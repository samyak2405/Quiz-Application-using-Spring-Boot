package com.samyak.quizapp.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDAO extends JpaRepository <question,Integer>{
    List<question> findByCategory(String category);

    List<question> findBydifficultylevel(String difficultlevel);
}
