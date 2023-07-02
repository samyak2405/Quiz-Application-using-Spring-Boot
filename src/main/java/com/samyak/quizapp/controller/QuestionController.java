package com.samyak.quizapp.controller;

import com.samyak.quizapp.DAO.QuestionDAO;
import com.samyak.quizapp.DAO.question;
import com.samyak.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @GetMapping("allQuestions")
    public List<question> getAllQuestions()
    {
        return questionService.getAllQuestions();
    }
    @GetMapping("category/{category}")
    public List<question> getQuestionsByCategory(@PathVariable("category") String category)
    {
        return questionService.getQuestionsByCategory(category);
    }

    @GetMapping("difficultylevel/{difficultylevel}")
    public List<question> getQuestionByLevel(@PathVariable("difficultylevel") String difficultylevel){
        return questionService.getQuestionsByLevel(difficultylevel);
    }

    @PostMapping("add")
    public String addQuestion(@RequestBody question question)
    {
        return questionService.addQuestion(question);
    }

    @DeleteMapping("delete/{id}")
    public String deleteQuestion(@PathVariable("id") Integer id)
    {
        return questionService.deleteQuestion(id);
    }

    @PutMapping("update/{id}")
    public String updateQuestion(@PathVariable("id") Integer id,@RequestBody question question)
    {
        return questionService.updateQuestion(id,question);
    }
}
