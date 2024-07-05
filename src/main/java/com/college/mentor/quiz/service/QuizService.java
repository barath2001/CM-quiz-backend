package com.college.mentor.quiz.service;

import com.college.mentor.quiz.dto.PaginatedResponse;
import com.college.mentor.quiz.dto.QuizDTO;
import com.college.mentor.quiz.model.Quiz;
import com.college.mentor.quiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionService questionService;

    public PaginatedResponse<QuizDTO> getAllQuizzes(Pageable pageable) {
         Page<Quiz> quizPage = quizRepository.findAll(pageable);
         List<QuizDTO> content = quizPage.getContent().stream().map(quiz -> new QuizDTO(quiz.getId(), quiz.getTitle(), quiz.getDescription())).toList();

        PaginatedResponse<QuizDTO> response = new PaginatedResponse<>();
        response.setContent(content);
        response.setPage(quizPage.getNumber());
        response.setSize(quizPage.getSize());
        response.setTotalPages(quizPage.getTotalPages());
        response.setTotalElements(quizPage.getTotalElements());

        return response;
    }

    public Optional<Quiz> getQuizById(Long id) {
        return quizRepository.findById(id);
    }

    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

//    public Quiz updateQuiz(Quiz updatedQuiz) throws Exception {
//        if (updatedQuiz.getId() == null) throw new Exception("Quiz not found");
//        Optional<Quiz> optionalQuiz = getQuizById(updatedQuiz.getId());
//        if (optionalQuiz.isEmpty()) throw new Exception("Quiz not found");
//        Quiz existingQuiz = optionalQuiz.get();
//        existingQuiz.setTitle(updatedQuiz.getTitle());
//        existingQuiz.setDescription(updatedQuiz.getDescription());
//        existingQuiz.setQuestions(Collections.emptyList());
//
//        for (Question updatedQuestion : updatedQuiz.getQuestions()) {
//            if (updatedQuestion.getId() != null) {
//                Optional<Question> optionalQuestion = questionService.findById(updatedQuestion.getId());
//                if (optionalQuestion.isPresent()) {
//                    Question existingQuestion = optionalQuestion.get();
//                    existingQuestion.setOptions(Collections.emptyList());
//                    for ()
//                }
//            }
//        }
//
//        return quizRepository.save(existingQuiz);
//    }

    public void deleteQuizById(Long id) {
        quizRepository.deleteById(id);
    }
}
