package com.example.springbootmicroservice3.controller;

import com.example.springbootmicroservice3.request.CourseServiceRequest;
import com.example.springbootmicroservice3.utils.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("gateway/course")//pre-path
public class CourseController
{
    @Autowired
    private CourseServiceRequest courseServiceRequest;



    @PostMapping
    public ResponseEntity<?> saveCourse(
            @RequestParam("title") String title,
            @RequestParam("duree") String duree,
            @RequestParam("lien") String lien,
            @RequestParam("price") double price,
            @RequestParam("image") MultipartFile image) {
        return courseServiceRequest.saveCourse(title, duree, lien, price, image);
    }



    @DeleteMapping("{courseId}")//gateway/course/{courseId}
    public ResponseEntity<?> deleteCourse(@PathVariable Long courseId)
    {
        courseServiceRequest.deleteCourse(courseId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping//gateway/course
    public ResponseEntity<?> getAllCourses()
    {
        return ResponseEntity.ok(courseServiceRequest.getAllCourses());
    }

    @GetMapping("/{courseId}") // gateway/course/{courseId}
    public ResponseEntity<?> getCourseById(@PathVariable Long courseId) {
        Object course = courseServiceRequest.getCourseById(courseId);
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
        return courseServiceRequest.updateCourse( image,courseId, title, duree, lien, price);
    }
}
