package com.college.mentor.quiz.service;

import com.college.mentor.quiz.model.Option;
import com.college.mentor.quiz.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OptionService {

    @Autowired
    private OptionRepository optionRepository;

    public Optional<Option> findById(Long id) {
        return optionRepository.findById(id);
    }

    public Option create (Option option) {
        return optionRepository.save(option);
    }
}
