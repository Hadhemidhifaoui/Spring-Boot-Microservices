package com.example.SpringbootMicroservice1.model;



import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.Optional;

@Data
@Entity
@Table(name = "test")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "test_id", columnDefinition = "BIGINT DEFAULT 1")
    private Course course;

    @Column(name = "name", length = 100, nullable = true)
    private String name;

    @Column(name = "description", length = 255, nullable = true)
    private String description;

    @JdbcTypeCode(SqlTypes.JSON)
    @OneToMany(mappedBy = "test")
    @Column(name = "questions", nullable = true)
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

    public void addCourse(Course course) {
        this.course = course;
        course.setTest(this);
    }




}

