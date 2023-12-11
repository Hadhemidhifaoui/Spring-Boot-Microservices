package com.example.SpringbootMicroservice1.service;



import com.example.SpringbootMicroservice1.dto.SuggestionContent;
import com.example.SpringbootMicroservice1.dto.TestQuestionRequest;
import com.example.SpringbootMicroservice1.model.Question;
import com.example.SpringbootMicroservice1.model.Suggestion;
import com.example.SpringbootMicroservice1.model.Test;
import com.example.SpringbootMicroservice1.repository.QuestionRepository;
import com.example.SpringbootMicroservice1.repository.SuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private SuggestionService suggestionService;

    @Autowired
    private SuggestionRepository suggestionRepository;

    public Question updateQuestion(Long questionId, Question updatedQuestion) {
        Optional<Question> existingQuestion = questionRepository.findById(questionId);

        if (existingQuestion.isPresent()) {
            Question question = existingQuestion.get();
            question.setText(updatedQuestion.getText());
            question.setType(updatedQuestion.getType());
            // Autres propriétés à mettre à jour si nécessaire

            return questionRepository.save(question);
        }

        return null;
    }

    public Question findQuestionById(Long questionId) {
        Optional<Question> question = questionRepository.findById(questionId);
        return question.orElse(null);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }

    @Transactional
    public Question createQuestion(Test test, TestQuestionRequest questionRequest) {
        // Créer une nouvelle question
        Question question = new Question();
        question.setText(questionRequest.getQuestionContent());
        question.setTest(test);
        question.setType(questionRequest.getQuestionType());

        // Save the question to make it a managed entity
        question = questionRepository.save(question);

        // Ajouter des suggestions à la question si elles sont présentes
        List<SuggestionContent> suggestionContents = questionRequest.getSuggestionContents();
        if (suggestionContents != null) {
            List<Suggestion> suggestions = new ArrayList<>();

            for (SuggestionContent suggestionContent : suggestionContents) {
                Suggestion suggestion = new Suggestion();
                suggestion.setText(suggestionContent.getSuggestionContent());

                // Associer la suggestion à la question après avoir sauvegardé la question
                suggestion.setQuestion(question);

                // Save the suggestion in the repository
                suggestionRepository.save(suggestion);

                // Ajouter la suggestion à la liste
                suggestions.add(suggestion);
            }

            // Associer les suggestions à la question après avoir créé toutes les suggestions
            question.setSuggestions(suggestions);

            // Save the question again (optional, depending on your cascade settings)
            // It may not be necessary to save the question again if CascadeType.ALL is set on the suggestions relationship
            // question = questionRepository.save(question);

            // Retourner la question sauvegardée
            return question;
        }

        return question; // If there are no suggestions, return the question without suggestions
    }

}

