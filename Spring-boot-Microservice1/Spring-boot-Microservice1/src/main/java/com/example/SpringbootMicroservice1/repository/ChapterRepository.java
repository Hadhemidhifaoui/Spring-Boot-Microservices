package com.example.SpringbootMicroservice1.repository;

import com.example.SpringbootMicroservice1.model.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    // Ajoutez des méthodes spécifiques au besoin
}

