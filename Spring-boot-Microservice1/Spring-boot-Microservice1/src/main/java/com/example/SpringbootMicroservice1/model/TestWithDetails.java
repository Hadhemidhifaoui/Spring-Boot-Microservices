package com.example.SpringbootMicroservice1.model;

import lombok.Data;

@Data
public class TestWithDetails {
    private Test test;
    private Long courseId;
    private Long userId;

}

