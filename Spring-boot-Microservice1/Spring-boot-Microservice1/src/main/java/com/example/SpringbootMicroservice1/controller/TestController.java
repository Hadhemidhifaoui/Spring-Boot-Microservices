package com.example.SpringbootMicroservice1.controller;

import com.example.SpringbootMicroservice1.dto.TestQuestionRequest;
import com.example.SpringbootMicroservice1.dto.TestRequest;
import com.example.SpringbootMicroservice1.dto.TestWithCourseDTO;
import com.example.SpringbootMicroservice1.model.*;
import com.example.SpringbootMicroservice1.service.CourseServiceImpl;
import com.example.SpringbootMicroservice1.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/tests")
public class TestController {

    @Autowired
    private TestService testService;
    @Autowired
    private CourseServiceImpl courseService;
    @PutMapping("/{testId}")
    public ResponseEntity<?> updateTest(@PathVariable Long testId, @RequestBody Test updatedTest) {
        Test test = testService.updateTest(testId, updatedTest);

        if (test != null) {
            return ResponseEntity.ok(test);
        } else {
            return new ResponseEntity<>("Test not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{testId}")
    public ResponseEntity<?> getTestById(@PathVariable Long testId) {
        Test test = testService.findTestById(testId);

        if (test != null) {
            return ResponseEntity.ok(test);
        } else {
            return new ResponseEntity<>("Test not found", HttpStatus.NOT_FOUND);
        }
    }

    /*GetMapping
    public ResponseEntity<List<Test>> getAllTests() {
        List<Test> tests = testService.getAllTestsWithDetails();
        return new ResponseEntity<>(tests, HttpStatus.OK);

    }*/
    @GetMapping
    public List<TestWithCourseDTO> getAllTestsWithDetails() {
        List<Test> tests = testService.getAllTestsWithDetails();
        List<TestWithCourseDTO> testDTOs = new ArrayList<>();

        for (Test test : tests) {
            TestWithCourseDTO testDTO = new TestWithCourseDTO();
            testDTO.setTestId(test.getId());
            testDTO.setTestName(test.getName());
            testDTO.setTestDescription(test.getDescription());

            Course course = test.getCourse();
            if (course != null) {
                testDTO.setCourseId(course.getId());
                testDTO.setCourseTitle(course.getTitle());
                testDTO.setCourseimage(course.getImage());
            }

            testDTOs.add(testDTO);
        }

        System.out.println("Tests data: " + testDTOs); // Vérifiez les données renvoyées
        return testDTOs;
    }


    @GetMapping("/details/{testId}")
    public ResponseEntity<Test> getTestDetails(@PathVariable Long testId) {
        Test test = testService.getTestDetails(testId);

        if (test != null) {
            // Récupérer les détails du cours
            Course course = test.getCourse();
            if (course != null) {
                // Chargez les détails du cours (ajoutez ces méthodes à votre service de cours)
                course = courseService.getCourseDetails(course.getId());
                test.setCourse(course);
            }
        }

        return ResponseEntity.ok(test);
    }



    @PostMapping
    public ResponseEntity<Test> saveTest(@RequestBody Test test) {
        Test savedTest = testService.saveTest(test);
        return new ResponseEntity<>(savedTest, HttpStatus.CREATED);
    }


    @DeleteMapping("/{testId}")
    public ResponseEntity<String> deleteTest(@PathVariable Long testId) {
        testService.deleteTest(testId);
        return ResponseEntity.ok("Test deleted successfully");
    }



    @PostMapping("/addTest")
    public ResponseEntity<?> addTestWithQuestionsAndAnswers(@RequestBody TestRequest request) {
        Test test = testService.addTestWithQuestionsAndAnswers(request);
        return ResponseEntity.ok(test);
    }

    @GetMapping("/byCourse/{courseId}")
    public ResponseEntity<List<Test>> getTestsByCourse(@PathVariable Long courseId) {
        List<Test> tests = testService.getTestsByCourseId(courseId);
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }
    @GetMapping("/rep/byCourse/{courseId}")
    public ResponseEntity<List<Test>> getTestsByCourseId(
            @PathVariable Long courseId,
            @RequestParam Long userId) {  // Ajoutez le paramètre userId

        List<Test> tests = testService.getTestsrepByCourseId(courseId, userId);
        return ResponseEntity.ok(tests);
    }

  /*  @GetMapping("/allTests")
    public ResponseEntity<List<TestWithDetails>> getAllTests() {
        List<TestWithDetails> testsWithDetails = testService.getAllTests();
        return ResponseEntity.ok(testsWithDetails);
    } */

}
