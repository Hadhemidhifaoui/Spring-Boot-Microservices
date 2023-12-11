package com.example.springbootmicroservice3.model;

import java.util.List;

public class TestQuestionRequest {
    private String questionContent;
    private String questionType;
    private List<SuggestionContent> suggestionContents;

    // Constructors, getters, and setters

    public TestQuestionRequest() {
    }

    public TestQuestionRequest(String questionContent, String questionType, List<SuggestionContent> suggestionContents) {
        this.questionContent = questionContent;
        this.questionType = questionType;
        this.suggestionContents = suggestionContents;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public List<SuggestionContent> getSuggestionContents() {
        return suggestionContents;
    }

    public void setSuggestionContents(List<SuggestionContent> suggestionContents) {
        this.suggestionContents = suggestionContents;
    }
}
