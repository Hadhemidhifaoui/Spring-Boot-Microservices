package com.example.SpringbootMicroservice1.dto;

import java.util.List;
import lombok.Data;

@Data
public class TestRequest {
    private String testName;
    private String testDescription;
    private Long course_id;
    private List<String> questionContents;
    private List<List<String>> suggestionContents;
    private List<List<String>> reponseContents;

    // getters and setters
}

