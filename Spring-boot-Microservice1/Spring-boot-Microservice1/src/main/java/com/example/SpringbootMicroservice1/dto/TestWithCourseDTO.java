package com.example.SpringbootMicroservice1.dto;

import lombok.Data;

@Data
public class TestWithCourseDTO {
    private Long testId;
    private String testName;
    private String testDescription;
    private Long courseId;
    private String courseTitle;

    private String courseimage;


}

