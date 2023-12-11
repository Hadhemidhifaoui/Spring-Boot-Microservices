package com.example.SpringbootMicroservice1.model;

import com.example.SpringbootMicroservice1.dto.TestQuestionRequest;

import java.util.List;

public class TestCreationRequest {
    private Long courseId;
    private String testName;
    private String testDescription;
    private List<TestQuestionRequest> questions;

    // Constructors, getters, and setters

    public TestCreationRequest() {
    }

    public TestCreationRequest(Long courseId, String testName, String testDescription, List<TestQuestionRequest> questions) {
        this.courseId = courseId;
        this.testName = testName;
        this.testDescription = testDescription;
        this.questions = questions;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestDescription() {
        return testDescription;
    }

    public void setTestDescription(String testDescription) {
        this.testDescription = testDescription;
    }

    public List<TestQuestionRequest> getQuestions() {
        return questions;
    }

    public void setQuestions(List<TestQuestionRequest> questions) {
        this.questions = questions;
    }
}

