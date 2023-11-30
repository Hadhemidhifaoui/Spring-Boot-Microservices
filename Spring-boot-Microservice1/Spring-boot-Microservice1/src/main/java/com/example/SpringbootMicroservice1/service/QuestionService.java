package com.example.SpringbootMicroservice1.service;



import com.example.SpringbootMicroservice1.dto.TestQuestionRequest;
import com.example.SpringbootMicroservice1.model.Question;
import com.example.SpringbootMicroservice1.model.Suggestion;
import com.example.SpringbootMicroservice1.model.Test;
import com.example.SpringbootMicroservice1.repository.QuestionRepository;
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

        // Sauvegarder la question
        question = questionRepository.save(question);

        // Ajouter des suggestions à la question
        List<Suggestion> suggestions = new ArrayList<>();
        for (String suggestionContent : questionRequest.getSuggestionContents()) {
            Suggestion suggestion = new Suggestion();
            suggestion.setText(suggestionContent);
            suggestion.setQuestion(question);
            suggestions.add(suggestionService.addSuggestion(suggestion));
        }

        // Associer les suggestions à la question
        question.setSuggestions(suggestions);

        // Retourner la question sauvegardée
        return question;
    }
}

