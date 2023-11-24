package com.example.SpringbootMicroservice1.repository;

import com.example.SpringbootMicroservice1.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    // Ajoutez des méthodes spécifiques au besoin
}

