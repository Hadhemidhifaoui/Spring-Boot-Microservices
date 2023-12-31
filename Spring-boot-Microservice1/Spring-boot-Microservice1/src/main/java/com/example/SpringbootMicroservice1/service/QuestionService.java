package com.example.SpringbootMicroservice1.service;



import com.example.SpringbootMicroservice1.model.Question;
import com.example.SpringbootMicroservice1.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

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

    // Ajoutez d'autres méthodes de service au besoin
}

