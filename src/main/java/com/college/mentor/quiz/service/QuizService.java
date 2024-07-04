package com.college.mentor.quiz.service;

import com.college.mentor.quiz.dto.QuizDTO;
import com.college.mentor.quiz.model.Quiz;
import com.college.mentor.quiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    public List<QuizDTO> getAllQuizzes() {
        List<Quiz> quizzes = quizRepository.findAll();
        return quizzes.stream().map(quiz -> new QuizDTO(quiz.getId(), quiz.getTitle(), quiz.getDescription())).collect(Collectors.toList());
    }

    public Optional<Quiz> getQuizById(Long id) {
        return quizRepository.findById(id);
    }

    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public void deleteQuizById(Long id) {
        quizRepository.deleteById(id);
    }

}
