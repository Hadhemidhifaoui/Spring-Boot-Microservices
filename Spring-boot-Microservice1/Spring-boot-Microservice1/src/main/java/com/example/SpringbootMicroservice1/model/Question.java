package com.example.SpringbootMicroservice1.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

    @Column(name = "text", length = 255, nullable = false)
    private String text;

    @Column(name = "type", length = 20, nullable = false)
    private String type;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers;
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Suggestion> suggestions;
}

