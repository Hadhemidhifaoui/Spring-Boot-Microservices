package com.example.SpringbootMicroservice1.service;

import com.example.SpringbootMicroservice1.model.Answer;
import com.example.SpringbootMicroservice1.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    public Answer updateAnswer(Long answerId, Answer updatedAnswer) {
        Optional<Answer> existingAnswer = answerRepository.findById(answerId);

        if (existingAnswer.isPresent()) {
            Answer answer = existingAnswer.get();
            answer.setText(updatedAnswer.getText());
            // Autres propriétés à mettre à jour si nécessaire

            return answerRepository.save(answer);
        }

        return null;
    }

    public Answer findAnswerById(Long answerId) {
        Optional<Answer> answer = answerRepository.findById(answerId);
        return answer.orElse(null);
    }

    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    public Answer saveAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public void deleteAnswer(Long answerId) {
        answerRepository.deleteById(answerId);
    }

}

