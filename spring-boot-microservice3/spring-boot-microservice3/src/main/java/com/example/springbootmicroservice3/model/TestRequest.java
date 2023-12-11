package com.example.springbootmicroservice3.model;

import lombok.Data;

import java.util.List;
@Data
public class TestRequest {
    private String testName;
    private String testDescription;
    private int course_id;
    private List<TestQuestionRequest> questions;


}



