package com.samyak.quizapp.service;

import com.samyak.quizapp.DAO.QuestionDAO;
import com.samyak.quizapp.DAO.QuizDAO;
import com.samyak.quizapp.model.QuestionWrapper;
import com.samyak.quizapp.model.Quiz;
import com.samyak.quizapp.model.Answers;
import com.samyak.quizapp.model.question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDAO quizDAO;
    @Autowired
    QuestionDAO questionDAO;
    public ResponseEntity<String> createQuiz(String category, int numberOfQue, String title) {

        List<question> questions = questionDAO.findRandomQuestionsByCategory(category,numberOfQue);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDAO.save(quiz);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
        Optional<Quiz> quiz = quizDAO.findById(id);
        List<question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionWrappers = new ArrayList<>();
        for(question q:questionsFromDB)
        {
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestion_title(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionWrappers.add(qw);
        }
        return new ResponseEntity<>(questionWrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Answers> response) {
        Quiz quiz = quizDAO.findById(id).get();
        List<question> questions = quiz.getQuestions();
        int i = 0;
        int right = 0;
        for(Answers res: response)
        {
            if(res.getResponse().equals(questions.get(i++).getRight_answer()))
                right++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
