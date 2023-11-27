package com.example.SpringbootMicroservice1.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(name = "text", length = 255, nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "suggestion_id", nullable = false)
    private Suggestion suggestion;
}

