package com.example.SpringbootMicroservice1.controller;

import com.example.SpringbootMicroservice1.model.Course;
import com.example.SpringbootMicroservice1.model.Test;
import com.example.SpringbootMicroservice1.service.CourseService;

import com.example.SpringbootMicroservice1.utils.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/course")
public class CourseController
{
    @Autowired
    private CourseService courseService;
    @Autowired
    private StorageService storage;

    @PostMapping ("/save")//api/course
    public ResponseEntity<?> saveCourse(Course course,@RequestParam(value = "file") MultipartFile file)
    {   storage.store(file);
        course.setImage(file.getOriginalFilename());
        return new ResponseEntity<>(courseService.saveCourse(course), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteById/{courseId}")//api/course/{courseId}
    public ResponseEntity<?> deleteCourse(@PathVariable Long courseId)
    {
        courseService.deleteCourse(courseId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping ("/getAll")//api/course
    public ResponseEntity<?> getAllCourses()
    {
        return ResponseEntity.ok(courseService.findAllCourses());
    }

    @GetMapping("getCourse/{courseId}") // api/course/{courseId}
    public ResponseEntity<?> getCourseById(@PathVariable Long courseId) {
        Course course = courseService.findCourseById(courseId);
        if (course != null) {
            return ResponseEntity.ok(course);
        } else {
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/updateCourse/{courseId}") // api/course/{courseId}
    public ResponseEntity<?> updateCourse(@PathVariable Long courseId, @RequestBody Course updatedCourse) {
        Course course = courseService.updateCourse(courseId, updatedCourse);
        if (course != null) {
            return ResponseEntity.ok(course);
        } else {
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }
    }




}
