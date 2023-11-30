package com.example.SpringbootMicroservice1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "test")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id", unique = false)
    private Course course;

    @Column(name = "name", length = 100, nullable = true)
    private String name;

    @Column(name = "description", length = 255, nullable = true)
    private String description;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Question> questions = new ArrayList<>();

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
        course.getTests().add(this);
    }
}
