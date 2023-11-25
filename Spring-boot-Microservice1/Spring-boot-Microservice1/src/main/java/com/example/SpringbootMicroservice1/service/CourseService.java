package com.example.SpringbootMicroservice1.service;

import com.example.SpringbootMicroservice1.model.Course;

import java.util.List;

public interface CourseService
{
    Course saveCourse(Course course);

    void deleteCourse(Long courseId);

    List<Course> findAllCourses();
    Course updateCourse(Long courseId, Course updatedCourse);
    Course findCourseById(Long courseId);
}
