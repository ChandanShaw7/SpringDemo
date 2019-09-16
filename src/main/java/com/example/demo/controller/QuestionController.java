package com.example.demo.controller;


import com.example.demo.model.Question;
import com.example.demo.services.QuestionsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/{user_id}/questions")
public class QuestionController {

    @Autowired
    private QuestionsServices questionsServices;

    @RequestMapping(produces = "application/json")
    public ResponseEntity<Iterable<Question>> getQuestions(@PathVariable int user_id){
        return new ResponseEntity<Iterable<Question>>(questionsServices.getAllQuestion(user_id), HttpStatus.OK);
    }

    @RequestMapping(value = "/{question_id}",produces = "application/json")
    public ResponseEntity<Optional<Question>> getOneQuestion(@PathVariable(value = "question_id") int question_id){
//        System.out.println(questionsServices.getQuestion(question_id).get().getDescription());
        Optional<Question> questi;
        questi = questionsServices.getQuestion(question_id);
        System.out.println(questi.get().getDescription());
        System.out.println(questi.get().getQ_id());
        System.out.println(questi.get().getTitle());
        System.out.println(questi.get().getUser().getId());
        return new ResponseEntity<Optional<Question>>(questionsServices.getQuestion(question_id), HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity insertOneQuestion(@PathVariable(value = "user_id") int u_id, @Valid @RequestBody Question question){
        System.out.println(question);
        System.out.println(u_id);
        System.out.println(question.getDescription());
        return questionsServices.insertQuestion(u_id,question);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/{question_id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity updateOneQuestion(@PathVariable(value = "user_id") int user_id, @PathVariable(value = "question_id") int question_id, @RequestBody Question question){
        System.out.println(question);
        return questionsServices.updateQuestion(question, question_id, user_id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{question_id}")
    public ResponseEntity deleteQuestion(@PathVariable(value = "question_id") int question_id){
        return questionsServices.deleteQuestion(question_id);
    }
}