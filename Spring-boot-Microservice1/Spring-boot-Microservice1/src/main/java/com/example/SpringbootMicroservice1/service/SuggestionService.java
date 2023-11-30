package com.example.SpringbootMicroservice1.service;

import com.example.SpringbootMicroservice1.model.Suggestion;
import com.example.SpringbootMicroservice1.repository.SuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SuggestionService {
    @Autowired
    private SuggestionRepository suggestionRepository;

    @Transactional
    public Suggestion addSuggestion(Suggestion suggestionRequest) {
        Suggestion suggestion = new Suggestion();
        suggestion.setText(suggestionRequest.getText());

        return suggestionRepository.save(suggestion);
    }
}

