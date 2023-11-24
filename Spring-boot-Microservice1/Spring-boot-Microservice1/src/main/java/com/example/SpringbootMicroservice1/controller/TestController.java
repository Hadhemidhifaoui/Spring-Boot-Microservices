package com.example.SpringbootMicroservice1.controller;

import com.example.SpringbootMicroservice1.model.Test;
import com.example.SpringbootMicroservice1.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tests")
public class TestController {

    @Autowired
    private TestService testService;

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

    @GetMapping
    public ResponseEntity<List<Test>> getAllTests() {
        List<Test> tests = testService.getAllTests();
        return ResponseEntity.ok(tests);
    }

    @PostMapping
    public ResponseEntity<Test> saveTest(@RequestBody Test test) {
        Test savedTest = testService.saveTest(test);
        return new ResponseEntity<>(savedTest, HttpStatus.CREATED);
    }

    @DeleteMapping("/{testId}")
    public ResponseEntity<?> deleteTest(@PathVariable Long testId) {
        testService.deleteTest(testId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Ajoutez d'autres méthodes du contrôleur au besoin
}

