package com.example.SpringbootMicroservice1.dto;



public class SuggestionContent {
    private String suggestionContent;

    // Constructors, getters, and setters

    public SuggestionContent() {
    }

    public SuggestionContent(String suggestionContent) {
        this.suggestionContent = suggestionContent;
    }

    public String getSuggestionContent() {
        return suggestionContent;
    }

    public void setSuggestionContent(String suggestionContent) {
        this.suggestionContent = suggestionContent;
    }
}

