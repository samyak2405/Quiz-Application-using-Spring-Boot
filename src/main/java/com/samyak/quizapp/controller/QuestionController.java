package com.samyak.quizapp.controller;

import com.samyak.quizapp.model.question;
import com.samyak.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @GetMapping("allQuestions")
    public ResponseEntity<List<question>> getAllQuestions()
    {
        return questionService.getAllQuestions();
    }
    @GetMapping("category/{category}")
    public ResponseEntity<List<question>> getQuestionsByCategory(@PathVariable("category") String category)
    {
        return questionService.getQuestionsByCategory(category);
    }

    @GetMapping("difficultylevel/{difficultylevel}")
    public ResponseEntity<List<question>> getQuestionByLevel(@PathVariable("difficultylevel") String difficultylevel){
        return questionService.getQuestionsByLevel(difficultylevel);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody question question)
    {
        return questionService.addQuestion(question);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable("id") Integer id)
    {
        return questionService.deleteQuestion(id);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> updateQuestion(@PathVariable("id") Integer id,@RequestBody question question)
    {
        return questionService.updateQuestion(id,question);
    }
}
