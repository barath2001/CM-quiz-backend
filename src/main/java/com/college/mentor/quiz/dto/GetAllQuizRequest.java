package com.college.mentor.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllQuizRequest {
    private Pagination pagination;
    private Sort sort;
}
