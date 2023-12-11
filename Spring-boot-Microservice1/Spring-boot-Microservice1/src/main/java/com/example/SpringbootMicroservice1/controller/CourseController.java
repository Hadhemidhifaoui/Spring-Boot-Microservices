package com.example.SpringbootMicroservice1.controller;

import com.example.SpringbootMicroservice1.model.Course;
import com.example.SpringbootMicroservice1.model.Test;
import com.example.SpringbootMicroservice1.service.CourseService;

import com.example.SpringbootMicroservice1.service.CourseServiceImpl;
import com.example.SpringbootMicroservice1.utils.StorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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
    private StorageService storage;
    @Autowired
    private CourseServiceImpl courseService;
    /*@PostMapping("/save") //api/course
    public ResponseEntity<?> saveCourse(@RequestBody Course course)
    {
        return new ResponseEntity<>(courseService.saveCourse(course), HttpStatus.CREATED);
    }*/

    @PostMapping()
    public ResponseEntity<Course> saveCourse(
            @RequestParam("title") String title,
            @RequestParam("duree") String duree,
            @RequestParam("lien") String lien,
            @RequestParam("price") double price,
            @RequestParam("image") MultipartFile image) {
        Course course=new Course();
        course.setTitle(title);
        course.setDuree(duree);
        course.setLien(lien);
        course.setPrice(price);
        Course savedCourse=courseService.saveCourse(course,image);

        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }
    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storage.loadFile(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping //api/course
    public ResponseEntity<?> getAllCourses()
    {
        return ResponseEntity.ok(courseService.findAllCourses());
    }
    @DeleteMapping("/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long courseId) {
        try {
            courseService.deleteCourse(courseId);
            return new ResponseEntity<>("Course with ID " + courseId + " deleted successfully", HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("Failed to delete course with ID " + courseId, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{courseId}") // api/course/{courseId}
    public ResponseEntity<?> getCourseById(@PathVariable Long courseId) {
        Course course = courseService.findCourseById(courseId);
        if (course != null) {
            return ResponseEntity.ok(course);
        } else {
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{courseId}")
    public ResponseEntity<?> updateCourse(
            @PathVariable Long courseId,
            @RequestParam(name = "image", required = false) MultipartFile image,
            @RequestParam("title") String title,
            @RequestParam("duree") String duree,
            @RequestParam("lien") String lien,
            @RequestParam("price") double price) {

        Course updatedCourse = new Course();
        updatedCourse.setTitle(title);
        updatedCourse.setDuree(duree);
        updatedCourse.setLien(lien);
        updatedCourse.setPrice(price);

        Course course = courseService.updateCourse(courseId, updatedCourse, image);

        if (course != null) {
            return ResponseEntity.ok(course);
        } else {
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Course> addCourse(@RequestBody Course request) {
        Course newCourse = courseService.addCourse(
                request.getTitle(),
                request.getSubtitle(),
                request.getDuree(),
                request.getPrice(),
                request.getImage(),
                request.getLien()
        );

        return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
    }



}