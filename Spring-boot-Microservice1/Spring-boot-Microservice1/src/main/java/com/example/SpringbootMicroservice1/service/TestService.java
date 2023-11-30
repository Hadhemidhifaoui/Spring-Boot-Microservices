package com.example.SpringbootMicroservice1.service;


import com.example.SpringbootMicroservice1.dto.TestQuestionRequest;
import com.example.SpringbootMicroservice1.model.*;
import com.example.SpringbootMicroservice1.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TestService {


    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private SuggestionRepository suggestionRepository;

    @Autowired
    private AnswerRepository reponseRepository;
    @Autowired
    private QuestionService questionService;

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    public Test updateTest(Long testId, Test updatedTest) {
        Optional<Test> existingTest = testRepository.findById(testId);

        if (existingTest.isPresent()) {
            Test test = existingTest.get();
            test.setName(updatedTest.getName());
            test.setDescription(updatedTest.getDescription());



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

    @Transactional
    public Test addTestWithQuestionsAndAnswers(String testName, String testDescription, Long courseId, List<TestQuestionRequest> questionRequests) {
        // Vérifier si le cours existe
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course with id " + courseId + " not found"));

        // Créer un nouveau test
        Test test = new Test();
        test.setName(testName);
        test.setDescription(testDescription);
        test.setCourse(course);



        // Ajouter des questions au test
        List<Question> questions = new ArrayList<>();
        for (TestQuestionRequest questionRequest : questionRequests) {
            Question question = questionService.createQuestion(test, questionRequest);
            questions.add(question);
        }

        // Associer les questions au test
        test.setQuestions(questions);


        // Sauvegarder le test
        test = testRepository.save(test);
        return test;
    }


}
