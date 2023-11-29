package com.example.SpringbootMicroservice1.service;

import com.example.SpringbootMicroservice1.model.*;
import com.example.SpringbootMicroservice1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Test addTestWithQuestionsAndAnswers(String testName, String testDescription, Long idCourse,
                                               List<String> questionContents, List<List<String>> suggestionContents, List<List<String>> reponseContents) {

        Test test = new Test();

        test.setName(testName);
        test.setDescription(testDescription);

        Course course = null;
        try {
            course = courseRepository.findById(idCourse)
                    .orElseThrow(() -> new Exception("Course not found with ID: " + idCourse));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        test.addCourse(course);//addCourse()



        // Ajoutez les questions, suggestions et réponses
        for (int i = 0; i < questionContents.size(); i++) {
            Question question = new Question();
            question.setText(questionContents.get(i));

            // Ajoutez les suggestions
            List<String> suggestions = suggestionContents.get(i);
            for (String suggestionContent : suggestions) {
                Suggestion suggestion = new Suggestion();
                suggestion.setText(suggestionContent);

                // Enregistrez la suggestion
                suggestionRepository.save(suggestion);

                // Ajoutez la suggestion à la question
                question.addSuggestion(suggestion);

            }

            // Ajoutez les réponses
            List<String> reponses = reponseContents.get(i);
            for (String reponseContent : reponses) {
                Answer reponse = new Answer();
                reponse.setText(reponseContent);

                // Enregistrez la réponse
                reponseRepository.save(reponse);

                // Ajoutez la réponse à la question
                question.addReponse(reponse);

            }
            // Enregistrez la question
            question = questionRepository.save(question);

            // Ajoutez la question au test
            test.addQuestion(question);
        }
        // Enregistrez le test
        test = testRepository.save(test);
        return test;
    }
}
