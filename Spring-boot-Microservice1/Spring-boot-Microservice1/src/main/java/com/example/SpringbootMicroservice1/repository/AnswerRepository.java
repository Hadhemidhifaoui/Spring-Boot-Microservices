package com.example.SpringbootMicroservice1.repository;

import com.example.SpringbootMicroservice1.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    // Ajoutez des méthodes spécifiques au besoin
}

