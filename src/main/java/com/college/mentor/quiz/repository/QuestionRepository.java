package com.college.mentor.quiz.repository;

import com.college.mentor.quiz.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
