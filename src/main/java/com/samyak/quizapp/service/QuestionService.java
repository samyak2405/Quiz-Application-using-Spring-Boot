package com.samyak.quizapp.service;

import com.samyak.quizapp.DAO.QuestionDAO;
import com.samyak.quizapp.model.question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDAO questionDAO;
    public ResponseEntity<List<question>> getAllQuestions()
    {
        try {
            return new ResponseEntity<>(questionDAO.findAll(), HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<question>> getQuestionsByCategory(String category) {
        return new ResponseEntity<>(questionDAO.findByCategory(category),HttpStatus.OK);
    }

    public ResponseEntity<List<question>> getQuestionsByLevel(String difficultylevel) {
        return new ResponseEntity<>(questionDAO.findBydifficultylevel(difficultylevel),HttpStatus.OK);
    }

    public ResponseEntity<String> addQuestion(question question) {
        if(question!=null)
        {
            questionDAO.save(question);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<String> deleteQuestion(Integer id) {
        questionDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public boolean existById(Integer id)
    {
        return questionDAO.existsById(id);
    }

    public ResponseEntity<String> updateQuestion( Integer id, question question) {
        if(questionDAO.existsById(id))
        {
            question existQuestion = questionDAO.getReferenceById(id);
            existQuestion.setCategory(question.getCategory());
            existQuestion.setDifficultylevel(question.getDifficultylevel());
            existQuestion.setOption1(question.getOption1());
            existQuestion.setOption2(question.getOption2());
            existQuestion.setOption3(question.getOption3());
            existQuestion.setOption4(question.getOption4());
            existQuestion.setQuestion_title(question.getQuestion_title());
            existQuestion.setRight_answer(question.getRight_answer());
            questionDAO.save(existQuestion);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
