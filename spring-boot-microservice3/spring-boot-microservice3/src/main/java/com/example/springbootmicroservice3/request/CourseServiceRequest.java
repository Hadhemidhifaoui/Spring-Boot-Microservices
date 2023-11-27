package com.example.springbootmicroservice3.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "course-service"//Name of course-service application
        , path = "/api/course"//Pre-path for course-service
        //,url = "${course.service.url}"
        ,configuration = FeignConfiguration.class
)
public interface CourseServiceRequest
{
    @PostMapping("save")
///api/course/save
    Object saveCourse(@RequestBody Object requestBody);

    @DeleteMapping("{courseId}")//api/course/{courseId}
    void deleteCourse(@PathVariable("courseId") Long courseId);

    @GetMapping
//api/course
    List<Object> getAllCourses();

    @GetMapping("/{courseId}")
    Object getCourseById(@PathVariable("courseId") Long courseId);

    @PutMapping("/{courseId}")
    Object updateCourse(@PathVariable("courseId") Long courseId, @RequestBody Object updatedCourse);
}
