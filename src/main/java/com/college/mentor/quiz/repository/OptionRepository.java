package com.college.mentor.quiz.repository;

import com.college.mentor.quiz.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface OptionRepository extends JpaRepository<Option, Long> {
}
