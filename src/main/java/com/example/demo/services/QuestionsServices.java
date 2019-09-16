package com.example.demo.services;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Question;
import com.example.demo.model.User;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionsServices {

    @Autowired
    private QuestionRepository questionsRepository;
    @Autowired
    private UserRepository userRepository;

//
    public Iterable<Question> getAllQuestion(int u_id){
        Iterable<Question> allQuestions = null;
        try{
            allQuestions = questionsRepository.findByUserId(u_id);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println(allQuestions);
        return allQuestions;
    }

    public Optional<Question> getQuestion(int q_id){
//        System.out.println(q_id);
//        Optional<Question> quest= questionsRepository.findById(q_id);
        return questionsRepository.findById(q_id);
    }

//    public ResponseEntity<?> addQuestion(Question question){
//        try {
//            questionsRepository.save(question)
//                return ResponseEntity.ok().build();
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
//        return ResponseEntity.badRequest().build();
//    }

    public ResponseEntity<?> insertQuestion(int u_id, Question question){

        System.out.println("Enter insert question");

        Optional<User> userDetails = userRepository.findById(u_id);
            if (userDetails.isPresent()) {
                User user = userDetails.get();
                System.out.println(user.getId());
                question.setUser(user);
                questionsRepository.save(question);
                return ResponseEntity.ok().build();
             }
        throw new ResourceNotFoundException("User not found with user id: ", String.valueOf(u_id));

    }


    public ResponseEntity<?> updateQuestion(Question question,int q_id, int u_id){

        if (!userRepository.existsById(u_id)) {
            throw new ResourceNotFoundException("user not found with user Id: ", String.valueOf(u_id));
        }
        Optional<Question> questionDetails = questionsRepository.findById(q_id);
            if (questionDetails.isPresent()){
                questionDetails.get().setDescription(question.getDescription());
                Question quest = questionsRepository.save(questionDetails.get());
                System.out.println(quest.getTitle());
                return ResponseEntity.ok().build();
            }
            throw new ResourceNotFoundException("Question not found with question",String.valueOf(q_id));
    }

    public ResponseEntity<?> deleteQuestion(int q_Id){

//        if (!userRepository.existsById(u_id)){
//            throw new ResourceNotFoundException("user not found with user Id: ",String String.valueOf(u_id));

        if(!questionsRepository.existsById(q_Id))
            throw new ResourceNotFoundException("Question not found with question",String.valueOf(q_Id));

        questionsRepository.deleteById(q_Id);
        return ResponseEntity.ok().build();
    }



}
