package com.college.mentor.quiz.service;

import com.college.mentor.quiz.model.Question;
import com.college.mentor.quiz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Optional<Question> findById(Long id) {
        return questionRepository.findById(id);
    }

    public Question create (Question question) {
        return questionRepository.save(question);
    }
}
