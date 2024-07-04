package com.college.mentor.quiz.controller;

import com.college.mentor.quiz.dto.QuizDTO;
import com.college.mentor.quiz.model.Quiz;
import com.college.mentor.quiz.service.QuizService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping
    public ResponseEntity<List<QuizDTO>> getAllQuizzes() {
        List<QuizDTO> quizzes = quizService.getAllQuizzes();
        return ResponseEntity.ok(quizzes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long id) {
        Optional<Quiz> optionalQuiz = quizService.getQuizById(id);
        return optionalQuiz.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@Valid @RequestBody Quiz quiz) {
        Quiz createdQuiz = quizService.createQuiz(quiz);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdQuiz.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdQuiz);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteQuizById(@PathVariable Long id) {
        Optional<Quiz> optionalQuiz = quizService.getQuizById(id);

        if (optionalQuiz.isEmpty()) return ResponseEntity.notFound().build();

        quizService.deleteQuizById(id);
        return ResponseEntity.noContent().build();
    }
}