package com.college.mentor.quiz.controller;

import com.college.mentor.quiz.dto.GetAllQuizRequest;
import com.college.mentor.quiz.dto.PaginatedResponse;
import com.college.mentor.quiz.dto.QuizDTO;
import com.college.mentor.quiz.model.Quiz;
import com.college.mentor.quiz.service.QuizService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Controller
@RequestMapping("/api/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/all")
    public ResponseEntity<PaginatedResponse<QuizDTO>> getAllQuizzes(@Valid @RequestBody GetAllQuizRequest request) {

        Sort sort = request.getSort().getSortOrder().equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(request.getSort().getSortBy()).ascending() : Sort.by(request.getSort().getSortBy()).descending();
        Pageable pageable = PageRequest.of(request.getPagination().getPage(), request.getPagination().getSize(), sort);

        PaginatedResponse<QuizDTO> quizzes = quizService.getAllQuizzes(pageable);
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

//    @PutMapping
//    public ResponseEntity<Quiz> updateQuiz(@Valid @RequestBody Quiz quiz) {
//        try {
//            Quiz updatedQuiz = quizService.updateQuiz(quiz);
//            return ResponseEntity.ok(updatedQuiz);
//        }
//        catch (Exception e) {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteQuizById(@PathVariable Long id) {
        Optional<Quiz> optionalQuiz = quizService.getQuizById(id);

        if (optionalQuiz.isEmpty()) return ResponseEntity.notFound().build();

        quizService.deleteQuizById(id);
        return ResponseEntity.noContent().build();
    }
}