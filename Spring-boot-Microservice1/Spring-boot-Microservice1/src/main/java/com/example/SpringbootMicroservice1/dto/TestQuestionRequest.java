package com.example.SpringbootMicroservice1.dto;

import lombok.Data;

import java.util.List;

@Data
public class TestQuestionRequest {
    private String questionContent;
    private String questionType;
    private List<String> suggestionContents;


}

