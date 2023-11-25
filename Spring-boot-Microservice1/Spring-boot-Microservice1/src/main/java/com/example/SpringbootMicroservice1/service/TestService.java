package com.example.SpringbootMicroservice1.service;

import com.example.SpringbootMicroservice1.model.Test;
import com.example.SpringbootMicroservice1.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    public Test updateTest(Long testId, Test updatedTest) {
        Optional<Test> existingTest = testRepository.findById(testId);

        if (existingTest.isPresent()) {
            Test test = existingTest.get();
            test.setName(updatedTest.getName());
            test.setDescription(updatedTest.getDescription());

            // Vous pouvez également mettre à jour la liste de chapitres si nécessaire
            // test.setChapters(updatedTest.getChapters());

            return testRepository.save(test);
        }

        return null;
    }

    public Test findTestById(Long testId) {
        Optional<Test> test = testRepository.findById(testId);
        return test.orElse(null);
    }

    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    public Test saveTest(Test test) {
        return testRepository.save(test);
    }

    public void deleteTest(Long testId) {
        testRepository.deleteById(testId);
    }

}

