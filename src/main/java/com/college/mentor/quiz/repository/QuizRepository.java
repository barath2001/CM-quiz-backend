package com.college.mentor.quiz.repository;

import com.college.mentor.quiz.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
