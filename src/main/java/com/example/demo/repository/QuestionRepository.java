package com.example.demo.repository;

import com.example.demo.model.Question;
//import com.example.demo.model.User;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.data.repository.CrudRepository;

import java.util.List;

//import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Integer> {

    @Query(value = "SELECT * FROM Question q where q.u_id=?1",nativeQuery=true)
    Iterable<Question> findByUserId(int u_id);
}
