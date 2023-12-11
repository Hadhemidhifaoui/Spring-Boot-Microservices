package com.example.springbootmicroservice3.request;

import com.example.springbootmicroservice3.model.TestRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(value = "course-service"//Name of course-service application
        , path = ""//Pre-path for course-service
        //,url = "${course.service.url}"
        ,configuration = FeignConfiguration.class
)
public interface CourseServiceRequest
{
    /*@PostMapping("/api/course/save")

    Object saveCourse(@RequestBody Object requestBody);*/

    @PostMapping(value = "/api/course" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    ResponseEntity<?> saveCourse(
            @RequestPart("title") String title,
            @RequestPart("duree") String duree,
            @RequestPart("lien") String lien,
            @RequestPart("price") double price,
            @RequestPart("image") MultipartFile image);


    @DeleteMapping("/api/course/{courseId}")//api/course/{courseId}
    void deleteCourse(@PathVariable("courseId") Long courseId);

    @GetMapping("/api/course")
//api/course
    List<Object> getAllCourses();

    @GetMapping("/api/course/{courseId}")
    Object getCourseById(@PathVariable("courseId") Long courseId);




    @PutMapping(value = "/api/course/{courseId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<?> updateCourse(
            @RequestPart(name = "image", required = false) MultipartFile image,
            @PathVariable Long courseId,
            @RequestPart("title") String title,
            @RequestPart("duree") String duree,
            @RequestPart("lien") String lien,
            @RequestPart("price") double price);


    @PutMapping("/tests/{testId}")
    Object updateTest(@PathVariable Long testId, @RequestBody Object updatedTest);

    @GetMapping("/tests/{testId}")
    Object getTestById(@PathVariable Long testId);

    @GetMapping("/tests")
    List<Object> getAllTests();
    @GetMapping("/tests/details/{testId}")
    Object getTestDetails(@PathVariable Long testId);

    @PostMapping("/tests")
    Object saveTest(@RequestBody Object test);

    @DeleteMapping("/tests/{testId}")
    void deleteTest(@PathVariable Long testId);

    @PostMapping("/tests/addTest")
    Object addTestWithQuestionsAndAnswers(@RequestBody TestRequest request);


    @GetMapping("/tests/byCourse/{courseId}")
    List<Object> getTestsByCourse(@PathVariable Long courseId);

    @GetMapping("/tests/rep/byCourse/{courseId}")
    List<Object> getTestsByCourseId(@PathVariable Long courseId);
}
