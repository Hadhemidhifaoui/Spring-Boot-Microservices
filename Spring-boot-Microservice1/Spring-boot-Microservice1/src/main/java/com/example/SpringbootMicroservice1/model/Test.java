package com.example.SpringbootMicroservice1.model;



import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "test")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;



    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "description", length = 255)
    private String description;
    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    private List<Question> questions;

    public void addQuestion(Question question) {
        questions.add(question);
        question.setTest(this);
    }
    public void addSuggestionToQuestion(Long questionId, Suggestion suggestion) {
        Question question = findQuestionById(questionId);
        if (question != null) {
            question.addSuggestion(suggestion);
        }
    }

    private Question findQuestionById(Long questionId) {
        return questions.stream()
                .filter(question -> question.getId().equals(questionId))
                .findFirst()
                .orElse(null);
    }
    public void addReponseToSuggestion(Long questionId, Long suggestionId, Answer reponse) {
        Question question = findQuestionById(questionId);
        if (question != null) {
            question.addReponseToSuggestion(suggestionId, reponse);
        }
    }



}

