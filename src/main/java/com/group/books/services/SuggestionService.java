package com.group.books.services;

import com.group.books.entities.Suggestion;
import com.group.books.repos.SuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuggestionService {

    @Autowired
    private SuggestionRepository suggestionRepo;

    public void insertSuggestion(Suggestion suggestion) {
        suggestionRepo.save(suggestion);
    }

    public List<Suggestion> getAllSuggestions() {
        return suggestionRepo.findAll();
    }

    public void deleteSuggestionById(int suggestionId) {
        suggestionRepo.deleteById(suggestionId);
    }
}
