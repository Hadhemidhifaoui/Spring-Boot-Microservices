package com.example.SpringbootMicroservice1.service;

import com.example.SpringbootMicroservice1.model.*;
import com.example.SpringbootMicroservice1.repository.AnswerRepository;
import com.example.SpringbootMicroservice1.repository.QuestionRepository;
import com.example.SpringbootMicroservice1.repository.SuggestionRepository;
import com.example.SpringbootMicroservice1.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestService {

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
    public Test addTestWithQuestionsAndAnswers(Long courseId, String testName, String testDescription,
                                               List<String> questionContents, List<List<String>> suggestionContents, List<List<String>> reponseContents) {
        Test test = new Test();
        test.setName(testName);
        test.setDescription(testDescription);

        // Associez le test au cours
        Course course = new Course();
        course.setId(courseId);
        test.setCourse(course);

        // Enregistrez le test
        test = testRepository.save(test);

        // Ajoutez les questions, suggestions et réponses
        for (int i = 0; i < questionContents.size(); i++) {
            Question question = new Question();
            question.setText(questionContents.get(i));

            // Ajoutez la question au test
            test.addQuestion(question);

            // Enregistrez la question
            question = questionRepository.save(question);

            // Ajoutez les suggestions
            List<String> suggestions = suggestionContents.get(i);
            for (String suggestionContent : suggestions) {
                Suggestion suggestion = new Suggestion();
                suggestion.setText(suggestionContent);

                // Ajoutez la suggestion à la question
                question.addSuggestion(suggestion);

                // Enregistrez la suggestion
                suggestionRepository.save(suggestion);
            }

            // Ajoutez les réponses
            List<String> reponses = reponseContents.get(i);
            for (String reponseContent : reponses) {
                Answer reponse = new Answer();
                reponse.setText(reponseContent);

                // Ajoutez la réponse à la question
                question.addReponse(reponse);

                // Enregistrez la réponse
                reponseRepository.save(reponse);
            }
        }

        return test;
    }
}

