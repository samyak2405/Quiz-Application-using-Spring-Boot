package com.samyak.quizapp.service;

import com.samyak.quizapp.DAO.QuestionDAO;
import com.samyak.quizapp.DAO.question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionDAO questionDAO;
    public List<question> getAllQuestions() {
        return questionDAO.findAll();
    }

    public List<question> getQuestionsByCategory(String category) {
        return questionDAO.findByCategory(category);
    }

    public List<question> getQuestionsByLevel(String difficultylevel) {
        return questionDAO.findBydifficultylevel(difficultylevel);
    }

    public String addQuestion(question question) {
        questionDAO.save(question);
        return "success";
    }

    public String deleteQuestion(Integer id) {
        questionDAO.deleteById(id);
        return "success";
    }

    public boolean existById(Integer id)
    {
        return questionDAO.existsById(id);
    }

    public String updateQuestion( Integer id, question question) {
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
            return "success";
        }
        return "failure";
    }
}
